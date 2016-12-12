/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.controller;

import empresafxtotal.model.ClienteDAO;
import empresafxtotal.model.FuncionarioDAO;
import empresafxtotal.model.ProdutoDAO;
import empresafxtotal.model.VendaDAO;
import empresafxtotal.model.VendaItemDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.NumberStringConverter;

/**
 * FXML Controller class
 *
 * @author Usuario-PC
 */
public class FXMLTelaVendaController implements Initializable {
    private Venda venda;
    private  int pkVenda;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<Funcionario> comboBoxFuncionario;

    @FXML
    private ComboBox<Cliente> comboBoxCliente;

    @FXML
    private ComboBox<Produto> comboBoxProdutos;

    @FXML
    private TextField textFieldValor;

    @FXML
    private TextField textFieldQtd;

    @FXML
    private TableView tableViewVendasItens;

    @FXML
    private TableColumn<VendaItem, String> nome;

    @FXML
    private TableColumn<VendaItem, String> idade;

    @FXML
    private TableColumn<VendaItem, Number> email;

    @FXML
    private TableColumn<VendaItem, String> columnFuncionario;

    @FXML
    private TableColumn<VendaItem, String> columnCliente;

    @FXML
    private ComboBox<Venda> comboVendas;

    final ObservableList<VendaItem> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Produto> prod = ProdutoDAO.retreaveAll();
        comboBoxProdutos.getItems().addAll(prod);
        List<Cliente> cli = ClienteDAO.retreaveAll();
        comboBoxCliente.getItems().addAll(cli);
        System.out.println(cli);
        List<Funcionario> func = FuncionarioDAO.retreaveAll();
        comboBoxFuncionario.getItems().addAll(func);
        List<Venda> listaVenda;
        try {
            listaVenda = VendaDAO.retreaveAll();
            comboVendas.getItems().addAll(listaVenda);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTelaVendaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tableViewVendasItens.setEditable(true);

        idade.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
        email.setCellValueFactory(new PropertyValueFactory<>("qtd"));
        nome.setCellValueFactory(new PropertyValueFactory<>("produto"));
        email.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        email.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<VendaItem, Number>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<VendaItem, Number> event) {
                ((VendaItem) event.getTableView().getItems().get(event.getTablePosition().getRow())).setQtd(event.getNewValue().intValue());
            }
        });
        /* idade.setCellValueFactory(new Callback<CellDataFeatures<VendaItem, Integer>, ObservableValue<Integer>>() {
     public ObservableValue<Integer> call(CellDataFeatures<VendaItem, Integer> p) {
         // p.getValue() returns the Person instance for a particular TableView row
         return  p.getValue().getProduto().pkProdutoProperty().asObject();
     }
  });*/
        tableViewVendasItens.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.DELETE) {
                    deletar();
                }
            }
        });

        tableViewVendasItens.setItems(null);
        tableViewVendasItens.setItems(data);
        System.out.println(data);

    }

    public void load() {
        VendaItem prod = new VendaItem(Integer.parseInt(textFieldQtd.getText()), Integer.parseInt(textFieldValor.getText()), comboBoxProdutos.getValue());
        data.add(prod);

    }

    private void clearForm() {

    }

    public void deletar() {
        data.remove(tableViewVendasItens.getSelectionModel().getSelectedItem());

    }

    public void carregar() throws SQLException {
        venda = comboVendas.getValue();   
        data.removeAll(data);
        data.addAll(venda.getItens());
        comboBoxFuncionario.setValue(venda.getVendedor());
        comboBoxCliente.setValue(venda.getCliente());
        pkVenda = VendaDAO.retreaveNumero(venda.getNumero());

    }

    public void save() throws SQLException {
        ArrayList<VendaItem> userList = new ArrayList<VendaItem>(data);
        int numero = VendaDAO.retreaveNumero();
       Date data =  new Date();
       
        if(comboVendas.getValue()==null){
            System.out.println("editar");
                                VendaDAO.create(new Venda(numero + 1, new Date(), comboBoxCliente.getValue(), comboBoxFuncionario.getValue(), userList));

        } else {
            int count=0;
            while (VendaItemDAO.retreaveFkVendaItem(pkVenda).size()>count){
                ArrayList<Integer> vendaitem = VendaItemDAO.retreaveFkVendaItem(pkVenda);
                userList.get(count).setPkVendaItem(vendaitem.get(count));
                count++;
            }
            userList.forEach((a)->{a.setFkVenda(pkVenda);});
                System.out.println("novo");
                    VendaDAO.update(new Venda(venda.getNumero(), data, comboBoxCliente.getValue(), comboBoxFuncionario.getValue(), userList,pkVenda));

        }
    }
}
