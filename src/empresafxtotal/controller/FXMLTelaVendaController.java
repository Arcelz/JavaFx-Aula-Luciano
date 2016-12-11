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
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Usuario-PC
 */
public class FXMLTelaVendaController implements Initializable {

    private Produto p;
    private Funcionario f;
    
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
    private TableColumn<VendaItem, Integer> idade;

    @FXML
    private TableColumn<VendaItem, String> email;
    
    @FXML
    private TableColumn<VendaItem, String> columnFuncionario;

    @FXML
    private TableColumn<VendaItem, String> columnCliente;
    private int isNumber= 1;
    
    
    final ObservableList<VendaItem> data = FXCollections.observableArrayList()
   ;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Produto> prod = ProdutoDAO.retreaveAll();
        comboBoxProdutos.getItems().addAll(prod);
        List<Cliente> cli = ClienteDAO.retreaveAll();
        comboBoxCliente.getItems().addAll(cli);
        System.out.println(cli);
        List<Funcionario> func = FuncionarioDAO.retreaveAll();
        comboBoxFuncionario.getItems().addAll(func);

        
        
        
        idade.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
                email.setCellValueFactory(new PropertyValueFactory<>("qtd"));
                nome.setCellValueFactory(new PropertyValueFactory<>("produto"));
     /* idade.setCellValueFactory(new Callback<CellDataFeatures<VendaItem, Integer>, ObservableValue<Integer>>() {
     public ObservableValue<Integer> call(CellDataFeatures<VendaItem, Integer> p) {
         // p.getValue() returns the Person instance for a particular TableView row
         return  p.getValue().getProduto().pkProdutoProperty().asObject();
     }
  });*/
     
 
        tableViewVendasItens.setItems(null);
        tableViewVendasItens.setItems(data);
        System.out.println(data);
        
    }
    public void load(){
      VendaItem prod = new VendaItem(Integer.parseInt(textFieldQtd.getText()),Integer.parseInt(textFieldValor.getText()),comboBoxProdutos.getValue());
       isNumber++;
       data.add(prod);
       
    }
    private void clearForm(){
        
    }
    
    public void save() throws SQLException{
        Collection<VendaItem> userCollection = new HashSet<VendaItem>(data);

  ArrayList<VendaItem> userList = new ArrayList<VendaItem>(data);
     
        int numero = VendaDAO.retreaveNumero();
        System.out.println(numero);
        VendaDAO.create(new Venda(numero+1, new Date(), comboBoxCliente.getValue(), comboBoxFuncionario.getValue(), userList));
    }
}
