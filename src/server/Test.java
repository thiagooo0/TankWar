package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test
{
	public static String url ="jdbc:sqlserver://localhost:1433;DatabaseName = TankWar"; 
	public static String name = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static String user = "sa";
	public static String password = "066310309";
	public static String sql = "";
	Connection conn = null;
	Statement st = null;
	ResultSet set = null;
	
	public Test()
	{
	
		//建立连接
		try
		{
			Class.forName(name);
		} catch (ClassNotFoundException e)
		{
			System.out.println("driver");
		}
		
		try
		{
			conn = DriverManager.getConnection(url,user,password);
		} catch (SQLException e)
		{
			System.out.println("connection");
		}
		
		try
		{
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE
					,ResultSet.HOLD_CURSORS_OVER_COMMIT);
		} catch (SQLException e)
		{
			System.out.println("statement");
		}
		try
		{
			ResultSet set = st.executeQuery("select * from Users");
			
			System.out.println(set.getString(1) + " " +set.next() + " " + set.getRow() + " " + set.getString(2) + " " + set.next() + " " + set.getRow());
//			System.out.println(set.getRow() + "-" + set.getString(1) + "-" + set.next());
//			System.out.println(set.getRow() + "-" + set.getString(2));
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("sucess");
	}
	
	public static void main(String[] args)
	{
		Test t = new Test();
	}
}
