package server;
//��������ͻ��˽�������
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServerConnetThread extends Thread
{
	
	private ServerSocket serverSocket;//�׽���
	private int port = 4444;//�˿ں�
	public boolean flag = true;
	
	//���ݿ����
	private String url ="jdbc:sqlserver://localhost:1433;DatabaseName = TankWar"; 
	private String name = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String user = "sa";
	private String password = "066310309";
	Connection conn = null;
	Statement st = null;
	ResultSet set = null;
	
	public ServerConnetThread()
	{
		//ʵ����socket
		try
		{
			serverSocket = new ServerSocket(port);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block		
			System.out.println("new ServerSocket warning");
		}

		//���������ݿ�����
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
		ParseXML.st = st;
	}

	public void run()
	{
		while(flag == true)
		{
			try
			{
				System.out.println("waiting connect");
				Socket socket = serverSocket.accept();//������������ӣ�������յ����ӣ�Ӧ��дһ���µ���������xml�ļ��ʹ洢socket

				System.out.println("G");
				ParseXML par = new ParseXML();
				par.init(socket);
				
				
				
//				InputStream is = socket.getInputStream();
//				OutputStream os = socket.getOutputStream();
				
//				ObjectInputStream ois =new ObjectInputStream(is);
//				
//				Document doc = (Document)ois.readObject();
//				
//				XMLOutputter output = new XMLOutputter(Format.getPrettyFormat());
//				
//				output.output(doc, new FileWriter("text.xml"));
//				
//				
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				System.out.println("new socket warning");
			} 
//				catch (ClassNotFoundException e)
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		
	}
	
	public static void main(String[] args)
	{
		ServerConnetThread sc = new ServerConnetThread();
		sc.start();
	}
}
