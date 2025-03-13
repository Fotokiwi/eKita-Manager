package customclasses;

public class Permission {
	
	private String permissionID;
	private Boolean permissionGiven = false;
	
	public Permission() {		
		
	}
	
	public Permission(String id, Boolean given) {
		this.permissionID = id;
		this.permissionGiven = given;
	}
	
	public String getID() {
		return this.permissionID;
	}
	
	public Boolean getGiven() {
		return this.permissionGiven;
	}
	
	public void setID(String id) {
		this.permissionID = id;
	}
	
	public void setGiven(Boolean given) {
		this.permissionGiven = given;
	}

}
