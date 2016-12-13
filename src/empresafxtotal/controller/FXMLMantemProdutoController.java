/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.controller;

import empresafxtotal.model.ProdutoDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Usuario-PC
 */
public class FXMLMantemProdutoController implements Initializable {
    private Produto p;
    private int pkProduto;
    
     @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldEstoqueMin;

    @FXML
    private ComboBox<Produto> comboBoxProdutos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Produto> l;
        try {
            l = ProdutoDAO.retreaveAll();
            comboBoxProdutos.getItems().addAll(l);

        } catch (SQLException ex) {
Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Aconteceu um erro em produto");
            alert.setHeaderText("Erro em carregar produto");
            alert.setContentText(Logger.getLogger(FXMLMantemProdutoController.class.getName()).getName());

            alert.showAndWait();
          }
    }    
    public void load(){
        p = comboBoxProdutos.getValue();
        
        textFieldNome.setText(p.getNome());
        textFieldEstoqueMin.setText(Integer.toString(p.getEstoqueMinino()));
        pkProduto = p.getPk_produto();
    }
    public void limpaTela(){
         textFieldNome.clear();
          textFieldEstoqueMin.clear();
    }
    public void salvar(){
      boolean insert = false;
      if(p == null){
          p = new Produto();
          insert = true;
      }
      p.setNome(textFieldNome.getText());
      p.setEstoqueMinino(Integer.parseInt(textFieldEstoqueMin.getText()));
           if (insert) {
          try {
              p.setQtdEstoque(0);
              p.save();
          } catch (SQLException ex) {
Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Aconteceu um erro em produto");
            alert.setHeaderText("Erro em salvar produto");
            alert.setContentText(Logger.getLogger(FXMLMantemProdutoController.class.getName()).getName());

            alert.showAndWait();          }
           }
           else{
            try {
              p.update();
          } catch (SQLException ex) {
Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Aconteceu um erro em produto");
            alert.setHeaderText("Erro em atualizar produto");
            alert.setContentText(Logger.getLogger(FXMLMantemProdutoController.class.getName()).getName());

            alert.showAndWait();          }
           }
                   limpaTela();

    }
}
