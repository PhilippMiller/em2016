package whs.gdi2.tippspiel.database.models;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class TableRankingObject {
	
	private DefaultTableModel dtm;
	private ArrayList<Team> teams;
	
	public TableRankingObject() {
		this.dtm = dtm;
		this.teams = teams;
	}
	
	

	public DefaultTableModel getDtm() {
		return dtm;
	}
	
	public ArrayList<Team> getTeams() {
		return teams;
	}

	public void setDtm(DefaultTableModel dtm) {
		this.dtm = dtm;
	}

	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}
	

}
