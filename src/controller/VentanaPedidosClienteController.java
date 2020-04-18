/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Enso
 */
public class VentanaPedidosClienteController implements Initializable {

    @FXML
    private GridPane gridPaneMainMenu;
    @FXML
    private JFXButton btnMinimize;
    @FXML
    private JFXButton btnClose;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnMinimizeClicked(MouseEvent event) {
    }

    @FXML
    private void btnCloseClicked(MouseEvent event) {
    }
    
}
