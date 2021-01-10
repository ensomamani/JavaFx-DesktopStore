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
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.sql.SQLException;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Enso
 */
public class AddItemPcsLocalController implements Initializable {

    @FXML
    private JFXButton btnPc;
    @FXML
    private FontAwesomeIcon iconText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        
    }    
    public void setNamePcs(PcCliente pc) throws SQLException {
        btnPc.setText(pc.getNombre_pc());
    }

    @FXML
    private void onMouseClickedBtnPc(MouseEvent event) {
        System.out.println("hola mundo");
    }

    
    
}
