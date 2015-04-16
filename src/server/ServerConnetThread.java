package server;
//服务器与客户端建立连接
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnetThread extends Thread
{
	
	private ServerSocket serverSocket;//套接字
	private int port = 4444;//端口号
	public boolean flag = true;
	
	public ServerConnetThread()
	{
		try
		{
			serverSocket = new ServerSocket(port);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block		
			System.out.println("new ServerSocket worning");
		}

		
	}

	public void run()
	{
		while(flag == true)
		{
			try
			{
				Socket socket = serverSocket.accept();//建立服务端连接，如果接收到连接，应该写一个新的类来解析xml文件和存储socket

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
				System.out.println("new socket worning");
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
