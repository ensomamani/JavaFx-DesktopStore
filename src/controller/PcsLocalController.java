/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.CategoriaProductoDAO;
import DAO.IngresoProductoDAO;
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
import Utilidades.ControladorGeneral;
import Utilidades.ControladorValidaciones;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.CategoriaProducto;
import model.IngresoProducto;
import model.PcCliente;
import model.Producto;
import model.ProductoCatTipProv;
import model.Proveedor;
import model.TipoProducto;

/**
 * FXML Controller class
 *
 * @author Enso
 */
public class PcsLocalController implements Initializable {

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
    private TextField txtBuscarProd;
    @FXML
    private FontAwesomeIcon iconAviso;
    @FXML
    private FontAwesomeIcon iconAviso1;
    @FXML
    private FontAwesomeIcon iconAviso2;
    @FXML
    private FontAwesomeIcon iconAviso3, iconAviso4,
            iconAviso5, iconAviso6, iconAviso7, iconAviso8, iconAviso9, iconAviso10;
    @FXML
    private ImageView productImage;
    @FXML
    private TextField txtPesoProd;
    @FXML
    private TableView<ProductoCatTipProv> tableProductos;
    @FXML
    private JFXButton btnLimpiar;
    @FXML
    private JFXButton btnEditar;
    /**
     * Initializes the controller class.
     */
    ProductoDAO productoDao;
    Producto model;
    IngresoProducto modelIngresoProducto;
    IngresoProductoDAO ingresoProductoDAO;
    FileChooser fileChooser;
    String pathImage = "";
    @FXML
    private JFXButton btnEliminarProd;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            llamarPcs();
            //arrenglar la inserccion de productos referente a la imagen
            llenarComboCategoria();
            setVisibleIconAviso();
            llenarComboTipo();
            llenarComboProveedor();
            calendarIngreso.setValue(LocalDate.now());
            insertarDatosTableView();
            insertarColumnasTableViewProductos();
            codigoProducto();
            txtHoraIngresoProd.setText(LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute());
            txtHoraIngresoProd.setEditable(false);
            setFocus();
        } catch (SQLException ex) {
            Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void setFocus(){
        txtNombreProd.setFocusTraversable(true);
        
        
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
            if (file != null) {
                System.out.println("si contiene una imagen");
                int imagenLength = (int) file.length();
                if (imagenLength < 64000) {
                    System.out.println("Imagen con el peso correcto " + imagenLength);
                    FileInputStream is = null;
                    try {
                        is = new FileInputStream(file);
                        Image image = new Image(is);
                        productImage.setImage(image);
                        pathImage = file.getAbsolutePath();
                        iconAviso10.setIcon(FontAwesomeIcons.CHECK);
                        iconAviso10.getStyleClass().add("icon-check-correct");
                        iconAviso10.setVisible(true);
                        System.out.println("se agrego imagen");
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
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor inserta una imagen menor a 64kb", "Peso de imagen es excesivo", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("No inserto imagen");
            }
        } else {
            System.out.println("hola mundo");
        }
    }

    @FXML
    private void btnAgregarProd(MouseEvent event) throws SQLException {
        model = new Producto();
        String hora = LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute();
        productoDao = new ProductoDAO();
        ingresoProductoDAO = new IngresoProductoDAO();
        FontAwesomeIcon[] icon = {iconAviso, iconAviso1, iconAviso2, iconAviso3, iconAviso4, iconAviso5};
        TextField[] txt = {txtCodProd, txtNombreProd, txtPesoProd, txtPrecioProd, txtCantidadProd, txtHoraIngresoProd};
        ComboBox[] totalCombos = {comboCatProd, comboTipoProd, comboProveedor};
        FontAwesomeIcon[] icon2 = {iconAviso6, iconAviso7, iconAviso8};

        if (event.getSource().equals(btnAgregarProd)) {
            if (ControladorGeneral.verificarInputsVacios(txt, icon, totalCombos, icon2)) {
                if (productoDao.existeProductoBool(txtNombreProd.getText())) {
                    if (ControladorGeneral.verificarInputsVacios(txt, icon, totalCombos, icon2)) {
                        Producto p = productoDao.existeProducto(txtNombreProd.getText());
                        modelIngresoProducto = new IngresoProducto(0, LocalDate.now().toString(), hora, Integer.parseInt(txtCantidadProd.getText()), "ACTIVO", 1, p.getId_Producto());
                        ingresoProductoDAO.insertarIngresoProducto(modelIngresoProducto);
                        //System.out.println("Confirmo aqui que el producto si existe " + p.getId_Producto() + " " + p.getNombre_Producto());
                        int sumatotal = p.getStock() + Integer.parseInt(txtCantidadProd.getText());
                        //System.out.println("suma total con la anterior: " + sumatotal + " stock antiguo: " + p.getStock());
                        productoDao.actualizarStockProducto(p.getId_Producto(), sumatotal);
                        JOptionPane.showMessageDialog(null, "¡Se agregó el producto correctamente!.", "Exito!", JOptionPane.OK_OPTION);
                        insertarDatosTableView();
                    }
                } else {
                    try {
                        File path = new File(pathImage);
                        if (productImage.getImage() != null) {
                            model = new Producto(productoDao.getUltimoCodigo(), txtNombreProd.getText(), txtPesoProd.getText(),
                                    Double.valueOf(txtPrecioProd.getText()), Integer.parseInt(txtCantidadProd.getText()),
                                    calendarIngreso.getValue().toString(), ControladorGeneral.imageByte(path),
                                    "ACTIVO", obtenerIdCategoria(), obtenerIdTipo(), obtenerIdProveedor());
                            productoDao.insertarProducto(model);

                            modelIngresoProducto = new IngresoProducto(0, LocalDate.now().toString(), hora,
                                    Integer.parseInt(txtCantidadProd.getText()), "ACTIVO", 1, Integer.parseInt(txtCodProd.getText()));

                            ingresoProductoDAO.insertarIngresoProducto(modelIngresoProducto);
                            codigoProducto();
                            limpiarInputs();
                            insertarDatosTableView();
                            JOptionPane.showMessageDialog(null, "Se agrego el producto correctamente", "OK", JOptionPane.OK_OPTION, null);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "ERROR EN CONEXION A BASE DE DATOS " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor llene todos los espacion en blanco", "Error información", JOptionPane.ERROR_MESSAGE);
                codigoProducto();
            }
        }
    }

    private void setVisibleIconAviso() {
        FontAwesomeIcon[] icon = {iconAviso, iconAviso1, iconAviso2, iconAviso3, iconAviso4,
            iconAviso5, iconAviso6, iconAviso7, iconAviso8, iconAviso9, iconAviso10};
        for (int i = 0; i < icon.length; i++) {
            icon[i].setVisible(false);
        }
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
            for (CategoriaProducto c : a.listarCategoria()) {
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
            for (TipoProducto c : a.consultarTipoProducto()) {
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
            for (Proveedor p : a.listarProveedor()) {
                comboProveedor.getItems().add(p.getNombre_proveedor());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ObservableList<ProductoCatTipProv> getProducto() {
        ObservableList<ProductoCatTipProv> data = FXCollections.observableArrayList();
        productoDao = new ProductoDAO();
        try {
            for (ProductoCatTipProv p : productoDao.listarProductos()) {
                data.add(new ProductoCatTipProv(p.getId(), p.getNombreProducto(), p.getPeso(), p.getPrecioVenta(),
                        p.getStock(), p.getNombreCategoria(), p.getTipoProducto(), p.getNombreProveedor(), p.getFechaVencimiento(), p.getImagen()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    private void insertarColumnasTableViewProductos() {
        TableColumn<ProductoCatTipProv, String> codigoColumn = new TableColumn<>("Codigo");
        codigoColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<ProductoCatTipProv, String> nombreColumn = new TableColumn<>("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));

        TableColumn<ProductoCatTipProv, String> pesoColumn = new TableColumn<>("Peso");
        pesoColumn.setCellValueFactory(new PropertyValueFactory<>("peso"));

        TableColumn<ProductoCatTipProv, String> precioColumn = new TableColumn<>("Precio");
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));

        TableColumn<ProductoCatTipProv, String> stockColumn = new TableColumn<>("Stock");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        TableColumn<ProductoCatTipProv, String> CategoriaColumn = new TableColumn<>("Categoria");
        CategoriaColumn.setCellValueFactory(new PropertyValueFactory<>("nombreCategoria"));

        TableColumn<ProductoCatTipProv, String> tipoColumn = new TableColumn<>("Tipo");
        tipoColumn.setCellValueFactory(new PropertyValueFactory<>("tipoProducto"));

        TableColumn<ProductoCatTipProv, String> proveedorColumn = new TableColumn<>("Proveedor");
        proveedorColumn.setCellValueFactory(new PropertyValueFactory<>("nombreProveedor"));

        TableColumn<ProductoCatTipProv, String> fechaVencimientoColumn = new TableColumn<>("Fecha Vencimiento");
        fechaVencimientoColumn.setCellValueFactory(new PropertyValueFactory<>("fechaVencimiento"));

//        TableColumn<ProductoCatTipProv, String> imagenColumn = new TableColumn<>("Nombre Imagen");
//        imagenColumn.setCellValueFactory(new PropertyValueFactory<>("iimagen"));
        tableProductos.getColumns().addAll(codigoColumn, nombreColumn, pesoColumn, precioColumn, stockColumn,
                CategoriaColumn, tipoColumn, proveedorColumn, fechaVencimientoColumn);
    }

    private void insertarDatosTableView() {
        tableProductos.setItems(getProducto());
    }

    @FXML
    private void tProductosClick(MouseEvent event) {
        if (event.getSource().equals(tableProductos)) {
            if (tableProductos.getSelectionModel().getSelectedItem() != null) {
                ProductoCatTipProv p = tableProductos.getSelectionModel().getSelectedItem();
                txtCodProd.setText("" + p.getId());
                txtNombreProd.setText("" + p.getNombreProducto());
                txtPesoProd.setText("" + p.getPeso());
                txtPrecioProd.setText("" + p.getPrecioVenta());
                txtCantidadProd.setText("" + p.getStock());
                comboCatProd.setValue(p.getNombreCategoria());
                comboTipoProd.setValue(p.getTipoProducto());
                comboProveedor.setValue(p.getNombreProveedor());
                calendarIngreso.setValue(LocalDate.parse(p.getFechaVencimiento()));
                productImage.setImage(ControladorGeneral.bytesToImage(p.getImagen()));
            } else {
                System.out.println("No seleccionaste nada de la tabla");
            }
        }
    }

    @FXML
    private void btnLimpiarClick(MouseEvent event) {
        if (event.getSource().equals(btnLimpiar)) {
            limpiarInputs();
        }
    }

    //listo el codigo para el input
    private void codigoProducto() throws SQLException {
        txtCodProd.setText("" + productoDao.getUltimoCodigo());
    }

    private void limpiarInputs() {
        try {
            txtCodProd.setText("" + productoDao.getUltimoCodigo());
            txtNombreProd.setText("");
            txtPesoProd.setText("");
            txtPrecioProd.setText("");
            txtCantidadProd.setText("");
            calendarIngreso.setValue(LocalDate.now());
            productImage.setImage(null);
            comboCatProd.getSelectionModel().clearSelection();
            comboProveedor.getSelectionModel().clearSelection();
            comboTipoProd.getSelectionModel().clearSelection();
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    @FXML
    private void btnEditarClicked(MouseEvent event) {
        if (event.getSource().equals(btnEditar)) {
            CategoriaProductoDAO cDao = new CategoriaProductoDAO();
            TipoProductoDAO tDao = new TipoProductoDAO();
            ProveedorDAO pDao = new ProveedorDAO();
            File f = new File(pathImage);
            FontAwesomeIcon[] icon = {iconAviso, iconAviso1, iconAviso2, iconAviso3, iconAviso4, iconAviso5};
            TextField[] txt = {txtCodProd, txtNombreProd, txtPesoProd, txtPrecioProd, txtCantidadProd, txtHoraIngresoProd};
            ComboBox[] totalCombos = {comboCatProd, comboTipoProd, comboProveedor};
            FontAwesomeIcon[] icon2 = {iconAviso6, iconAviso7, iconAviso8};
            try {
                if (ControladorGeneral.verificarInputsVacios(txt, icon, totalCombos, icon2)) {
                    CategoriaProducto c = cDao.consultarIdCategoria(comboCatProd.getValue());
                    TipoProducto t = tDao.consultarIdTipo(comboTipoProd.getValue());
                    Proveedor p = pDao.obtenerIdProveedor(comboProveedor.getValue());
                    model = new Producto(Integer.parseInt(txtCodProd.getText()), txtNombreProd.getText(), txtPesoProd.getText(), Double.parseDouble(txtPrecioProd.getText()), Integer.parseInt(txtCantidadProd.getText()), calendarIngreso.getValue().toString(), ControladorGeneral.imageByte(f), c.getIdCategoria(), t.getId_tipo(), p.getId_proveedor());
                    ProductoDAO pdao = new ProductoDAO();
                    pdao.actualizarProducto(model);
                    insertarDatosTableView();
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor llene todos los espacion en blanco", "Error información", JOptionPane.ERROR_MESSAGE);
                    codigoProducto();
                }

            } catch (SQLException ex) {
                Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(f);

        }
    }

    @FXML
    private void btnEliiminarClicked(MouseEvent event) {
        if (event.getSource().equals(btnEliminarProd)) {
            FontAwesomeIcon[] icon = {iconAviso, iconAviso1, iconAviso2, iconAviso3, iconAviso4, iconAviso5};
            TextField[] txt = {txtCodProd, txtNombreProd, txtPesoProd, txtPrecioProd, txtCantidadProd, txtHoraIngresoProd};
            ComboBox[] totalCombos = {comboCatProd, comboTipoProd, comboProveedor};
            FontAwesomeIcon[] icon2 = {iconAviso6, iconAviso7, iconAviso8};

            if (ControladorGeneral.verificarInputsVacios(txt, icon, totalCombos, icon)) {
                int result = JOptionPane.showConfirmDialog(null, "¿Esta segur@ de eliminar este producto?", "Eliminar producto", JOptionPane.OK_CANCEL_OPTION);;
                if (result == 0) {
                    System.out.println(result);
                    ingresoProductoDAO = new IngresoProductoDAO();
                    modelIngresoProducto = new IngresoProducto();
                    modelIngresoProducto.setId_producto(Integer.parseInt(txtCodProd.getText()));
                    ingresoProductoDAO.eliminarIngresoProducto(modelIngresoProducto);
                    model = new Producto();
                    productoDao = new ProductoDAO();
                    model.setId_Producto(Integer.parseInt(txtCodProd.getText()));
                    productoDao.eliminarProducto(model);
                    insertarDatosTableView();
                    limpiarInputs();
                }

            }
        }
    }

    @FXML
    private void buscarProducto(KeyEvent event) {
       tableProductos.setItems(buscarProductos());
    }
    private ObservableList<ProductoCatTipProv> buscarProductos() {
        productoDao = new ProductoDAO();
        model = new Producto();
        ObservableList<ProductoCatTipProv> data = FXCollections.observableArrayList();
        String buscar = txtBuscarProd.getText().toLowerCase();
        try {
            for(ProductoCatTipProv p : productoDao.listarProductosPorNombre(buscar)) {
                data.add(new ProductoCatTipProv(p.getId(), p.getNombreProducto(), p.getPeso(), p.getPrecioVenta(), p.getStock(), p.getNombreCategoria(), p.getTipoProducto(), p.getNombreProveedor(), p.getFechaVencimiento(), p.getImagen()));              
            }
        } catch (SQLException ex) {
            Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

}
