package customclasses;

public class ListPermissions {
	
	private String permissionID;
	private Boolean permissionActive;
	private String permissionName;
	private String permissionDescription;
	private String permissionShortDescription;
	
	public ListPermissions() {		
		
	}
	
	public ListPermissions(String id, Boolean active, String name, String description, String shortDescription) {
		this.permissionID = id;
		this.permissionActive = active;
		this.permissionName = name;
		this.permissionDescription = description;
		this.permissionShortDescription = shortDescription;
	}
	
	public String getID() {
		return this.permissionID;
	}
	
	public Boolean getActive() {
		return this.permissionActive;
	}
	
	public int getActiveInt() {
		if(this.permissionActive) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public String getName() {
		if(this.permissionName.equalsIgnoreCase("")) {
			return this.permissionID;
		}
		return this.permissionName;
	}
	
	public String getDescriptionOrName() {
		if(this.permissionName.equalsIgnoreCase("")) {
			return this.permissionID;
		}
		if(this.permissionShortDescription.equalsIgnoreCase("")) {
			return this.permissionName;
		}
		return this.permissionName + " - " + this.permissionShortDescription;
	}
	
	public String getDescription() {
		return this.permissionDescription;
	}
	
	public String getShortDescription() {
		return this.permissionShortDescription;
	}
	
	public void setID(String id) {
		this.permissionID = id;
	}
	
	public void setActive(Boolean active) {
		this.permissionActive = active;
	}
	
	public void setName(String name) {
		this.permissionName = name;
	}
	
	public void setDescription(String description) {
		this.permissionDescription = description;
	}
	
	public void setShortDescription(String shortDescription) {
		this.permissionShortDescription = shortDescription;
	}

}
