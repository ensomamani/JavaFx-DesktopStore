/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 *
 * @author Enso
 */
public class ControladorValidaciones {
    
    public static boolean verificarInputsVacios(TextField[] txt, FontAwesomeIcon[] icon) {
        boolean result = false;
        for (int i = 0; i < txt.length ; i++) {
            if (!txt[i].getText().isEmpty()) {
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
  
}
