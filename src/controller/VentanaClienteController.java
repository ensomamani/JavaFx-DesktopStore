/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.DetallePedidoDAOImpl;
import DAO.PedidoDAOImpl;
import DAO.ProductoDAOImpl;
import InterfaceDAO.DetallePedidoDAO;
import InterfaceDAO.PedidoDAO;
import Utilidades.ControladorGeneral;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

import model.DetallePedido;
import model.Pedido;
import model.Producto;
import model.PropertiesServer;

import static view.Principal.stageExtends;

/**
 * FXML Controller class
 *
 * @author Enso
 */
public class VentanaClienteController implements Initializable {

    FXMLLoader fxmlLoader;
    private Stage stage;
    public static Stage stageExtendsVentanaCliente;
    ProductoDAOImpl productoDAOImpl;
    Producto modelProducto;
    String categoria = "Bebidas";
    String tipo = "gaseosa";
    PropertiesServer configProperties = new PropertiesServer();
    double x,y;
    public static int idPedidoVerHistoria = 0;
    public static FlowPane extendsAreaCarrito;
    public static Label precioTotalProducto;
    public static FlowPane extendsAreaProducto;
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
    private JFXButton btnChocolate, btnVainilla, btnSalada, btnSinAzucar, btnPicante, btnSinPicante, btnDulces, btnSalados, btnCitricos,
            btnJugos, btnAgua, btnRehidratantes, btnGasesosas, btnKekes, btnBocadito, btnSnacks, btnGalleta, btnBebidas, cerrarVenCliente,
            iconifiedVenCliente, verPedidosCliente;
    @FXML
    private Label lblTitleCategoria;
    @FXML
    private Label lblTitleTipo;
    @FXML
    private TextField txtBuscarProductoCliente;
    @FXML
    private FlowPane areaCarrito;
    @FXML
    private Label lblPrecioTotal;
    @FXML
    private Button btnOrdenarTodo;
    @FXML
    private Label labelNamePc;
    @FXML
    private AnchorPane mainLayout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
        llenarProductos();
        extendsAreaCarrito = areaCarrito;
        precioTotalProducto = lblPrecioTotal;
        extendsAreaProducto = areaProductos;
        labelNamePc.setText(configProperties.getPropertiesValueNamePc());
        moveLayout();
        PedidoDAOImpl s = new PedidoDAOImpl();
        System.out.println(s.getNewId() + 1);
    }

    private void verifyProductsInStock() {
        productoDAOImpl = new ProductoDAOImpl();
        System.out.println(productoDAOImpl.getQuantityStock(0));
    }

    private void llenarProductos() {
        try {
            productoDAOImpl = new ProductoDAOImpl();
            modelProducto = new Producto();
            ArrayList<Producto> listProd = productoDAOImpl.listarProductoCliente();
            Node[] nodes = new Node[listProd.size()];
            for (int i = 0; i < nodes.length; i++) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddItemVentanaTienda.fxml"));
                AddItemVentanaTiendaController controller = new AddItemVentanaTiendaController();
                fxmlLoader.setController(controller);
                nodes[i] = fxmlLoader.load();
                areaProductos.getChildren().add(nodes[i]); 
                if (productoDAOImpl.getQuantityStock(listProd.get(i).getId_Producto()) >= 1) {
                    controller.llamarProducto(listProd.get(i));                  
                } else {                    
                    controller.llamarProductoStockCero(listProd.get(i));
                    ControladorGeneral.setDisableToProductsStockCero(""+listProd.get(i).getId_Producto(), nodes[i]); 
                }
            }
        } catch (Exception e) {
        }
    }

    @FXML
    private void verPedidoClicked(MouseEvent event) {
        try {

            //stage.initModality(Modality.WINDOW_MODAL);
            stage = new Stage();
            stageExtendsVentanaCliente = stage;
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Tus Pedidos");
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
            new ZoomIn(sidebarBebidas).play();
            activarMenu(btnBebidas);
            categoria = "Bebidas";
            lblTitleCategoria.setText(categoria);
            lblTitleTipo.setText(tipo);
            listarProductosCategoria(categoria);
        }
    }

    @FXML
    private void btnGalletasClicked(MouseEvent event) {
        if (event.getSource().equals(btnGalleta)) {
            sidebarGalletas.toFront();
            new ZoomIn(sidebarGalletas).play();
            activarMenu(btnGalleta);
            categoria = "Galletas";
            lblTitleCategoria.setText(categoria);
            listarProductosCategoria(categoria);
            lblTitleTipo.setText("");
        }
    }

    @FXML
    private void btnSnackClicked(MouseEvent event) {
        if (event.getSource().equals(btnSnacks)) {
            sidebarSnacks.toFront();
            new ZoomIn(sidebarSnacks).play();
            activarMenu(btnSnacks);
            categoria = "Snacks";
            lblTitleCategoria.setText(categoria);
            listarProductosCategoria(categoria);
            lblTitleTipo.setText("");
        }
    }

    @FXML
    private void btnBocaditoClicked(MouseEvent event) {
        if (event.getSource().equals(btnBocadito)) {
            sidebarBocaditos.toFront();
            new ZoomIn(sidebarBocaditos).play();
            activarMenu(btnBocadito);
            categoria = "Bocaditos";
            lblTitleCategoria.setText(categoria);
            listarProductosCategoria(categoria);
            lblTitleTipo.setText("");
        }

    }

    @FXML
    private void btnKekeClicked(MouseEvent event) {
        if (event.getSource().equals(btnKekes)) {
            sidebarKekes.toFront();
            new ZoomIn(sidebarKekes).play();
            activarMenu(btnKekes);
            categoria = "Kekes";
            lblTitleCategoria.setText(categoria);
            listarProductosCategoria(categoria);
            lblTitleTipo.setText("");
        }

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
        productoDAOImpl = new ProductoDAOImpl();
        modelProducto = new Producto();
        ArrayList<Producto> listar = productoDAOImpl.listarProductoTienda(cat);
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
        productoDAOImpl = new ProductoDAOImpl();
        modelProducto = new Producto();
        ArrayList<Producto> listar = productoDAOImpl.listarProductoTienda(categoria, tipo);
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
            listarProductosCategoriaTipo(categoria, tipo);
            lblTitleTipo.setText("Gaseosas");
        } else if (obj.equals(btnCitricos)) {
            tipo = "citricos";
            listarProductosCategoriaTipo(categoria, tipo);
            lblTitleTipo.setText("Citricos");
        } else if (obj.equals(btnJugos)) {
            tipo = "jugos";
            listarProductosCategoriaTipo(categoria, tipo);
            lblTitleTipo.setText("Jugos");
        } else if (obj.equals(btnAgua)) {
            tipo = "agua";
            listarProductosCategoriaTipo(categoria, tipo);
            lblTitleTipo.setText("Agua");
        } else if (obj.equals(btnRehidratantes)) {
            tipo = "rehidratante";
            listarProductosCategoriaTipo(categoria, tipo);
            lblTitleTipo.setText("Rehidratante");
        } else if (obj.equals(btnChocolate)) {
            tipo = "chocolate";
            listarProductosCategoriaTipo(categoria, tipo);
            lblTitleTipo.setText("Chocolate");
        } else if (obj.equals(btnVainilla)) {
            tipo = "vainilla";
            listarProductosCategoriaTipo(categoria, tipo);
            lblTitleTipo.setText("Vainilla");
        } else if (obj.equals(btnSalada)) {
            tipo = "salada";
            listarProductosCategoriaTipo(categoria, tipo);
            lblTitleTipo.setText("Salada");
        } else if (obj.equals(btnSinAzucar)) {
            tipo = "sinAzucar";
            listarProductosCategoriaTipo(categoria, tipo);
            lblTitleTipo.setText("Sin azúcar");
        } else if (obj.equals(btnPicante)) {
            tipo = "picante";
            listarProductosCategoriaTipo(categoria, tipo);
            lblTitleTipo.setText("Picante");
        } else if (obj.equals(btnSinPicante)) {
            tipo = "sinPicante";
            listarProductosCategoriaTipo(categoria, tipo);
            lblTitleTipo.setText("Sin picante");
        } else if (obj.equals(btnDulces)) {
            tipo = "dulces";
            listarProductosCategoriaTipo(categoria, tipo);
            lblTitleTipo.setText("Dulce");
        } else if (obj.equals(btnSalados)) {
            tipo = "salada";
            listarProductosCategoriaTipo(categoria, tipo);
            lblTitleTipo.setText("Salado");
        }

    }

    @FXML
    private void txtBuscarCliente(KeyEvent event) {
        String txt = txtBuscarProductoCliente.getText();
        if (txt.length() > 0) {
            buscarPorNombre(txt);
        } else {
            llenarProductos();
        }

    }

    private void buscarPorNombre(String nombre) {
        areaProductos.getChildren().clear();
        productoDAOImpl = new ProductoDAOImpl();
        modelProducto = new Producto();
        ArrayList<Producto> listar = productoDAOImpl.listarProductoNombre(nombre);
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
    private void ordenarTodoCarrito(ActionEvent event) {
        if (event.getSource().equals(btnOrdenarTodo)) {
            if (areaCarrito.getChildren().size() == 0) {
                JOptionPane.showMessageDialog(null, "Carrito vacío, selecciona algo de la tienda.", "Carro vacío", JOptionPane.ERROR_MESSAGE);
            } else {
                savePedido();
                for (Node n : areaCarrito.getChildren()) {
                    Label idLabel = (Label) n.lookup("#labelIdProducto");
                    ControladorGeneral.findNodesToUnable(idLabel.getText());
                }
                areaCarrito.getChildren().clear();
                lblPrecioTotal.setText("00.00");
            }
        }
    }

    //metodo para guardar todos los pedidos del carrito
    private void savePedido() {
        //aqui debe coger el codigo del configProperties
        //debe restarle la cantidad que se ordena a la tabla productos 
        PedidoDAO pedidoDao = new PedidoDAOImpl();
        Pedido modelPedido = new Pedido();
        modelPedido.setIdPedido(pedidoDao.getNewId() + 1);
        modelPedido.setEstado("Abierto");
        modelPedido.setHora(LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getSecond());
        modelPedido.setFecha(LocalDate.now().toString());
        modelPedido.setIdPc(configProperties.getPropertiesValueId());
        pedidoDao.register(modelPedido);
        saveDetallePedido(modelPedido);
        //idPedidoVerHistoria = modelPedido.getIdPedido();
    }
    
    private void saveDetallePedido(Pedido modelPedido) {
        DetallePedidoDAO detallePedidoDao = new DetallePedidoDAOImpl();
        DetallePedido modelDetallePedido = new DetallePedido();
        productoDAOImpl = new ProductoDAOImpl();
        areaCarrito.getChildren().forEach((t) -> {
            Label labelId, labelPrecio;
            labelId = (Label) t.lookup("#labelIdProducto");
            TextField getTextFieldCantidad = (TextField) t.lookup("#txtCantidad");
            labelPrecio = (Label) t.lookup("#lblPrecio");
            modelDetallePedido.setIdDetallePedido(detallePedidoDao.getNewId(modelDetallePedido));
            modelDetallePedido.setCantidad(Integer.parseInt(getTextFieldCantidad.getText()));
            modelDetallePedido.setSubtotal(Double.parseDouble(labelPrecio.getText()));
            modelDetallePedido.setEstado("Pendiente");
            modelDetallePedido.setIdPedido(modelPedido.getIdPedido());
            modelDetallePedido.setIdProducto(Integer.parseInt(labelId.getText()));
            detallePedidoDao.register(modelDetallePedido);
            productoDAOImpl.updateStockProduct(modelDetallePedido.getIdProducto(), modelDetallePedido.getCantidad());
        });
    }
    
    //metodo para mover la ventana
    private void moveLayout(){
        mainLayout.setOnMousePressed((MouseEvent event) -> {
            x = stageExtends.getX() - event.getScreenX();
            y = stageExtends.getY() - event.getScreenY();
        });
        
        mainLayout.setOnMouseDragged((MouseEvent event) -> {
            stageExtends.setX(event.getScreenX() + x);
            stageExtends.setY(event.getScreenY() + y);
        });
    }
}
