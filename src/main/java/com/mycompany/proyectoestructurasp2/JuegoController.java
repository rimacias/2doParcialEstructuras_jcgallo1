/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoestructurasp2;

import Clases.Tablero;
import Clases.fichas;
import TDA.Tree;
import TDA.TreeComparator;

import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JuegoController<T>{

    private static char figuraJugador;
    private static char figuraOponente;
    private boolean empiezaJugador;
    
    int[][] espacios_ocupados;
    
    
    @FXML
    private GridPane tablero;
    
    @FXML
    private Label buena;
    
    @FXML
    private Label suerte;

    @FXML
    private void switchToMenu(ActionEvent event) throws IOException {
        //App.setRoot("Menu");
        App.switchScenes(event, "Menu", 600, 400);
    }

    
    @FXML
    private void initialize() {
    	
        int i = 0;
        int j = 0;
        Tablero tableroJuego = new Tablero();
        
        figuraJugador = SeleccionController.getFiguraJugador();
        figuraOponente = SeleccionController.getFiguraOponente();
        empiezaJugador = SeleccionController.isJugadorEmpieza();
        espacios_ocupados = new int[3][3];
        fichas fichaJ = null;
        fichas fichaO = null;
        
        if(figuraJugador == 'X') {
        	fichaJ = fichas.X;
        	fichaO = fichas.O;
        }
        
        if(figuraJugador == 'O') {
        	fichaJ = fichas.O;
        	fichaO = fichas.X;
        }
        
        System.out.println("OYE: LA figura es: " + figuraJugador);
        System.out.println("OYE: LA figura del op es: " + figuraOponente);
        
        tableroJuego.mostrarTablero();
        TreeComparator<Tablero> cmp = Tablero::compareTo;
        
        oponenteJuega(tablero,tableroJuego,espacios_ocupados, fichaO, fichaJ, cmp,empiezaJugador);
        if(!empiezaJugador) {
        	tableroJuego.restarMovimientos();
        }
        
        for (i = 0; i < 3; i++) {
        	
            for (j = 0; j < 3; j++) {
            		
            		final int x;
            		final int y;
            		final fichas fj;
            		final fichas fo;
            		fj = fichaJ;
            		fo = fichaO;
            		x= i;
            		y = j;
            		StackPane root = new StackPane();
            		
            		
            	
            		
                //Image image = new Image("espol/imagenes/O.png",50,50,false,false);
                //ImageView iv = new ImageView(image);
            		
            		root.setOnMouseClicked(e -> {
            		if(espacios_ocupados[y][x] != 1) {
            			
            							root.getChildren().clear();
            							
            							Image image = new Image("imagenes/" + figuraJugador + ".png", 
            							50, 50, false, false);
                    
            							ImageView iv = new ImageView(image);
            							
            							
            							
            							
            							root.getChildren().add(iv);
            							root.setPrefSize(120, 120);
            							root.setAlignment(Pos.CENTER);
            							espacios_ocupados[y][x] = 1;
            							
            							tableroJuego.setFichaT(y, x, fj);
            							tableroJuego.utilidadTablero(fo, fj);
            							tableroJuego.mostrarTablero();
            							tableroJuego.restarMovimientos();
            							boolean cd = tableroJuego.winn(y,x);
            							
            							if(!cd) {
            								int[] posiciones = oponenteJuega(tablero,tableroJuego,espacios_ocupados, fo, fj, cmp,false);
            								//generadorArbol1(tableroJuego,fo,fj,cmp);
            								System.out.println("Tablero resultante: ");
            								tableroJuego.mostrarTablero();
            								tableroJuego.restarMovimientos();
            								espacios_ocupados[posiciones[0]][posiciones[1]] = 1;
            								boolean cd1 = tableroJuego.winn(posiciones[0], posiciones[1]);
            								
            								if(cd1) {
            										mostrarAlerta(Alert.AlertType.WARNING, "Juego Terminado! Ganador: "+tableroJuego.getMarcaG().getFicha());
            										volverMenu();	
            									
            									}
            							
            							}
            							else if(cd) {
            								mostrarAlerta(Alert.AlertType.WARNING, "Juego Terminado! Ganador:"+tableroJuego.getMarcaG().getFicha());
    										volverMenu();	
            								
            							}
            							
            							
                    //mostrarEspacios();
            		}
            		else {
            			
            							Alert alert = new Alert(Alert.AlertType.ERROR);
            							alert.setHeaderText("Error");
            							alert.setContentText("Celda ya ocupada por figura");
            							alert.showAndWait();
            			
            			
            		}
                });
            	
            		
            		
            		tablero.add(root, i, j);
                
                
                
                
                
                
            
            	
            }
        }
       
        
        

    

   }
    
    public int[] oponenteJuega(GridPane tableroVisual,Tablero tableroJuego,int[][] espacios, fichas fj, fichas fo, Comparator<Tree<Tablero>> cmp,boolean empiezaJugador) {
    	int[] posiciones = new int[2];
    	StackPane root2 = new StackPane();
		Tree<Tablero> arbolGenerado = arbolJugador(tableroJuego,fj,fo,cmp);
		Tree<Tablero> proximoTablero = arbolGenerado.getRoot().getChildren().peek();
		
		int proximoX = proximoTablero.getRoot().getContent().getPosibleX();
		int proximoY = proximoTablero.getRoot().getContent().getPosibleY();
		posiciones[0] = proximoX;
		posiciones[1] = proximoY;
		if(!empiezaJugador) {
		tableroJuego.setFichaT(proximoX, proximoY, fj);
		
        Image image2 = new Image("imagenes/" + fj.getFicha() + ".png", 
				50, 50, false, false);

		ImageView iv2 = new ImageView(image2);
		
		root2.getChildren().add(iv2);
		root2.setPrefSize(120, 120);
		root2.setAlignment(Pos.CENTER);
        
		tableroVisual.add(root2,proximoY, proximoX);
		}
		System.out.println("Utilidad del mayor: "+arbolGenerado.getChildrenByUtility().getRoot().getContent().getUtilidad());
		proximoTablero.getRoot().getContent().mostrarTablero();
		System.out.println("Se colocó en : "+"X:"+proximoX+" Y: "+proximoY);
    	
    	PriorityQueue<Tree<Tablero>>  hijosRaiz= arbolGenerado.getRoot().getChildren();
    	
    	
    	Iterator<Tree<Tablero>> it = hijosRaiz.iterator();
    	while(it.hasNext()) {
    		
    			Tree<Tablero> hijo = it.next();
    			System.out.println("Hijos de tablero Actual: "+"con utilidad: "+hijo.getRoot().getContent().getUtilidad());
    			hijo.getRoot().getContent().mostrarTablero();
    			
    		
    		
    		
    		
    		
    	}
    	
    	return posiciones;
    	
    }
    
    
    
    
    
    
    
    //Metodo que muestra los espacios ocupados en la consola representado por 1.
    public void mostrarEspacios() {
    	
    	for(int i = 0; i < 3; i++) {
    		
    		System.out.println(espacios_ocupados[i][0]+"  "+espacios_ocupados[i][1]+ "  "+ 
    				espacios_ocupados[i][2]);
    		
    	}
    	
    	System.out.println("                  ");
    }
    
    //Metodo que crea un arbol del tablero dado de las posibles posibilidades de colocar ficha del siguiente turno 
public void arbolOponente(Tree<Tablero> tableroActual, fichas fichaOponente, fichas fichaJugador, Comparator<Tree<Tablero>> cmp) {
    	
    	Tablero tableroA =  tableroActual.getRoot().getContent();
    	
    	int contador = 0;
    	Tablero tableroCopia= new Tablero();
    	
    	
    	PriorityQueue<Tree<Tablero>> hijos = new PriorityQueue<Tree<Tablero>>(cmp);
    	tableroActual.getRoot().setChildren(hijos);
    	for(int i=0;i<3;i++) {
    		
    		for(int j=0;j<3;j++) {
    			
    			tableroCopia = tableroA;
    			
    			if(!(tableroCopia.isCeldafichas(i, j))) {
    				
    				tableroCopia.setFichaT(i, j, fichaOponente);
    				int utilidad = tableroCopia.utilidadTablero(fichaJugador, fichaOponente);
    				tableroCopia.setUtilidad(utilidad);
    				int movx = i;
    				int movy = j;
    				
    				
    				Tablero t = new Tablero();
					tableroCopia.copiarTablero(t);
					t.setFichaT(i, j, fichaOponente);
					tableroCopia.copiarUtilidad(t);
					t.setPosibleX(movx);
    				t.setPosibleY(movy);
					
					
					
    				
    				tableroActual.getRoot().getChildren().add(new Tree<Tablero>(t));
    				System.out.println("Tablero hijo del segundo turno: ");
    				tableroCopia.mostrarTablero();
    				System.out.println("En x: "+t.getPosibleX()+","+"En y: "+t.getPosibleY());
    				tableroCopia.setFichaT(i, j, fichas.VACIO);
    				contador++;
    				
    				
    			}
    		}
    	}
    	
    	if(contador!=0) {
    	Tree<Tablero> tableroMenor = hijos.peek();
    	Tablero tablerito = tableroMenor.getRoot().getContent();
    	int utilidadMinima = tablerito.getUtilidad();
    	tableroActual.getRoot().getContent().setUtilidad(utilidadMinima);
    	System.out.println("Tablero con menor utilidad de este grupo: "+"con utilidad: "+tablerito.getUtilidad() );
    	tablerito.mostrarTablero();
    	
    	}
    	
    	
    	
    	
    	
    	
    }
    
    public Tree<Tablero> arbolJugador(Tablero tableroJugador,fichas jugador, fichas oponente,Comparator<Tree<Tablero>> cmp) {
    	
    	Tree<Tablero> arbolPrincipal = new Tree<Tablero>(tableroJugador);
    	
    	Tablero copiaTablero = tableroJugador;
    	
    	
    	System.out.println("Raiz: ");
    	copiaTablero.mostrarTablero();
    	Tablero tableroCopia= new Tablero();
    	PriorityQueue<Tree<Tablero>> hijos2 = new PriorityQueue<Tree<Tablero>>(cmp.reversed());
    	arbolPrincipal.getRoot().setChildren(hijos2);
    	for(int i=0;i<3;i++) {
    		
    			for(int j=0;j<3;j++) {
    			
    				tableroCopia = copiaTablero;
    				if(!(tableroCopia.isCeldafichas(i, j))) {
    					
    					
    					tableroCopia.setFichaT(i, j, jugador);
    					System.out.println("Tablero de posible movimiento: ");
    					tableroCopia.mostrarTablero();
    					Tablero t = new Tablero();
    					int movx = i;
        				int movy = j;
        				
        				tableroCopia.copiarMov(t);
    					tableroCopia.copiarTablero(t);
    					t.setPosibleX(movx);
        				t.setPosibleY(movy);
    					
    					t.setFichaT(i, j, jugador);
    					System.out.println("En x: "+t.getPosibleX()+","+"En y: "+t.getPosibleY());
    					
    					arbolOponente(new Tree<Tablero>(t),oponente,jugador,cmp);
    					arbolPrincipal.getRoot().getChildren().add(new Tree<Tablero>(t));
    					tableroCopia.setFichaT(i, j, fichas.VACIO);
    					
    				
    				
    				}
    			}
    	}
    	
    	
    	
    	
    	
    	return arbolPrincipal;
    	
    }


    

	
        
    private void volverMenu() {
    	try {
    		
            Parent root = App.loadFXML("Menu");
            Stage gameStage = (Stage) buena.getScene().getWindow();
            gameStage.close();
            Stage stage  = new Stage();
            Scene scene = new Scene(root, 593, 395);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e){
            System.out.println("File not found, Error al cargar pantalla");
        }
    }
    
    
    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);

        alert.setTitle("Resultado de operacion");
        alert.setHeaderText("Notificacion");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
   
    
    }
