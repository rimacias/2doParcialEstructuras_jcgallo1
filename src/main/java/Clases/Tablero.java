/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import static Clases.fichas.*;

/**
 *
 * @author Grupo8
 */
public class Tablero implements Comparable<Tablero>{
    
    private final fichas[][] tabla;
    private fichas marcaG;
    private boolean cambiarTurno, gameOver;
    private final int ancho_t = 3;
    private int movimientos = 9;
    private int utilidad;
    private int posibleX ;    //posible posicion futura x,y
    private int posibleY ;


    
    //start an empty board

	public Tablero() {
        tabla = new fichas[ancho_t][ancho_t];
        cambiarTurno = true;
        gameOver = false;
        marcaG = VACIO;
        empezarTablero();
    }

    //Starts the board
    private void empezarTablero() {
        for (int fila = 0; fila < ancho_t; fila++) {
            for (int col = 0; col < ancho_t; col++) {
                tabla[fila][col] = VACIO;
            }
        }
    }
    
    
    
    
    //Method that copies a board into a empty board
    
    public void copiarTablero(Tablero vacio) {
    	
    	for (int fila = 0; fila < ancho_t; fila++) {
            for (int col = 0; col < ancho_t; col++) {
            	if(this.isCeldafichas(fila, col))
                vacio.tabla[fila][col] = this.tabla[fila][col];
            }
        }
    	
    }
    
    
    //Method that copies a board's utility into another board.
    public void copiarUtilidad(Tablero t2) {
    	
    	t2.utilidad = this.utilidad;
    }
    
    //Method that copies a board's possible future move into another board.
    public void copiarMov(Tablero t2) {
    	
    	t2.posibleX = this.posibleX;
    	t2.posibleY = this.posibleY;
    }
    
    
    //Method that shows actual board on the console.
    public void mostrarTablero() {
    	
    	for(int i = 0; i < ancho_t; i++) {
    		System.out.println(this.getFichaT(i, 0).getFicha()+"  "+this.getFichaT(i, 1).getFicha()+"  "
    				+this.getFichaT(i, 2).getFicha());
    	}
    	
    	System.out.println("                  ");
    }

 


    
    //Methot that obtains the utility of  a token on the actual board.
    public int utilidadTablero(fichas fAJugar, fichas fOponente) {
    	
    	int utilidadT;
    	int utilidadFichaAJugar = this.funcionUtilidadDe(fAJugar);
    	int utilidadFichaOponente = this.funcionUtilidadDe(fOponente);
    	
    	utilidadT = utilidadFichaAJugar - utilidadFichaOponente;
    	System.out.println("Utilidad de la figura: "+fAJugar.getFicha()+" en el tablero es: "+ utilidadT);
    	return utilidadT;
    	
    	
    	
    	
    }
    
    
    //Method that obtains the actual utility of a token
    private int funcionUtilidadDe(fichas fACalcular) {
    	
    	char fichaUtil = fACalcular.getFicha();
    	int utilidadTotal;
    	
    	//utilidad de las filas con la ficha a buscar
    	int utilidadFilas = 0;
    	
    	for(int i = 0; i < 3 ; i++) {
    		
    			
    			char fichaj0 = getFichaT(i,0).getFicha();
    			char fichaj1 = getFichaT(i,1).getFicha();
    			char fichaj2 = getFichaT(i,2).getFicha();
    			
    			boolean cd1 = (fichaj0 == fichaUtil || fichaj0 == '-');
    			boolean cd2 = (fichaj1 == fichaUtil || fichaj1 == '-');
    			boolean cd3 = (fichaj2 == fichaUtil || fichaj2 == '-');
    			
    			if(cd1 && cd2 && cd3) {
    				utilidadFilas++;
    			}
    			
    		
    	}
    	
    	//utilidad de las columnas con la ficha a buscar
    	int utilidadColumnas = 0;
    	
    	for(int j = 0; j < 3 ; j++) {
    		
			
			char fichai0 = getFichaT(0,j).getFicha();
			char fichai1 = getFichaT(1,j).getFicha();
			char fichai2 = getFichaT(2,j).getFicha();
			
			boolean cd4 = (fichai0 == fichaUtil) || (fichai0 == '-');
			boolean cd5 = (fichai1 == fichaUtil) || (fichai1 == '-');
			boolean cd6 = (fichai2 == fichaUtil) || (fichai2 == '-');
			
			if(cd4 && cd5 && cd6) {
				utilidadColumnas++;
			}
			
		
    	}
    	
    	
    	//utilidad de las diagonales de  con la ficha a buscar
    	int utilidadDiagonales = 0;
    	
    	char ficha00 = getFichaT(0,0).getFicha();
		char ficha11 = getFichaT(1,1).getFicha();
		char ficha22 = getFichaT(2,2).getFicha();
		char ficha02 = getFichaT(0,2).getFicha();
		char ficha20 = getFichaT(2,0).getFicha();
		
		boolean cd7 = (ficha00 == fichaUtil) || (ficha00 == '-');
		boolean cd8 = (ficha11 == fichaUtil) || (ficha11 == '-');
		boolean cd9 = (ficha22 == fichaUtil) || (ficha22 == '-');
		boolean cd10 = (ficha02 == fichaUtil) || (ficha02 == '-');
		boolean cd11= (ficha20 == fichaUtil) || (ficha20 == '-');
		
		
		if(cd7 && cd8 && cd9) {
			utilidadDiagonales++;
		}
		if(cd10 && cd8 && cd11) {
			utilidadDiagonales++;
		}
		
		
    	
    	utilidadTotal = utilidadFilas + utilidadColumnas + utilidadDiagonales;
    	System.out.println("La figura: "+fichaUtil+"tiene:");
    	System.out.println("Utilidad de filas: "+utilidadFilas);
    	System.out.println("Utilidad de col: "+utilidadColumnas);
    	System.out.println("Utilidad de diago: "+utilidadDiagonales);

    	return utilidadTotal;
    	
    	
    }
    
    
    
    
    
    
    
    //Method that checks if there was a winner after placing a token 
    public boolean winn(int fila, int col) {
        int filaSum = 0;
       
  
        for (int c = 0; c < ancho_t; c++) {
            filaSum += getFichaT(fila, c).getFicha();
        }
        if (calcularGanador(filaSum) != VACIO) {
            System.out.println(marcaG + " VICTORIA DE LA FILA " + fila);
            return true;
        }

        // Check column for winner.
        filaSum = 0;
        for (int r = 0; r < ancho_t; r++) {
            filaSum += getFichaT(r, col).getFicha();
        }
        if (calcularGanador(filaSum) != VACIO) {
            System.out.println(marcaG + " VICTORIA DE LA COLUMNA" + col);
            return true;
        }

        // Top-left to bottom-right diagonal.
        filaSum = 0;
        for (int i = 0; i < ancho_t; i++) {
            filaSum += getFichaT(i, i).getFicha();
        }
        if (calcularGanador(filaSum) != VACIO) {
            System.out.println(marcaG + " GANADOR DE ARRIBA-IZQUIERDA "
                    + "FONDO-DERECHA-DIAGONAL");
            return true;
        }

        // Top-right to bottom-left diagonal.
        filaSum = 0;
        int indexMax = ancho_t - 1;
        for (int i = 0; i <= indexMax; i++) {
            filaSum += getFichaT(i, indexMax - i).getFicha();
        }
        if (calcularGanador(filaSum) != VACIO) {
            System.out.println(marcaG + " GANADOR DE  ARRIBA-DERECHA DE "
                    + "FONDO-IZQUIERDA DIAGONAL.");
            return true;
        }

        if (!sinMovimientos()) {
            gameOver = true;
            System.out.println("SIN MOVIMIENTOS!");
            return true;
        }
        
        return false;
    }

//Method that calculates the winner according to which token has 3 of it type on the board..
    private fichas calcularGanador(int filaSum) {
        int Xgana = X.getFicha() * ancho_t;
        int Ogana = O.getFicha() * ancho_t;
        if (filaSum == Xgana) {
            gameOver = true;
            marcaG = X;
            return X;
        } else if (filaSum == Ogana) {
            gameOver = true;
            marcaG = O;
            return O;
        }
        gameOver = true;
        marcaG = VACIO;
        return VACIO;
    }
    
    
    
    //Method that returns the number of available spaces on the board.
    public int espaciosVacios() {
    	int espacios = 0;
    	for(int i= 0; i<ancho_t;i++) {
    		for(int j=0; j<ancho_t;j++) {
    			if(!this.isCeldafichas(i, j)) {
    				espacios++;
    			}
    			
    		}
    		
    	}
    	
    	return espacios;
    	
    }
    
    
    

    
    public boolean sinMovimientos() {
        return movimientos > 0;
    }
    
    public void restarMovimientos() {
    	this.movimientos = movimientos-1;
    	System.out.println(movimientos);
    }

    public fichas getFichaT(int fila, int col) {
        return tabla[fila][col];
    }

    public boolean isCeldafichas(int fila, int colum) {
        return tabla[fila][colum].isFicha();
    }

    public void setFichaT(int fila, int column, fichas newFicha) {
        this.tabla[fila][column] = newFicha;
        
    }
    
    
    
    public int[][] ubicarPosicion(fichas fichaABuscar){
    	int[][] posiciones = new int[1][2];
    	
    	for (int fila = 0; fila < ancho_t; fila++) {
            for (int col = 0; col < ancho_t; col++) {
            		if(this.getFichaT(fila, col) == fichaABuscar) {
            			posiciones[0][0] = fila;
            			posiciones[0][1] = col;
            		}
            }
        }
    	
    	
    	return posiciones;
    	
    	
    }

    @Override
    public String toString() {
        StringBuilder strBldr = new StringBuilder();
        for (fichas[] fila : tabla) {
            for (fichas tile : fila) {
                strBldr.append(tile).append(' ');
            }
            strBldr.append("\n");
        }
        return strBldr.toString();
    }

    public boolean isCambioTurno() {
        return cambiarTurno;
    }

    public int getAncho() {
        return ancho_t;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public fichas getMarcaG() {
        return marcaG;
    }

	public int getUtilidad() {
		return utilidad;
	}

	public void setUtilidad(int utilidad) {
		this.utilidad = utilidad;
	}
	
	public int getPosibleX() {
		return posibleX;
	}

	public void setPosibleX(int posibleX) {
		this.posibleX = posibleX;
	}
	
	public int getPosibleY() {
		return posibleY;
	}

	public void setPosibleY(int posibleY) {
		this.posibleY = posibleY;
	}

	@Override
	public int compareTo(Tablero o) {
		// TODO Auto-generated method stub
		return this.getUtilidad() - o.getUtilidad();
	}
}

