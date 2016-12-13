package empresafxtotal.model;


import empresafxtotal.controller.CompraItem;
import empresafxtotal.controller.VendaItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CompraItemDAO {

    public CompraItemDAO() {
    }

    public static int create(CompraItem compraItem) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "insert into compras_itens (fk_compra, fk_produto, qtd, valor_unitario) "
                + "values ("
                + compraItem.getFkCompra()+ ","
                + compraItem.getProduto().getPk_produto() + ","
                + compraItem.getQtd() + ","
                + compraItem.getValorUnitario()
                + ")";
        stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stm.getGeneratedKeys();
        rs.next();
        int key = rs.getInt(1);
        compraItem.setPkCompraItem(key);

        return key;
    }

    public static VendaItem retreave(int pkVenda) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "select * from vendas_itens where fk_venda = " + pkVenda;
        ResultSet rs = stm.executeQuery(sql);
        rs.next();

        return new VendaItem(
                rs.getInt("fk_venda"),
                ProdutoDAO.retreave(rs.getInt("fk_produto")),    
                rs.getInt("qtd"),
                rs.getDouble("valor_unitario"));
    }

    public static ArrayList<CompraItem> retreaveByCompra(int fkCompra) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        System.out.println(fkCompra);
        String sql = "select * from compras_itens where fk_compra = " + fkCompra;
        ResultSet rs = stm.executeQuery(sql);
           ArrayList<CompraItem> compraItems = new ArrayList<>();
        while(rs.next()){
            
                compraItems.add( new CompraItem(
                rs.getInt("fk_compra"),
                ProdutoDAO.retreave(rs.getInt("fk_produto")),
                rs.getInt("qtd"),
                rs.getDouble("valor_unitario"),
                rs.getInt("pk_item"))
                      );
        
        }
        return compraItems;
    }

    public static ArrayList<CompraItem> retreaveAll() throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "select * from compras_itens ";
        ResultSet rs = stm.executeQuery(sql);

        ArrayList<CompraItem> listaCompraItem = new ArrayList<>();
        while (rs.next()) {

            listaCompraItem.add(new CompraItem(
                    rs.getInt("fk_compra"),
                    ProdutoDAO.retreave(rs.getInt("fk_produto")),
                    rs.getInt("qtd"),
                    rs.getDouble("valor_unitario")));
        }
        return listaCompraItem;
    }
    
    public static void update(CompraItem compraItem) throws SQLException{
        Statement stm = BancoDados.createConnection().createStatement();
        String sql ="update compras_itens set " 
                + "fk_compra = '" + compraItem.getFkCompra()
                + "', fk_produto = '" + compraItem.getProduto().getPk_produto()
                + "', qtd = '" + compraItem.getQtd()
                + "', valor_unitario = '" + compraItem.getValorUnitario()
                 + "' where pk_item = " + compraItem.getPkCompraItem();
        System.out.println(sql);
    stm.execute(sql);
    }
    
}