package mvc;
public class Member {
	private String username;
	private String password;
	private String usertype;

	public Member() { }

	public Member(String username, String password, String usertype) {
		this.username = username;
		this.password = password;
		this.usertype = usertype;
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

	public String getUsertype(){
		return this.usertype;
	}

	public void setUsertype(String usertype){
		this.usertype = usertype;
	}
}
