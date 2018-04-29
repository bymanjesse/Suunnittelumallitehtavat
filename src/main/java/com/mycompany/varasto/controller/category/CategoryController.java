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

public class CategoryController implements Initializable, CategoryInterface {

    
    // fxml muuttujat
    @FXML
    private TableView<Category> categoryTable;
    @FXML
    private TableColumn<Category, Long> idColumn;
    @FXML
    private TableColumn<Category, String> typeColumn, descriptionColumn;
    @FXML
    private TextField searchField;
    @FXML
    private Button editButton, deleteButton;
    private CategoryModel model;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Button menu;
    @FXML
    private VBox drawer;
    private ResourceBundle rb;
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        model = new CategoryModel();
        this.rb = rb;

        drawerAction();
        loadData();
        // taulukon nimet
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        // täytetään categorioilla
        categoryTable.setItems(CATEGORYLIST);
        
        // search fieldin jatkuva seuraaminen
        filterData();
        
        // delete ja edit buttoneille handläys jotta valitaan listasta editointia varten 
        editButton
                .disableProperty()
                .bind(Bindings.isEmpty(categoryTable.getSelectionModel().getSelectedItems()));
        deleteButton
                .disableProperty()
                .bind(Bindings.isEmpty(categoryTable.getSelectionModel().getSelectedItems()));
    }

    private void filterData() {
        // serach toiminto categorioille
        // tämä on suoraan erimerkistä vedettu ja vaihdettu vaan esim esimerkistä muutujia esim CATEGORYLIST categorytable jne
        // Wraps an ObservableList and filters it's content using the provided Predicate. 
        // All changes in the ObservableList are propagated immediately to the FilteredList.
        // Tuossa pieni pätkä joka ehkä auttaa ymmärtämään.
        // ottaa siis searchfieldin inputin ja vertaa Categorylistaan 
        
        FilteredList<Category> searchedData = new FilteredList<>(CATEGORYLIST, e -> true);
        searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                searchedData.setPredicate(category -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (category.getType().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (category.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            // sitten asetetaan löydetty data pöydälle näkyviin
            // kutsutaan initialize funktiossa
            SortedList<Category> sortedData = new SortedList<>(searchedData);
            sortedData.comparatorProperty().bind(categoryTable.comparatorProperty());
            categoryTable.setItems(sortedData);
        });
    }

    private void loadData() {
        // ladataan categorylist
        if (!CATEGORYLIST.isEmpty()) {
            CATEGORYLIST.clear();
        }
        CATEGORYLIST.addAll(model.getCategories());
    }
    // menu button menun avaus ja sulkeminen
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
  // valikkomenun buttoneiden actionit
     @FXML
    public void adminAction(ActionEvent event) throws Exception {

        windows("/fxml/Admin.fxml", rb.getString("administrator"), event, rb);
    }

    @FXML
    public void productAction(ActionEvent event) throws Exception {

        windows("/fxml/Product.fxml", rb.getString("product"), event, rb);
    }

    @FXML
    public void purchaseAction(ActionEvent event) throws Exception {

        windows("/fxml/Purchase.fxml", rb.getString("purchase"), event, rb);
    }

    @FXML
    public void salesAction(ActionEvent event) throws Exception {

        windows("/fxml/Sales.fxml", rb.getString("sales"), event, rb);
    }

    @FXML
    public void supplierAction(ActionEvent event) throws Exception {
        windows("/fxml/Supplier.fxml", rb.getString("supplier"), event, rb);
    }

    @FXML
    public void reportAction(ActionEvent event) throws Exception {
        windows("/fxml/Report.fxml", rb.getString("report"), event, rb);
    }

    @FXML
    public void staffAction(ActionEvent event) throws Exception {
        windows("/fxml/Employee.fxml", rb.getString("staff"), event, rb);
    }
    
    // logout napin event handleri
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
    // käytetään kun ladataan uusi ikuna menu valikosta
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

    
    // add napin event handler
    @FXML
    public void addAction(ActionEvent event) throws Exception {
        // avataan uusi ikkuna add.fxml.
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/category/Add.fxml"), rb);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        root.setOnMousePressed((MouseEvent e) -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        // mouse drad event handleri jotta voi skaalata ikkunaa
        root.setOnMouseDragged((MouseEvent e) -> {
            stage.setX(e.getScreenX() - xOffset);
            stage.setY(e.getScreenY() - yOffset);
        });
        // astetaan title jne
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(rb.getString("newcategory"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    // edit napin 
    public void editAction(ActionEvent event) throws Exception {
        // otetaan selected item pöydästä 
        Category selectedCategory = categoryTable.getSelectionModel().getSelectedItem();
        int selectedCategoryId = categoryTable.getSelectionModel().getSelectedIndex();
        // ladataan edit.fxml
        FXMLLoader loader = new FXMLLoader((getClass().getResource("/fxml/category/Edit.fxml")), rb);
        // asetetaan controlleri näin jotta voidaan kutsua controllerista myöhemmin toista funktota (setcategory)
        // add napille on asetettu controlleri fxml tiedostossa
        EditController controller = new EditController();
        loader.setController(controller);
        Parent root = loader.load();
        // luodaan scene ja stage
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        root.setOnMousePressed((MouseEvent e) -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        // handleri screenin koolle mousedrag
        root.setOnMouseDragged((MouseEvent e) -> {
            stage.setX(e.getScreenX() - xOffset);
            stage.setY(e.getScreenY() - yOffset);
        });
        // asetetaan titlet jnejne
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(rb.getString("editCategory"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        controller.setCategory(selectedCategory, selectedCategoryId);
        categoryTable.getSelectionModel().clearSelection();
    }

    
    // delete napin handleri 
    @FXML
    public void deleteAction(ActionEvent event) {
        // varmistetaan käyttäjältä poisto
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(rb.getString("delete"));
        alert.setHeaderText(rb.getString("DeleteCat"));
        alert.setContentText(rb.getString("confirmation"));
        // jos painaa ok niin poistetaan 
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Category selectedCategory = categoryTable.getSelectionModel().getSelectedItem();

            model.deleteCategory(selectedCategory);
            CATEGORYLIST.remove(selectedCategory);
        }
        // clearataan valinta
        categoryTable.getSelectionModel().clearSelection();
    }
}