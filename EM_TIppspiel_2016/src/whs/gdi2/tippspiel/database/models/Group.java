package whs.gdi2.tippspiel.database.models;

import java.util.LinkedList;
import java.util.List;

public class Group {
	
	protected String groupName;
	protected List<Team> team;
	
	public Group () {
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
