/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.PcCliente;
import DAO.pcClienteDao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Enso
 */
public class AddItemPcsLocalController implements Initializable {
    pcClienteDao pcDao = null;
    PcCliente pc = null;
    
    @FXML
    private JFXButton btnPc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    public void mostrarPcs(PcCliente pcs) throws SQLException {
        btnPc.setText(pcs.getNombre_pc());
    }
    
}
