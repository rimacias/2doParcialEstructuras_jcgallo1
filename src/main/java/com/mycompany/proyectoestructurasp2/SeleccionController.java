/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoestructurasp2;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;

public class SeleccionController {

    @FXML
    private Label seleccionaLabel, empiezaLabel;

    @FXML
    private GridPane gridSeleccion;

    @FXML
    private GridPane gridEmpezar;

    @FXML
    private RadioButton xRadio;

    @FXML
    private RadioButton oRadio;

    @FXML
    private RadioButton jugadorRadio;

    @FXML
    private RadioButton cpuRadio;

    private static char figuraJugador;
    private static char figuraOponente;

    private static boolean jugadorEmpieza;

    @FXML
    private void switchToJugar(ActionEvent event) throws IOException {
        //App.setRoot("secondary");
        if (!seleccionValida()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Selecciona al menos una opción de cada campo");
            alert.showAndWait();

        } else {
            System.out.println("Figura del jugador: " + figuraJugador);
            System.out.println("Figura del oponente: " + figuraOponente);
            System.out.println("Empieza el jugador?: " + jugadorEmpieza);
            App.switchScenes(event, "Juego", 600, 400);
            //App.setRoot("Juego");
        }
    }

    @FXML
    private void switchToMenu(ActionEvent event) throws IOException {
        //App.setRoot("Menu");
        App.switchScenes(event, "Menu", 600, 400);
    }

    //Acciones que se encargan de deseleccionar la otra opción si selecciona la contraria (ejem: si selecciona 'X' se deselecciona 'O')
    @FXML
    protected void setJRadio() {
        if (!jugadorRadio.isSelected()) {
            jugadorRadio.setSelected(true);
            cpuRadio.setSelected(false);
        } else if (cpuRadio.isSelected()) {
            cpuRadio.setSelected(false);
            jugadorRadio.setSelected(true);
        }
        setJugadorEmpieza(true);
    }

    @FXML
    protected void setCRadio() {
        if (!cpuRadio.isSelected()) {
            cpuRadio.setSelected(true);
            jugadorRadio.setSelected(false);
        } else if (jugadorRadio.isSelected()) {
            jugadorRadio.setSelected(false);
            cpuRadio.setSelected(true);
        }
        setJugadorEmpieza(false);
    }

    @FXML
    protected void setXRadio() {
        if (!xRadio.isSelected()) {
            xRadio.setSelected(true);
            oRadio.setSelected(false);
        } else if (oRadio.isSelected()) {
            oRadio.setSelected(false);
            xRadio.setSelected(true);
        }
        setFiguraJugador('X');
        setFiguraOponente('O');
        
    }

    @FXML
    protected void setORadio() {
        if (!oRadio.isSelected()) {
            oRadio.setSelected(true);
            xRadio.setSelected(false);
        } else if (xRadio.isSelected()) {
            xRadio.setSelected(false);
            oRadio.setSelected(true);
        }
        setFiguraJugador('O');
        setFiguraOponente('X');
    }

    //Permite ver que no haya campos sin seleccionar
    public boolean seleccionValida() {

        return ((jugadorRadio.isSelected() || cpuRadio.isSelected()) && (xRadio.isSelected() || oRadio.isSelected()));

    }

    public static char getFiguraJugador() {
        return figuraJugador;
    }

    public static void setFiguraJugador(char figuraJugador) {
        SeleccionController.figuraJugador = figuraJugador;
    }

    public static boolean isJugadorEmpieza() {
        return jugadorEmpieza;
    }

    public static void setJugadorEmpieza(boolean jugadorEmpieza) {
        SeleccionController.jugadorEmpieza = jugadorEmpieza;
    }

	public static char getFiguraOponente() {
		return figuraOponente;
	}

	public static void setFiguraOponente(char figuraOponente) {
		SeleccionController.figuraOponente = figuraOponente;
	}
    
    
    

}
