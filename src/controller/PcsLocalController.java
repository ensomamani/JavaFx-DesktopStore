/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.CategoriaProductoDAO;
import DAO.ProductoDAO;
import DAO.ProveedorDAO;
import DAO.TipoProductoDAO;
import DAO.pcClienteDao;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.Effect;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Principal;
import animatefx.animation.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import DAO.pcClienteDao;
import Utilidades.ControladorValidaciones;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.converter.LocalDateStringConverter;
import javax.swing.JOptionPane;
import model.CategoriaProducto;
import model.PcCliente;
import model.Producto;
import model.Proveedor;
import model.TipoProducto;
/**
 * FXML Controller class
 *
 * @author Enso
 */
public class PcsLocalController implements Initializable {
    
    ProductoDAO productoDao = new ProductoDAO();
    Producto model;
    private FileChooser fileChooser;
    String pathImage = "";
    @FXML
    private GridPane gridPaneMainMenu;
    @FXML
    private Pane PCsLocal;
    @FXML
    private AnchorPane RegistrarProveedor;
    @FXML
    private JFXButton btnPcsLocal;
    @FXML
    private JFXButton btnAgregarProducto;
    @FXML
    private JFXButton btnRegistrarProveedor;
    @FXML
    private JFXButton btnMinimize;
    @FXML
    private JFXButton btnClose;
    @FXML
    private AnchorPane AgregarProducto;
    @FXML
    private JFXButton btnConsultarVentas;
    @FXML
    private AnchorPane ConsultarVentas;
    @FXML
    private Label lblMainName;
    @FXML
    private JFXButton btnConsultarVentas1;
    @FXML
    private  FlowPane areaPcs;
    @FXML
    private TextField txtCodProd;
    @FXML
    private ComboBox<String> comboTipoProd;
    @FXML
    private ComboBox<String> comboCatProd;
    @FXML
    private TextField txtNombreProd;
    @FXML
    private ComboBox<String> comboProveedor;
    @FXML
    private TextField txtPrecioProd;
    @FXML
    private DatePicker calendarIngreso;
    @FXML
    private TextField txtHoraIngresoProd;
    @FXML
    private TextField txtCantidadProd;
    @FXML
    private JFXButton btnImage;
    @FXML
    private JFXButton btnAgregarProd;
    @FXML
    private FontAwesomeIcon btnEditarProd;
    @FXML
    private JFXButton btnEliminarProd;
    @FXML
    private TextField txtBuscarProd;
    @FXML
    private FontAwesomeIcon iconAviso;
    @FXML
    private FontAwesomeIcon iconAviso1;
    @FXML
    private FontAwesomeIcon iconAviso2;
    @FXML
    private FontAwesomeIcon iconAviso3,iconAviso4,
                            iconAviso5,iconAviso6,iconAviso7,iconAviso8,iconAviso9,iconAviso10;
    @FXML
    private ImageView productImage;
    @FXML
    private TextField txtPesoProd;
    @FXML
    private TableView<String> tableProductos;
 
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            llamarPcs();
            //arrenglar la inserccion de productos referente a la imagen
            insertarProducto();
            llenarComboCategoria();
            setVisibleIconAviso();
            llenarComboTipo();
            llenarComboProveedor();
            calendarIngreso.setValue(LocalDate.now());
            insertarDatosTableView();
            //comboProveedor.getItems().add("hola mundo");
        } catch (SQLException ex) {
            Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//Metodo que permite insertar los nombres de las pcs desde la bd hacia la parte grafica del programa 
    private void llamarPcs() throws SQLException {
        pcClienteDao pcDao = new pcClienteDao();
        ArrayList<PcCliente> pcList = pcDao.consultar();
        Node[] nodes = new Node[pcList.size()];
        
        for (int i = 0; i < nodes.length; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddItemPcsLocal.fxml"));
                AddItemPcsLocalController controller = new AddItemPcsLocalController();
                loader.setController(controller);
                nodes[i] = loader.load();
                areaPcs.getChildren().add(nodes[i]);
                controller.setNamePcs(pcList.get(i));
            } catch (Exception e) {
            } 
        }
        
        
        
    }
    @FXML
    private void btnPcsLocalClicked(MouseEvent event) {
        new ZoomIn(PCsLocal).play();
            PCsLocal.toFront();
            new BounceIn(lblMainName).play();
            System.out.println("hola mundo");
            btnPcsLocal.getStyleClass().add("btn-submenu-sidebar-active"); 
    }

    @FXML
    private void btnAgregarProdClicked(MouseEvent event) {
        AgregarProducto.toFront();
    }

    @FXML
    private void btnRegistrarProvClicked(MouseEvent event) {
            RegistrarProveedor.toFront();
            System.out.println("hola mundo");  
    }

    @FXML
    private void btnMinimizeClicked(MouseEvent event) {
        Principal.stageExtends.setIconified(true);
    }

    @FXML
    private void btnCloseClicked(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void btnConsultarVenClicked(MouseEvent event) {
        ConsultarVentas.toFront();
    }
    
    @FXML
    private void btnImagenClicked(MouseEvent event) {
        //boton de agregar imagen
        //se encarga de elegir la imagen del producto
        if (event.getSource().equals(btnImage)) {
            fileChooser = new FileChooser();
            model = new Producto();
            File file = fileChooser.showOpenDialog(null);
            FileInputStream is = null;
            try {
                is = new FileInputStream(file.getAbsolutePath());
                Image image = new Image(is);
                productImage.setImage(image);
                pathImage = file.getAbsolutePath();
                iconAviso10.setIcon(FontAwesomeIcons.CHECK);
                iconAviso10.getStyleClass().add("icon-check-correct");
                iconAviso10.setVisible(true);
                System.out.println("se agrego imagen");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RuntimeException re){
                System.out.println(re.getMessage());
            } finally {
                if (is != null) {
                    try {
                            is.close();
                    } catch (IOException ex) {
                        Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } 
        } 
    }

    @FXML
    private void btnAgregarProd(MouseEvent event) {
        model = new Producto();
        FontAwesomeIcon[] icon = {iconAviso, iconAviso1, iconAviso2, iconAviso3, iconAviso4,
                                 iconAviso5};
        TextField[] txt = {txtCodProd, txtNombreProd, txtPesoProd, txtPrecioProd, txtCantidadProd, txtHoraIngresoProd};
        ComboBox[] totalCombos = {comboCatProd, comboTipoProd, comboProveedor};
        FontAwesomeIcon[] icon2 = {iconAviso6, iconAviso7, iconAviso8};
        try {
            if (event.getSource().equals(btnAgregarProd)) {
             if (ControladorValidaciones.verificarInputsVacios(txt, icon, totalCombos, icon2) ) {
                 File file = new File(pathImage);
                 //model.setId_Producto(48);
                 model.setNombre_Producto(txtNombreProd.getText());
                 model.setPeso(txtPesoProd.getText());
                 model.setPrecio_venta(Double.valueOf(txtPrecioProd.getText()));
                 model.setStock(Integer.parseInt(txtCantidadProd.getText()));
                 model.setFecha_vencimieto(calendarIngreso.getValue().toString());
                 model.setImagen(ControladorValidaciones.imageByte(file));
                 model.setEstado("ACTIVO");
                 model.setId_categoria(obtenerIdCategoria());
                 model.setId_tipo(obtenerIdTipo());
                 model.setId_proveedor(obtenerIdProveedor());
                 try {
                     if (productImage.getImage() != null) {
                         productoDao.insertarProducto(model);
                         JOptionPane.showMessageDialog(null, "Se agrego el producto correctamente", "OK", JOptionPane.OK_OPTION);
                     } 
                 } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null, "ERROR EN CONEXION A BASE DE DATOS " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                 } 
            } else {
                  JOptionPane.showMessageDialog(null, "Por favor llene todos los espacion en blanco", "Error información",JOptionPane.ERROR_MESSAGE);
            }
        }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    

    
    private void setVisibleIconAviso(){
        FontAwesomeIcon[] icon = {iconAviso, iconAviso1, iconAviso2, iconAviso3, iconAviso4,
                                 iconAviso5, iconAviso6, iconAviso7, iconAviso8, iconAviso9, iconAviso10};
        for (int i = 0; i < icon.length; i++) {
            icon[i].setVisible(false);
        }
    }

    private void btnEditarProducto(MouseEvent event) {
        System.out.println(obtenerIdCategoria());
    }
    
    private void insertarProducto() throws SQLException {
        //model = new Producto(29,"Soda","50g",0.70,10,"05/10/2020","hola","Activo",1,2,2);
        //productoDao.insertarProducto(model);
  
        
    }
     private int obtenerIdCategoria() {
        CategoriaProductoDAO a = new CategoriaProductoDAO();
        int result = 0;
        try {
           result = a.consultarIdCategoria(comboCatProd.getValue()).getIdCategoria();
        } catch (SQLException ex) {
            Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
     private void llenarComboCategoria() {
        CategoriaProductoDAO a = new CategoriaProductoDAO();
        try {
            for(CategoriaProducto c : a.listarCategoria()) {
                comboCatProd.getItems().add(c.getNombre_categoria());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    private int obtenerIdTipo() {
        TipoProductoDAO a = new TipoProductoDAO();
        int result = 0;
        try {
            result = a.consultarIdTipo(comboTipoProd.getValue()).getId_tipo();
        } catch (SQLException ex) {
            Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
     
    private void llenarComboTipo() {
        TipoProductoDAO a = new TipoProductoDAO();
        try {
            for(TipoProducto c : a.consultarTipoProducto()) {
                comboTipoProd.getItems().add(c.getNombre_tipo());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    private int obtenerIdProveedor() {
        int result = 0;
        ProveedorDAO a = new ProveedorDAO();
        try {
            result = a.obtenerIdProveedor(comboProveedor.getValue()).getId_proveedor();
        } catch (SQLException ex) {
            Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    private void llenarComboProveedor() {
        ProveedorDAO a = new ProveedorDAO();
        try {
            for(Proveedor p : a.listarProveedor()) {
                comboProveedor.getItems().add(p.getNombre_proveedor());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void insertarDatosTableView() {
        ProductoDAO dao = new ProductoDAO();
        ProveedorDAO a = new ProveedorDAO();
        // ME QUEDE AQUI EN EL LLENADO DEL TABLEVIEW DE PRODUCTOS
        try {
            for (Producto p : dao.listarProductos()) {
                System.out.println(p.getNombre_Producto()); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        
        
    }
}
