/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import javafx.scene.control.TextField;

/**
 *
 * @author Enso
 */
public class ControladorGeneral {
    //metodo para limpiar input
    public static void limpiarInput(TextField[] txt){
        for(TextField t : txt) {
            t.setText("");
        }
    }
}
