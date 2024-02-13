package hilo;

import java.io.IOException;
import java.net.Socket;

import logica.Logica;
import services.GameService;
import services.HtmlService;

public class LanzarHilo implements Runnable{
	
	Logica logica;
	HtmlService leerHtmls;
	GameService gameService;
	Socket socCliente;
	
	public LanzarHilo(Logica logica,HtmlService leerHtmls,GameService gameService,Socket socCliente) {
		this.logica = logica;
		this.leerHtmls = leerHtmls;
		this.gameService = gameService;
		this.socCliente = socCliente;
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			logica.procesaPeticion(socCliente,leerHtmls,gameService);
		    socCliente.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
