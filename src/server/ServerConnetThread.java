package server;
//��������ͻ��˽�������
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnetThread extends Thread
{
	
	private ServerSocket serverSocket;//�׽���
	private int port = 4444;//�˿ں�
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
				Socket socket = serverSocket.accept();//������������ӣ�������յ����ӣ�Ӧ��дһ���µ���������xml�ļ��ʹ洢socket

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
