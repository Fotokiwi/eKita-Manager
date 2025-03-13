package customclasses;

public class ListGroup {
	
	private Integer groupID = 0;
	private String groupName;
	private String groupDescription;
	private String groupRoom;
	private Integer groupEmployee;
	
	public ListGroup() {
		
	}

	public ListGroup(int id, String name, String beschreibung, String zimmer, int mitarbeiter) {
		this.groupID = id;
		this.groupName = name;
		this.groupDescription = beschreibung;
		this.groupRoom = zimmer;
		this.groupEmployee = mitarbeiter;
	}
	
	public void setGroupID(int id) {
		this.groupID = id;
	}
	
	public int getGroupID() {
		return this.groupID;
	}
	
	public void setGroupName(String name) {
		this.groupName = name;
	}
	
	public String getGroupName() {
		return this.groupName;
	}
	
	public void setGroupDescription(String description) {
		this.groupDescription = description;
	}
	
	public String getGroupDescription() {
		return this.groupDescription;
	}
	
	public void setGroupRoom(String room) {
		this.groupRoom = room;
	}
	
	public String getGroupRoom() {
		return this.groupRoom;
	}
	
	public void setGroupEmployee(int employee) {
		this.groupEmployee = employee;
	}
	
	public int getGroupEmployee() {
		return this.groupEmployee;
	}
	
}
