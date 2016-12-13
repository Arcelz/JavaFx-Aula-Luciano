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
import javafx.scene.control.Alert.AlertType;
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
public class FXMLTelaVendaController implements Initializable {
    private Venda venda;
    private VendaItem prod;

    private  int pkVenda;
    @FXML
    private AnchorPane anchorPane;
            
    @FXML
    private Label labelValorTotal;


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
    private TableColumn<VendaItem, String> tableColumnNome;

    @FXML
    private TableColumn<VendaItem, Number> tableColumnValorUnitario;

    @FXML
    private TableColumn<VendaItem, Number> tableColumnQuantidade;

    @FXML
    private TableColumn<VendaItem, String> columnFuncionario;

    @FXML
    private TableColumn<VendaItem, String> columnCliente;
    
    
    @FXML
    private Button botaoAdicionar;
    @FXML
    private Button botaoDeletar;
 @FXML
    private Button botaoLimpar;

    @FXML
    private ComboBox<Venda> comboVendas;

    final ObservableList<VendaItem> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Produto> prod;
        try {
            prod = ProdutoDAO.retreaveAll();
            comboBoxProdutos.getItems().addAll(prod);
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Aconteceu um erro em produto");
            alert.setHeaderText("Erro em produto");
            alert.setContentText(Logger.getLogger(FXMLTelaVendaController.class.getName()).getName());

            alert.showAndWait();
        }
        
        List<Cliente> cli;
        try {
            cli = ClienteDAO.retreaveAll();
            comboBoxCliente.getItems().addAll(cli);
        } catch (SQLException ex) {
Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Aconteceu um erro em Cliente");
            alert.setHeaderText("Erro em cliente");
            alert.setContentText(Logger.getLogger(FXMLTelaVendaController.class.getName()).getName());

            alert.showAndWait();        }
        
        List<Funcionario> func;
        try {
            func = FuncionarioDAO.retreaveAll();
            comboBoxFuncionario.getItems().addAll(func);

        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
  alert.setTitle("Aconteceu um erro em funcionario");
            alert.setHeaderText("Erro em funcionario");
            alert.setContentText(Logger.getLogger(FXMLTelaVendaController.class.getName()).getName());

            alert.showAndWait();          }
        List<Venda> listaVenda;
        try {
            listaVenda = VendaDAO.retreaveAll();
            comboVendas.getItems().addAll(listaVenda);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTelaVendaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tableViewVendasItens.setEditable(true);

        tableColumnValorUnitario.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
        tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<>("qtd"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("produto"));
       
       
        tableColumnValorUnitario.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        tableColumnValorUnitario.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<VendaItem, Number>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<VendaItem, Number> event) {
                ((VendaItem) event.getTableView().getItems().get(event.getTablePosition().getRow())).setValorUnitario(event.getNewValue().doubleValue());
                        atualizaValor();

            }
        });
        tableColumnQuantidade.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        tableColumnQuantidade.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<VendaItem, Number>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<VendaItem, Number> event) {
                ((VendaItem) event.getTableView().getItems().get(event.getTablePosition().getRow())).setQtd(event.getNewValue().intValue());
                        atualizaValor();

            }
        });
     
      
        tableViewVendasItens.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.DELETE) {
                deletar();
            }
        });

        tableViewVendasItens.setItems(null);
        tableViewVendasItens.setItems(data);

    }

    public void load() {
        if(comboBoxProdutos.getValue()==null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Nenhuma seleção");
            alert.setHeaderText("Nenhuma Produto Selecionada");
            alert.setContentText("Por favor, selecione uma produto.");

            alert.showAndWait();
    
        }
       if(textFieldQtd.getText().trim().equals("")&&textFieldValor.getText().trim().equals("")){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Selecao de valores produto");
            alert.setHeaderText("Nenhum campo foi prenchido de produto");
            alert.setContentText("Por favor, preencha os campos de valor e quantidade.");

            alert.showAndWait();
        }
       else{
           prod= new VendaItem();
           prod.setQtd(Integer.parseInt(textFieldQtd.getText()));
           prod.setValorUnitario(Integer.parseInt(textFieldValor.getText()));
           prod.setProduto(comboBoxProdutos.getValue());      
           data.add(prod);
        
        atualizaValor();
       }
        
    }
    private void atualizaValor(){
        double valor = 0;

        for(VendaItem ven:data){
           valor += ven.getQtd()*ven.getValorUnitario();
        }
        labelValorTotal.setText("R$"+Double.toString(valor));
    }

    public void clear() {
        data.removeAll(data);
         atualizaValor();
    }

    public void deletar() {
        data.remove(tableViewVendasItens.getSelectionModel().getSelectedItem());
        atualizaValor();

    }

    public void carregar() throws SQLException {
        venda = comboVendas.getValue();   
        data.removeAll(data);
        data.addAll(venda.getItens());
        comboBoxFuncionario.setValue(venda.getVendedor());
        comboBoxCliente.setValue(venda.getCliente());
        botaoAdicionar.setDisable(true);
        textFieldQtd.setDisable(true);
        textFieldValor.setDisable(true);
        comboBoxCliente.setDisable(true);
        comboBoxFuncionario.setDisable(true);
        comboBoxProdutos.setDisable(true);
        botaoDeletar.setDisable(true);
        botaoLimpar.setDisable(true);
           tableViewVendasItens.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.DELETE) {
            }
        });
                atualizaValor();

    }

    public void save() throws SQLException {
        ArrayList<VendaItem> vendaitem = new ArrayList<>(data);
        int numero = VendaDAO.retreaveNumero();
       
        if(comboVendas.getValue()==null){
           venda = new Venda();
           venda.setCliente(comboBoxCliente.getValue());
           venda.setNumero(numero+1);
           venda.setData(new Date());
           venda.setVendedor(comboBoxFuncionario.getValue());
           venda.setItens(vendaitem);
           venda.save();

        } else {
           venda.update();
        }
    }
}
