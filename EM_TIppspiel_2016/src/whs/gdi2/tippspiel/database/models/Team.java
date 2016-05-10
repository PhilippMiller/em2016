package whs.gdi2.tippspiel.database.models;

import java.util.LinkedList;
import java.util.List;

public class Team {
	
	protected String teamName;
	protected ParticipantsField pf = null;
	protected List<Match> match;
	
	public Team () {
		match = new LinkedList<Match>();
	}
	
	public void playedMatch (Match match) {
		this.match.add(match);
		match.team.add(this);
	}
	
	public void isPartOf (ParticipantsField pf) {
		this.setPf(pf);
		pf.includesTeam(this);
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public ParticipantsField getPf() {
		return pf;
	}

	public void setPf(ParticipantsField pf) {
		this.pf = pf;
	}

	public List<Match> getMatch() {
		return match;
	}

	public void setMatch(List<Match> match) {
		this.match = match;
	}
}
