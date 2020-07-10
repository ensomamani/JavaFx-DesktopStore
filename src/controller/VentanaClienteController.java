/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ProductoDAO;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DBUtils;
import model.Producto;
import view.Principal;
import static view.Principal.stageExtends;

/**
 * FXML Controller class
 *
 * @author Enso
 */
public class VentanaClienteController implements Initializable {

    FXMLLoader fxmlLoader;
    Stage stage = null;
    ProductoDAO productoDAO;
    Producto modelProducto;
    String categoria = null;
    String tipo = null;

    @FXML
    private FlowPane areaProductos;
    @FXML
    private Pane sidebarGalletas;
    @FXML
    private Pane sidebarSnacks;
    @FXML
    private Pane sidebarBocaditos;
    @FXML
    private Pane sidebarBebidas;
    @FXML
    private Pane sidebarKekes;
    @FXML
    private JFXButton btnChocolate,btnVainilla,btnSalada,btnSinAzucar,btnPicante,btnSinPicante,btnDulces,btnSalados,btnCitricos,
            btnJugos,btnAgua,btnRehidratantes,btnGasesosas,btnKekes,btnBocadito,btnSnacks,btnGalleta,btnBebidas,cerrarVenCliente,
            iconifiedVenCliente,verPedidosCliente;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        llenarProductos();
        btnGasesosas.setOnMouseClicked(value -> {
            
        });
        
    }

   
    private void llenarProductos() {
        try {
            productoDAO = new ProductoDAO();
            modelProducto = new Producto();
            ArrayList<Producto> listProd = productoDAO.listarProductoCliente();
            Node[] nodes = new Node[listProd.size()];
            for (int i = 0; i < nodes.length; i++) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddItemVentanaTienda.fxml"));
                AddItemVentanaTiendaController controller = new AddItemVentanaTiendaController();
                fxmlLoader.setController(controller);
                nodes[i] = fxmlLoader.load();
                areaProductos.getChildren().add(nodes[i]);
                controller.llamarProducto(listProd.get(i));
            }
        } catch (Exception e) {
        }

    }

    @FXML
    private void verPedidoClicked(MouseEvent event) {

        try {

            //stage.initModality(Modality.WINDOW_MODAL);
            stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Tus Pedidos");
            System.out.println("hola mundo");
            Parent root = FXMLLoader.load(getClass().getResource("/view/VerHistorialOrdenCliente.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(VentanaClienteController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @FXML
    private void minimizarVenClienteClicked(MouseEvent event) {
        if (event.getSource().equals(iconifiedVenCliente)) {
            stageExtends.setIconified(true);
        }
    }

    @FXML
    private void cerrarVentanaClicked(MouseEvent event) {
        if (event.getSource().equals(cerrarVenCliente)) {
            System.exit(0);
        }
    }

    @FXML
    private void btnBebidasClicked(MouseEvent event) {
        if (event.getSource().equals(btnBebidas)) {
            sidebarBebidas.toFront();
            activarMenu(btnBebidas);
            categoria = "Bebidas";
            listarProductosCategoria(categoria);
        }
    }

    @FXML
    private void btnGalletasClicked(MouseEvent event) {
        sidebarGalletas.toFront();
        activarMenu(btnGalleta);
        categoria = "Galletas";
        listarProductosCategoria(categoria);
    }

    @FXML
    private void btnSnackClicked(MouseEvent event) {
        sidebarSnacks.toFront();
        activarMenu(btnSnacks);
        categoria = "Snacks";
        listarProductosCategoria(categoria);
    }

    @FXML
    private void btnBocaditoClicked(MouseEvent event) {
        sidebarBocaditos.toFront();
        activarMenu(btnBocadito);
    }

    @FXML
    private void btnKekeClicked(MouseEvent event) {
        sidebarKekes.toFront();
        activarMenu(btnKekes);
    }

    private void activarMenu(Button b) {
        Button[] botones = {btnBebidas, btnBocadito, btnGalleta, btnKekes, btnSnacks};
        for (Button be : botones) {
            if (b.equals(be)) {
                be.getStyleClass().add("mark-main-button");
            } else {
                be.getStyleClass().remove("mark-main-button");
            }
        }
    }

    private void listarProductosCategoria(String cat) {
        areaProductos.getChildren().clear();
        new ZoomIn(areaProductos).play();
        productoDAO = new ProductoDAO();
        modelProducto = new Producto();
        ArrayList<Producto> listar = productoDAO.listarProductoTienda(cat);
        Node[] nodes = new Node[listar.size()];
        try {
            for (int i = 0; i < nodes.length; i++) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddItemVentanaTienda.fxml"));
                AddItemVentanaTiendaController controller = new AddItemVentanaTiendaController();
                fxmlLoader.setController(controller);
                nodes[i] = fxmlLoader.load();
                areaProductos.getChildren().add(nodes[i]);
                controller.llamarProducto(listar.get(i));
            }
        } catch (IOException ex) {
            Logger.getLogger(VentanaClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarProductosCategoriaTipo(String cat, String tip) {
        areaProductos.getChildren().clear();
        new ZoomIn(areaProductos).play();
        productoDAO = new ProductoDAO();
        modelProducto = new Producto();
        ArrayList<Producto> listar = productoDAO.listarProductoTienda(categoria, tipo);
        Node[] nodes = new Node[listar.size()];
        try {
            for (int i = 0; i < nodes.length; i++) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddItemVentanaTienda.fxml"));
                AddItemVentanaTiendaController controller = new AddItemVentanaTiendaController();
                fxmlLoader.setController(controller);
                nodes[i] = fxmlLoader.load();
                areaProductos.getChildren().add(nodes[i]);
                controller.llamarProducto(listar.get(i));

            }
        } catch (IOException ex) {
            Logger.getLogger(VentanaClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void eventsAction(ActionEvent event) {
        Object obj = event.getSource();
        if (obj.equals(btnGasesosas)) {
            tipo = "gaseosa";
            listarProductosCategoriaTipo(categoria,tipo);
        } else if(obj.equals(btnCitricos)) {
            tipo = "citricos";
            listarProductosCategoriaTipo(categoria,tipo);
        } else if(obj.equals(btnJugos)) {
            tipo = "jugos";
            listarProductosCategoriaTipo(categoria,tipo);
        } else if(obj.equals(btnAgua)) {
            tipo = "agua";
            listarProductosCategoriaTipo(categoria,tipo);
        } else if(obj.equals(btnRehidratantes)) {
            tipo = "rehidratante";
            listarProductosCategoriaTipo(categoria,tipo);
        }
                
    }
}
