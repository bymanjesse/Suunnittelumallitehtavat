/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.varasto.controller.purchase;

/**
 *
 * @author aleks
 */
import com.mycompany.varasto.entity.Product;
import com.mycompany.varasto.entity.Purchase;
import com.mycompany.varasto.entity.Supplier;
import com.mycompany.varasto.interfaces.PurchaseInterface;
import static com.mycompany.varasto.interfaces.PurchaseInterface.PURCHASELIST;
import com.mycompany.varasto.model.ProductModel;
import com.mycompany.varasto.model.PurchaseModel;
import com.mycompany.varasto.model.SupplierModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddController implements Initializable, PurchaseInterface {
    // fxml muuttujat
    @FXML
    private ComboBox productBox, supplierBox;
    @FXML
    private TextField quantityField, priceField;
    @FXML
    private Button saveButton;
    private ProductModel productModel;
    private SupplierModel supplierModel;
    private PurchaseModel purchaseModel;
    private ResourceBundle rb;
    // initialisointi
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        this.rb = rb;
        productModel = new ProductModel();
        supplierModel = new SupplierModel();
        purchaseModel = new PurchaseModel();
        ObservableList<String> productList = FXCollections.observableArrayList(productModel.getProductNames());
        ObservableList<String> supplierList = FXCollections.observableArrayList(supplierModel.getNames());
        productBox.setItems(productList);
        supplierBox.setItems(supplierList);
    }
    // tallennetaan uusi tuotteiden osto
    @FXML
    public void handleSave(ActionEvent event) {
        // validoidaan input
        if (validateInput()) {
            // valitaan boxeista tuote ja supplier mitä tahdotaan ostaa lisää
            Product product = productModel.getProduct(productBox.getSelectionModel().getSelectedIndex() + 1);
            Supplier supplier = supplierModel.getSupplier(supplierBox.getSelectionModel().getSelectedIndex() + 1);
            // määrä ja hinta
            double quantity = Double.parseDouble(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());
            double total = quantity * price;
            Purchase purchase = new Purchase(
                    product,
                    supplier,
                    quantity,
                    price,
                    total
            );
            // päivitetään tuotteen määrä tietokannassa
            Product updatingProduct = productModel.getProduct(product.getId());
            updatingProduct.setQuantity(updatingProduct.getQuantity() + quantity);
            productModel.increaseProduct(updatingProduct);
            // tallennetaan osto tietokantaan
            purchaseModel.savePurchase(purchase);
            // päivitetään ostojen lista 
            PURCHASELIST.clear();
            PURCHASELIST.addAll(purchaseModel.getPurchases());

            ((Stage) saveButton.getScene().getWindow()).close();
            // ilmoitus onnistumisesta
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(rb.getString("successfull"));
            alert.setHeaderText(rb.getString("PurchComp"));
            alert.setContentText(rb.getString("prodAddSucc"));
            alert.showAndWait();
        }
    }
    // cancel napin handlaus
    @FXML
    public void handleCancel(ActionEvent event) {
        priceField.setText("");
        quantityField.setText("");
        productBox.valueProperty().setValue(null);
        supplierBox.valueProperty().setValue(null);
    }
    // inputtien validointi
    private boolean validateInput() {

        String errorMessage = "";

        if (priceField.getText() == null || priceField.getText().length() == 0) {
            errorMessage += rb.getString("NoProdPrice") + "\n";
        }

        if (quantityField.getText() == null || quantityField.getText().length() == 0) {
            errorMessage += rb.getString("NoProdQuan") + "\n";
        }

        if (productBox.getSelectionModel().isEmpty()) {
            errorMessage += rb.getString("NoProdDes") + "\n";
        }

        if (supplierBox.getSelectionModel().isEmpty()) {
            errorMessage += rb.getString("NoProdSup") + "\n";
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
