package mvc;
public class MessageInfo {
	private String title;
	private String email;
	private String message;

	public MessageInfo() { }

	public MessageInfo(String title, String email, String message) {
		this.title = title;
		this.email = email;
		this.message = message;
	}

	public String getMessagetitle() {
		return this.title;
	}

	public void setMessagetitle(String title) {
		this.title = title;
	}

	public String getMessageemail() {
		return this.email;
	}

	public void setMessageemail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
