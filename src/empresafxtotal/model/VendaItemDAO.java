package empresafxtotal.model;


import empresafxtotal.controller.VendaItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VendaItemDAO {

    public VendaItemDAO() {
    }

    public static int create(VendaItem vendaItem) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "insert into vendas_itens (fk_venda, fk_produto, qtd, valor_unitario) "
                + "values ("
                + vendaItem.getFkVenda() + ","
                + vendaItem.getProduto().getPk_produto() + ","
                + vendaItem.getQtd() + ","
                + vendaItem.getValorUnitario()
                + ")";
        stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stm.getGeneratedKeys();
        rs.next();
        int key = rs.getInt(1);
        vendaItem.setPkVendaItem(key);

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

    public static ArrayList<VendaItem> retreaveByVenda(int fkVenda) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        System.out.println(fkVenda);
        String sql = "select * from vendas_itens where fk_venda = " + fkVenda;
        ResultSet rs = stm.executeQuery(sql);
           ArrayList<VendaItem> vendaitens = new ArrayList<>();
        while(rs.next()){
            
                vendaitens.add( new VendaItem(
                rs.getInt("fk_venda"),
                ProdutoDAO.retreave(rs.getInt("fk_produto")),
                rs.getInt("qtd"),
                rs.getDouble("valor_unitario"),
                rs.getInt("pk_item"))
                      );
        
        }
        return vendaitens;
    }

    public static ArrayList<VendaItem> retreaveAll() throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "select * from vendas_itens ";
        ResultSet rs = stm.executeQuery(sql);

        ArrayList<VendaItem> listaVendaItem = new ArrayList<>();
        while (rs.next()) {

            listaVendaItem.add(new VendaItem(
                    rs.getInt("fk_venda"),
                    ProdutoDAO.retreave(rs.getInt("fk_produto")),
                    rs.getInt("qtd"),
                    rs.getDouble("valor_unitario")));
        }
        return listaVendaItem;
    }
    
    public static void update(VendaItem vendaItem) throws SQLException{
        Statement stm = BancoDados.createConnection().createStatement();
        String sql ="update vendas_itens set " 
                + "fk_venda = '" + vendaItem.getFkVenda()
                + "', fk_produto = '" + vendaItem.getProduto().getPk_produto()
                + "', qtd = '" + vendaItem.getQtd()
                + "', valor_unitario = '" + vendaItem.getValorUnitario()
                 + "' where pk_item = " + vendaItem.getPkVendaItem();
        System.out.println(sql);
    stm.execute(sql);
    }
     public static ArrayList<Integer> retreaveFkVendaItem(int fkVenda) throws SQLException {
        Statement stm = BancoDados.createConnection().createStatement();
        String sql = "select * from vendas_itens where fk_venda =" + fkVenda;
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Integer> vendaitem =new ArrayList<>();
        while(rs.next()){
          
       vendaitem.add(
               rs.getInt("pk_item"));
    }
        return vendaitem;
    }
}