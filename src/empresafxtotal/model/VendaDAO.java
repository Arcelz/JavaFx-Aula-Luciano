package empresafxtotal.model;


import empresafxtotal.controller.Venda;
import empresafxtotal.controller.VendaItem;
import empresafxtotal.model.BancoDados;
import empresafxtotal.model.ClienteDAO;
import empresafxtotal.model.FuncionarioDAO;
import empresafxtotal.model.VendaItemDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendaDAO {

    public VendaDAO() {
    }

    public static int create(Venda venda) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "insert into vendas (fk_cliente, fk_vendedor, numero, datas) "
                + "values ('"
                + venda.getCliente().getPk_cliente() + "','"
                + venda.getVendedor().getPk_funcionario() + "','"
                + venda.getNumero() + "','"
                + venda.getData()
                + "')";
        stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
        System.out.println(sql);
        ResultSet rs = stm.getGeneratedKeys();
        rs.next();
        int key = rs.getInt(1);
        venda.setPkVenda(key);
        System.out.println(key);
        ArrayList<VendaItem> itens = new ArrayList<>(venda.getItens());
        System.out.println(itens);
       itens.forEach((a)->{
            try {
                VendaItemDAO.create(a);
            } catch (SQLException ex) {
                Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        return key;
    }

    public static Venda retreave(int pk_venda) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "select * from vendas where pk_venda = " + pk_venda;
        ResultSet rs = stm.executeQuery(sql);
        rs.next();

        ArrayList<VendaItem> vendaitem = VendaItemDAO.retreaveByVenda(rs.getInt("pk_venda"));

        return new Venda(
                ClienteDAO.retreave(rs.getInt("fk_cliente")),
                FuncionarioDAO.retreave(rs.getInt("fk_vendedor")),
                rs.getInt("numero"),
                rs.getDate("datas"));
    }

    public static Venda retreaveByVenda(int fkVenda) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "select * from vendas where fk_cliente =" + fkVenda;
        ResultSet rs = stm.executeQuery(sql);
        rs.next();
          ArrayList<VendaItem> vendaitem = VendaItemDAO.retreaveByVenda(rs.getInt("pk_venda"));

        return new Venda(
                ClienteDAO.retreave(rs.getInt("fk_cliente")),
                FuncionarioDAO.retreave(rs.getInt("fk_vendedor")),
                rs.getInt("numero"),
                rs.getDate("datas"));
    }
     public static int retreaveNumero() throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "select max(numero) from vendas";
        ResultSet rs = stm.executeQuery(sql);
        rs.next();
        return rs.getInt(1);
    }
     
     public static int retreaveNumero(int numero) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "select pk_venda from vendas where numero="+numero;
        ResultSet rs = stm.executeQuery(sql);
        rs.next();
        return rs.getInt(1);
    }
    

    public static ArrayList<Venda> retreaveAll() throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "select * from vendas";
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Venda> listaVenda = new ArrayList<>();
        
        while (rs.next()) {

            ArrayList<VendaItem> vendaitem = VendaItemDAO.retreaveByVenda(rs.getInt("pk_venda"));
            listaVenda.add(new Venda(
                    rs.getInt("numero"),
                    rs.getDate("datas"),
                    ClienteDAO.retreave(rs.getInt("fk_cliente")),
                    FuncionarioDAO.retreave(rs.getInt("fk_vendedor")),
                    vendaitem
                 ));
        }
        return listaVenda;
    }

    public static void delete(Venda venda) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "delete from vendas where pk_venda = " + venda.getPkVenda();
        stm.execute(sql);
    }

    public static void update(Venda venda) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "update vendas set "
                + "fk_cliente ='" + venda.getCliente().getPk_cliente()
                + "',fk_vendedor ='" + venda.getVendedor().getPk_funcionario()
                + "',numero ='" + venda.getNumero()
                + "',datas ='" + venda.getData()
                + "' where pk_venda =" + venda.getPkVenda();
        System.out.println(sql);
         stm.execute(sql);
         venda.getItens().forEach((a)-> {
            try {
                VendaItemDAO.update(a);
            } catch (SQLException ex) {
                Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}