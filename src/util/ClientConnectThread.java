package util;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
//�ڿͻ�����������������ӵ��ࡣ
public class ClientConnectThread
{
	private String serverAddress = "thiagooo0.vicp.cc";
	private int serverPort = 48372;
	public static Socket socket;
	
	public void connect()
	{
		try
		{
			socket = new Socket(serverAddress, serverPort);//��������
			
			GetMessage gm = new GetMessage();
			gm.start();

		} catch (UnknownHostException e)
		{
			//����Ӧ���ڽ�����������ʾ�������޷���������֮�������
			System.out.println("ClientConnectThread----------can`t find server");
		} catch (IOException e)
		{
			System.out.println("ClientConnectThread----------new socket worning");
		} 
	}
	
	public static void main(String[] args)
	{
		ClientConnectThread cc = new ClientConnectThread();
		cc.connect();
		
		SendMessage sm = new SendMessage();
		sm.login("1", "23");
		
//		try
//		{
//			InetAddress address = InetAddress.getLocalHost();
//			String str =address.getHostAddress();
//			System.out.println(str);
//			System.out.println(address);
//		} catch (UnknownHostException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}
