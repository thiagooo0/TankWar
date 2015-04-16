package server;
//解析用户发来的xml文档.
//应该是用解析到用途之后就直接搜索数据库进行处理，然后返回信息。
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

import org.jdom.Document;
import org.jdom.Element;

public class ParseXML
{
	public static HashMap<String, Socket> userMap = new HashMap<String, Socket>();//存储用户和对应的套接字
    private Socket socket;
    private Document doc;
    private String id;
	
	//对用户做判断：是否第一次登陆啊，要做什么工作啊。
	public void init(Socket socket)
	{
		try
		{
			this.socket = socket;
			//等到输入流
			InputStream is = socket.getInputStream();
			ObjectInputStream oos = new ObjectInputStream(is);
			 //得到文件对象
			doc = (Document)oos.readObject();
			Element user = doc.getRootElement();
			id = user.getAttributeValue("id");
			
			//是否个人信息的修改
			 Element personal = user.getChild("personal"); 
			if(personal != null)
			{
				Element target = personal.getChild("target");
				Element type = personal.getChild("type");
				
				if("ask".equals(type.getText()))
				{
					String targetText = target.getText();

					//登陆
					if("login".equals(targetText))
					{
						login(id, personal);
						return;
					}
					//注册
					else if("register".equals(targetText))
					{
						register();
                        return;
					}
					//登出
					else if("logout".equals(targetText))
					{
						logout();
						return;
					}
					//改变
					else if("change".equals(targetText))
					{
						change();
					}
				}
//				if(!userMap.containsKey(id))
//				userMap.put(id, socket);
			}
			
			
		} catch (IOException e)
		{
			System.out.println("can`t find a socket in ParseXML creater");
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void logout()
	{
		
	}
	
	private void change()
	{
		
	}
	//登陆
	private void login(String id, Element personal)
	{
		Element password = personal.getChild("password");
		//处理登陆
		System.out.println(password.getText());
		
		Document document = new Document();
		
		Element user = new Element("user");
		user.setAttribute("id", id);

		Element personal2 = new Element("personal");
		
		Element type = new Element("type");
		type.setText("sucess");
		
		Element target = new Element("target");
		target.setText("login");
		
		document.addContent(user);
		user.addContent(personal2);
		personal2.addContent(target);
		personal2.addContent(type);
		
		send(document);
		
	}
	
	private void register()
	{
		
	}
	
	private void send(Document doc)
	{
		OutputStream os;
		try
		{
			os = socket.getOutputStream();		
			ObjectOutputStream oos = new ObjectOutputStream(os);
		
			oos.writeObject(doc);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ParselXML----------ioexception");
		}

	}
}
