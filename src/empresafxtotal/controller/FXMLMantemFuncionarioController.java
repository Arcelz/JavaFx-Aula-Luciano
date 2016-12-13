/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.controller;

import empresafxtotal.model.CargoDAO;
import empresafxtotal.model.FuncionarioDAO;
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


public class FXMLMantemFuncionarioController implements Initializable {

    private Funcionario f;
    private FuncionarioEndereco e;
    private int pkCargo;
    private int pkFuncionario;
    private int fkEndereco;
    
    
    
     @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField textFieldnome;

    @FXML
    private TextField textFieldCpf;

    @FXML
    private ComboBox<Cargo> comboBoxCargo;

    @FXML
    private TextField textFieldEndereco;

    @FXML
    private TextField textFieldBairro;

    @FXML
    private TextField textFieldCidade;

    @FXML
    private TextField textFieldCep;

    @FXML
    private ComboBox<String> comboBoxEstado;

    @FXML
    private ComboBox<String> comboBoxPais;

    @FXML
    private ComboBox<Funcionario> comboBoxFuncionarios;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxEstado.getItems().addAll("AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RS", "SC", "SE", "SP", "TO" );
        comboBoxPais.getItems().addAll("Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antarctica", "Antigua and Barbuda",
"Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados",
"Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana",
"Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burma", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde",
"Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo, Democratic Republic",
"Congo, Republic of the", "Costa Rica", "Cote d'Ivoire", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark",
"Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea",
"Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana",
"Greece", "Greenland", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hong Kong",
"Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan",
"Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, North", "Korea, South", "Kuwait", "Kyrgyzstan", "Laos", "Latvia",
"Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia", "Madagascar",
"Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia",
"Moldova", "Mongolia", "Morocco", "Monaco", "Mozambique", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand",
"Nicaragua", "Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Panama", "Papua New Guinea", "Paraguay", "Peru",
"Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Samoa", "San Marino", " Sao Tome",
"Saudi Arabia", "Senegal", "Serbia and Montenegro", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia",
"Solomon Islands", "Somalia", "South Africa", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden",
"Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad and Tobago",
"Tunisia", "Turkey", "Turkmenistan", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States",
"Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe");
    
    List<Funcionario> l;
        try {
            l = FuncionarioDAO.retreaveAll();
            comboBoxFuncionarios.getItems().addAll(l);

        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
  alert.setTitle("Aconteceu um erro em funcionario");
            alert.setHeaderText("Erro em funcionario");
            alert.setContentText(Logger.getLogger(FXMLMantemFuncionarioController.class.getName()).getName());

            alert.showAndWait();  
        }
          List<Cargo> carg;
        try {
            carg = CargoDAO.retreaveAll();
                    comboBoxCargo.getItems().addAll(carg);

        } catch (SQLException ex) {
Alert alert = new Alert(Alert.AlertType.ERROR);
  alert.setTitle("Aconteceu um erro em funcionario");
            alert.setHeaderText("Erro em carregar cargos em funcionario");
            alert.setContentText(Logger.getLogger(FXMLMantemFuncionarioController.class.getName()).getName());

            alert.showAndWait();          }
}
    public void load(){
        f = comboBoxFuncionarios.getValue();
        e = comboBoxFuncionarios.getValue().getFuncEndereco();

        textFieldnome.setText(f.getNome());
        textFieldCpf.setText(f.getCpf());
        comboBoxCargo.setValue(f.getCargo());
        textFieldEndereco.setText(f.getFuncEndereco().getLogradouro());
        textFieldBairro.setText(f.getFuncEndereco().getBairro());
        textFieldCep.setText(f.getFuncEndereco().getCep());
        textFieldCidade.setText(f.getFuncEndereco().getCidade());
        comboBoxEstado.setValue(f.getFuncEndereco().getEstado());
        comboBoxPais.setValue(f.getFuncEndereco().getPais());
        pkCargo = f.getFk_cargo();
        pkFuncionario = f.getPk_funcionario();
        fkEndereco = f.getFuncEndereco().getPk_endereco();
    }
    public void limpaTela(){
        textFieldBairro.clear();
        textFieldCep.clear();
        textFieldCpf.clear();
        textFieldCidade.clear();
        textFieldEndereco.clear();
        textFieldnome.clear();
        comboBoxEstado.getSelectionModel().clearSelection();
        comboBoxPais.getSelectionModel().clearSelection();
    }
    public void salvar(){
        boolean insert = false;

        if (f == null) {
            f = new Funcionario();
            e = new FuncionarioEndereco();
            insert = true;
        }
         e.setBairro(textFieldBairro.getText());
        e.setEstado(comboBoxEstado.getValue());
        e.setLogradouro(textFieldEndereco.getText());
        e.setCidade(textFieldCidade.getText());
        e.setPais(comboBoxPais.getValue());
        e.setCep(textFieldCep.getText());
        f.setNome(textFieldnome.getText());
        f.setCpf(textFieldCpf.getText());
        f.setFuncEndereco(e);
        
         if (insert){
            try {
                f.save();
            } catch (SQLException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
  alert.setTitle("Aconteceu um erro em funcionario");
            alert.setHeaderText("Erro em salvar funcionario");
            alert.setContentText(Logger.getLogger(FXMLMantemFuncionarioController.class.getName()).getName());
            alert.showAndWait();  
            }
         }
         else{
            try {
                f.update();
            } catch (SQLException ex) {
Alert alert = new Alert(Alert.AlertType.ERROR);
  alert.setTitle("Aconteceu um erro em funcionario");
            alert.setHeaderText("Erro em atualizar funcionario");
            alert.setContentText(Logger.getLogger(FXMLMantemFuncionarioController.class.getName()).getName());
            alert.showAndWait();             }
         }
    }
    public void cancelar(){
        
    }
}
