package models;

import java.io.Serializable;

public class Coordenada implements Serializable{
	
	int ejeX;
	int ejeY;
	int idPartida;
	char valoDentro;
	String userNick;
	
	/**
	 * 
	 * @param ejeX
	 * @param ejeY
	 * @param valoDentro
	 */
	public Coordenada(int ejeX, int ejeY, char valoDentro) {
		this.ejeX = ejeX;
		this.ejeY = ejeY;
		this.valoDentro = valoDentro;
	}
	
	/**
	 * 
	 * @param ejeX
	 * @param ejeY
	 * @param valoDentro
	 * @param idPartida
	 */
	public Coordenada(int ejeX, int ejeY, char valoDentro, int idPartida) {
		this.ejeX = ejeX;
		this.ejeY = ejeY;
		this.valoDentro = valoDentro;
		this.idPartida = idPartida;
	}
	
	/**
	 * 
	 * @param ejeX
	 * @param ejeY
	 */
	public Coordenada(int ejeX, int ejeY) {
		this.ejeX = ejeX;
		this.ejeY = ejeY;
		this.valoDentro = '0';
	}
	
	

	/**
	 * 
	 * @param ejeX
	 * @param ejeY
	 * @param valoDentro
	 * @param userNick
	 */
	public Coordenada(int ejeX, int ejeY, char valoDentro, String userNick) {
		super();
		this.ejeX = ejeX;
		this.ejeY = ejeY;
		this.valoDentro = valoDentro;
		this.userNick = userNick;
	}

	public int getEjeX() {
		return ejeX;
	}

	public void setEjeX(int ejeX) {
		this.ejeX = ejeX;
	}

	public int getEjeY() {
		return ejeY;
	}

	public void setEjeY(int ejeY) {
		this.ejeY = ejeY;
	}

	public char getValoDentro() {
		return valoDentro;
	}

	public void setValoDentro(char valoDentro) {
		this.valoDentro = valoDentro;
	}

	public int getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(int idPartida) {
		this.idPartida = idPartida;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	
	
	
	

}
