package whs.gdi2.tippspiel.database.models;

import java.util.List;
import java.util.Date;
import java.util.LinkedList;

public class Match {
	
	protected int gameId, homeTeamHt, guestTeamHt, homeTeamEnd, guestTeamEnd, yellowCardsHome, yellowCardsGuest, redCardsHome, redCardsGuest;
	
	protected List<Team> team;
	
	private String hometeam;
	private String guestteam;
	private Date gameTimeAndDate;
	private boolean extension;
	private int guestExtendEnd, homeExtendEnd;
	private boolean penalty;
	private int homeElevenEnd, guestElevenEnd;

	public Match() {
		team = new LinkedList<Team>();
	}
	
	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getHomeTeamHt() {
		return homeTeamHt;
	}

	public void setHomeTeamHt(int homeTeamHt) {
		this.homeTeamHt = homeTeamHt;
	}

	public int getGuestTeamHt() {
		return guestTeamHt;
	}

	public void setGuestTeamHt(int guestTeamHt) {
		this.guestTeamHt = guestTeamHt;
	}

	public int getHomeTeamEnd() {
		return homeTeamEnd;
	}

	public void setHomeTeamEnd(int homeTeamEnd) {
		this.homeTeamEnd = homeTeamEnd;
	}

	public int getGuestTeamEnd() {
		return guestTeamEnd;
	}

	public void setGuestTeamEnd(int guestTeamEnd) {
		this.guestTeamEnd = guestTeamEnd;
	}

	public int getYellowCardsHome() {
		return yellowCardsHome;
	}

	public void setYellowCardsHome(int yellowCardsHome) {
		this.yellowCardsHome = yellowCardsHome;
	}

	public int getYellowCardsGuest() {
		return yellowCardsGuest;
	}

	public void setYellowCardsGuest(int yellowCardsGuest) {
		this.yellowCardsGuest = yellowCardsGuest;
	}

	public int getRedCardsHome() {
		return redCardsHome;
	}

	public void setRedCardsHome(int redCardsHome) {
		this.redCardsHome = redCardsHome;
	}

	public int getRedCardsGuest() {
		return redCardsGuest;
	}

	public void setRedCardsGuest(int redCardsGuest) {
		this.redCardsGuest = redCardsGuest;
	}

	public String getHometeam() {
		return hometeam;
	}

	public void setHometeam(String hometeam) {
		this.hometeam = hometeam;
	}

	public String getGuestteam() {
		return guestteam;
	}

	public void setGuestteam(String guestteam) {
		this.guestteam = guestteam;
	}

	public Date getGameTimeAndDate() {
		return gameTimeAndDate;
	}

	public void setGameTimeAndDate(Date gameTimeAndDate) {
		this.gameTimeAndDate = gameTimeAndDate;
	}

	public int getGuestExtendEnd() {
		return guestExtendEnd;
	}

	public void setGuestExtendEnd(int guestExtendEnd) {
		this.guestExtendEnd = guestExtendEnd;
	}

	public int getHomeExtendEnd() {
		return homeExtendEnd;
	}

	public void setHomeExtendEnd(int homeExtendEnd) {
		this.homeExtendEnd = homeExtendEnd;
	}

	public int getHomeElevenEnd() {
		return homeElevenEnd;
	}

	public void setHomeElevenEnd(int homeElevenEnd) {
		this.homeElevenEnd = homeElevenEnd;
	}

	public int getGuestElevenEnd() {
		return guestElevenEnd;
	}

	public void setGuestElevenEnd(int guestElevenEnd) {
		this.guestElevenEnd = guestElevenEnd;
	}

	public boolean isExtension() {
		return extension;
	}

	public void setExtension(boolean extension) {
		this.extension = extension;
	}

	public boolean isPenalty() {
		return penalty;
	}

	public void setPenalty(boolean elevenmeters) {
		this.penalty = elevenmeters;
	}

	public List<Team> getTeam() {
		return team;
	}

}
