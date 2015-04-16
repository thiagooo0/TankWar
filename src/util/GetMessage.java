package util;
//客户端得到消息
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
//用于随时接收服务器发送过来的信息。关闭程序的时候要调用close方法
public class GetMessage extends Thread
{
	public boolean flag = false;
	private Document doc;
	
	public GetMessage()
	{
		flag = true;
	}
	
	public void close()
	{
		flag = false;
	}
	
	public void run()
	{	

		while(flag)
		{
			try
			{
				InputStream is = ClientConnectThread.socket.getInputStream();
				ObjectInputStream oos = new ObjectInputStream(is);
				
				doc = (Document)oos.readObject();//等到文档对象
				
				
//				用于测试，把xml打印出来
//				FileWriter writer = new FileWriter("test1.xml");
//
//				XMLOutputter put = new XMLOutputter(Format.getPrettyFormat());
//				
//				put.output(doc, writer);
				
				Element user = doc.getRootElement();
				
				String id = user.getAttributeValue("id");//解析出id

				Element personal = user.getChild("personal");//解析出个人信息
				Element friends = user.getChild("friends");//
				Element messages = user.getChild("messages");//
				Element team = user.getChild("team");//
				
				
				if(personal != null)//不为空则证明有个人信息
				{
					//目标是什么
					Element target = personal.getChild("target");
					String str = target.getText();
					
					//登陆
					if(str.equals("login"))
					{
						login(personal, id);
						friends = null;
					}
					//登出
					else if(str.equals("logout"))
					{
						logout(personal, id);
					}
					//注册
					else if(str.equals("register"))
					{
						register(personal, id);
						continue;
					}
					//修改用户信息
					else if(str.equals("change"))
					{
						change(personal, id);
					}
				}
				if(friends != null)
				{
					
				}
				if(messages != null)
				{
					
				}
				if(team != null)
				{
					
				}
				//清空doc
				doc = null;
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				System.out.println("GetMessage------can`t get a inputStream");
			} catch (ClassNotFoundException e)
			{
				// TODO Auto-generated catch block
				System.out.println("GetMessage--------worning class");
			}
		}
		
	}
	
	//登陆，一共要得到的信息有，用户个人信息，朋友列表，留言，添加好友的信息
	@SuppressWarnings({ "unchecked", "unused" })
	private void login(Element personal, String id)
	{
		String type = personal.getChildText("type");
		//登陆失败
		if(type.equals("fail"))
		{
			String str = personal.getChildText("worning");
			System.out.println(str);
			//登陆失败，str为失败原因。
			//请继续写代码............
		}
		else if(type.equals("sucess"))
		{
			SendMessage.userID = id;
			
			//登陆成功，得到用户名，用户头像
			String name = personal.getChildText("name");
			String photoID = personal.getChildText("photoID");
			
			//用于测试地把这些信息输出出去
			System.out.println("id:" + id +" ;name:" + name + " ;photoID:" + photoID);
			
			//战绩
			List<Element> achlist = personal.getChildren("ach");
			for(int i = 0; i < achlist.size(); i++)
			{
				Element ach = (Element)achlist.get(i);
				String mode = ach.getChildText("mode");//模式
				String gross = ach.getChildText("gross");//总局数
				String ratio = ach.getChildText("ratio");//胜率
				//....use it
			}
			
			Element friends = doc.getRootElement().getChild("friends");
			//有朋友啊
			if(friends != null)
			{
				List<Element> flist = friends.getChildren("friend");
			    for(int i = 0; i < flist.size(); i++)
			    {
			    	//好友的信息
			    	Element friend = flist.get(i);
			    	String fid = friend.getChildText("id");
			    	String fphotoID = friend.getChildText("photoID");
			    	String fname = friend.getChildText("name");
			    	String target = friend.getChildText("target");
			    	
//			    	if("show".equals(target))
//			    	{
//			    		List<Element> message = friend.getChildren("message");
//			    	
//			    		if(message != null)
//			    		{
//			    			for(int r = 0; i < message.size(); i++)
//			    			{
//			    				String time = message.get(r).getChildText("time");
//			    				String content = message.get(r).getChildText("content");
//			    				//dosomething;
//			    				//....
//			    			}
//			    		}
//			    		//dosomething
//			    		//
//			    	}
			    	if("add".equals(target))
			    	{
			    		String ftype = friend.getChildText("type");
			    		if("ask".equals(ftype))
			    		{
			    			//人家要加你了，怎么办
			    			//.....
			    		}
			    	}
			    }
			}
			
			
			
		}
	}
	
	private void logout(Element personal, String id)
	{
		
	}
	
	//注册
	private void register(Element personal, String id)
	{
		
	}
	
	
	private void change(Element personal, String id)
	{
		
	}
}
