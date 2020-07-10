/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Enso
 */
public class AddItemCartController implements Initializable {

    @FXML
    private Label lblNombreProd;
    @FXML
    private Button btnAumentarCan;
    @FXML
    private Button btnDisminuirCan;
    @FXML
    private TextField txtCantidad;
    @FXML
    private Label lblPrecio;
    @FXML
    private ImageView imageCarrito;
    @FXML
    private Button btnCloseItemCart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
