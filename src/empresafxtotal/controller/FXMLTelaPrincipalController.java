/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Usuario-PC
 */
public class FXMLTelaPrincipalController implements Initializable {

    @FXML
    private AnchorPane anchorPaneTelas;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void abraTelaMantemCliente() throws IOException{
        AnchorPane a = FXMLLoader.load(getClass().getResource("/empresafxtotal/view/FXMLMantemCliente.fxml"));
        anchorPaneTelas.getChildren().setAll(a);
    }
    public void abraTelaMantemFornecedor() throws IOException{
        AnchorPane a = FXMLLoader.load(getClass().getResource("/empresafxtotal/view/FXMLMantemFornecedor.fxml"));
        anchorPaneTelas.getChildren().setAll(a);
    }
    public void abraTelaMantemCargo() throws IOException{
        AnchorPane a = FXMLLoader.load(getClass().getResource("/empresafxtotal/view/FXMLMantemCargo.fxml"));
        anchorPaneTelas.getChildren().setAll(a);
    } 
    public void abraTelaMantemFuncionario() throws IOException{
        AnchorPane a = FXMLLoader.load(getClass().getResource("/empresafxtotal/view/FXMLMantemFuncionario.fxml"));
        anchorPaneTelas.getChildren().setAll(a);
    }
    public void abraTelaMantemProduto() throws IOException{
        AnchorPane a = FXMLLoader.load(getClass().getResource("/empresafxtotal/view/FXMLMantemProduto.fxml"));
        anchorPaneTelas.getChildren().setAll(a);
    }
             public void abraTelaVenda() throws IOException{
        AnchorPane a = FXMLLoader.load(getClass().getResource("/empresafxtotal/view/FXMLTelaVenda.fxml"));
        anchorPaneTelas.getChildren().setAll(a);
    }
}
