package mvc;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;
import java.sql.*;
import javax.naming.*;

@WebServlet(urlPatterns = {"/loadEditTaskPage"})
public class LoadEditTaskPage extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();
    String taskName = request.getParameter("selectedTask");
    TaskModel currentTask = new TaskModel();

    //Get task list
    @SuppressWarnings("unchecked")
    ArrayList<TaskModel> tasks = (ArrayList<TaskModel>) session.getAttribute("currentTasks");

    //Get task from list
    for (int i = 0; i < tasks.size(); i++)
    {
        if (tasks.get(i).getTaskname().equals(taskName)) currentTask = tasks.get(i);
    }

    //Put task in a session variable
    session.setAttribute("currentTask", currentTask);

    //Redirect user to edit task page
    response.sendRedirect("editTask.jsp");
	}
}
