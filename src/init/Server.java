package init;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import hilo.LanzarHilo;
import logica.Logica;
import services.GameService;
import services.HtmlService;

public class Server {

	public static void main(String[] args) throws IOException, Exception {
	    ServerSocket socServidor = new ServerSocket(5000);
	    Socket socCliente;
	    Logica logica = new Logica();
	    HtmlService leerHtmls = new HtmlService();
		GameService gameService = new GameService();
	    while (true) {
	      socCliente = socServidor.accept();
	      System.out.println("Atendiendo al cliente ");
	      // Crear un nuevo hilo para manejar la conexión con el cliente
	      LanzarHilo hilo =new LanzarHilo(logica,leerHtmls,gameService,socCliente);
          Thread LanzarHilo = new Thread(hilo);
          LanzarHilo.start();
	      //logica.procesaPeticion(socCliente);
	      //socCliente.close();
	      System.out.println("cliente atendido");
	    }
	  }
}
