/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.Principal;
import static view.Principal.stageExtends;
/**
 * FXML Controller class
 *
 * @author Enso
 */
public class VentanaClienteController implements Initializable {
    
    @FXML
    private JFXButton verPedidosCliente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        clickShow();
    }    
    
    private void clickShow() {

    }

    @FXML
    private void verPedidoClicked(MouseEvent event) {
        
         try {
                Stage stage = new Stage();
                //stage.initModality(Modality.WINDOW_MODAL);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setTitle("Tus Pedidos");
                System.out.println("hola mundo");
                Parent root = FXMLLoader.load(getClass().getResource("/view/VerHistorialOrdenCliente.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(VentanaClienteController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error: " + ex.getMessage());
            }
    }
}
