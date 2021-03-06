/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.varasto.controller.employee;

/**
 *
 * @author aleks
 */
import com.mycompany.varasto.entity.Employee;
import com.mycompany.varasto.interfaces.EmployeeInterface;
import static com.mycompany.varasto.interfaces.EmployeeInterface.EMPLOYEELIST;
import com.mycompany.varasto.model.EmployeeModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

public class AddController implements Initializable, EmployeeInterface {
// fxml muuttujat
    @FXML
    private TextField firstField, lastField, usernameField, phoneField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextArea addressArea;
    @FXML
    private Button saveButton;
    private EmployeeModel employeeModel;
    private ResourceBundle rb;

    
        // luodaan ikkunan avauksessa uusi eployee model jotta saadaan siirettyä sen avulla 
    // dataa tietokantaan
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        employeeModel = new EmployeeModel();
        this.rb = rb;
    }
    // cancel napin event handler
    @FXML
    public void handleCancel(ActionEvent event) {
        firstField.setText("");
        lastField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        phoneField.setText("");
        addressArea.setText("");
    }
    // save napin event hadler
    @FXML
    public void handleSave(ActionEvent event) {
        // jos inputit ok
        if (validateInput()) {
            // otetaan fieldien tiedot ja tehdään niillä uusi employee
            Employee employee = new Employee(
                    firstField.getText(),
                    lastField.getText(),
                    usernameField.getText(),
                    DigestUtils.sha1Hex(passwordField.getText()),
                    phoneField.getText(),
                    addressArea.getText()
            );
            // tallennetaan tietokanta ja päivitetään lista
            employeeModel.saveEmployee(employee);
            EMPLOYEELIST.clear();
            EMPLOYEELIST.addAll(employeeModel.getEmployees());

            ((Stage) saveButton.getScene().getWindow()).close();
            // ilmoitus onnistumisesta
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(rb.getString("successful"));
            alert.setHeaderText(rb.getString("empCreated"));
            alert.setContentText(rb.getString("empCreatedSuc"));
            alert.showAndWait();
        }
    }

    private boolean validateInput() {
        // inputtien validointi
        String errorMessage = "";

        if (firstField.getText() == null || firstField.getText().length() == 0) {
            errorMessage += rb.getString("noFName") + "\n";
        }

        if (lastField.getText() == null || lastField.getText().length() == 0) {
            errorMessage += rb.getString("NoLastName") + "\n";
        }

        if (usernameField.getText() == null || usernameField.getText().length() == 0) {
            errorMessage += rb.getString("NoUser") + "\n"; 
        }

        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += rb.getString("NoPassword") + "\n";  
        }

        if (phoneField.getText() == null || phoneField.getText().length() == 0) {
            errorMessage += rb.getString("NoPassword") + "\n"; 
        }

        if (addressArea.getText() == null || addressArea.getText().length() == 0) {
            errorMessage += rb.getString("noEmailAddress") + "\n";  
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("invalidF"));
            alert.setHeaderText(rb.getString("correctInvalidF"));
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }
    // x napin handlaus
    @FXML
    public void closeAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
