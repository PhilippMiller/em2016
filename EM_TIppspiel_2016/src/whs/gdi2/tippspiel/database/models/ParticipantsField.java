package whs.gdi2.tippspiel.database.models;

import java.util.LinkedList;
import java.util.List;

public class ParticipantsField {
	
	protected String groupName;
	protected List<Team> team;
	
	public ParticipantsField () {
		team = new LinkedList<Team>();
	}
	
	public void includesTeam (Team team) {
		this.team.add(team);		
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
