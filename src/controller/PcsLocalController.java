/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import model.PcCliente;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            llamarPcs();
        } catch (SQLException ex) {
            Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
    
    
}
