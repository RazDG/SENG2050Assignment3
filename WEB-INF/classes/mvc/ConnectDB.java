package mvc;
import javax.sql.*;
import java.sql.*;
import javax.naming.*;

public class ConnectDB
{
	private static final DataSource dataSource = makeDataSource();

	//creates DataSource
	private static DataSource makeDataSource()
	{
		try
		{
			InitialContext ctx = new InitialContext();
			return (DataSource) ctx.lookup("java:/comp/env/jdbc/GroupProjDB");
		}
		catch (NamingException e)
		{
			throw new RuntimeException(e);
		}
	}

	//creates DB connection
	public static Connection getConnection() throws SQLException
	{
		return dataSource.getConnection();
	}
}
