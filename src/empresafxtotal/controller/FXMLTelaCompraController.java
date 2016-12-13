/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.controller;

import empresafxtotal.model.CompraDAO;
import empresafxtotal.model.FornecedorDAO;
import empresafxtotal.model.ProdutoDAO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
public class FXMLTelaCompraController implements Initializable {
    private Compra compra;
    CompraItem prod;
@FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<Fornecedor> comboBoxFornecedores;

    @FXML
    private ComboBox<Produto> comboBoxProdutos;

    @FXML
    private TextField textFieldValorUnitario;

    @FXML
    private TextField textFieldQuantidade;

    @FXML
    private TextField textFieldNumero;

    @FXML
    private Button botaoAdicionar;

    @FXML
    private Label labelValorTotal;

    @FXML
    private Button botaoDeletar;

    @FXML
    private ComboBox<Compra> comboCompras;

    @FXML
    private TableView tableViewCompraItens;

    @FXML
    private TableColumn<CompraItem, String> TableColumnNome;

    @FXML
    private TableColumn<CompraItem, Number> tableColumnValorUnitario;

    @FXML
    private TableColumn<CompraItem, Number> tableColumnQuantidade;
    
        final ObservableList<CompraItem> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        List<Produto> prod = ProdutoDAO.retreaveAll();
         comboBoxProdutos.getItems().addAll(prod);
    } catch (SQLException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Aconteceu um erro em produto");
            alert.setHeaderText("Erro em produto");
            alert.setContentText(Logger.getLogger(FXMLTelaVendaController.class.getName()).toString());

            alert.showAndWait();
    }
    try {
        List<Fornecedor> forn = FornecedorDAO.retreaveAll();
        comboBoxFornecedores.getItems().addAll(forn);
    } catch (SQLException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Aconteceu um erro em fornecedor");
            alert.setHeaderText("Erro em carregar fornecedors");
            alert.setContentText(Logger.getLogger(FXMLTelaVendaController.class.getName()).toString());

            alert.showAndWait();
    }
        try {
            List<Compra> ven = CompraDAO.retreaveAll();
            comboCompras.getItems().addAll(ven);
            
        
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTelaCompraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    tableViewCompraItens.setEditable(true);

    TableColumnNome.setCellValueFactory(new PropertyValueFactory<>("produto"));
    tableColumnValorUnitario.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
    tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<>("qtd"));
    
     tableColumnValorUnitario.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        tableColumnValorUnitario.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CompraItem, Number>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CompraItem, Number> event) {
                ((CompraItem) event.getTableView().getItems().get(event.getTablePosition().getRow())).setValorUnitario(event.getNewValue().doubleValue());
                        atualizaValor();

            }
        });
        tableColumnQuantidade.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        tableColumnQuantidade.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CompraItem, Number>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CompraItem, Number> event) {
                ((CompraItem) event.getTableView().getItems().get(event.getTablePosition().getRow())).setQtd(event.getNewValue().intValue());
                      atualizaValor();
                      
            }
        });
        
        tableViewCompraItens.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.DELETE) {
                deletar();
                        atualizaValor();

            }
        });
        tableViewCompraItens.setItems(null);
        tableViewCompraItens.setItems(observableList);

    }    
    public void load(){
        
   if(comboBoxProdutos.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nenhuma seleção");
            alert.setHeaderText("Nenhuma Produto Selecionada");
            alert.setContentText("Por favor, selecione uma produto.");

            alert.showAndWait();
    
        }
       if(textFieldQuantidade.getText().trim().equals("")&&textFieldValorUnitario.getText().trim().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selecao de valores produto");
            alert.setHeaderText("Nenhum campo foi prenchido de produto");
            alert.setContentText("Por favor, preencha os campos de valor e quantidade.");

            alert.showAndWait();
        }
       else{
           prod= new CompraItem();
           prod.setQtd(Integer.parseInt(textFieldQuantidade.getText()));
           prod.setValorUnitario(Integer.parseInt(textFieldValorUnitario.getText()));
           prod.setProduto(comboBoxProdutos.getValue());
        observableList.add(prod);
        
        atualizaValor();
       }
        
    }
    private void atualizaValor(){
        double valor = 0;

        for(CompraItem ven:observableList){
           valor += ven.getQtd()*ven.getValorUnitario();
        }
        labelValorTotal.setText("R$"+Double.toString(valor));
    }
    
    public void carregar(){
        compra = comboCompras.getValue();   
        observableList.removeAll(observableList);
        observableList.addAll(compra.getItens());
        comboBoxFornecedores.setValue(compra.getFornecedor());
        botaoAdicionar.setDisable(true);
        textFieldQuantidade.setDisable(true);
        textFieldValorUnitario.setDisable(true);
        comboBoxFornecedores.setDisable(true);
        comboBoxProdutos.setDisable(true);
        botaoDeletar.setDisable(true);
        botaoDeletar.setDisable(true);
           tableViewCompraItens.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.DELETE) {
            }
        });
              atualizaValor();

    }
    public void deletar(){
        observableList.remove(tableViewCompraItens.getSelectionModel().getSelectedItem());
        //atualizaValor();
    }
    public void save(){
                ArrayList<CompraItem> compraItem = new ArrayList<>(observableList);  
        try {
                        int numero = CompraDAO.retreaveNumero();

           if(comboCompras.getValue()==null){      
           compra = new Compra();
           compra.setNumero(numero+1);
           compra.setData(new Date());
           compra.setFornecedor(comboBoxFornecedores.getValue());
           compra.setItens(compraItem);
           compra.save();
                }
           else {
           compra.update();
        }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTelaCompraController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                

    }
 
}
