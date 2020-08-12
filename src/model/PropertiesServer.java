/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileInputStream;
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

    private static final Logger logger = Logger.getLogger(PropertiesServer.class.getName());

    public void getPropertiesValues() throws IOException {
        String propertiesFileName = "config.properties";
        Properties properties = new Properties();
        File file = new File("src/Resources/" + propertiesFileName);
        
        FileInputStream fis = new FileInputStream(file);
        if (fis != null) {
            properties.load(fis);
        }
        
        String namePc = properties.getProperty("namePcCliente");
        String nameServer = properties.getProperty("server");
        System.out.println(namePc + " " + nameServer);
    }
    
    public void setPropertiesValues(){}
}
