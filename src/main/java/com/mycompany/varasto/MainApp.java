/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.varasto;

/**
 *
 * @author aleks
 */
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    

    
   
    @Override
    public void start(Stage stage) throws Exception {
        String language ="en";
        String country ="EN";
        
        Locale currentlocale = new Locale(language, country);
        ResourceBundle rb = ResourceBundle.getBundle("MessageBundle_en_EN" , currentlocale);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"), rb);
        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        
        
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
        Scene scene = new Scene(root);
        stage.setTitle(rb.getString("v1"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        ResourceBundle rb;
        Locale currentlocale;
        String language ="en";
        String country ="EN";
        
        currentlocale = new Locale(language, country);
        
        rb = ResourceBundle.getBundle("MessageBundle_en_EN" , currentlocale);
        // aloitetaan sessio
        if (HibernateUtil.setSessionFactory()) {
            launch(args);
            HibernateUtil.getSessionFactory().close();
        } else {
            // virhetapahtumat eli jos tietokantayhteys ei pelaa
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(rb.getString("error"));
                alert.setHeaderText(rb.getString("dberror"));
                alert.setContentText(rb.getString("contactD"));
                alert.showAndWait();
                Platform.exit();
            });
        }

    }

}

