/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import controller.PcsLocalController;
import controller.VentanaClienteController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Enso
 */
public class ControladorGeneral {

    //metodo para limpiar input
    public static void limpiarInput(TextField[] txt) {
        for (TextField t : txt) {
            t.setText("");
        }
    }

    public static boolean verificarInputsVacios(TextField[] txt, FontAwesomeIcon[] icon, ComboBox[] cbo, FontAwesomeIcon[] iconCombo) {
        boolean result = false;
        for (int i = 0; i < txt.length; i++) {
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
        byte[] result = new byte[(int) file.length()];
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            if (fileInputStream != null) {
                fileInputStream.read(result);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Exception: " + ex.getMessage());

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

    //Método que recibe como parametro un array de bytes y devuelve una gráfica de imagenes
    public static Image bytesToImage(byte[] imageBytes) {
        return new Image(new ByteArrayInputStream(imageBytes));
    }

    //metodo para guardar una imagen en el disco local D:
    public static void guardarImagen(File fi) {
        String absoluteUrl = "D:\\img\\" + fi.getName();
        BufferedImage bi;
        try {
            bi = ImageIO.read(fi);
            ImageIO.write(bi, "jpg", new File(absoluteUrl));
            ImageIO.write(bi, "png", new File(absoluteUrl));
            System.out.println(absoluteUrl);
        } catch (IOException ex) {
            Logger.getLogger(PcsLocalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //para reubicar la direccion de una imagen o archivo
    public static File toDiscoLocalD(File fi) {
        return new File("D:\\img\\" + fi.getName());
    }

    public static boolean toInt(String text) {
        if (!text.matches("(^[a-zA-Z]{1})[a-zA-Z]*")) {
            System.out.println("hay letras");
            return true;
        }
        return false;
    }

    public static void findNodesToDisable(String id) {
        Node[] areaProductoItem = new Node[VentanaClienteController.extendsAreaProducto.getChildren().size()];
        for (int i = 0; i < areaProductoItem.length; i++) {
            areaProductoItem[i] = VentanaClienteController.extendsAreaProducto.getChildren().get(i);
            VBox v = (VBox) areaProductoItem[i];
            Label labelIdProducto = (Label) v.lookup("#labelIdProductoItem");
            if (labelIdProducto.getText().equals(id)) {
                Button bAgregarCar = (Button) v.lookup("#btnAgregarCarrito");
                Button bOrdenarProd = (Button) v.lookup("#ordenarProducto");
                Button botonAumentar = (Button) v.lookup("#aumentarProd");
                Button botonDisminuir = (Button) v.lookup("#disminuirProd");
                TextField txt = (TextField) v.lookup("#mostrarCantidad");
                if (bAgregarCar.isDisabled()) {
                    bAgregarCar.setDisable(true);
                    bOrdenarProd.toFront();
                    bOrdenarProd.setDisable(true);
                    botonAumentar.setDisable(true);
                    botonDisminuir.setDisable(true);
                    if (Integer.parseInt(txt.getText()) == 1) {
                        botonDisminuir.setDisable(false);
                    }
                }
            }
        }
    }

    public static void findNodesToUnable(String id) {
        Node[] areaProductoItem = new Node[VentanaClienteController.extendsAreaProducto.getChildren().size()];
        for (int i = 0; i < areaProductoItem.length; i++) {
            areaProductoItem[i] = VentanaClienteController.extendsAreaProducto.getChildren().get(i);
            VBox v = (VBox) areaProductoItem[i];
            Label labelIdProducto = (Label) v.lookup("#labelIdProductoItem");
            if (labelIdProducto.getText().equals(id)) {
                Button bAgregarCar = (Button) v.lookup("#btnAgregarCarrito");
                Button bOrdenarProd = (Button) v.lookup("#ordenarProducto");
                Button botonAumentar = (Button) v.lookup("#aumentarProd");
                Button botonDisminuir = (Button) v.lookup("#disminuirProd");
                TextField txt = (TextField) v.lookup("#mostrarCantidad");
                if (bAgregarCar.isDisabled()) {
                    bAgregarCar.setDisable(false);
                    bOrdenarProd.toFront();
                    bOrdenarProd.setDisable(false);
                    botonAumentar.setDisable(false);
                    botonDisminuir.setDisable(false);
                    if (Integer.parseInt(txt.getText()) == 1) {
                        botonDisminuir.setDisable(true);
                    }
                }
            }
        }
    }
    
    //deshabilita el slot del producto porque su stock es 0 por lo tanto debes de volver a llenar el stok de productos
    public static void setDisableToProductsStockCero(String id, Node n) {
        Label labelIdProducto = (Label) n.lookup("#labelIdProductoItem");
        Label labelNombreProd = (Label) n.lookup("#nombreProducto");
        Button bAgregarCar = (Button) n.lookup("#btnAgregarCarrito");
        Button bOrdenarProd = (Button) n.lookup("#ordenarProducto");
        Button botonAumentar = (Button) n.lookup("#aumentarProd");
        Button botonDisminuir = (Button) n.lookup("#disminuirProd");
        TextField txt = (TextField) n.lookup("#mostrarCantidad");
        HBox hb = (HBox) n.lookup("#checkProductoAgregado");
        if (labelIdProducto.getText().equals(id)) {
            bOrdenarProd.setText("Agotado");
            bOrdenarProd.setDisable(true);
            txt.setDisable(true);
            botonAumentar.setDisable(true);
            botonDisminuir.setDisable(true);
            bAgregarCar.setDisable(true);
            hb.setVisible(false);
            labelNombreProd.setDisable(true);
        }
    }

}
