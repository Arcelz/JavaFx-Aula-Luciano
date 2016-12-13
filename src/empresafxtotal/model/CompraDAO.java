package empresafxtotal.model;


import empresafxtotal.controller.Compra;
import empresafxtotal.controller.CompraItem;
import empresafxtotal.controller.Venda;
import empresafxtotal.controller.VendaItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class CompraDAO {

    public CompraDAO() {
    }

    public static int create(Compra compra) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "insert into compras (fk_fornecedor, numero, datas) "
                + "values ('"
                + compra.getFornecedor().getPk_fornecedor() + "','"
                + compra.getNumero() + "','"
                + compra.getData()
                + "')";
        stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
        System.out.println(sql);
        ResultSet rs = stm.getGeneratedKeys();
        rs.next();
        int key = rs.getInt(1);
        compra.setPkCompra(key);
        System.out.println(key);
        ArrayList<CompraItem> itens = new ArrayList<>(compra.getItens());
        for(CompraItem arrayItem :itens){
            CompraItemDAO.create(arrayItem);
        }
        return key;
    }

    public static Compra retreave(int pk_compra) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "select * from compras where pk_compra = " + pk_compra;
        ResultSet rs = stm.executeQuery(sql);
        rs.next();

        ArrayList<CompraItem> compraItem = CompraItemDAO.retreaveByCompra(rs.getInt("pk_compra"));

        return new Compra(
                FornecedorDAO.retreave(rs.getInt("fk_cliente")),
                rs.getInt("numero"),
                rs.getDate("datas"));
    }

    public static Compra retreaveByFornecedor(int fkFornecedor) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "select * from vendas where fk_fornecedor =" + fkFornecedor;
        ResultSet rs = stm.executeQuery(sql);
        rs.next();
          ArrayList<CompraItem> compraItem = CompraItemDAO.retreaveByCompra(rs.getInt("pk_venda"));

        return new Compra(
                FornecedorDAO.retreave(rs.getInt("fk_fornecedor")),
                rs.getInt("numero"),
                rs.getDate("datas"));
    }
     public static int retreaveNumero() throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "select max(numero) from compras";
        ResultSet rs = stm.executeQuery(sql);
        rs.next();
        return rs.getInt(1);
    }
     
  
    public static ArrayList<Compra> retreaveAll() throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "select * from compras";
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Compra> listaCompra = new ArrayList<>();
        
        while (rs.next()) {

            ArrayList<CompraItem> compraItem = CompraItemDAO.retreaveByCompra(rs.getInt("pk_compra"));
            listaCompra.add(new Compra(
                    rs.getInt("numero"),
                    rs.getDate("datas"),
                    FornecedorDAO.retreave(rs.getInt("fk_fornecedor")),
                    compraItem,
                    rs.getInt("pk_compra")
                 ));
        }
        return listaCompra;
    }

    public static void delete(Compra compra) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "delete from compras where pk_comprra = " + compra.getPkCompra();
        stm.execute(sql);
    }

    public static void update(Compra compra) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "update compras set "
                + "fk_fornecedor ='" + compra.getFornecedor().getPk_fornecedor()
                + "',numero ='" + compra.getNumero()
                + "',datas ='" + compra.getData()
                + "' where pk_compra =" + compra.getPkCompra();
        System.out.println(sql);
         stm.execute(sql);
         for (CompraItem comp : compra.getItens()){
             CompraItemDAO.update(comp);
         }

    }
}