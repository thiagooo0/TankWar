package util;
//客户端得到消息
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
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
				FileWriter writer = new FileWriter("text1.xml");

				Format f = Format.getPrettyFormat();
				f.setEncoding("GBK");
				XMLOutputter put = new XMLOutputter(f);
				
				put.output(doc, writer);
				
				
				//真正的代码
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
						logout(personal);
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
						change(personal);
					}
				}
				if(friends != null)
				{
					@SuppressWarnings("unchecked")
					List<Element> friendList = friends.getChildren("friend");
					int length = friendList.size();
					for(int i = 0; i < length; i++)
					{
						Element friendEle = friendList.get(i);

						String target = friendEle.getChild("target").getText();		
						String type = friendEle.getChild("type").getText();
						
						//搜索或者展示好友
						if(target.equals("show"))
						{
							//可能是添加好友时的搜索，有可能是查看好友资料，要看清楚。
							showFriend(friendEle);
						}
						//添加好友操作
						else if(target.equals("add"))
						{
							if(type.equals("ask"))
							{
								//添加好友请求
								addFriend_Ask(friendEle);
							}
							if(type.equals("success"))
							{
								//确认添加好友。不添加就不用返回了
//								addFriendSuccess();
							}
							if(type.equals("fail"))
							{
								//添加好友请求
								addFriend_Fail(friendEle);
							}
						}
						//删除好友操作
						else if(target.equals("delete"))
						{
							if(type.equals("success"))
							{
//								删除好友的操作
								deleteFriend_Success(friendEle);
							}
							
							if(type.equals("fail"))
							{
								deleteFriend_Fail(friendEle);
							}

						}
//						//邀请游戏
//						else if(target.equals("invite"))
//						{
//							if(type.equals("ask"))
//							{
//								//邀请好友游戏
////								inviteFriendAsk();
//							}
//							if(type.equals("sucess"))
//							{
//								//接受邀请
////								inviteFriendSucess();
//							}
//							if(type.equals("fail"))
//							{
//								//不接受邀请
////								inviteFriendFail();
//							}
//						}
						
					}
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
				System.out.println("GetMessage--------warning class");
			}
		}
		
	}
	
	private void deleteFriend_Success(Element friendEle)
	{

		String fid = friendEle.getChildText("id");
		System.out.println(fid + " not your friend anymore");
	}

	private void deleteFriend_Fail(Element friendEle)
	{
		String fid  = friendEle.getChildText("id");
		String warning = friendEle.getChildText("warning");
		
		System.out.println("delete " + fid + "fail. because " + warning);
	}
	
	private void addFriend_Fail(Element friendEle)
	{
		String warning = friendEle.getChildText("warning");

		//.....
		System.out.println(warning);
	}
	
	private void addFriend_Ask(Element friendEle)
	{
		String fid = friendEle.getChildText("id");
		String fname = friendEle.getChildText("name");
		String fphotoID = friendEle.getChildText("photoID");
		
		//.....
		System.out.println(fname + "(" + fid + ")" + " photoid:" + fphotoID  + " want to add you, man");
	}
	
	@SuppressWarnings("unchecked")
	private void showFriend(Element friendEle)
	{
		String type = friendEle.getChild("type").getText();
		
		if(type.trim().equals("success"))
		{
			
			if(friendEle.getAttributeValue("isFriend").trim().equals("true"))
			{
				System.out.println("这是你的朋友信息哦");
				String fname = friendEle.getChildText("name");
				String fphotoID = friendEle.getChildText("photoID");
				String fid = friendEle.getChildText("id");
				
				System.out.println("name : " + fname);
				System.out.println("photo : " + fphotoID);
				System.out.println("id : " + fid);
				
				List<Element> list = friendEle.getChildren("ach");
				int length = list.size();
				for(int i = 0; i < length; i++)
				{
					Element ach = list.get(i);
					String fmode = ach.getChildText("mode");
					String fgross = ach.getChildText("gross");
					String fratio = ach.getChildText("ratio");
					
					System.out.println("mode : " + fmode);
					System.out.println("gross : " + fgross);
					System.out.println("ratio : " + fratio);
				}
			}
			else
			{
				System.out.println("这是你搜索的用户的信息哦");
				String fname = friendEle.getChildText("name");
				String fphotoID = friendEle.getChildText("photoID");
				String fid = friendEle.getChildText("id");
				
				System.out.println("name : " + fname);
				System.out.println("photo : " + fphotoID);
				System.out.println("id : " + fid);
			}
		}
		else if(type.trim().equals("fail"))
		{
			//失败
			String warning = friendEle.getChildText("warning");
			System.out.println(warning);
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
			String str = personal.getChildText("warning");
			System.out.println("warning" + str);
			//登陆失败，str为失败原因。
			//请继续写代码............
		}
		
		else if(type.equals("success"))
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
			    			System.out.println(fname + "(" + fid + ") want you!!!!1");
			    			//人家要加你了，怎么办
			    			//.....
			    		}
			    	}
			    	else if("show".equals(target))
			    	{
			    		System.out.println("friends: id=" + fid +" name=" + fname + "photoID" + fphotoID);
			    	}
			    }
			    /*----------------------------------------------------------------------------------------------------
			    -------------------------------------------TEST--------------------------------------------
			    --------------------------------------------------------------------------------------------------*/
//			    SendMessage sm = new SendMessage();
//			    System.out.println("GM359----------------test");
//			    sm.addFriend_Ask("31415");

			}
			
			//未读信息
			Element messages = doc.getRootElement().getChild("messages");
			
			List<Element> list = messages.getChildren(); 
			
			if(!list.isEmpty())
			{
				int length = list.size();
				for(int i = 0; i < length; i++)
				{
					Element message = list.get(i);
					Element mid = message.getChild("id");
					
					String mids = mid.getText();
					Element mcontent = message.getChild("content");
					String mcontents = mcontent.getText();
					String mtimes = mcontent.getAttributeValue("time");
					
					System.out.println(mids + " " + mtimes);
					System.out.println("   " + mcontents);
				}
			}
			
		}
	}
	
	private void logout(Element personal)
	{
		if(personal.getChild("type").equals("success"))
		{
			SendMessage.userID = null;
			//还要做什么？
			
		}
		System.out.println("OUT!");
	}
	
	//注册
	private void register(Element personal, String id)
	{
		
		if("success".equals(personal.getChildText("type")))
		SendMessage.userID = id;
		
		String name = personal.getChildText("name");
		String photoID = personal.getChildText("photoID");
		
		System.out.println("hey, " + name + "photo:" + photoID);
		System.out.println("here is your id:" + id);
		
	}
	
	
	//更改信息
	private void change(Element personal)
	{
		if(personal.getChildText("type").equals("fail"))
		{
			String warning = personal.getChildText("warning");
			System.out.println(warning);
		}
		else
		{
			Element passwordEle = personal.getChild("password");
			Element photoIDEle = personal.getChild("photoID");
			Element nameEle = personal.getChild("name");
			
			if(passwordEle != null)
			{//更改了密码
				String password = passwordEle.getText();
				System.out.println(password);
			}
			if(photoIDEle != null)
			{//更改了图片id
				String photoID = photoIDEle.getText();
				System.out.println(photoID);
			}
			if(nameEle != null)
			{//更改了名字
				String name = nameEle.getText();
				System.out.println(name);
			}
		}
	}
}
