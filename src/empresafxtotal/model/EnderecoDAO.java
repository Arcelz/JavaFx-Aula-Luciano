/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.model;

import empresafxtotal.controller.Endereco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnderecoDAO {

    public static int create(Endereco e) throws SQLException {
            Statement stm = BancoDados.createConnection().createStatement();
            //INSERT INTO public.clientes_enderecos(
            //pk_enderenco, fk_cliente, logradouro, bairro, cidade, estado, 
            //pais, cep)
            //VALUES (?, ?, ?, ?, ?, ?, 
            //      ?, ?);

            String sql = "insert into clientes_enderecos (fk_cliente,logradouro,bairro,cidade,estado,pais,cep) values('"+e.getFk_cliente()+"','"+e.getLogradouro()+"','"+e.getBairro()+"','"+e.getCidade()+
                    "','"+e.getEstado()+"','"+e.getPais()+"','"+e.getCep()+"')";

            stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
                        System.out.println(sql);

            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);
            e.setPk_endereco(key);

            return key;

  
    }

    public static Endereco retreave(int pkEndereco) throws SQLException {
            Statement stm = BancoDados.createConnection().createStatement();//abrindo conexão no banco

            String sql = "Select * from clientes_enderecos where pk_enderenco =" + pkEndereco;
            ResultSet rs = stm.executeQuery(sql);
            rs.next();

            return new Endereco(rs.getString("logradouro"), rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getInt("pk_enderenco"),
                    rs.getInt("fk_cliente"));

       
    }

    public static Endereco retreaveByCliente(int fkCliente) throws SQLException {
       
            Statement stm = BancoDados.createConnection().createStatement();//abrindo conexão no banco

            String sql = "Select * from clientes_enderecos where fk_cliente =" + fkCliente;
            ResultSet rs = stm.executeQuery(sql);
            rs.next();

            return new Endereco(rs.getString("logradouro"), rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getInt("pk_enderenco"),
                    rs.getInt("fk_cliente"));

      

    }

    public static ArrayList<Endereco> retreaveAll() throws SQLException {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "Select * from clientes_enderecos";
            ResultSet rs = stm.executeQuery(sql);

            ArrayList<Endereco> e = new ArrayList<>();

            while (rs.next()) {
                e.add(new Endereco(
                        rs.getString("logradouro"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getInt("pk_enderenco"),
                        rs.getInt("fk_cliente")));
            }

            return e;
    }

    public static void delete(Endereco e) throws SQLException {

       
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "delete from clientes_enderecos where pk_enderenco=" + e.getPk_endereco();

            stm.execute(sql);
        

    }

    public static void update(Endereco e) throws SQLException {
        
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "update  clientes_enderecos set " + "logradouro='" + e.getLogradouro()
                    + "',bairro='" + e.getBairro() + "',cidade='" + e.getCidade() + "',estado='" + e.getEstado() + "',pais='" + e.getPais()
                    + "',cep='" + e.getCep() + "'where pk_enderenco=" + e.getPk_endereco();
            System.out.println(sql);
            stm.execute(sql);
        }

    

}
