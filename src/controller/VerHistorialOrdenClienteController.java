/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.PedidoDAOImpl;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import DTO.PedidoDTO;


/**
 * FXML Controller class
 *
 * @author Enso
 */
public class VerHistorialOrdenClienteController implements Initializable {

    Stage stage;
    double x,y;
    FXMLLoader fxmlLoader;
    @FXML
    private GridPane gridPaneMainMenu;
    @FXML
    private JFXButton btnMinimize;
    @FXML
    private JFXButton btnClose;
    @FXML
    private FlowPane areaOrdenCliente;
    @FXML
    private Pane mainLayout;
    @FXML
    private Label labelIdPedido;

    double precioTotal = 0;
    @FXML
    private Label labelPrecioTotal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        callItemViewOrders();
        moveLayout();
        labelIdPedido.setText(""+VentanaClienteController.idPedidoVerHistoria);
        areaOrdenCliente.getChildren().forEach((t) -> {
            GridPane gp = (GridPane) t;
            Label labelPrice = (Label) gp.getChildren().get(3);
            precioTotal += Double.parseDouble(labelPrice.getText());
            labelPrecioTotal.setText(String.format("%.2f", precioTotal));
           
        });
    }

    @FXML
    private void handleAction(ActionEvent event) {
        stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleMinimize(ActionEvent event) {
        stage = (Stage) btnMinimize.getScene().getWindow();
        stage.setIconified(true);
    }

    private void callItemViewOrders() {
        PedidoDAOImpl pedidoDaoImpl = new PedidoDAOImpl();
        List<PedidoDTO> data = pedidoDaoImpl.searchPedidosStateAndNamePc();
        Node[] node = new Node[data.size()];
        System.out.println(node.length);
        try {
            for (int i = 0; i < node.length; i++) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddItemVerHistorialCliente.fxml"));
                AddItemVerHistorialClienteController controller = new AddItemVerHistorialClienteController();
                fxmlLoader.setController(controller);
                node[i] = fxmlLoader.load();
                areaOrdenCliente.getChildren().add(node[i]);
                controller.setHistorialClientePedido(data.get(i));
            }
        } catch (IOException ex) {
            Logger.getLogger(VerHistorialOrdenClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void moveLayout() {      
        gridPaneMainMenu.setOnMousePressed((MouseEvent event) -> {
            stage = (Stage) mainLayout.getScene().getWindow();
            x = stage.getX() - event.getScreenX();
            y = stage.getY() - event.getScreenY();
        });
        
        gridPaneMainMenu.setOnMouseDragged((MouseEvent event) -> {
            stage = (Stage) mainLayout.getScene().getWindow();
            stage.setX(event.getScreenX() + x);
            stage.setY(event.getScreenY() + y);
        });
    }
    private void idPedido(int id) {
        System.out.println(id);
    }
    public void idPedido(String id) {
        labelIdPedido.setText(id);
    }
}
