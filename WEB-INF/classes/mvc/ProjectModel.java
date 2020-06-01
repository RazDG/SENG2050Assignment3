package mvc;

public class ProjectModel
{
  private String projectName;

  public ProjectModel() {}

  public ProjectModel(String name)
  {
    this.projectName = name;
  }

  public String getProjectName(){
    return this.projectName;
  }

  public void setProjectName(String projectName)
  {
    this.projectName = projectName;
  }
}
