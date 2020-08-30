/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Utilidades.ControladorGeneral;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Producto;

/**
 * FXML Controller class
 *
 * @author Enso
 */
public class AddItemCartController implements Initializable {

    double precioProducto = 0;
    public static int idProducto = 0;
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
    @FXML
    private FontAwesomeIcon btnAumentarCantidad;
    @FXML
    private VBox parentAddItemCart;
    @FXML
    private Label labelIdProducto;

    //public static Button btnCerrar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        labelIdProducto.setManaged(false);
    }

    @FXML
    private void btnAumentarClicked(MouseEvent event) {
        if (event.getSource().equals(btnAumentarCan)) {
            //recorremos una vez para que vaya aumentando uno en uno          
            //System.out.println(value);
            int resultado = Integer.parseInt(txtCantidad.getText()) + 1;
            txtCantidad.setText("" + resultado);
            btnDisminuirCan.setDisable(false);      
            double totalSumado = Double.parseDouble(lblPrecio.getText()) + precioProducto; 
            lblPrecio.setText(String.format("%.2f", totalSumado));
            double sumarConTotalGeneral = Double.parseDouble(VentanaClienteController.precioTotalProducto.getText()) + precioProducto;
            VentanaClienteController.precioTotalProducto.setText(String.format("%.2f", sumarConTotalGeneral));
            if (resultado > 5) {
                txtCantidad.setText("1");
                btnDisminuirCan.setDisable(true);
                //total de itemProducto
                double restarTotalProducto = Double.parseDouble(lblPrecio.getText()) - precioProducto;
                //total de la ventanaClienta
                double restarTotalGeneral = Double.parseDouble(VentanaClienteController.precioTotalProducto.getText()) - restarTotalProducto;
                VentanaClienteController.precioTotalProducto.setText(String.format("%.2f", restarTotalGeneral));
                lblPrecio.setText(""+precioProducto);                 
            }
        }
    }

    @FXML
    private void btnDisminuirClicked(MouseEvent event) {
        if (event.getSource().equals(btnDisminuirCan)) {
            //recorremos una vez para que vaya disminuyendo uno en uno
            int resultado = Integer.parseInt(txtCantidad.getText()) - 1;
            txtCantidad.setText("" + resultado);
            //Total restado del producto Item
            double totalRestado = Double.parseDouble(lblPrecio.getText()) - precioProducto; 
            lblPrecio.setText(String.format("%.2f", totalRestado));
            //Total restado de la ventana cliente
            double restarConTotalGeneral = Double.parseDouble(VentanaClienteController.precioTotalProducto.getText()) - precioProducto;
            VentanaClienteController.precioTotalProducto.setText(String.format("%.2f", restarConTotalGeneral));
            if (resultado == 1) {
                btnDisminuirCan.setDisable(true);
            }

        }
    }

    @FXML
    private void btnEliminarProdCarrito(ActionEvent event) {
        if (event.getSource().equals(btnCloseItemCart)) {
            VentanaClienteController.extendsAreaCarrito.getChildren().remove(parentAddItemCart);
            double eliminarTotalProducto = Double.parseDouble(VentanaClienteController.precioTotalProducto.getText()) - Double.parseDouble(lblPrecio.getText());
            System.out.println(eliminarTotalProducto);
            VentanaClienteController.precioTotalProducto.setText(String.format("%.2f", eliminarTotalProducto));          
            ControladorGeneral.findNodesToUnable(labelIdProducto.getText());

        }
    }


    public void setProductoCarrito(int id, String nombre, String cantidad, String precio, Image imagen) {      
        labelIdProducto.setText(""+id);
        lblNombreProd.setText(nombre);
        txtCantidad.setText(cantidad);
        double totalPrecioCantidad = Double.parseDouble(precio) * Integer.parseInt(cantidad);
        lblPrecio.setText(""+String.format("%.2f", totalPrecioCantidad));
        imageCarrito.setImage(imagen);
        precioProducto = Double.parseDouble(precio);
        if (Integer.parseInt(cantidad) > 1) {
            btnDisminuirCan.setDisable(false);
        }
    }

}
