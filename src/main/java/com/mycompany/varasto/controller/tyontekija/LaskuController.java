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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


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

    private double xOffset = 0;
    private double yOffset = 0;
    
    //
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
                    payment.getDiscount(),
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
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }
        }

    }

    private boolean validateInput() {
        // inputtien validointi
        String errorMessage = "";

        if (paidAmountField.getText() == null || paidAmountField.getText().length() == 0) {
            errorMessage += "Invalid Input!\n";
        } else if (Double.parseDouble(paidAmountField.getText()) < netPrice) {
            errorMessage += "Insufficient Input!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Please input the valid amount");
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