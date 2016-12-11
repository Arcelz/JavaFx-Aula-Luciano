/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.model;


import empresafxtotal.controller.Produto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutoDAO {
    
    private ProdutoDAO() {
        
    }
    
    public static int create(Produto produto) {
        try {
            Statement stm = BancoDados.createConnection().createStatement();
            //
            String sql = "insert into produtos (nome,estoque_minimo, qtd_estoque) values('" + produto.getNome() + "','" + produto.getEstoqueMinino() + "','"
                    + produto.getQtdEstoque() + "')";
            
            stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();//
            int key = rs.getInt(1); //retorna o id gravado no banco
            produto.setPk_produto(key);//guardamos o id salvo no banco na variavel setPk_cliente.
            
            return key;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
    public static Produto retreave(int pk_produto) {
        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            
            String sql = "Select * from produtos where pk_produto=" + pk_produto;
            
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            
            return new Produto(rs.getInt("pk_produto"), rs.getString("nome"), rs.getInt("estoque_minimo"), rs.getInt("qtd_estoque"));
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static void delete(Produto p) {
        
        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "delete from produtos where pk_enderenco=" + p.getPk_produto();
            
            stm.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static ArrayList<Produto> retreaveAll() {
        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "Select * from produtos";
            ResultSet rs = stm.executeQuery(sql);
            ArrayList<Produto> cs = new ArrayList();
            while (rs.next())//vamos fazer uma condição para que o next vai andando na tabela ate o final
            {
                
                cs.add(new Produto(
                        rs.getInt("pk_produto"),
                        rs.getString("nome"),
                        rs.getInt("estoque_minimo"),
                        rs.getInt("qtd_estoque")));
            }
            
            return cs;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static void update(Produto p) {
        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "update  produtos set " + "nome='" + p.getNome() + "',estoque_minimo='" + p.getEstoqueMinino()
                    + "',qtd_estoque='" + p.getQtdEstoque() + "'where pk_produto =" + p.getPk_produto();
            
            stm.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
