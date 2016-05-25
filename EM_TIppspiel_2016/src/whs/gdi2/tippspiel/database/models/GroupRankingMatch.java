package whs.gdi2.tippspiel.database.models;

public class GroupRankingMatch {
	
	private int gameId, homeTeamHt, guestTeamHt, homeTeamEnd, guestTeamEnd, yellowCardsHome, yellowCardsGuest, redCardsHome, redCardsGuest;
	private String homeTeamName, guestTeamName;
	private boolean penalty = false;

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

	public String getHomeTeamName() {
		return homeTeamName;
	}

	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}

	public String getGuestTeamName() {
		return guestTeamName;
	}

	public void setGuestTeamName(String guestTeamName) {
		this.guestTeamName = guestTeamName;
	}

	public boolean isPenalty() {
		return penalty;
	}

	public void setPenalty(boolean elfmeter) {
		this.penalty = elfmeter;
	}

}
