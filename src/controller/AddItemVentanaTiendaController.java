/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ProductoDAO;
import Utilidades.ControladorGeneral;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.Producto;

/**
 * FXML Controller class
 *
 * @author Enso
 */
public class AddItemVentanaTiendaController implements Initializable {
    private ProductoDAO productoDAO;
    private FXMLLoader fxLoader;
    @FXML
    private ImageView imagenProducto;
    @FXML
    private Label nombreProducto;
    @FXML
    private Label precioProducto;
    @FXML
    private TextField mostrarCantidad;
    @FXML
    private Button aumentarProd;
    @FXML
    private Button disminuirProd;
    @FXML
    private Button btnAgregarCarrito;
    @FXML
    private Button ordenarProducto;
    @FXML
    private VBox mainVboxItemProd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    public void llamarProducto(Producto p) {
        nombreProducto.setText(p.getNombre_Producto());
        precioProducto.setText(""+p.getPrecio_venta());
        imagenProducto.setImage(ControladorGeneral.bytesToImage(p.getImagen()));
    }
    @FXML
    private void btnAumentarClicked(MouseEvent event) {
        if(event.getSource().equals(aumentarProd)) {
            //inicializamos el incremente en 1
            int increment = 1;
            //recorremos una vez para que vaya aumentando uno en uno
            int value = Integer.parseInt(mostrarCantidad.getText());
            for (int i = 0; i < increment; i++) {
                value++;
                mostrarCantidad.setText(String.valueOf(value));
                disminuirProd.setDisable(false);
                if (value > 5) {
                    mostrarCantidad.setText("1");
                    disminuirProd.setDisable(true);
                }   
            } 
       }   
    }

    @FXML
    private void btnDisminuirClicked(MouseEvent event) {
        if (event.getSource().equals(disminuirProd)) {
            //inicializamos el incremente en 1
            int increment = 1;
            //recorremos una vez para que vaya disminuyendo uno en uno
            int value = Integer.parseInt(mostrarCantidad.getText());
            for (int i = 0; i < increment; i++) {
                value--;
                mostrarCantidad.setText(String.valueOf(value));
                if (value <= 1) {
                    mostrarCantidad.setText("1");
                    disminuirProd.setDisable(true);
                }
            }
        }  
    }  

    @FXML
    private void btnOrdenarClicked(MouseEvent event) {
        System.out.println("hola mundo");
        System.out.println(nombreProducto.getText());
        System.out.println(precioProducto.getText());
        
    }

    @FXML
    private void btnOrdenarCarritoClicked(MouseEvent event) {
        
    }
    
    
}
