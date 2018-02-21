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

public class EditController implements Initializable, CategoryInterface {
    
    // fxml muuttujat
    @FXML
    private TextField typeField;
    @FXML
    private TextArea descriptionArea;
    private long selectedCategoryId;
    @FXML
    private Button saveButton;
    private CategoryModel categoryModel;
    private Category category;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // luodaan uusi category model ja tyhjennetään kentät
        categoryModel = new CategoryModel();
        resetValues();
    }
    // astetaan categoria
    public void setCategory(Category category, long selectedCategoryId) {
        this.category = category;
        this.selectedCategoryId = selectedCategoryId;
        setData();
    }
    // tallennus napin handlaus
    @FXML
    public void handleSave(ActionEvent event) {
        // jos kaikki input fieldit täytetty kunnolla
        if (validateInput()) {
            // haetaan categorian id ja heitetään fieldeistä uusi type ja description sille
            Category editedCategory = new Category(
                    category.getId(),
                    typeField.getText(),
                    descriptionArea.getText()
            );
            // updatetaan tietokantaan
            categoryModel.updateCategory(editedCategory);
            // päivitetään categorylista
            CATEGORYLIST.set((int) selectedCategoryId, editedCategory);
            // suljetaan ikkuna ja annetaan ilmoitus onnistumisesta
            ((Stage) saveButton.getScene().getWindow()).close();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setHeaderText("Category Updated!");
            alert.setContentText("Category is updated successfully");
            alert.showAndWait();
        }
    }
    // set data edit fieldeihin
    private void setData() {
        typeField.setText(category.getType());
        descriptionArea.setText(category.getDescription());
    }
    // tyhjennetään fieldit
    private void resetValues() {
        typeField.setText("");
        descriptionArea.setText("");
    }
    // cancel napin handlays utsuu reset values voisi lisätä että sulkee vielä ikkunan  mutta en oo jaksanut
    @FXML
    public void handleCancel(ActionEvent event) {
        resetValues();
    }

    private boolean validateInput() {
        // validdoidaan edit ikkunan fieldien inputit jos eivät knnossa annetaan errormessage
        String errorMessage = "";

        if (typeField.getText() == null || typeField.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }

        if (descriptionArea.getText() == null || descriptionArea.getText().length() == 0) {
            errorMessage += "No email description!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }
    // ikkunan sulku x napista
    @FXML
    public void closeAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}

