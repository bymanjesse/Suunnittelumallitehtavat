/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.varasto.controller.tyontekija;

/**
 *
 * @author aleks
 */
import com.mycompany.varasto.entity.Item;
import com.mycompany.varasto.entity.Payment;
import com.mycompany.varasto.entity.Product;
import com.mycompany.varasto.interfaces.ProductInterface;
import static com.mycompany.varasto.interfaces.ProductInterface.PRODUCTLIST;
import com.mycompany.varasto.model.ProductModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;

import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

public class TyontekijaController implements Initializable, ProductInterface {
    // point of sales näkymä, eli työntekijän näkymä täällä tapahtuu myynti jnejne
    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableView<Item> listTableView;
    @FXML
    private TableColumn<Product, String> productColumn;
    @FXML
    private TableColumn<Item, String> itemColumn;
    @FXML
    private TableColumn<Item, Double> priceColumn, quantityColumn, totalColumn;
    @FXML
    private TextField searchField, productField, priceField, quantityField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private TextField subTotalField, vatField, netPayableField;
    @FXML
    private Button addButton, removeButton, paymentButton;
    @FXML
    private Label quantityLabel;
    @FXML
    private ObservableList<Item> ITEMLIST;
    private ProductModel productModel;

    private double xOffset = 0;
    private double yOffset = 0;
    private ResourceBundle rb;
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        this.rb = rb;
        // luodaan listat ja täytetään listat datalla ja cellValueilla ikkunan avauksen yhteydessä
        // 
        ITEMLIST = FXCollections.observableArrayList();
        productModel = new ProductModel();

        loadData();

        productColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue));
        productTableView.setItems(PRODUCTLIST);

        filterData();

        itemColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        listTableView.setItems(ITEMLIST);
        // add ja remove buttoneille jotta saavat valitun tuotteen
        addButton
                .disableProperty()
                .bind(Bindings.isEmpty(productTableView.getSelectionModel().getSelectedItems()));
        removeButton
                .disableProperty()
                .bind(Bindings.isEmpty(listTableView.getSelectionModel().getSelectedItems()));
    }
        // serach field
    private void filterData() {
        FilteredList<Product> searchedData = new FilteredList<>(PRODUCTLIST, e -> true);

        searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                searchedData.setPredicate(product -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (product.getProductName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (product.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });

            SortedList<Product> sortedData = new SortedList<>(searchedData);
            sortedData.comparatorProperty().bind(productTableView.comparatorProperty());
            productTableView.setItems(sortedData);
        });
    }

    private void loadData() {
        // jos productlist ei tyhjä tyhjennetään
        if (!PRODUCTLIST.isEmpty()) {
            PRODUCTLIST.clear();
        }
        // ja päivitetään uudella datalla
        PRODUCTLIST.addAll(productModel.getProducts());
    }

    private void showDetails(Product product) {
        // näyeteään tuotteen tiedot ruudussa laitetaan punainen väri taustalle jos tuotteita ei ole niin laitetaan punainen taustalle
        if (product != null) {
            quantityField.setDisable(false);
            productField.setText(product.getProductName());
            priceField.setText(String.valueOf(product.getPrice()));

            double quantity = product.getQuantity();

            if (quantity > 0) {
                quantityField.setEditable(true);
                quantityField.setStyle(null);
            } else {
                quantityField.setEditable(false);
               quantityField.setStyle("-fx-background-color: red;");
            }
            quantityLabel.setText(rb.getString("Stock") + String.valueOf(quantity));
            descriptionArea.setText(product.getDescription());
        } else {
            productField.setText("");
            priceField.setText("");
            quantityLabel.setText("");
            descriptionArea.setText("");
        }
    }
    // resetoinnit
    private void resetProductTableSelection() {
        productTableView.getSelectionModel().clearSelection();
    }

    private void resetItemTable() {
        ITEMLIST.clear();
    }

    private void resetAdd() {
        productField.setText("");
        priceField.setText("");
        quantityField.setText("");
        resetQuantityField();
        quantityLabel.setText(rb.getString("Available"));
        descriptionArea.setText("");
    }

    private void resetLasku() {
        subTotalField.setText("0.00");
        vatField.setText("0.00");
        netPayableField.setText("0.00");
    }

    private void resetQuantityField() {
        quantityField.setDisable(true);
    }

    private void resetPaymentButton() {
        paymentButton.setDisable(true);
    }

    private void resetInterface() {
        loadData();
        resetPaymentButton();
        resetProductTableSelection();
        resetItemTable();
        resetQuantityField();
        resetAdd();
        resetLasku();
    }

    @FXML
    public void resetAction(ActionEvent event) {
        resetInterface();
    }

    @FXML
    public void addAction(ActionEvent event) {
        // lisätään tuote tuotelistasta myytävien tuotteiden listalle ja lasketaan uusi hinta
        if (validateInput()) {
            String productName = productField.getText();
            double unitPrice = Double.parseDouble(priceField.getText());
            double quantity = Double.parseDouble(quantityField.getText());
            double total = unitPrice * quantity;
            ITEMLIST.add(new Item(productName, unitPrice, quantity, total));
            calculation();

            resetAdd();
            productTableView.getSelectionModel().clearSelection();
        }
    }

    private void calculation() {

        double subTotalPrice = 0.0;
        // otetaan kaikki tuotteet listasta johon työntekijä on lisännyt myytävät tyotteet ja lasketaan niiden hinta
        // lasketaan hinta, otetaan kaikkien tuotteiden hinta lisätään totaliin
        subTotalPrice = listTableView.getItems().stream().map(
                (item) -> item.getTotal()).reduce(subTotalPrice, (accumulator, _item) -> accumulator + _item);

        if (subTotalPrice > 0) {
            // jos hinta yli 0 niin enabletaan payment buttoni ja lasketaan verot mukaan
            paymentButton.setDisable(false);
            double vat = (double) subTotalPrice * 0.025;
            // ja 5 euron  alennus vaan ihan huvin vuoksi, jossain vaiheessa voisi laittaa että ötuon discountin saisi suorana guista 
            // en ole vielä sitä vaan tehnyt
            // esimerkki meni näin ja tein sitten niin
            double netPayablePrice = (double) (Math.abs((subTotalPrice + vat)));

            subTotalField.setText(String.valueOf(subTotalPrice));
            vatField.setText(String.valueOf(vat));
            netPayableField.setText(String.valueOf(netPayablePrice));
        }
    }
    // payment napin eli myynti napin handlaus
    @FXML
    public void paymentAction(ActionEvent event) throws Exception {
        // luodaan uusi payment jonne heitetään fieldien sisältö 
        Payment payment = new Payment(
                Double.parseDouble(subTotalField.getText().trim()),
                Double.parseDouble(vatField.getText().trim()),
                Double.parseDouble(netPayableField.getText().trim())
        );
        // haetaan tavarat listasta 
        ObservableList<Item> sold = listTableView.getItems();
        // ladataan laskunäkymä jossa näkyy tuotteiden hinta ja johon työntekijä laittaa maksetun summan
        FXMLLoader loader = new FXMLLoader((getClass().getResource("/fxml/Lasku.fxml")), rb);
        // asetetaan fxml tiedostolle controlleri ja ladataan ikkuna
        LaskuController controller = new LaskuController();
        loader.setController(controller);
        // käyetään controllerin SetData funktiota ja otetaan tiedot talteen 
        controller.setData(Double.parseDouble(netPayableField.getText().trim()), sold, payment);
        Parent root = loader.load();
        Stage stage = new Stage();
        root.setOnMousePressed((MouseEvent e) -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent e) -> {
            stage.setX(e.getScreenX() - xOffset);
            stage.setY(e.getScreenY() - yOffset);
        });
        // avataan lasku ikkuna
        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Payment");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.showAndWait();

        resetInterface();
    }
    // remove napin handlaus
    @FXML
    public void removeAction(ActionEvent event) {

        int index = listTableView.getSelectionModel().getSelectedIndex();

        if (index > 0) {
            listTableView.getItems().remove(index);
            calculation();
        } else if (index == 0) {
            listTableView.getItems().remove(index);
            resetLasku();
        }
    }
    // inputtien validointi
    private boolean validateInput() {

        String errorMessage = "";

        if (quantityField.getText() == null || quantityField.getText().length() == 0) {
            errorMessage += rb.getString("QuantnotSupplied") + "\n";
        } else {
            double quantity = Double.parseDouble(quantityField.getText());
            String available = quantityLabel.getText();
            double availableQuantity = Double.parseDouble(available.substring(7));

            if (quantity > availableQuantity) {
                errorMessage += rb.getString("outOfStock") + "\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(rb.getString("warn"));
            alert.setHeaderText(rb.getString("validnumberProd"));
            alert.setContentText(errorMessage);
            alert.showAndWait();
            quantityField.setText("");

            return false;
        }
    }
        // logout nappi
    @FXML
    public void logoutAction(ActionEvent event) throws Exception {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"), rb);
        Stage stage = new Stage();
        root.setOnMousePressed((MouseEvent e) -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent e) -> {
            stage.setX(e.getScreenX() - xOffset);
            stage.setY(e.getScreenY() - yOffset);
        });

        Scene scene = new Scene(root);
        stage.setTitle(rb.getString("v1"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
}
