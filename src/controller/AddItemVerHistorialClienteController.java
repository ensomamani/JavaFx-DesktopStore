/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.PedidoDAOImpl;
import InterfaceDAO.PedidoDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.PedidoDetPedProd;

/**
 * FXML Controller class
 *
 * @author Enso
 */
public class AddItemVerHistorialClienteController implements Initializable {

    @FXML
    private ImageView imageViewProducto;
    @FXML
    private Label labelNombreProducto;
    @FXML
    private Label labelCantidadOrdenCliemte;
    @FXML
    private Label labelPrecioProducto;
    @FXML
    private Label labelSubtotalOrdenCliente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setHistorialClientePedido(PedidoDetPedProd model) {
        
        imageViewProducto.setImage(model.getImagen());
        labelNombreProducto.setText(model.getNombre_Producto());
        labelCantidadOrdenCliemte.setText(""+model.getCantidadOrden());
        labelPrecioProducto.setText(""+model.getPrecioProducto());
        labelSubtotalOrdenCliente.setText(""+model.getSubtotalPedido());
    }
}
