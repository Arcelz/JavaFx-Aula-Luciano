/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.model;

import empresafxtotal.controller.FornecedorEndereco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FornecedorEnderecoDAO {

    public static int create(FornecedorEndereco forneEnd) {
        try {
            Statement stm = BancoDados.createConnection().createStatement();
            //INSERT INTO public.clientes_enderecos(
            //pk_enderenco, fk_cliente, logradouro, bairro, cidade, estado, 
            //pais, cep)
            //VALUES (?, ?, ?, ?, ?, ?, 
            //      ?, ?);

            String sql = "insert into fornecedores_enderecos (fk_fornecedor,logradouro,bairro,cidade,estado,pais,cep) values('" + forneEnd.getFk_fornecedor() + "','" + forneEnd.getLogradouro() + "','" + forneEnd.getBairro() + "','" + forneEnd.getCidade()
                    + "','" + forneEnd.getEstado() + "','" + forneEnd.getPais() + "','" + forneEnd.getCep() + "')";
            System.out.println(sql);
            stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);
            forneEnd.setPk_endereco(key);

            return key;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public static FornecedorEndereco retreave(int pkEndereco) {
        try {
            Statement stm = BancoDados.createConnection().createStatement();//abrindo conexão no banco

            String sql = "Select * from fornecedores_enderecos  where pk_endereco =" + pkEndereco;
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                return new FornecedorEndereco(rs.getString("logradouro"), rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getInt("pk_endereco"),
                        rs.getInt("fk_fornecedor"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static FornecedorEndereco retreaveByFornecedor(int fkEndereco) {
        try {
            Statement stm = BancoDados.createConnection().createStatement();//abrindo conexão no banco
 
            String sql = "Select * from fornecedores_enderecos  where fk_fornecedor =" + fkEndereco;
            ResultSet rs = stm.executeQuery(sql);
 
            if (rs.next()) {
 
                return new FornecedorEndereco(rs.getString("logradouro"), rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getInt("pk_endereco"),
                        rs.getInt("fk_fornecedor"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        return null;
    }

    public static ArrayList<FornecedorEndereco> retreaveAll() {
        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "Select * from fornecedores_enderecos";
            ResultSet rs = stm.executeQuery(sql);

            ArrayList<FornecedorEndereco> e = new ArrayList<>();

            while (rs.next()) {
                e.add(new FornecedorEndereco(
                        rs.getString("logradouro"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getInt("pk_endereco"),
                        rs.getInt("fk_fornecedor")));
            }

            return e;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static void delete(FornecedorEndereco fe) {

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

    public static void update(FornecedorEndereco fe) {
        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "update  fornecedores_enderecos set " + "logradouro='" + fe.getLogradouro()
                    + "',bairro='" + fe.getBairro() + "',cidade='" + fe.getCidade() + "',estado='" + fe.getEstado() + "',pais='" + fe.getPais()
                    + "',cep='" + fe.getCep() + "'where pk_endereco=" + fe.getPk_endereco();

            System.out.println(sql);
            stm.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
