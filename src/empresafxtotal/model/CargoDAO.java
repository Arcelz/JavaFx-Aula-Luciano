/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.model;

import empresafxtotal.controller.Cargo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CargoDAO {

    private CargoDAO() {

    }

    public static int create(Cargo c) throws SQLException {
        
            Statement stm = BancoDados.createConnection().createStatement();
            //
            String sql = "insert into cargos (nome,descricao) values('" + c.getNome() + "','" + c.getDescricao() + "')";

            stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();//
            int key = rs.getInt(1); //retorna o id gravado no banco
            c.setPk_cargo(key);//guardamos o id salvo no banco na variavel setPk_cliente.

            return key;

     
    }

    public static Cargo retreave(int pk_cargo) throws SQLException {
        
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();

            String sql = "Select * from cargos where pk_cargo=" + pk_cargo;

            ResultSet rs = stm.executeQuery(sql);
            rs.next();

            return new Cargo(rs.getInt("pk_cargo"), rs.getString("nome"), rs.getString("descricao"));

     
    }

    public static ArrayList<Cargo> retreaveAll() throws SQLException {
     
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "Select * from cargos";
            ResultSet rs = stm.executeQuery(sql);
            ArrayList<Cargo> cs = new ArrayList<>();
            while (rs.next())//vamos fazer uma condição para que o next vai andando na tabela ate o final
            {

                cs.add(new Cargo(
                        rs.getInt("pk_cargo"),
                        rs.getString("nome"),
                        rs.getString("descricao")));
            }

            return cs;
      
    }

    public static void delete(Cargo c) throws SQLException {

            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "delete from cargos where pk_cliente=" + c.getPk_cargo();
            System.out.println(sql);
            stm.execute(sql);
        } 

    

    public static void update(Cargo c) throws SQLException {
 
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "update  cargos set " + "nome='" + c.getNome() + "',descricao='" + c.getDescricao()+ "'where pk_cargo =" + c.getPk_cargo();
            System.out.println(sql);
            stm.execute(sql);
    
    }

}
