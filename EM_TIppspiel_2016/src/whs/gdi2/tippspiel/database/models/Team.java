package whs.gdi2.tippspiel.database.models;

import java.util.LinkedList;
import java.util.List;

public class Team {
	
	protected String teamName;
	protected Group group = null;
	protected List<Match> match;
	protected int goals, goalsAgainst, yellowCards, redCards, points;
	
	public Team () {
		match = new LinkedList<Match>();
	}
	
	public void playedMatch (Match match) {
		this.match.add(match);
		match.team.add(this);
	}
	
	public void isPartOf (Group pf) {
		this.setPf(pf);
		pf.includesTeam(this);
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Group getPf() {
		return group;
	}

	public void setPf(Group pf) {
		this.group = pf;
	}

	public List<Match> getMatch() {
		return match;
	}

	public void setMatch(List<Match> match) {
		this.match = match;
	}

	public int getGoals() {
		return goals;
	}

	public void setGoals(int goals) {
		this.goals = goals;
	}

	public int getGoalsAgainst() {
		return goalsAgainst;
	}

	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}

	public int getYellowCards() {
		return yellowCards;
	}

	public void setYellowCards(int yellowCards) {
		this.yellowCards = yellowCards;
	}

	public int getRedCards() {
		return redCards;
	}

	public void setRedCards(int redCards) {
		this.redCards = redCards;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
