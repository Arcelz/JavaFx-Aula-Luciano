/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.controller;

import empresafxtotal.model.ProdutoDAO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        List<Produto> l = ProdutoDAO.retreaveAll();
        comboBoxProdutos.getItems().addAll(l);
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
           if (insert) {
               p = new Produto(textFieldNome.getText(),Integer.parseInt(textFieldEstoqueMin.getText()), 0);
               p.save();
           }
           else{
             p = new Produto(pkProduto,textFieldNome.getText(),Integer.parseInt(textFieldEstoqueMin.getText()), 0);
  
             p.update();
           }
                   limpaTela();

    }
}
