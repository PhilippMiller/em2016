package whs.gdi2.tippspiel.database.models;

import java.util.LinkedList;
import java.util.List;

public class ParticipantsField {

	private List<Group> group;
	
	public ParticipantsField() {
		group = new LinkedList<Group>();
	}
	
	public void containsGroup (Group group) {
		this.getGroup().add(group);
		group.setPf(this);
	}

	public List<Group> getGroup() {
		return group;
	}

}
