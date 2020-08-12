/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.pcClienteDao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.PropertiesServer;
import model.PcCliente;

/**
 * FXML Controller class
 *
 * @author Enso
 */
public class VentanaClienteConectarController implements Initializable {

    PcCliente pcClienteModel;
    pcClienteDao pcClienteDao = new pcClienteDao();
    @FXML
    private JFXTextField textFieldNamePc;
    @FXML
    private JFXTextField textFieldNameServer;
    @FXML
    private JFXButton buttonCancel;
    @FXML
    private JFXButton buttonConfirm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    @FXML
    private void eventOnCancel(ActionEvent event) {
        PropertiesServer configServer = new PropertiesServer();
        configServer.getPropertiesValues();
    }
    
    @FXML
    private void eventOnAction(ActionEvent event) {
        if (event.getSource().equals(buttonConfirm)) {
            
            String namePc = textFieldNamePc.getText();
            String nameServer = textFieldNameServer.getText();
            if (!namePc.isEmpty() && !nameServer.isEmpty()) {
                if (!pcClienteDao.isNamePc(namePc)) {                  
                    pcClienteModel = new PcCliente();
                    //aqui te quedaste genera el codigo de forma automatica
                    pcClienteModel.setId_pc(pcClienteDao.getLastCodeFromTable(pcClienteModel));
                    pcClienteModel.setNombre_pc(namePc);
                    pcClienteDao.savePcCliente(pcClienteModel);
                    System.out.println("Nueva computadora registrada correctamente");
                    
                }
            }
        }
    }
    
}
