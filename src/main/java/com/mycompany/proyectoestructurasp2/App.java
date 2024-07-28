package com.mycompany.proyectoestructurasp2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.Node;


public class App extends Application {

    private static Stage stage;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Menu"), 593, 395);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml +".fxml"));
        return fxmlLoader.load();
    }
    
    public static void main(String[] args) {
        
        launch();
    }
    
    /*metodo que permite cambiar de escenas cambiando
    el tamaño de la ventana y la fija en el centro de la pantalla*/
    
    static void switchScenes(ActionEvent event, String fxml, int SizeX, int SizeY){
        try {
            Parent root = App.loadFXML(fxml);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root, SizeX, SizeY);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.show();
        } catch (IOException e){
            System.out.println("File not found, Error al cargar pantalla");
        }
        
    }
    
}