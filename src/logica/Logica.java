package logica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import models.Coordenada;
import models.User;
import services.GameService;
import services.HtmlService;

public class Logica {
	
	//HtmlService leerHtmls = new HtmlService();
	//GameService gameService = new GameService();
	
	public void procesaPeticion(Socket socketCliente,HtmlService leerHtmls,GameService gameService) throws IOException {
	    //variables locales
	    String peticion = "";
	    String[] tipo= null;
	    String numeroPost= "";
	    //Flujo de entrada
	    InputStreamReader inSR = new InputStreamReader(
	            socketCliente.getInputStream());
	    //espacio en memoria para la entrada de peticiones
	    BufferedReader bufLeer = new BufferedReader(inSR);

	    //objeto de java.io que entre otras características, permite escribir 
	    //'línea a línea' en un flujo de salida
	    PrintWriter printWriter = new PrintWriter(
	            socketCliente.getOutputStream(), true);

	    //mensaje petición cliente
	    int aux=0;
	    String url= "";
	    String contenLen= "";
	    /*while ((peticion = bufLeer.readLine()) != null) {
	    	
	    	if(aux ==0) {
	    		url = peticion;
	    		tipo = peticion.split(" ");
	    		aux++;
	    	}
	    System.out.println(peticion);
	    if(peticion.contains("Content-Length")) {
	    	contenLen = peticion;
	    }
	    if(peticion.contains("GET")) {
	    	break;
	    }
	    if(peticion.contains("Accept-Language")) {
	    	break;
	    }	    	    
	    };*/
	    
	    while (true) {
		    peticion = bufLeer.readLine();
		    
		    // Verificar si la línea es nula o si su longitud es cero
		    if (peticion == null || peticion.length() == 0) {
		        // Salir del bucle si no hay más datos para leer
		        break;
		    }

		    // Procesar cada línea de la petición

		    // Si es la primera línea, dividirla para obtener el método y la URL
		    if (aux == 0) {
		        url = peticion;
		        tipo = peticion.split(" ");
		        aux++;
		    }

		    System.out.println(peticion);

		    // Si la línea contiene el encabezado "Content-Length", guardarlo
		    if (peticion.contains("Content-Length")) {
		        contenLen = peticion;
		    }

		    // Si la línea contiene el método "GET", salir del bucle
		    if (peticion.contains("GET")) {
		        break;
		    }

		    // Otras operaciones que puedan realizar en función de los encabezados
		}
	    
	    if(tipo[0].equals("POST")) {
	    	int caracter;
	    	String cadena = "";
	    	String[] data =contenLen.split(" ");
    		//caracter = bufLeer.read();
    		//caracter = bufLeer.read();
	    	for(int x=0;x<Integer.parseInt(data[1]);x++) {
	    		caracter = bufLeer.read();
	    		char charActual = (char) caracter;
	    		//System.out.println(charActual);
                cadena+=charActual;
	    	}
           /* while ((caracter = bufLeer.read()) != -1) {
                char charActual = (char) caracter;
                System.out.print(charActual);
                cadena+=charActual;
            }*/
            System.out.println(cadena);
            String[] datos = cadena.split("=");
            numeroPost = datos[1];
            
	    }
	    

	    //para compactar la petición y facilitar así su análisis, suprimimos todos 
	    //los espacios en blanco que contenga
	    url = url.replaceAll(" ", "");

	    //si realmente se trata de una petición 'GET' (que es la única que vamos a
	    //implementar en nuestro Servidor)
	    //if (url.startsWith("GET")) {
	      //extrae la subcadena entre 'GET' y 'HTTP/1.1'
	    	url = url.substring(3, url.lastIndexOf("HTTP"));

	      //los demas
	      //String[] divTwo = peticion.split("?");
	      //si corresponde a la página de inicio
	      if (url.length() == 0 || url.equals("/")) {
	    	  String answer = leerHtmls.index();
	    	  printWriter.println("HTTP/1.1 200 OK");
	    	  printWriter.println("Content-Type:text/html;charset=UTF-8");
	    	  printWriter.println("Content-Length: " + answer.length() + 1);
	          printWriter.println("\n");
	    	  printWriter.println(answer);
	      }
	      else if (url.equals("/formularioGet")) {
	    	  String controlSelect = leerHtmls.createControlSelect(gameService.listGameFinalized());
	    	  String answer = leerHtmls.formularioGet();
	    	  String answerCheck = answer.replaceAll("--controlSelect--", controlSelect);
	    	  printWriter.println("HTTP/1.1 200 OK");
	    	  printWriter.println("Content-Type:text/html;charset=UTF-8");
	    	  printWriter.println("Content-Length: " + answerCheck.length() + 1);
	          printWriter.println("\n");
	    	  printWriter.println(answerCheck);
	      }else if (url.equals("/formularioPost")) {
	    	  String controlSelect = leerHtmls.createControlSelect(gameService.listGameFinalized());
	    	  String answer = leerHtmls.formularioPost();
	    	  String answerCheck = answer.replaceAll("--controlSelect--", controlSelect);
	    	  printWriter.println("HTTP/1.1 200 OK");
	    	  printWriter.println("Content-Type:text/html;charset=UTF-8");
	    	  printWriter.println("Content-Length: " + answerCheck.length() + 1);
	          printWriter.println("\n");
	    	  printWriter.println(answerCheck);
	      }else if (url.contains("resultado")) {
	    	  System.out.println("url: "+ url);
	    	  String num = "";
	    	  if(tipo[0].equals("POST")){
	    		  num = numeroPost;
	    	  }else {
	    		  String[] dataTwo = url.split("=");
		    	  System.out.println("numero a buscar : "+ dataTwo[1]);
		    	  num = dataTwo[1];
	    	  }
	    	  //jugadores
	    	  ArrayList<User> jugadores = gameService.listJugadores(num);
	    	  
	    	  //barcos y disparos 1
	    	  ArrayList<Coordenada> barcosJugador1 = gameService.bringBoard(jugadores.get(0).getUser(), num);
	    	  ArrayList<Coordenada> disparosJugador1 = gameService.myShots(num,jugadores.get(0).getUser());
	    
	    	//barcos y disparos 2
	    	  ArrayList<Coordenada> barcosJugador2 = gameService.bringBoard(jugadores.get(1).getUser(), num);
	    	  ArrayList<Coordenada> disparosJugador2 = gameService.myShots(num,jugadores.get(1).getUser());
	    	  
	    	  //resultados:
	    	  String ganador= "";
	    	  String perdedor = "";
	    	  for(User x: jugadores) {
	    		  if(x.getWinner().equals("YES")) {
	    			  ganador = x.getUser();
	    		  }else {
	    			  perdedor = x.getUser();
	    		  }
	    	  }
	    	  
	    	  //pintar tableros disparos
	    	  String tableroDisparosJugador1 = leerHtmls.createTableroDisparos(disparosJugador1);
	    	  String tableroDisparosJugador2 = leerHtmls.createTableroDisparos(disparosJugador2);
	    	  
	    	  //pintar tablero barcos y disparos
	    	  String tableroBarcosJugador1 = leerHtmls.createTableroBarcosDisparos(barcosJugador1, disparosJugador2);
	    	  String tableroBarcosJugador2 = leerHtmls.createTableroBarcosDisparos(barcosJugador2, disparosJugador1);
	    	  
	    	  
	    	  //prueba de muestra:
	    	  String envio = leerHtmls.respuestaResultado();
	    	  String enviocheck = envio.replaceAll("--tablero1--", tableroDisparosJugador1)
	    			  .replaceAll("--tablero2--", tableroBarcosJugador1)
	    			  .replaceAll("--tablero3--", tableroDisparosJugador2)
	    			  .replaceAll("--tablero4--", tableroBarcosJugador2)
	    			  .replaceAll("--jugador1--", jugadores.get(0).getUser())
	    			  .replaceAll("--ganador--", ganador)
	    			  .replaceAll("--perdedor--", perdedor)
	    			  .replaceAll("--ganadorId--", ganador)
	    			  .replaceAll("--jugador2--", jugadores.get(1).getUser());
	    	  printWriter.println("HTTP/1.1 200 OK");
	    	  printWriter.println("Content-Type:text/html;charset=UTF-8");
	    	  printWriter.println("Content-Length: " + enviocheck.length() + 1);
	          printWriter.println("\n");
	    	  printWriter.println(enviocheck);
	    	  
	    	  
	      } else {
	    	  String answer = leerHtmls.PaginaNoEncontrada();
	    	  printWriter.println("HTTP/1.1 200 OK");
	    	  printWriter.println("Content-Type:text/html;charset=UTF-8");
	    	  printWriter.println("Content-Length: " + answer.length() + 1);
	          printWriter.println("\n");
	    	  printWriter.println(answer);
	      }
	    
	    //}
	  }	
	
}
