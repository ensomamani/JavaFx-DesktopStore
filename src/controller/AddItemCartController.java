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
            int value = Integer.parseInt(txtCantidad.getText());
            int resultado = value + 1;
            txtCantidad.setText("" + resultado);
            btnDisminuirCan.setDisable(false);
            System.out.println(lblPrecio.getText());
            if (resultado > 5) {
                txtCantidad.setText("1");
                btnDisminuirCan.setDisable(true);
            }
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
            double ventanaTotalCliente = Double.parseDouble(VentanaClienteController.precioTotalProducto.getText()) - precioTotal;
            VentanaClienteController.precioTotalProducto.setText("" + ventanaTotalCliente);
            Node[] areaProductoItem = new Node[VentanaClienteController.extendsAreaProducto.getChildren().size()];
            for (int i = 0; i < areaProductoItem.length; i++) {
                areaProductoItem[i] = VentanaClienteController.extendsAreaProducto.getChildren().get(i);
                VBox v = (VBox) areaProductoItem[i];
                Label nombreProd = (Label) v.getChildren().listIterator(1).next();
                if (nombreProd.getText().equals(lblNombreProd.getText())) {
                    HBox hBox = (HBox) v.getChildren().listIterator(3).next();
                    Button b = (Button) hBox.getChildren().listIterator(2).next();
                    if (b.isDisabled()) {
                        b.setDisable(false);
                    }
                }
            }

        }
    }

    public void setProductoCarrito(int id, String nombre, String cantidad, String precio, Image imagen) {
        idProducto = id;
        lblNombreProd.setText(nombre);
        txtCantidad.setText(cantidad);
        precioTotal = Double.parseDouble(cantidad) * Double.parseDouble(precio);
        lblPrecio.setText(String.format("%.2f", precioTotal));
        imageCarrito.setImage(imagen);

    }

}
