/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Enso
 */
public class PropertiesServer {

    Properties properties = new Properties();
    private static final Logger logger = Logger.getLogger(PropertiesServer.class.getName());
    private final String propertiesFileName = "config.properties";
    private final File file = new File("src/Resources/" + propertiesFileName);

    public int getPropertiesValueId() {
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            if (fis != null) {
                properties.load(fis);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PropertiesServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PropertiesServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        int idPc = Integer.parseInt(properties.getProperty("id"));
        return idPc;
    }
    public String getPropertiesValueNamePc() {
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            if (fis != null) {
                properties.load(fis);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PropertiesServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PropertiesServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        String result = properties.getProperty("namePc");
        return result;
    }

    public void setPropertiesValues(String id, String namePc) {
        properties.setProperty("id", id);
        properties.setProperty("namePc", namePc);
        try {
            properties.store(new FileOutputStream(file), "Datos de la configuracion entre pc y servidor completada");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PropertiesServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PropertiesServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
