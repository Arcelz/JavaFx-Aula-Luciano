/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.model;

import empresafxtotal.controller.FornecedorEndereco;
import empresafxtotal.controller.FuncionarioEndereco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FuncionarioEnderecoDAO {
    
    
     public static int create (FuncionarioEndereco funcEnd){
        try {
            Statement stm = BancoDados.createConnection().createStatement();
            //INSERT INTO public.clientes_enderecos(
            //pk_enderenco, fk_cliente, logradouro, bairro, cidade, estado, 
            //pais, cep)
    //VALUES (?, ?, ?, ?, ?, ?, 
      //      ?, ?);
      
      
            String sql = "insert into funcionarios_enderecos (fk_funcionario,logadouro,bairro,cidade,estado,pais,cep) values('"+funcEnd.getFk_funcionario()+"','"+funcEnd.getLogradouro()+"','"+funcEnd.getBairro()+"','"+funcEnd.getCidade()+
                    "','"+funcEnd.getEstado()+"','"+funcEnd.getPais()+"','"+funcEnd.getCep()+"')";
            
            stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
            System.out.println(sql);
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);            
            funcEnd.setPk_endereco(key);
            
            return key;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            return 0;
        }
     
     public static FuncionarioEndereco retreave(int pkFuncionario) {
        try {
            Statement stm = BancoDados.createConnection().createStatement();//abrindo conexão no banco

            String sql = "Select * from funcionarios_enderecos  where pk_endereco =" + pkFuncionario;
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                return new FuncionarioEndereco(rs.getString("logadouro"), rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getInt("pk_endereco"),
                        rs.getInt("fk_funcionario"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static FuncionarioEndereco retreaveByFuncionario(int fkFuncionario) {
        try {
            Statement stm = BancoDados.createConnection().createStatement();//abrindo conexão no banco

            String sql = "Select * from funcionarios_enderecos  where fk_funcionario =" + fkFuncionario;
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {

                return new FuncionarioEndereco(rs.getString("logadouro"), rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getInt("pk_endereco"),
                        rs.getInt("fk_funcionario"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static ArrayList<FuncionarioEndereco> retreaveAll() {
        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "Select * from funcionarios_enderecos";
            ResultSet rs = stm.executeQuery(sql);

            ArrayList<FuncionarioEndereco> e = new ArrayList<>();

            while (rs.next()) {
                e.add(new FuncionarioEndereco(
                        rs.getString("logadouro"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getInt("pk_endereco"),
                        rs.getInt("fk_funcionario")));
            }

            return e;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
     public static void delete(FuncionarioEndereco fe) {

        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "delete from clientes where pk_endereco=" + fe.getPk_endereco();
            System.out.println(sql);
            stm.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void update(FuncionarioEndereco fe) {
        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "update  funcionarios_enderecos set " + "logadouro='" + fe.getLogradouro()
                    + "',bairro='" + fe.getBairro() + "',cidade='" + fe.getCidade() + "',estado='" + fe.getEstado() + "',pais='" + fe.getPais()
                    + "',cep='" + fe.getCep() + "'where pk_endereco=" + fe.getPk_endereco();

            System.out.println(sql);
            stm.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
}
