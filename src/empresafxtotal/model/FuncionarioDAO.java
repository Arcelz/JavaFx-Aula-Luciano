/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.model;

import empresafxtotal.controller.Cargo;

import empresafxtotal.controller.Funcionario;
import empresafxtotal.controller.FuncionarioEndereco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FuncionarioDAO {

    public static int create(Funcionario func) {
        try {
            Statement stm = BancoDados.createConnection().createStatement();
            //
            String sql = "insert into funcionarios (nome,cpf,fk_cargo) values('" + func.getNome() + "','" + func.getCpf() + "','"
                    + func.getFk_cargo() + "')";

            stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
            System.out.println(sql);
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();//
            int key = rs.getInt(1); //retorna o id gravado no banco
            func.setPk_funcionario(key);//guardamos o id salvo no banco na variavel setPk_cliente.

            FuncionarioEnderecoDAO.create(func.getFuncEndereco());

            return key;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public static Funcionario retreave(int pk_funcionario) {
        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();

            String sql = "Select * from funcionarios where pk_funcionario=" + pk_funcionario;

            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            Cargo c = CargoDAO.retreave(rs.getInt("fk_cargo"));
            FuncionarioEndereco e = FuncionarioEnderecoDAO.retreaveByFuncionario(pk_funcionario);
            //return new Funcionario(rs.getInt("pk_cliente"), rs.getString("nome"), rs.getString("cpf"), e);
            return new Funcionario(rs.getInt("pk_funcionario"), rs.getInt("fk_cargo"), rs.getString("nome"), rs.getString("cpf"), c, e);

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static ArrayList<Funcionario> retreaveAll() {
        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "Select * from funcionarios";
            ResultSet rs = stm.executeQuery(sql);
            ArrayList<Funcionario> cs = new ArrayList<>();
            while (rs.next())//vamos fazer uma condição para que o next vai andando na tabela ate o final
            {
                Cargo c = CargoDAO.retreave(rs.getInt("fk_cargo"));
                FuncionarioEndereco e = FuncionarioEnderecoDAO.retreaveByFuncionario(rs.getInt("pk_funcionario")); //Como já temos o retrave no
                //Endereco fazemo a consulta em cliente e pegamos a chave com o rs.getInt
                //Na parte de baixo vamos add a consulta na lista
                cs.add(new Funcionario(
                        rs.getInt("pk_funcionario"),
                        rs.getInt("fk_cargo"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        c,
                        e));
            }

            return cs;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static void delete(Funcionario f) {

        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "delete from funcionarios where pk_funcionario=" + f.getPk_funcionario();
            System.out.println(sql);
            
            stm.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void update(Funcionario f) {
        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "update  funcionarios set " + "nome='" + f.getNome() + "',cpf='" + f.getCpf() + "'where pk_funcionario =" + f.getPk_funcionario();
            FuncionarioEnderecoDAO.update(f.getFuncEndereco());
            System.out.println(sql);
            stm.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
