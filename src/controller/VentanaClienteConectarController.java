/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Enso
 */
public class VentanaClienteConectarController implements Initializable {

    @FXML
    private JFXTextField textFieldNamePc;
    @FXML
    private JFXTextField textFieldNameServer;
    @FXML
    private JFXButton buttonCancel;
    @FXML
    private JFXButton buttonConfirm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void eventOnCancel(ActionEvent event) {
    }

    @FXML
    private void eventOnAction(ActionEvent event) {
        if (event.getSource().equals(buttonConfirm)) {
            String namePc = textFieldNamePc.getText();
            String nameServer = textFieldNameServer.getText();
            if (!namePc.isEmpty() && !nameServer.isEmpty()) {
                
            }
        }
    }
    
}
