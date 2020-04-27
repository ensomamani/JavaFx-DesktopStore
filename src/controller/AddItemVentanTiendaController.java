/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.Productos;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
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
public class AddItemVentanTiendaController implements Initializable {
    private Productos productoDAO;
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
        llamarProducto();
    }    
    private void llamarProducto() {
        productoDAO = new Productos();
        try {
            Producto producto = new Producto();
            productoDAO.consultarProducto(producto);
            
            //Establecemos los valores y dentro llamamos a sus respectivos getters
            precioProducto.setText(String.valueOf(producto.getPrecio_venta()));
            nombreProducto.setText(producto.getNombre_Producto());
            imagenProducto.setImage(new Image(getClass().getResourceAsStream("/ImagesTienda/7up.png")));
        
        System.out.println("El del producto es: " + producto.getNombre_Producto());
        } catch (SQLException ex) {
            Logger.getLogger(AddItemVentanTiendaController.class.getName()).log(Level.SEVERE, null, ex);
        }  
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
    
    
}
