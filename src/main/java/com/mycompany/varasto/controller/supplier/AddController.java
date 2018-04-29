/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.varasto.controller.supplier;

/**
 *
 * @author aleks
 */
import com.mycompany.varasto.entity.Supplier;
import com.mycompany.varasto.interfaces.SupplierInterface;
import static com.mycompany.varasto.interfaces.SupplierInterface.SUPPLIERLIST;
import com.mycompany.varasto.model.SupplierModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddController implements Initializable, SupplierInterface {

    @FXML
    private TextField supplierField, phoneField;
    @FXML
    private TextArea addressArea;
    @FXML
    private Button saveButton;
    private SupplierModel supplierModel;
    private ResourceBundle rb;
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        supplierModel = new SupplierModel();
        this.rb = rb;
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        supplierField.setText("");
        phoneField.setText("");
        addressArea.setText("");
    }

    @FXML
    public void handleSave(ActionEvent event) {

        if (validateInput()) {

            Supplier supplier = new Supplier(
                    supplierField.getText(),
                    phoneField.getText(),
                    addressArea.getText()
            );

            supplierModel.saveSuplier(supplier);
            SUPPLIERLIST.clear();
            SUPPLIERLIST.addAll(supplierModel.getSuppliers());

            ((Stage) saveButton.getScene().getWindow()).close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(rb.getString("successfull"));
            alert.setHeaderText(rb.getString("supplierCreated"));
            alert.setContentText(rb.getString("SupplierCreatedSucc"));
            alert.showAndWait();
        }
    }

    private boolean validateInput() {

        String errorMessage = "";

        if (supplierField.getText() == null || supplierField.getText().length() == 0) {
            errorMessage += rb.getString("noFName") + "\n";
        }

        if (phoneField.getText() == null || phoneField.getText().length() == 0) {
            errorMessage += rb.getString("NoPhone") + "\n";
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
    
    @FXML
    public void closeAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}