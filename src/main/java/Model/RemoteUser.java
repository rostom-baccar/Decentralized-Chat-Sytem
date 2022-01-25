package Model;

public class RemoteUser {
	
	private String username;
	private String ipAdress;
	
	public RemoteUser(String username, String ipAdress) {
		this.username=username;
		this.ipAdress=ipAdress;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	
}