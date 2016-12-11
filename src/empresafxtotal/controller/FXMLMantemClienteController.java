/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.controller;

import empresafxtotal.model.ClienteDAO;
import empresafxtotal.model.EnderecoDAO;
import java.net.URL;
import java.sql.SQLException;
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
public class FXMLMantemClienteController implements Initializable {
  private Endereco e;
    private Cliente c;
    private int fkCliente;
    private int pkEndereco;


@FXML
    private TextField textFieldNome;

    @FXML
    private AnchorPane anchorPane;


    @FXML
    private TextField textFieldCPF;

    @FXML
    private TextField textFieldEndereco;

    @FXML
    private TextField textFieldBairro;

    @FXML
    private TextField textFieldCidade;

    @FXML
    private ComboBox<String> comboBoxEstado;

    @FXML
    private ComboBox<String> comboBoxPais;

    @FXML
    private TextField textFieldCEP;
    
    @FXML
    private ComboBox<Cliente> comboBoxClientes;
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
        
        List<Cliente> l = ClienteDAO.retreaveAll();
        comboBoxClientes.getItems().addAll(l);
    }    
    public void load(){
        c = comboBoxClientes.getValue();
        
        textFieldNome.setText(c.getNome());
        textFieldCPF.setText(c.getCpf());
        textFieldEndereco.setText(c.getEndereco().getLogradouro());
        textFieldBairro.setText(c.getEndereco().getBairro());
        textFieldCEP.setText(c.getEndereco().getCep());
        textFieldCidade.setText(c.getEndereco().getCidade());
        comboBoxEstado.setValue(c.getEndereco().getEstado());
        comboBoxPais.setValue(c.getEndereco().getPais());
        fkCliente = c.getPk_cliente();
        pkEndereco = c.getEndereco().getPk_endereco();
      
    }
    public void limpaTela(){
        textFieldBairro.clear();
        textFieldCEP.clear();
        textFieldCPF.clear();
        textFieldCidade.clear();
        textFieldEndereco.clear();
        textFieldNome.clear();
        comboBoxEstado.getSelectionModel().clearSelection();
        comboBoxPais.getSelectionModel().clearSelection();
    }
    public void salvar() throws SQLException {
        boolean insert = false;

        if (c == null) {
            c = new Cliente();
            e = new Endereco();
            insert = true;
        }
        //Controller.Endereco e = new Controller.Endereco(logradouro, bairro, cidade, estado, pais, cep)

        //c.setEndereco(e);
        //ClienteDAO.create(c);
        if (insert) {
              e = new Endereco(textFieldEndereco.getText(), textFieldBairro.getText(), textFieldCidade.getText(), comboBoxEstado.getValue(), comboBoxPais.getValue(), textFieldCEP.getText());
            c= new Cliente(textFieldNome.getText(), textFieldCPF.getText());
            c.setEndereco(e);
            c.save();
        } else {
         e = new Endereco(textFieldEndereco.getText(), textFieldBairro.getText(), textFieldCidade.getText(), comboBoxEstado.getValue(), comboBoxPais.getValue(), textFieldCEP.getText(), pkEndereco, fkCliente);
            c = new Cliente(fkCliente, textFieldNome.getText(), textFieldCPF.getText());
            c.setEndereco(e);
            c.update();
        }

        limpaTela();

    }
     public void cancelar(){
        limpaTela();
    }
    }
   

