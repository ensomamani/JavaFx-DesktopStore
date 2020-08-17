/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.PropertiesServer;

/**
 *
 * @author Enso
 */
public class Principal extends Application {

    public static Stage stageExtends;

    @Override
    public void start(Stage primaryStage) throws Exception {
        PropertiesServer configServer = new PropertiesServer();
        String namePc = configServer.getPropertiesValues();
        if (!namePc.isEmpty()) {
            initWindow("VentanaCliente.fxml", primaryStage);
        } else {
            initWindow("VentanaClienteConectar.fxml", primaryStage);

        }
    }

    private void initWindow(String nameFxml, Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(nameFxml));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        stageExtends = primaryStage;
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
