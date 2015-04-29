package server;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

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
	
	public Test() throws IOException
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
		
		//--------------------------------------------------------------------------------------------------------
		
//		try
//		{
//			FileInputStream writer = new FileInputStream("E:\\TankWarInfo\\31416.xml");		
//			
//			InputStreamReader isr = new InputStreamReader(writer, "GBK");
//			
//			SAXBuilder b = new SAXBuilder();
//			
//			Document document = b.build(isr);
//			
//			System.out.println(document.getRootElement().getChild("messages").getChild("message").getChildText("content"));
//		} catch (FileNotFoundException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JDOMException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		
//		try
//		{
//			String id = "31415";
//			ResultSet set = st.executeQuery("select * from Users");
//
//			set.moveToInsertRow();
//			set.updateString("Name", "name");
//			set.updateString("Password", "123456");
//			set.insertRow();
//			set.last();
//			String str = set.getString(1);
//			System.out.println(str);
//		} catch (SQLException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		File file = new File("E:\\TankWarInfo\\314177.xml");
//
//		Document doc = new Document();
//		doc.addContent(new Element("user"));
//		
//		FileWriter writer = new FileWriter(file);
//		
//		XMLOutputter out = new XMLOutputter();
//		
//		out.output(doc, writer);
//		
//		System.out.println("sucess");
		
		
		SAXBuilder builder = new SAXBuilder();
		try
		{
			Document doc = builder.build(new File("text1.xml"));
			
		} catch (JDOMException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		
//		sql = "select * from Friendship where ID1 = '31415' and ID2 = '31419'";
//
//		try
//		{
//			ResultSet set = st.executeQuery(sql);
////			set.next();
//			System.out.println(set.next());
//		} catch (SQLException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	    
	}
	
	public static void main(String[] args) throws IOException
	{
		Test t = new Test();


	}
}
