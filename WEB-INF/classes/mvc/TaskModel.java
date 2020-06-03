package mvc;
import java.util.*;

public class TaskModel
{
    private String projectname;
    private String taskname;
    private String assigneduser;
    private Date startDate;
    private Date dueDate;

    public TaskModel() {}

    public TaskModel(String projectname, String taskname)
    {
      this.projectname = projectname;
      this.taskname = taskname;
    }

    public String getProjectname()
    {
      return this.projectname;
    }

    public void setProjectname(String projectname)
    {
      this.projectname = projectname;
    }

    public String getTaskname()
    {
      return this.taskname;
    }

    public void setTaskname(String taskname)
    {
      this.taskname = taskname;
    }

    public String getAssigneduser()
    {
      return this.assigneduser;
    }

    public void setAssigneduser(String assigneduser)
    {
      this.assigneduser = assigneduser;
    }

    public Date getStartDate()
    {
      return this.startDate;
    }

    public void setStartDate(Date startDate)
    {
      this.startDate = startDate;
    }

    public Date getDueDate()
    {
      return this.dueDate;
    }

    public void setDueDate(Date dueDate)
    {
      this.dueDate = dueDate;
    }
}
