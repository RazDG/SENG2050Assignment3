package mvc;
public class Member {
	private String username;
	private String password;

	public Member() { }

	public Member(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return this.password;
	}

	public void setUserpassword(String password) {
		this.password = password;
	}
}
