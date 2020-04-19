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
        Productos productoDAO = new Productos();
        try {
            Producto producto = new Producto();
            productoDAO.consultarProducto(producto);
            precioProducto.setText(String.valueOf(producto.getPrecio_venta()));
            nombreProducto.setText(producto.getNombre_Producto());
            
        
        
        System.out.println("El del producto es: " + producto.getNombre_Producto());
        } catch (SQLException ex) {
            Logger.getLogger(AddItemVentanTiendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }    

    @FXML
    private void btnAumentarClicked(MouseEvent event) {
        int counterClicks = event.getClickCount();
        int aumentar = 1;
        if (counterClicks < 5) {
            mostrarCantidad.setText(String.valueOf(aumentar + 1));
            aumentar = aumentar + 1;
        }
        
    }


    @FXML
    private void btnDisminuirClicked(MouseEvent event) {
    }
    
}
