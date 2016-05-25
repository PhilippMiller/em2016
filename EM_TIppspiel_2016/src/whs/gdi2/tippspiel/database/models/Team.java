package whs.gdi2.tippspiel.database.models;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import whs.gdi2.tippspiel.Config;

public class Team implements Comparable<Team> {
	
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

	@Override
	public int compareTo(Team o2) {
		
		// PUNKTE
		if (this.getPoints() < o2.getPoints()) {
			return 1;
		} else if (this.getPoints() > o2.getPoints()) {
			return -1;
		} else {
			// TORE
			if (this.getGoals() < o2.getGoals()) {
				return 1;
			} else if (this.getGoals() > o2.getGoals()) {
				return -1;
			} else {
				// TORVERH�LTNIS
				if (this.getGoals() - this.getGoalsAgainst() > o2.getGoals() - o2.getGoalsAgainst()) {
					return 1;
				} else if (this.getGoals() - this.getGoalsAgainst() < o2.getGoals() - o2.getGoalsAgainst()) {
					return -1;
				} else {
					// FAIRPLAY
					if (this.getRedCards() + this.getYellowCards() > o2.getRedCards() + o2.getYellowCards()) {
						return 1;
					} else if (this.getRedCards() + this.getYellowCards() < o2.getRedCards() + o2.getYellowCards()) {
						return -1;
					} else {
						// KOEFFIZIENT
						if (Config.getKoeffizienten().get(this.getTeamName()) < Config.getKoeffizienten().get(o2.getTeamName())) {
							return 1;
						} else if (Config.getKoeffizienten().get(this.getTeamName()) > Config.getKoeffizienten().get(o2.getTeamName())) {
							return -1;
						} else {
							return 0;
						}
					}
				}
			}
		}
	}
}
