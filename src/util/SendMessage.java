package util;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.jdom.Document;
import org.jdom.Element;

//客户端发送信息给服务器的类
//使用方法：生成实例，调用对应方法

public class SendMessage
{
	public static String userID ;//保存用户的id
	private Document document;//文档类
	private Element userEle;//存放文档的根元素
	public SendMessage()
	{
		//创建实例的时候就要
		document = new Document();
		userEle = new Element("user");//根元素是user
		document.addContent(userEle);//把根元素放到
	}
	
	
	//处理登陆事件
	public void login(String id, String password)
	{
		userEle.setAttribute("id", id);//填入id
		
		Element personal  = new Element("personal");
		Element target  = new Element("target");
		target.addContent("login");//目标是登陆
		Element type = new Element("type");
		type.addContent("ask");
		
		Element pw = new Element("password");
		pw.addContent(password);//填入密码
		
		userEle.addContent(personal);
		personal.addContent(target).addContent(pw).addContent(type);
		
		this.send();//biu，发出去了
	}
	
	//发送文件
	public boolean send()
	{
		OutputStream os;
		try
		{
			//得到输入流
			os = ClientConnectThread.socket.getOutputStream();	
			
			ObjectOutputStream oos = new ObjectOutputStream(os);

			//发送文档对象出去
			oos.writeObject(document);
		} catch (IOException e)
		{
			//这些要不要在界面上体现呢？
			System.out.println("send message failed");
		}
		return true;
	}	

}
