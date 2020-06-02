package mvc;
import java.util.*;

public class ProjectModel
{
  private String projectName;
  private ArrayList<String> groupMembers;

  public ProjectModel() {}

  public ProjectModel(String name)
  {
    this.projectName = name;
    this.groupMembers = new ArrayList<String>();
  }

  public String getProjectName(){
    return this.projectName;
  }

  public void setProjectName(String projectName)
  {
    this.projectName = projectName;
  }

  public ArrayList<String> getGroupMembers()
  {
    return this.groupMembers;
  }

  public void setGroupMembers(String groupMember)
  {
    groupMembers.add(groupMember);
  }
}
