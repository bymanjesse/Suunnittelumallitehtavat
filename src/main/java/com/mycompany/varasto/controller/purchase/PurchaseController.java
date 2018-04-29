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
import com.mycompany.varasto.entity.Purchase;
import com.mycompany.varasto.interfaces.PurchaseInterface;
import static com.mycompany.varasto.interfaces.PurchaseInterface.PURCHASELIST;
import com.mycompany.varasto.model.PurchaseModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class PurchaseController implements Initializable, PurchaseInterface {
       // fxml muuttujat
    @FXML
    private TableView<Purchase> purchaseTable;
    @FXML
    private TableColumn<Purchase, Long> idColumn;
    @FXML
    private TableColumn<Purchase, String> productColumn, supplierColumn, dateColumn;
    @FXML
    private TableColumn<Purchase, Double> quantityColumn, priceColumn, totalColumn;
    @FXML
    private TextField searchField;
    @FXML
    private Button menu;
    @FXML
    private VBox drawer;
    private PurchaseModel model;
    private double xOffset = 0;
    private double yOffset = 0;
    private ResourceBundle rb;
    
        // ladataan ikkuna
    // lue categoryControllien kommentit ovat samanliaset
    // mutta muutettu vain Category purchaseksi ja joitan muita pieniä muutoksia GUI hin
    // 

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        this.rb = rb;
        model = new PurchaseModel();
        
        drawerAction();
        loadData();
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productColumn.setCellValueFactory((TableColumn.CellDataFeatures<Purchase, String> p) -> 
                new SimpleStringProperty(p.getValue().getProduct().getProductName()));
        supplierColumn.setCellValueFactory((TableColumn.CellDataFeatures<Purchase, String> p)
                -> new SimpleStringProperty(p.getValue().getSupplier().getName()));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        purchaseTable.setItems(PURCHASELIST);
        
        filterData();
        
    }

    private void filterData() {
        FilteredList<Purchase> searchedData = new FilteredList<>(PURCHASELIST, e -> true);
        searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                searchedData.setPredicate(purchase -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (purchase.getProduct().getProductName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (purchase.getProduct().getCategory().getType().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    
                    return false;
                });
            });

            SortedList<Purchase> sortedData = new SortedList<>(searchedData);
            sortedData.comparatorProperty().bind(purchaseTable.comparatorProperty());
            purchaseTable.setItems(sortedData);
        });
    }
    
    private void loadData() {
    
        if(!PURCHASELIST.isEmpty()){
            PURCHASELIST.clear();
        }
        
        PURCHASELIST.addAll(model.getPurchases());
    }
    
    private void drawerAction() {

        TranslateTransition openNav = new TranslateTransition(new Duration(350), drawer);
        openNav.setToX(0);
        TranslateTransition closeNav = new TranslateTransition(new Duration(350), drawer);
        menu.setOnAction((ActionEvent evt) -> {
            if (drawer.getTranslateX() != 0) {
                openNav.play();
                menu.getStyleClass().remove("menu-button");
                menu.getStyleClass().add("open-menu");
            } else {
                closeNav.setToX(-(drawer.getWidth()));
                closeNav.play();
                menu.getStyleClass().remove("open-menu");
                menu.getStyleClass().add("menu-button");
            }
        });
    }
   @FXML
    public void adminAction(ActionEvent event) throws Exception {
        
        windows("/fxml/Admin.fxml", rb.getString("administrator"), event, rb);
    }
    
    @FXML
    public void productAction(ActionEvent event) throws Exception {

        windows("/fxml/Product.fxml", rb.getString("product"), event, rb);
    }
    
    @FXML
    public void categoryAction(ActionEvent event) throws Exception {

        windows("/fxml/Category.fxml", rb.getString("category"), event, rb);
    }

    @FXML
    public void salesAction(ActionEvent event) throws Exception {

        windows("/fxml/Sales.fxml", rb.getString("sales"), event, rb);
    }
    
    @FXML
    public void reportAction(ActionEvent event) throws Exception {
        
        windows("/fxml/Report.fxml", rb.getString("report"), event, rb);
    }

    @FXML
    public void supplierAction(ActionEvent event) throws Exception {
        
        windows("/fxml/Supplier.fxml", rb.getString("supplier"), event, rb);
    }
    
    @FXML
    public void staffAction(ActionEvent event) throws Exception {
        
        windows("/fxml/Employee.fxml", rb.getString("staff"), event, rb);
    }
    
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

    private void windows(String path, String title, ActionEvent event, ResourceBundle rb) throws Exception {

        double width = ((Node) event.getSource()).getScene().getWidth();
        double height = ((Node) event.getSource()).getScene().getHeight();

        Parent root = FXMLLoader.load(getClass().getResource(path), rb);
        Scene scene = new Scene(root, width, height);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void addAction(ActionEvent event) throws Exception {
    
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/purchase/Add.fxml"), rb);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        root.setOnMousePressed((MouseEvent e) -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent e) -> {
            stage.setX(e.getScreenX() - xOffset);
            stage.setY(e.getScreenY() - yOffset);
        });
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(rb.getString("newPur"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
}
