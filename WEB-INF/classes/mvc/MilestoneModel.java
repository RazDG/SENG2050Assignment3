package mvc;
import java.util.*;

public class MilestoneModel
{
  private String projectname;
  private String milestone;
  private String dueDate;
  private boolean isComplete;

  public MilestoneModel() {}

  public MilestoneModel(String projectname, String milestone)
  {
    this.projectname = projectname;
    this.milestone = milestone;
    this.isComplete = false;
  }

  public String getProjectname()
  {
    return this.projectname;
  }

  public void setProjectname(String projectname)
  {
    this.projectname = projectname;
  }

  public String getMilestone()
  {
    return this.milestone;
  }

  public void setMilestone(String milestone)
  {
    this.milestone = milestone;
  }

  public String getDueDate()
  {
    return this.dueDate;
  }

  public void setDueDate(String dueDate)
  {
    this.dueDate = dueDate;
  }

  public boolean getIsComplete()
  {
    return this.isComplete;
  }

  public void setIsComplete(boolean isComplete)
  {
    this.isComplete = isComplete;
  }
}
