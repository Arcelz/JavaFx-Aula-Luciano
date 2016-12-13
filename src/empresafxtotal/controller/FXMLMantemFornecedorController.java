/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.controller;

import empresafxtotal.model.FornecedorDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class FXMLMantemFornecedorController implements Initializable {

    private FornecedorEndereco e;
    private Fornecedor f;
    int pkEndereco;
    int fkFornecedor;

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
    private ComboBox<Fornecedor> comboBoxClientes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxEstado.getItems().addAll("AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RS", "SC", "SE", "SP", "TO");
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

        List<Fornecedor> forne;
        try {
            forne = FornecedorDAO.retreaveAll();
            comboBoxClientes.getItems().addAll(forne);

        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Aconteceu um erro em fornecedor");
            alert.setHeaderText("Erro em carregar fornecedor");
            alert.setContentText(Logger.getLogger(FXMLMantemFornecedorController.class.getName()).getName());

            alert.showAndWait();
        }
    }

    public void load() {
        f = comboBoxClientes.getValue();
        e = comboBoxClientes.getValue().getForneEnd();
        textFieldNome.setText(f.getNome());
        textFieldCPF.setText(f.getCpf());
        textFieldEndereco.setText(f.getForneEnd().getLogradouro());
        textFieldBairro.setText(f.getForneEnd().getBairro());
        textFieldCEP.setText(f.getForneEnd().getCep());
        textFieldCidade.setText(f.getForneEnd().getCidade());
        comboBoxEstado.setValue(f.getForneEnd().getEstado());
        comboBoxPais.setValue(f.getForneEnd().getPais());
        fkFornecedor = f.getPk_fornecedor();
        pkEndereco = f.getForneEnd().getPk_endereco();

    }

    public void limpaTela() {
        textFieldBairro.clear();
        textFieldCEP.clear();
        textFieldCPF.clear();
        textFieldCidade.clear();
        textFieldEndereco.clear();
        textFieldNome.clear();
        comboBoxEstado.getSelectionModel().clearSelection();
        comboBoxPais.getSelectionModel().clearSelection();
    }

    public void salvar() {
        boolean insert = false;

        if (f == null) {
            f = new Fornecedor();
            e = new FornecedorEndereco();
            insert = true;
        }
        e.setBairro(textFieldBairro.getText());
        e.setEstado(comboBoxEstado.getValue());
        e.setLogradouro(textFieldEndereco.getText());
        e.setCidade(textFieldCidade.getText());
        e.setPais(comboBoxPais.getValue());
        e.setCep(textFieldCEP.getText());
        f.setNome(textFieldNome.getText());
        f.setCpf(textFieldCPF.getText());
        f.setForneEnd(e);

        if (insert) {

            try {
                f.save();
            } catch (SQLException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Aconteceu um erro em fornecedor");
                alert.setHeaderText("Erro em salvar fornecedor");
                alert.setContentText(Logger.getLogger(FXMLMantemFornecedorController.class.getName()).getName());

                alert.showAndWait();
            }
        } else {
            try {
                f.update();
            } catch (SQLException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Aconteceu um erro em fornecedor");
                alert.setHeaderText("Erro em atualizar fornecedor");
                alert.setContentText(Logger.getLogger(FXMLMantemFornecedorController.class.getName()).getName());

                alert.showAndWait();
            }

        }

        limpaTela();
    }

    public void cancelar() {
        limpaTela();
    }
}
