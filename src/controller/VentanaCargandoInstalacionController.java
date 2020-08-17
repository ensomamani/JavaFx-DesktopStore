/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXSpinner;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.util.Duration;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author Enso
 */
public class VentanaCargandoInstalacionController implements Initializable {

    @FXML
    private JFXSpinner spinnerInstall;
    @FXML
    private Label labelMessageInstall;
    @FXML
    private FontAwesomeIcon iconLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initInstallation();
    } 
    
    public void initInstallation() {
        Timeline timeLine = new Timeline(
                new KeyFrame(
                        Duration.ONE, 
                        new KeyValue(spinnerInstall.progressProperty(), 0)
                ),                
                new KeyFrame(
                        Duration.seconds(7), 
                        new KeyValue(spinnerInstall.progressProperty(), 1)
                ),
                new KeyFrame(Duration.seconds(3), (event) -> {
                    labelMessageInstall.setText("FALTA POCO PARA TERMINAR");  
                    propertiesIcon(true, FontAwesomeIcons.DOWNLOAD);
                })
        );
        timeLine.setCycleCount(1);
        timeLine.play();
        timeLine.setOnFinished(this::finishInstall);
        
    }
    private void finishInstall(ActionEvent event) {
        closeInstaller();
        
    }
    private void closeInstaller() {   
        labelMessageInstall.setText("¡INSTALACIÓN EXITOSA!");
        iconLabel.setVisible(true);
        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(2), this::closeStageInstall));
        timer.setCycleCount(1);
        timer.play();
        
    }
    private void closeStageInstall(ActionEvent event) {
        Principal.stageExtends.close();
        callWindowClient();
    }
    private void callWindowClient() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/VentanaCliente.fxml"));
            Scene scene = new Scene(root);
            Principal.stageExtends.setScene(scene);
            Principal.stageExtends.setResizable(false);
            Principal.stageExtends.show();
        } catch (IOException ex) {
            Logger.getLogger(VentanaCargandoInstalacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    private void propertiesIcon(boolean value, FontAwesomeIcons s) {
        iconLabel.setVisible(value);
        iconLabel.setIcon(s);
    }
    
}
