/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ProductoDAO;
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
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import model.PcCliente;
import model.Producto;
/**
 * FXML Controller class
 *
 * @author Enso
 */
public class PcsLocalController implements Initializable {
    
    ProductoDAO productoDao = new ProductoDAO();
    Producto model;
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
    private FlowPane areaPcs;
    @FXML
    private TextField txtCodProd;
    @FXML
    private ComboBox<?> comboTipoProd;
    @FXML
    private ComboBox<String> comboCatProd;
    @FXML
    private TextField txtNombreProd;
    @FXML
    private ComboBox<?> comboProveedor;
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
    private JFXButton btnEditarProd;
    @FXML
    private JFXButton btnEliminarProd;
    @FXML
    private TextField txtBuscarProd;
    @FXML
    private TableView<?> tableProductos;
    @FXML
    private FontAwesomeIcon iconAviso,iconAviso1,iconAviso2,iconAviso3,iconAviso4,
                            iconAviso5,iconAviso6,iconAviso7,iconAviso8,iconAviso9;
    @FXML
    private ImageView productImage;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            llamarPcs();
            //arrenglar la inserccion de productos referente a la imagen
            //insertarProducto();
            llenarCombo();
            insertarProducto();
            setVisibleIconAviso();
        } catch (SQLException ex) {
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
    
    private void insertarProducto() throws SQLException {
        //model = new Producto(29,"Soda","50g",0.70,10,"05/10/2020","hola","Activo",1,2,2);
        //productoDao.insertarProducto(model);
  
        
    }
     private void llenarCombo() {
        comboCatProd.getItems().addAll("hola");
        
        
        
    }
    private FileChooser fileChooser;
    String pathImage = "";
    @FXML
    private void btnImagenClicked(MouseEvent event) {
        fileChooser = new FileChooser();
        model = new Producto();
        File file = fileChooser.showOpenDialog(null);
        FileInputStream is = null;
        try {
            is = new FileInputStream(file.getAbsolutePath());
            Image image = new Image(is);
            productImage.setImage(image);
            pathImage = file.getAbsolutePath();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void btnAgregarProd(MouseEvent event) {
        model = new Producto();
        if (event.getSource().equals(btnAgregarProd)) {
             if (verificarInputsVacios()) {
                 File file = new File(pathImage);
                 model.setId_Producto(37);
                 model.setNombre_Producto(txtNombreProd.getText());
                 model.setPrecio_venta(Double.valueOf(txtPrecioProd.getText()));
                 model.setStock(Integer.valueOf(txtCantidadProd.getText()));
          
                 model.setFecha_vencimieto("2020-12-12");
                 model.setImagen(imageByte(file));
                 model.setEstado("ACTIVO");
                 model.setId_categoria(1);
                 model.setId_tipo(1);
                 model.setId_proveedor(1);
                 try {
                     productoDao.insertarProducto(model);
                 } catch (SQLException ex) {
                     Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             
                 
                
            }
            
        }
    }
    private byte[] imageByte(File file) {
        byte[] result = new byte[(int)file.length()];
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(result);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) { 
            Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return result;
    }
    private boolean verificarInputsVacios() {
        boolean result = false;
        TextField[] txtText = {txtCodProd, txtNombreProd, txtPrecioProd, txtCantidadProd, txtHoraIngresoProd};
        FontAwesomeIcon[] icon = {iconAviso, iconAviso1, iconAviso2, iconAviso3, iconAviso4};

        for (int i = 0; i < txtText.length; i++) {
            if (!txtText[i].getText().isEmpty()) {
                icon[i].setIcon(FontAwesomeIcons.CHECK);
                icon[i].getStyleClass().add("icon-check-correct");
                icon[i].setVisible(true);
                result = true;
            } else {
                icon[i].setIcon(FontAwesomeIcons.CLOSE);
                icon[i].getStyleClass().add("icon-close-error"); 
                icon[i].setVisible(true);
                result = false;
            }
        }
        if (result == false) {
            JOptionPane.showMessageDialog(null, "Por favor llene todos los espacion en blanco", "Error información",JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }
    private void setVisibleIconAviso(){
        FontAwesomeIcon[] icon = {iconAviso, iconAviso1, iconAviso2, iconAviso3, iconAviso4,
                                 iconAviso5, iconAviso6, iconAviso7, iconAviso8, iconAviso9};
        for (int i = 0; i < icon.length; i++) {
            icon[i].setVisible(false);
        }
    }
}
