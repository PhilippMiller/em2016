package whs.gdi2.tippspiel.database.models;

import java.util.LinkedList;
import java.util.List;

import whs.gdi2.tippspiel.Config;

public class Team implements Comparable<Team> {
	
	protected String teamName;
	protected Group group = null;
	protected List<Match> match;
	protected int goals, goalsAgainst, yellowCards, redCards, points, wins, fails, draw;
	
	public Team () {
		match = new LinkedList<Match>();
	}
	
	public void playedMatch (Match match) {
		this.match.add(match);
		match.getTeam().add(this);
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
				// TORVERHÄLTNIS
				if (this.getGoals() - this.getGoalsAgainst() > o2.getGoals() - o2.getGoalsAgainst()) {
					return 1;
				} else if (this.getGoals() - this.getGoalsAgainst() < o2.getGoals() - o2.getGoalsAgainst()) {
					return -1;
				} else {
					// FAIRPLAY
					if ((this.getRedCards() * 3) + this.getYellowCards() > (o2.getRedCards() * 3) + o2.getYellowCards()) {
						return 1;
					} else if ((this.getRedCards() * 3) + this.getYellowCards() < (o2.getRedCards() * 3) + o2.getYellowCards()) {
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

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getFails() {
		return fails;
	}

	public void setFails(int fails) {
		this.fails = fails;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}
}
