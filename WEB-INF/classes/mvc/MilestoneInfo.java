package mvc;
public class MilestoneInfo {
	private String taskName;
  private String startDate;
	private String dueDate;
	private String submissionDate;

	public MilestoneInfo() { }

	public MilestoneInfo(String taskName, String startDate, String dueDate, String submissionDate) {
		this.taskName = taskName;
		this.startDate = startDate;
		this.dueDate = dueDate;
    this.submissionDate = submissionDate;
	}

	public String gettaskName() {
		return this.taskName;
	}
	public void settaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getstartDate() {
		return this.startDate;
	}
	public void setstartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getdueDate() {
		return this.dueDate;
	}
	public void setdueDate(String dueDate) {
		this.dueDate = dueDate;
	}

  public String getsubmissionDate() {
		return this.submissionDate;
	}
	public void setsubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}
}
