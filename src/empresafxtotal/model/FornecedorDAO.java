package empresafxtotal.model;


import empresafxtotal.controller.Fornecedor;
import empresafxtotal.controller.FornecedorEndereco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FornecedorDAO {

    private FornecedorDAO() {

    }

    public static int create(Fornecedor forne) {
        try {
            Statement stm = BancoDados.createConnection().createStatement();
            //
            String sql = "insert into fornecedores (nome,cpf) values('" + forne.getNome() + "','" + forne.getCpf() + "')";

            stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();//
            int key = rs.getInt(1); //retorna o id gravado no banco
            forne.setPk_fornecedor(key);//guardamos o id salvo no banco na variavel setPk_cliente.

            FornecedorEnderecoDAO.create(forne.getForneEnd());

            return key;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public static Fornecedor retreave(int pk_fornecedor) {
        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();

            String sql = "Select * from fornecedores where pk_cliente=" + pk_fornecedor;

            ResultSet rs = stm.executeQuery(sql);
            rs.next();

            FornecedorEndereco e = FornecedorEnderecoDAO.retreaveByFornecedor(pk_fornecedor);
            return new Fornecedor(rs.getInt("pk_cliente"), rs.getString("nome"), rs.getString("cpf"), e);

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static Fornecedor retreaveByForneceEnd(int pk_fornecedor) {
        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();

            String sql = "Select * from fornecedores where pk_cliente=" + pk_fornecedor;

            ResultSet rs = stm.executeQuery(sql);
            rs.next();

            FornecedorEndereco e = FornecedorEnderecoDAO.retreaveByFornecedor(pk_fornecedor);
            return new Fornecedor(rs.getInt("pk_cliente"), rs.getString("nome"), rs.getString("cpf"), e);

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

   public static ArrayList<Fornecedor> retreaveAll() {
        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "Select * from fornecedores";
            ResultSet rs = stm.executeQuery(sql);
 
            ArrayList<Fornecedor> cs = new ArrayList<>();
 
            while (rs.next())//vamos fazer uma condição para que o next vai andando na tabela ate o final
            {
                FornecedorEndereco e = FornecedorEnderecoDAO.retreaveByFornecedor(rs.getInt("pk_fornecedor")); //Como já temos o retrave no
                //Endereco fazemo a consulta em cliente e pegamos a chave com o rs.getInt
                //Na parte de baixo vamos add a consulta na lista
                cs.add(new Fornecedor(
                        rs.getInt("pk_fornecedor"),
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

    public static void delete(Fornecedor f) {

        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "delete from fornecedores where pk_fornecedor=" + f.getPk_fornecedor();
            System.out.println(sql);
            stm.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void update(Fornecedor f) {
        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "update  fornecedores set " + "nome='" + f.getNome()+ "',cpf='" + f.getCpf() + "'where pk_fornecedor=" + f.getPk_fornecedor();
            FornecedorEnderecoDAO.update(f.getForneEnd());
            System.out.println(sql);
            stm.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
