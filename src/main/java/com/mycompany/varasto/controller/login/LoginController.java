/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.varasto.controller.login;

/**
 *
 * @author aleks
 * 
 */

import com.mycompany.varasto.model.EmployeeModel;
import java.beans.PropertyChangeEvent;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

public class LoginController implements Initializable {
    // fxml muuttujat
    @FXML
    private TextField usernameField, passwordField;
    @FXML
    private Label errorLabel;
    private Label signin;
    private EmployeeModel model;
    private ResourceBundle rb;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new EmployeeModel();
        this.rb = rb;
        // kuunnellan mitä kirjoitetaan 
        enterPressed();
    }

    private void enterPressed() {
        // otetaan fieldit ja authenticatetaan ne
        
        usernameField.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                try {
                    authenticate(ke);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        passwordField.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                try {
                    authenticate(ke);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }
    // login napin event handler
    @FXML
    public void loginAction(ActionEvent event) throws Exception {

        authenticate(event);
    }

    private void authenticate(Event event) throws Exception {
        
        // validoidaan etttä inputit hyväksyttäviä
        if (validateInput()) {

            String username = usernameField.getText().trim();
            String password = DigestUtils.sha1Hex((passwordField.getText().trim()));
            // tarkistetaan onko käyttäjää olemassa
            if (model.checkUser(username)) {
                // takistetaan salasana
                if (model.checkPassword(username, password)) {

                    ((Node) (event.getSource())).getScene().getWindow().hide();

                    String type = model.getEmployeeType(username);
                    // kirjaudutaan joko admin tai employee ruutuun
                    switch (type) {
                        case "admin":
                            windows("/fxml/Admin.fxml", rb.getString("administrator"), rb);
                            break;

                        case "employee":
                            windows("/fxml/Tyontekija.fxml", rb.getString("tyontekija"), rb);
                            break;
                    }
                } else {
                    // muuten virheilmoitukset mkä meni vikaan
                    passwordField.setText("");
                    errorLabel.setText(rb.getString("wrongpw"));
                }
            } else {
                resetFields();
                errorLabel.setText(rb.getString("usernex"));
            }
        }
    }
    // käytetään ikkunan avaamiseen
    private void windows(String path, String title, ResourceBundle rb) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(path), rb);        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    // tyhjennetään kentät cancel napilla
    private void resetFields() {
        usernameField.setText("");
        passwordField.setText("");
    }
    // cancel nappi
    @FXML
    public void cancelAction(ActionEvent event) {
        resetFields();
    }
    // x nappi
    @FXML
    public void closeAction(ActionEvent event) {
        Platform.exit();
    }
    // - nappi
    @FXML
    public void minusAction(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    @FXML
    public void ESPaction(ActionEvent event)throws Exception{
        Locale currentlocale;
        String language ="es";
        String country ="BO";
        this.rb.clearCache();
        currentlocale = new Locale(language, country);
        
        this.rb = ResourceBundle.getBundle("MessageBundle_es_BO" , currentlocale);
        windows("/fxml/Login.fxml","testi",rb);
    }
        @FXML
    public void ENaction(ActionEvent event)throws Exception{
        Locale currentlocale;
        String language ="en";
        String country ="EN";
        this.rb.clearCache();
        
        currentlocale = new Locale(language, country);
        this.rb = ResourceBundle.getBundle("MessageBundle_en_EN" , currentlocale);
        windows("/fxml/Login.fxml","testi",rb);
    }

    private boolean validateInput() {
        
        // inputtien validointi

        String errorMessage = "";

        if (usernameField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += rb.getString("credentials") + "/n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            errorLabel.setText(errorMessage);
            return false;
        }
    }

}

