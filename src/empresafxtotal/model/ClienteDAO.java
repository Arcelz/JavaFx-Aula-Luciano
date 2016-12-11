/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.model;

import empresafxtotal.controller.Cliente;
import empresafxtotal.controller.Endereco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus
 */
public class ClienteDAO {

    private ClienteDAO() {

    }

    public static int create(Cliente c) {
        try {
            Statement stm = BancoDados.createConnection().createStatement();
            //
            String sql = "insert into clientes (nome,cpf) values('" + c.getNome() + "','" + c.getCpf() + "')";

            stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();//
            int key = rs.getInt(1); //retorna o id gravado no banco
            c.setPk_cliente(key);//guardamos o id salvo no banco na variavel setPk_cliente.
            System.out.println(key);
            EnderecoDAO.create(c.getEndereco()); //Aqui chamamos a DAO do enderenco e passamos os valores para ela gravar pelo getEnderenco.

            return key;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public static Cliente retreave(int pk_cliente) {
        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();

            String sql = "Select * from clientes where pk_cliente=" + pk_cliente;

            ResultSet rs = stm.executeQuery(sql);
            rs.next();

            Endereco e = EnderecoDAO.retreaveByCliente(pk_cliente);
            return new Cliente(rs.getInt("pk_cliente"), rs.getString("nome"), rs.getString("cpf"), e);

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static ArrayList<Cliente> retreaveAll() {
        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "Select * from clientes";
            ResultSet rs = stm.executeQuery(sql);
            ArrayList<Cliente> cs = new ArrayList<>();
            while (rs.next())//vamos fazer uma condição para que o next vai andando na tabela ate o final
            {
                Endereco e = EnderecoDAO.retreaveByCliente(rs.getInt("pk_cliente")); //Como já temos o retrave no
                //Endereco fazemo a consulta em cliente e pegamos a chave com o rs.getInt
                //Na parte de baixo vamos add a consulta na lista
                cs.add(new Cliente(
                        rs.getInt("pk_cliente"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        e));
            }

            return cs;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static Cliente retreaveByClienteEnde(int fk_cliente) {
        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();

            String sql = "Select * from clientes where pk_cliente=" + fk_cliente;

            ResultSet rs = stm.executeQuery(sql);
            rs.next();

            Endereco e = EnderecoDAO.retreaveByCliente(fk_cliente);
            return new Cliente(rs.getInt("pk_cliente"), rs.getString("nome"), rs.getString("cpf"), e);

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static void delete(Cliente c) {

        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "delete from clientes where pk_cliente=" + c.getPk_cliente();
            System.out.println(sql);
            stm.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

  
    public static void update(Cliente c) {
        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "update  clientes set " + "nome='" + c.getNome() + "',cpf='" + c.getCpf() + "'where pk_Cliente=" + c.getPk_cliente();
            EnderecoDAO.update(c.getEndereco());
            System.out.println(sql);
            stm.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
