/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.varasto.controller.tyontekija;

import com.mycompany.varasto.entity.Lasku;
import com.mycompany.varasto.entity.Item;
import com.mycompany.varasto.entity.Payment;
import com.mycompany.varasto.entity.Product;
import com.mycompany.varasto.entity.Sale;
import com.mycompany.varasto.model.EmployeeModel;
import com.mycompany.varasto.model.LaskuModel;
import com.mycompany.varasto.model.ProductModel;
import com.mycompany.varasto.model.SalesModel;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 *
 * @author aleks
 */
public class LaskuController implements Initializable {

    // fxml muuttujat
    @FXML
    private TextField totalAmountField, paidAmountField;
    private double netPrice;
    private ObservableList<Item> items;
    private EmployeeModel employeeModel;
    private ProductModel productModel;
    private SalesModel salesModel;
    private LaskuModel laskuModel;
    private Payment payment;
    private ResourceBundle rb;

    private double xOffset = 0;
    private double yOffset = 0;
    
    //
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rb = rb;
        productModel = new ProductModel();
        employeeModel = new EmployeeModel();
        salesModel = new SalesModel();
        laskuModel = new LaskuModel();
        totalAmountField.setText(String.valueOf(netPrice));
    }

    public void setData(double netPrice, ObservableList<Item> items, Payment payment) {

        this.netPrice = netPrice;
        this.items = FXCollections.observableArrayList(items);
        this.payment = payment;
    }

    @FXML
    public void confirmAction(ActionEvent event) throws Exception {
        // validoidaan inputti, että on maksettu terpeeksi
        if (validateInput()) {
            double paid = Double.parseDouble(paidAmountField.getText().trim());
            double retail = Math.abs(paid - netPrice);

            String LaskuId = String.valueOf(new Timestamp(System.currentTimeMillis()).getTime());
            // tehdään payment oliolla ja työntekijän tiedoilla lasku
            Lasku lasku = new Lasku(
                    LaskuId,
                    employeeModel.getEmployee(2),
                    payment.getSubTotal(),
                    payment.getVat(),
                    payment.getPayable(),
                    paid,
                    retail
            );
            // tallennetaan lasku
            laskuModel.saveLasku(lasku);
            // ja siirretään tuotteet myydyiksi
            for (Item i : items) {

                Product p = productModel.getProductByName(i.getItemName());
                double quantity = p.getQuantity() - i.getQuantity();
                p.setQuantity(quantity);
                productModel.decreaseProduct(p);

                Sale sale = new Sale(
                        laskuModel.getLasku(LaskuId),
                        productModel.getProductByName(i.getItemName()),
                        i.getQuantity(),
                        i.getUnitPrice(),
                        i.getTotal()
                );
                // tallennetaan myynti
                salesModel.saveSale(sale);
            }
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/fxml/Confirm.fxml")));
            ConfirmController controller = new ConfirmController();
            controller.setData(retail, items, LaskuId);
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            root.setOnMousePressed((MouseEvent e) -> {
                xOffset = e.getSceneX();
                yOffset = e.getSceneY();
            });
            root.setOnMouseDragged((MouseEvent e) -> {
                stage.setX(e.getScreenX() - xOffset);
                stage.setY(e.getScreenY() - yOffset);
            });
            stage.setTitle("Confirm");
            stage.setScene(scene);
            stage.show();
        }
        }

    
    

    private boolean validateInput() {
        // inputtien validointi
        String errorMessage = "";

        if (paidAmountField.getText() == null || paidAmountField.getText().length() == 0) {
            errorMessage += rb.getString("invalidInput") + "\n";
        } else if (Double.parseDouble(paidAmountField.getText()) < netPrice) {
            errorMessage += rb.getString("InsufInput") + "\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(rb.getString("warn"));
            alert.setHeaderText(rb.getString("amount"));
            alert.setContentText(errorMessage);
            alert.showAndWait();
            paidAmountField.setText("");

            return false;
        }
    }
    // x napin handlaus
    @FXML
    public void closeAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}