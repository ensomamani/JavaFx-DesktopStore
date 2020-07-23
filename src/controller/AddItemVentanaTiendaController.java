/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ProductoDAO;
import Utilidades.ControladorGeneral;
import animatefx.animation.ZoomIn;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.Producto;
import controller.VentanaClienteController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author Enso
 */
public class AddItemVentanaTiendaController implements Initializable {

    private ProductoDAO productoDAO;
    private FXMLLoader fxmlLoader;
    public static Label precioItemVentanaTienda;
    private double totalVentanaCliente = 0;
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
    @FXML
    private HBox checkProductoAgregado;

    @FXML
    private Label lblCantidadAgregado;
    private int idProducto = 0;
    public static Button botonActivo;
    public static Button disminuir, aumentar, agregar;
    public static Label prod;
    public static VBox mainBox;
    Node nodo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        deshabilitarFocus();
        mainBox = mainVboxItemProd;
        botonActivo = btnAgregarCarrito;
    }

    private void deshabilitarFocus() {
        aumentarProd.setFocusTraversable(false);
        disminuirProd.setFocusTraversable(false);
        btnAgregarCarrito.setFocusTraversable(false);
        ordenarProducto.setFocusTraversable(false);
        mostrarCantidad.setFocusTraversable(false);
    }

    public void llamarProducto(Producto p) {
        idProducto = p.getId_Producto();
        nombreProducto.setText(p.getNombre_Producto());
        precioProducto.setText("" + p.getPrecio_venta());
        imagenProducto.setImage(ControladorGeneral.bytesToImage(p.getImagen()));
    }

    @FXML
    private void btnAumentarClicked(MouseEvent event) {
        if (event.getSource().equals(aumentarProd)) {
            //recorremos una vez para que vaya aumentando uno en uno
            int value = Integer.parseInt(mostrarCantidad.getText());
            int resultado = value + 1;
            mostrarCantidad.setText("" + resultado);
            disminuirProd.setDisable(false);
            if (resultado > 5) {
                mostrarCantidad.setText("1");
                disminuirProd.setDisable(true);
            }
        }
    }

    @FXML
    private void btnDisminuirClicked(MouseEvent event) {
        if (event.getSource().equals(disminuirProd)) {
            //recorremos una vez para que vaya disminuyendo uno en uno
            int value = Integer.parseInt(mostrarCantidad.getText());
            int resultado = value - 1;
            mostrarCantidad.setText("" + resultado);
            if (resultado == 1) {
                disminuirProd.setDisable(true);
            }

        }
    }

    @FXML
    private void btnOrdenarClicked(MouseEvent event) {
        System.out.println(nombreProducto.getText());
        System.out.println(precioProducto.getText());
        System.out.println(mostrarCantidad.getText());
        for (int i = 0; i < VentanaClienteController.extendsAreaCarrito.getChildren().size(); i++) {
            System.out.println(VentanaClienteController.extendsAreaCarrito.getChildren());
        }
    }

    @FXML
    public void btnOrdenarCarritoClicked(MouseEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddItemCart.fxml"));
            AddItemCartController controller = new AddItemCartController();
            fxmlLoader.setController(controller);
            nodo = fxmlLoader.load();
            VentanaClienteController.extendsAreaCarrito.getChildren().add(nodo);
            new ZoomIn(nodo).play();
            controller.setProductoCarrito(idProducto, nombreProducto.getText(), mostrarCantidad.getText(), precioProducto.getText(), imagenProducto.getImage());
            double precioTotal = Integer.parseInt(mostrarCantidad.getText()) * Double.parseDouble(precioProducto.getText());
            totalVentanaCliente = Double.parseDouble(VentanaClienteController.precioTotalProducto.getText()) + precioTotal;
            VentanaClienteController.precioTotalProducto.setText(String.format("%.2f", totalVentanaCliente));
            lblCantidadAgregado.setText(mostrarCantidad.getText());
            //se declaro aqui la variable addCart de tipo static porque cuando pase al carrito esta guardara el boton ordenar producto y cuando pase
            // al area del carrito esta podra tener acceso pero el intermediario va ser el btn ordenar carrito Clicked 
            //ordenarProducto.toBack();
            btnAgregarCarrito.setDisable(true);

        } catch (IOException ex) {
            Logger.getLogger(AddItemVentanaTiendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deshabilibarBotonesRaiz(String nombre) {
        if (nombreProducto.getText().equals(nombre)) {
            nombreProducto.getParent().getChildrenUnmodifiable().clear();
        }
    }
}
