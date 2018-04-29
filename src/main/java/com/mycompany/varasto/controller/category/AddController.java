/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.varasto.controller.category;

/**
 *
 * @author aleks
 */
import com.mycompany.varasto.entity.Category;
import com.mycompany.varasto.interfaces.CategoryInterface;
import static com.mycompany.varasto.interfaces.CategoryInterface.CATEGORYLIST;
import com.mycompany.varasto.model.CategoryModel;
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

public class AddController implements Initializable, CategoryInterface {
    
    // fxml muuttujat add categoryyn
    @FXML
    private TextField typeField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private Button saveButton;
    private CategoryModel categoryModel;
    private ResourceBundle rb;

    
    // luodaan ikkunan avauksessa uusi category model jotta saadaan siirettyä sen avulla 
    // dataa tietokantaan
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        categoryModel = new CategoryModel();
        this.rb = rb;
    }
    // cancel napin event handler
    @FXML
    public void handleCancel(ActionEvent event) {
        typeField.setText("");
        descriptionArea.setText("");
    }
    // save napin event handler
    @FXML
    public void handleSave(ActionEvent event) {
        // validoidaan että kaikki inputut ovat kunnossa
        if (validateInput()) {
            // jos ovat luodaan uusi categoria ja syötetään fieldien sisältö siihen
            Category category = new Category(
                    typeField.getText(),
                    descriptionArea.getText()
            );
            // tallennetaan modelin avulla tietokantaan
            categoryModel.saveCategory(category);
            // clearataan categorylista ja päivitetään 
            CATEGORYLIST.clear();
            CATEGORYLIST.addAll(categoryModel.getCategories());
            // sulje ikkuna
            ((Stage) saveButton.getScene().getWindow()).close();
            // ilmoitus onnistumisesta
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(rb.getString("successful"));
            alert.setHeaderText(rb.getString("category"));
            alert.setContentText(rb.getString("categorycreated"));
            alert.showAndWait();
        }
    }
    
    private boolean validateInput() {
        // input fieldien validointi add categorylle
        String errorMessage = "";

        if (typeField.getText() == null || typeField.getText().length() == 0) {
            errorMessage += rb.getString("noname")+ "\n";
        }

        if (descriptionArea.getText() == null || descriptionArea.getText().length() == 0) {
            errorMessage += rb.getString("noemail")+ "\n";
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
    // close napin eventhanndler
    @FXML
    public void closeAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
