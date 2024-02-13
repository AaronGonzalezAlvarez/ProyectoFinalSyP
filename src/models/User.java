package models;

public class User {
	
	String user;
	String winner;
	
	public User(String user, String winner) {
		this.user = user;
		this.winner = winner;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getWinner() {
		return winner;
	}
	
	public void setWinner(String winner) {
		this.winner = winner;
	}
	
}
