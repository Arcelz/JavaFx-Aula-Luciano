/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.controller;

import empresafxtotal.model.CargoDAO;
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
public class FXMLMantemCargoController implements Initializable {
    private int pkCargo;
    private Cargo c;
    

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldDescricao;

    @FXML
    private ComboBox<Cargo> comboBoxCargos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Cargo> l = CargoDAO.retreaveAll();
        comboBoxCargos.getItems().addAll(l);
    }    
    
    public void load(){
        c = comboBoxCargos.getValue();
        textFieldNome.setText(c.getNome());
        textFieldDescricao.setText(c.getDescricao());
        pkCargo = c.getPk_cargo();
    }
    public void limpaTela(){
        textFieldNome.clear();
        textFieldDescricao.clear();
    }
    
    public void salvar(){
       boolean insert = false;
       if(c==null){
           c = new Cargo();
           insert=true;
       }
       if(insert){
           c = new Cargo(textFieldNome.getText(), textFieldDescricao.getText());
           c.save();
       }
       else{
           c = new Cargo(pkCargo,textFieldNome.getText(),textFieldDescricao.getText());
           c.update();
       }
       limpaTela();
    }
    
}
