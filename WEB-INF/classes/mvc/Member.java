package mvc;
import java.util.*;

public class Member {
	private String username;
	private String password;
	private String usertype;
	private ArrayList<String> projects;

	public Member() { }

	public Member(String username, String password, String usertype) {
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		this.projects = new ArrayList<String>();
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

	public ArrayList<String> getProjects()
	{
		return this.projects;
	}

	public void setProject(String projectName)
	{
		this.projects.add(projectName);
	}

	public int getProjectCount()
	{
		return this.projects.size();
	}
}
