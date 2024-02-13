package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import models.Coordenada;

public class HtmlService {
	
	public String index() {
		String aux = "";
		File archivo = new File("src/html/index.html");

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
            	aux+=linea;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return aux;
	}
	
	public String PaginaNoEncontrada() {
		String aux = "";
		File archivo = new File("src/html/noEncontrado.html");

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
            	aux+=linea;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return aux;
	}
	
	public String formularioGet() {
		String aux = "";
		File archivo = new File("src/html/formularioGet.html");

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
            	aux+=linea;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return aux;
	}
	
	public String formularioPost() {
		String aux = "";
		File archivo = new File("src/html/formularioPost.html");

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
            	aux+=linea;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return aux;
	}
	
	public String respuestaResultado() {
		String aux = "";
		File archivo = new File("src/html/tablero.html");

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
            	aux+=linea;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return aux;
	}

	public String createControlSelect(ArrayList<Integer> listGameFinalized) {
		String aux="";
		for(Integer x: listGameFinalized) {
			aux+="<option value='"+x+"'>partida "+x+"</option>";
		}
		return aux;
	}
	
	public String createTablero() {
		String tablero="";
		for(int x=0;x<10;x++) {
			tablero+="<div class='fila'>";
			for(int y=0;y<10;y++) {
				tablero+="<div class='celda'></div>";
			}
			tablero+="</div>";
		}
		return tablero;
	}
	
	public String createTableroDisparos(ArrayList<Coordenada> coordenadas) {
		String tablero="";
		boolean colocarCelda= true;
		for(int x=0;x<10;x++) {
			tablero+="<div class='fila'>";
			for(int y=0;y<10;y++) {
				for(Coordenada z: coordenadas) {
					if(z.getEjeX() ==x && z.getEjeY() == y && z.getValoDentro() == 'A') {
						tablero+="<div class='celda disparo'></div>";
						colocarCelda = false;
						break;
					}else if(z.getEjeX() ==x && z.getEjeY() == y && z.getValoDentro() == 'T') {
						tablero+="<div class='celda tocado'></div>";
						colocarCelda = false;
						break;
					}						
				}
				if(colocarCelda) {
					tablero+="<div class='celda'></div>";
				}
				colocarCelda = true;
			}
			tablero+="</div>";
		}
		return tablero;
	}
	
	public String createTableroBarcosDisparos(ArrayList<Coordenada> barcos,ArrayList<Coordenada> disparosOponentes) {
		
		//barcos
		char[][] tableroAux = new char[10][10];
		
		for (int i = 0; i < tableroAux.length; i++) {
		    for (int j = 0; j < tableroAux[i].length; j++) {
		        tableroAux[i][j] = 'N';
		    }
		}
		
		for(Coordenada e : barcos) {
			tableroAux[e.getEjeX()][e.getEjeY()] = e.getValoDentro();
		}
		
		for(Coordenada e : disparosOponentes) {
			tableroAux[e.getEjeX()][e.getEjeY()] = e.getValoDentro();
		}
		
		
		
		String tablero="";
		boolean colocarCelda= true;
		for(int x=0;x<10;x++) {
			tablero+="<div class='fila'>";
			for(int y=0;y<10;y++) {
					if(tableroAux[x][y] == 'A') {
						tablero+="<div class='celda disparo'></div>";
					}else if(tableroAux[x][y] == 'T') {
						tablero+="<div class='celda tocado'></div>";
					}else if(tableroAux[x][y] == 'B') {
						tablero+="<div class='celda barco'></div>";
					}else {
						tablero+="<div class='celda'></div>";
					}			
				}

			tablero+="</div>";
			}
		return tablero;
	}
}
