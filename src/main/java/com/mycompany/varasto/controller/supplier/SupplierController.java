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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

public class SupplierController implements Initializable, SupplierInterface {
    // katso muiden controllien kommentteja kaikki hyvin samanlaisia muutettu vain jotta toimivat eri luokille
    @FXML
    private TableView<Supplier> supplierTable;
    @FXML
    private TableColumn<Supplier, Long> idColumn;
    @FXML
    private TableColumn<Supplier, String> nameColumn, phoneColumn, addressColumn;
    @FXML
    private TextField searchField;
    @FXML
    private Button editButton, deleteButton;
    private SupplierModel model;
    
    private double xOffset = 0;
    private double yOffset = 0;
    
    @FXML
    private Button menu;
    @FXML
    private VBox drawer;
    private ResourceBundle rb;
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        model = new SupplierModel();
        this.rb = rb;
        drawerAction();
        loadData();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        supplierTable.setItems(SUPPLIERLIST);

        filterData();
        
        editButton
                .disableProperty()
                .bind(Bindings.isEmpty(supplierTable.getSelectionModel().getSelectedItems()));
        deleteButton
                .disableProperty()
                .bind(Bindings.isEmpty(supplierTable.getSelectionModel().getSelectedItems()));
    }

    private void filterData() {
        FilteredList<Supplier> searchedData = new FilteredList<>(SUPPLIERLIST, e -> true);
        searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                searchedData.setPredicate(supplier -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (supplier.getName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (supplier.getAddress().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (supplier.getPhone().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });

            SortedList<Supplier> sortedData = new SortedList<>(searchedData);
            sortedData.comparatorProperty().bind(supplierTable.comparatorProperty());
            supplierTable.setItems(sortedData);
        });
    }

    private void loadData(){
    
        if (!SUPPLIERLIST.isEmpty()) {
            SUPPLIERLIST.clear();
        }
        
        SUPPLIERLIST.addAll(model.getSuppliers());
    }
    
    private void drawerAction() {
        // funktio joka vaihtaa menu napin ulkonäköä sen perusteella onko menu näkyvissä vai ei ja tuo menun näkyviin
        // kommentoiduilla osuuksilla saa muutettua ulkonäköa css tiedostossa
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
  // menun nappien event handlerit
    
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

        windows("/fxml/Category.fxml", rb.getString("asd"), event, rb);
    }

    @FXML
    public void purchaseAction(ActionEvent event) throws Exception {

        windows("/fxml/Purchase.fxml",  rb.getString("purchase"), event, rb);
    }

    @FXML
    public void salesAction(ActionEvent event) throws Exception {

        windows("/fxml/Sales.fxml", rb.getString("sales"), event, rb);
    }
    
    @FXML
    public void reportAction(ActionEvent event) throws Exception {
        windows("/fxml/Report.fxml",  rb.getString("report"), event, rb);
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
        stage.setTitle("Inventory:: Version 1.0");
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
    
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/supplier/Add.fxml"), rb);
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
        stage.setTitle("New Supplier");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void editAction(ActionEvent event) throws Exception {

        Supplier selectedSupplier = supplierTable.getSelectionModel().getSelectedItem();
        int selectedSupplierId = supplierTable.getSelectionModel().getSelectedIndex();
        FXMLLoader loader = new FXMLLoader((getClass().getResource("/fxml/supplier/Edit.fxml")), rb);
        EditController controller = new EditController();
        loader.setController(controller);
        Parent root = loader.load();
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
        stage.setTitle("Update Details");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        controller.setSupplier(selectedSupplier, selectedSupplierId);
        supplierTable.getSelectionModel().clearSelection();
    }

    @FXML
    public void deleteAction(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Remove");
        alert.setHeaderText("Remove Supplier");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Supplier selectedSupplier = supplierTable.getSelectionModel().getSelectedItem();

            model.deleteSuplier(selectedSupplier);
            SUPPLIERLIST.remove(selectedSupplier);
        }

        supplierTable.getSelectionModel().clearSelection();
    }
    
}
