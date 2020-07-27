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

    double precioTotal = 0;
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
    private int idProducto = 0;

    //public static Button btnCerrar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void btnAumentarClicked(MouseEvent event) {
        if (event.getSource().equals(btnAumentarCan)) {
            //recorremos una vez para que vaya aumentando uno en uno
            double precioProducto = Double.parseDouble(lblPrecio.getText());
            int value = Integer.parseInt(txtCantidad.getText());
            System.out.println(value);
            int resultado = value + 1;
            txtCantidad.setText("" + resultado);
            btnDisminuirCan.setDisable(false);      
            double totalAumentado = value * precioProducto;
            System.out.println(totalAumentado);
            double totalAumentadoSumado = Double.parseDouble(VentanaClienteController.precioTotalProducto.getText()) + totalAumentado;
            if (resultado > 5) {
                txtCantidad.setText("1");
                totalAumentadoSumado = precioProducto;
                btnDisminuirCan.setDisable(true);
                //System.out.println(VentanaClienteController.precioTotalProducto.getText());
            }
            //System.out.println(totalAumentadoSumado);

        }
    }

    @FXML
    private void btnDisminuirClicked(MouseEvent event) {
        if (event.getSource().equals(btnDisminuirCan)) {
            //recorremos una vez para que vaya disminuyendo uno en uno
            int value = Integer.parseInt(txtCantidad.getText());
            int resultado = value - 1;
            txtCantidad.setText("" + resultado);
            if (resultado == 1) {
                btnDisminuirCan.setDisable(true);
            }

        }
    }

    @FXML
    private void btnEliminarProdCarrito(ActionEvent event) {
        if (event.getSource().equals(btnCloseItemCart)) {
            VentanaClienteController.extendsAreaCarrito.getChildren().remove(parentAddItemCart);
            double ventanaTotalCliente = Integer.parseInt(txtCantidad.getText()) * Double.parseDouble(lblPrecio.getText());
            
            double totalARestar = Double.parseDouble(VentanaClienteController.precioTotalProducto.getText()) -  ventanaTotalCliente;
            VentanaClienteController.precioTotalProducto.setText(String.format("%.2f", totalARestar));
            findNodesToDisable();

        }
    }

    private void findNodesToDisable() {
        Node[] areaProductoItem = new Node[VentanaClienteController.extendsAreaProducto.getChildren().size()];
        for (int i = 0; i < areaProductoItem.length; i++) {
            areaProductoItem[i] = VentanaClienteController.extendsAreaProducto.getChildren().get(i);
            VBox v = (VBox) areaProductoItem[i];
            Label nombreProd = (Label) v.lookup("#nombreProducto");
            if (nombreProd.getText().equals(lblNombreProd.getText())) {
                Button bAgregarCar = (Button) v.lookup("#btnAgregarCarrito");
                Button bOrdenarProd = (Button) v.lookup("#ordenarProducto");
                Button botonAumentar = (Button) v.lookup("#aumentarProd");
                Button botonDisminuir = (Button) v.lookup("#disminuirProd");
                TextField txt = (TextField) v.lookup("#mostrarCantidad");
                if (bAgregarCar.isDisabled()) {
                    bAgregarCar.setDisable(false);
                    bOrdenarProd.toFront();
                    bOrdenarProd.setDisable(false);
                    botonAumentar.setDisable(false);
                    botonDisminuir.setDisable(false);
                    if (Integer.parseInt(txt.getText()) == 1) {
                        botonDisminuir.setDisable(true);
                    }
                    
                    //AddItemVentanaTiendaController.ordenarProd.toFront();
                }
            }
        }
    }

    public void setProductoCarrito(int id, String nombre, String cantidad, String precio, Image imagen) {
        idProducto = id;
        lblNombreProd.setText(nombre);
        txtCantidad.setText(cantidad);
        //precioTotal = Double.parseDouble(cantidad) * Double.parseDouble(precio);
        lblPrecio.setText(precio);
        imageCarrito.setImage(imagen);

    }

}
