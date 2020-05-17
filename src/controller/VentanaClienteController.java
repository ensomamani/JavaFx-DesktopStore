/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ProductoDAO;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
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
   
    @FXML
    private JFXButton verPedidosCliente;
    @FXML
    private FlowPane areaProductos;
    @FXML
    private JFXButton iconifiedVenCliente;
    @FXML
    private JFXButton cerrarVenCliente;
    @FXML
    private JFXButton btnBebidas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        llenarProductos();
        
    }    
    
    private void llenarProductos() {
        try {
            Node[] nodes = new Node[3];
            for (int i = 0; i < nodes.length; i++) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddItemVentanaTienda.fxml"));
                nodes[i] = fxmlLoader.load();
                areaProductos.getChildren().add(nodes[i]);
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
    private void btnShowBebidas(MouseEvent event) {
    }
}
