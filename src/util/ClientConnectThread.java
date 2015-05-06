package util;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
//在客户端与服务器建立连接的类。
public class ClientConnectThread
{
//	真实地址
//	private String serverAddress = "thiagooo0.vicp.cc";
//	private int serverPort = 48372;
	
	//测试用地址
	private String serverAddress = "LocalHost";
	private int serverPort = 4444;
	
	public static Socket socket;
	
	public void connect()
	{
		try
		{
			socket = new Socket(serverAddress, serverPort);//建立连接
			
			GetMessage gm = new GetMessage();
			gm.start();

		} catch (UnknownHostException e)
		{
			//这里应该在界面上有所显示，弹出无法连接网络之类的字样
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
		sm.login("31415", "234567");
//		sm.login("31420", "123456");
//		sm.register("hallo", "2", "123456");
//		sm.addFriend_Ask("31415");
		sm.deleteFriend("31418");
//		SendMessage sm2 = new SendMessage();
//		sm2.logout();
//		sm2.change(null, null, "uuu", "3");
		
//		sm.showFriend("31415");
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
