package whs.gdi2.tippspiel.database.models;

import java.util.LinkedList;
import java.util.List;

public class Group {
	
	protected String groupName;
	protected List<Team> team;
	protected ParticipantsField pf;
	
	public Group () {
		this.team = new LinkedList<Team>();
		this.pf = null;
	}
	
	public void includesTeam (Team team) {
		this.team.add(team);		
	}
	
	public List<Team> getTeam() {
		return team;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public ParticipantsField getPf() {
		return pf;
	}

	public void setPf(ParticipantsField pf) {
		this.pf = pf;
	}

}
