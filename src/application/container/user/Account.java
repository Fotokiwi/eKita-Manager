package application.container.user;

public class Account {
	
	private String login;
	private String username;
	private String accounttype;
	
	public Account(String login, String username, String accounttype) {
		
		this.login = login;
		this.username = username;
		this.accounttype = accounttype;
		
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getAccounttype() {
		return this.accounttype;
	}
	
	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

}
