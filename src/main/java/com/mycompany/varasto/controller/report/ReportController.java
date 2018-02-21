/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.varasto.controller.report;

/**
 *
 * @author aleks
 */
import com.mycompany.varasto.entity.Lasku;
import com.mycompany.varasto.interfaces.ReportInterface;
import static com.mycompany.varasto.interfaces.ReportInterface.REPORTLIST;
import com.mycompany.varasto.model.LaskuModel;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ReportController implements Initializable, ReportInterface {

    @FXML
    private TableView<Lasku> reportTable;
    @FXML
    private TableColumn<Lasku, Long> idColumn;
    @FXML
    private TableColumn<Lasku, String> employeeColumn, dateColumn;
    @FXML
    private TableColumn<Lasku, Double> totalColumn, vatColumn, discountColumn, 
            payableColumn, paidColumn, returnedColumn;
    @FXML
    private TextField searchField;
    @FXML
    private LaskuModel model;

    private double xOffset = 0;
    private double yOffset = 0;
    
    @FXML
    private Button menu;
    @FXML
    private VBox drawer;
    
    
    // lue categoryControllien kommentit ovat samanliaset
    // mutta muutettu vain Category report ja joitan muita pieniä muutoksia GUI hin
    // 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new LaskuModel();
        
        drawerAction();
        loadData();
        // tehdään taulun column nimet jne
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        employeeColumn.setCellValueFactory((TableColumn.CellDataFeatures<Lasku, String> p)
                -> new SimpleStringProperty(p.getValue().getEmployee().getUserName()));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        vatColumn.setCellValueFactory(new PropertyValueFactory<>("vat"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));
        payableColumn.setCellValueFactory(new PropertyValueFactory<>("payable"));
        paidColumn.setCellValueFactory(new PropertyValueFactory<>("paid"));
        returnedColumn.setCellValueFactory(new PropertyValueFactory<>("returned"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        reportTable.setItems(REPORTLIST);

        filterData();

    }

    private void filterData() {
        FilteredList<Lasku> searchedData = new FilteredList<>(REPORTLIST, e -> true);
        searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                searchedData.setPredicate(report -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    return report.getEmployee().getUserName().toLowerCase().contains(lowerCaseFilter);
                });
            });

            SortedList<Lasku> sortedData = new SortedList<>(searchedData);
            sortedData.comparatorProperty().bind(reportTable.comparatorProperty());
            reportTable.setItems(sortedData);
        });
    }
    
    private void loadData(){
    
        if (!REPORTLIST.isEmpty()) {
            REPORTLIST.clear();
        }
        REPORTLIST.addAll(model.getLaskut());
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
        windows("/fxml/Admin.fxml", "Admin", event);
    }
    
    @FXML
    public void productAction(ActionEvent event) throws Exception {

        windows("/fxml/Admin.fxml", "Admin", event);
    }
    
    @FXML
    public void categoryAction(ActionEvent event) throws Exception {

        windows("/fxml/Category.fxml", "Category", event);
    }

    @FXML
    public void purchaseAction(ActionEvent event) throws Exception {

        windows("/fxml/Purchase.fxml", "Purchase", event);
    }

    @FXML
    public void salesAction(ActionEvent event) throws Exception {

        windows("/fxml/Sales.fxml", "Sales", event);
    }

    @FXML
    public void supplierAction(ActionEvent event) throws Exception {
        windows("/fxml/Supplier.fxml", "Supplier", event);
    }
    
    @FXML
    public void staffAction(ActionEvent event) throws Exception {
        windows("/fxml/Employee.fxml", "Employee", event);
    }

    @FXML
    public void logoutAction(ActionEvent event) throws Exception {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
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

    private void windows(String path, String title, ActionEvent event) throws Exception {

        double width = ((Node) event.getSource()).getScene().getWidth();
        double height = ((Node) event.getSource()).getScene().getHeight();

        Parent root = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene(root, width, height);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
