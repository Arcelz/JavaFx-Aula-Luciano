/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.controller;

import empresafxtotal.model.CargoDAO;
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
        List<Cargo> cargos;
        try {
            cargos = CargoDAO.retreaveAll();
                    comboBoxCargos.getItems().addAll(cargos);

        } catch (SQLException ex) {
Alert alert = new Alert(Alert.AlertType.ERROR);
  alert.setTitle("Aconteceu um erro em cargo");
            alert.setHeaderText("Erro em carregar cargo");
            alert.setContentText(Logger.getLogger(FXMLMantemCargoController.class.getName()).getName());

            alert.showAndWait();        }
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
       c.setNome(textFieldNome.getText());
       c.setDescricao(textFieldDescricao.getText());
       if(insert){
           try {
               c.save();
           } catch (SQLException ex) {
               Logger.getLogger(FXMLMantemCargoController.class.getName()).log(Level.SEVERE, null, ex);
Alert alert = new Alert(Alert.AlertType.ERROR);
  alert.setTitle("Aconteceu um erro em cargo");
            alert.setHeaderText("Erro em salvar cargo");
            alert.setContentText(Logger.getLogger(FXMLMantemCargoController.class.getName()).getName());

            alert.showAndWait();        }
               
       }
       else{
           try {
               c.update();
           } catch (SQLException ex) {
Alert alert = new Alert(Alert.AlertType.ERROR);
  alert.setTitle("Aconteceu um erro em cargo");
            alert.setHeaderText("Erro em atualizar cargo");
            alert.setContentText(Logger.getLogger(FXMLMantemCargoController.class.getName()).getName());

            alert.showAndWait();              }
       }
       limpaTela();
    }
    
}
