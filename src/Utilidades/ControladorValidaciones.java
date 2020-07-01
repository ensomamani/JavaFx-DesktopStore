/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import controller.PcsLocalController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javax.swing.JOptionPane;

/**
 *
 * @author Enso
 */
public class ControladorValidaciones {
    
    public static boolean verificarInputsVacios(TextField[] txt, FontAwesomeIcon[] icon, ComboBox[] cbo, FontAwesomeIcon[] iconCombo) {
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
        for (int i = 0; i < cbo.length; i++) {
            if (cbo[i].getValue() != null) {
                iconCombo[i].setIcon(FontAwesomeIcons.CHECK);
                    iconCombo[i].getStyleClass().add("icon-check-correct");
                    iconCombo[i].setVisible(true);
                    result = true;
                    System.out.println("he seleccionado " + cbo[i].getValue());
            } else {
                iconCombo[i].setIcon(FontAwesomeIcons.CLOSE);
                iconCombo[i].getStyleClass().add("icon-close-error"); 
                iconCombo[i].setVisible(true);
                result = false;
            }
        }
        return result;
    }
    
    //Convierte la imagen a un array de bytes
    public static byte[] imageByte(File file) {
        byte[] result = new byte[(int)file.length()];
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(result);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Por favor selecciona una imagen");
        } catch (IOException ex) { 
            //Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Por favor selecciona una imagen");
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
    public static Image imageToFile(byte[] imageBytes) {
       return new Image(new ByteArrayInputStream(imageBytes));
    }
}

