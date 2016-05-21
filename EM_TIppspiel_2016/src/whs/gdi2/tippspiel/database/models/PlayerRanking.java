package whs.gdi2.tippspiel.database.models;

import java.util.Date;

public class PlayerRanking implements Comparable<PlayerRanking> {
	private int playerID = 0;
	private int points = 0;
	private int platz = 0;
	private Date date;
	
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getPlatz() {
		return platz;
	}
	public void setPlatz(int platz) {
		this.platz = platz;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public int compareTo(PlayerRanking arg0) {
		if(arg0.points > this.points) return 1;
		if(arg0.points < this.points) return -1;
		
		return 0;
	}
}
