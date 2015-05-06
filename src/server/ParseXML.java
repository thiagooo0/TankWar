package server;
//解析用户发来的xml文档.
//应该是用解析到用途之后就直接搜索数据库进行处理，然后返回信息。
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class ParseXML extends Thread
{
	public static HashMap<String, Socket> userMap = new HashMap<String, Socket>();//存储用户和对应的套接字
    private Socket socket;
    private Document doc;
    private String id;
    public static Statement st;
	private String sql = "";
    private String infoAddress = "E:\\TankWarInfo\\";//个人文档存储地址
    private InputStream is;
	private boolean flag = true;
    
	public ParseXML(Socket socket)
	{
		this.socket = socket;
		try
		{
			is = socket.getInputStream();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    @Override
    public void run()
    {
    	while(flag)
    	{	//对用户做判断：是否第一次登陆啊，要做什么工作啊。
    		try
    		{
    			//等到输入流
  
//    			System.out.println("wait new one");
    			ObjectInputStream oos = new ObjectInputStream(is);

    			//得到文件对象
    			doc = (Document)oos.readObject();
    			Element user = doc.getRootElement();
    			id = user.getAttributeValue("id");
    			    			System.out.println("get one message from " + id);
    			
    			saveXML(doc, "text2.xml");
//
//			FileWriter writer = new FileWriter("text2.xml");
//			
//			Format f = Format.getPrettyFormat();
//			f.setEncoding("UTF-8");
//			
//			XMLOutputter put = new XMLOutputter(f);
//			
//			put.output(doc, writer);
    			
    			
    			
    			
    			
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
    						Element password = personal.getChild("password");
    						login(id, password);
//    						break;
    					}
    					//注册
    					else if("register".equals(targetText))
    					{
    						register();
//    						break;
    					}
    					//登出
    					else if("logout".equals(targetText))
    					{
    						logout(id);
//    						break;
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
    			
    			//关于好友的操作，不包括聊天
    			Element friends = user.getChild("friends");
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
    						if(type.equals("ask"))
    						{
    							//可能是添加好友时的搜索，有可能是查看好友资料，要看清楚。
    							showFriend(friendEle);
    						}
    					}
    					//添加好友操作
    					else if(target.equals("add"))
    					{
    						if(type.equals("ask"))
    						{
    							//添加好友请求
    							addFriend_Ask(friendEle);
//    							break;
    						}
    						if(type.equals("success"))
    						{
    							//确认添加好友。不添加就不用返回了
    							addFriend_Success(friendEle);
    						}
    					}
    					//删除好友操作
    					else if(target.equals("delete"))
    					{
    						if(type.equals("ask"))
    						{
    							//删除好友的操作
    							deleteFriend(friendEle);
    						}
    						
    					}
    					//邀请游戏
    					else if(target.equals("invite"))
    					{
    						if(type.equals("ask"))
    						{
    							//邀请好友游戏
    							inviteFriend_Ask(friendEle);
    						}
    						if(type.equals("sucess"))
    						{
    							//接受邀请
    							inviteFriend_Sucess(friendEle);
    						}
    						if(type.equals("fail"))
    						{
    							//不接受邀请_
    							inviteFriend_Fail(friendEle);
    						}
    					}
    					
    				}
    			}
    			
    			
    		} catch (IOException e)
    		{
    			System.out.println("lost connect whit id: " + id);
    			logout(id);
    			
    		} catch (ClassNotFoundException e)
    		{
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
//    		send();
//    		System.out.println("??");
    	}
    }
    
	//添加好友
	private void addFriend_Ask(Element friendEle)
	{

		String fid = friendEle.getChild("id").getText();
		
		System.out.println("id: " + id + " 想加 id:" + fid + " 为好友");
		
		if(isFriend(fid))//已经是好友了
		{
			Element warning = new Element("warning");
			warning.setText("TA已经是你的好友了哦~。~");
			
			friendEle.getChild("type").setText("fail");
			friendEle.addContent(warning);
			
			//要发送回去
			Document fdoc = new Document();
			Element user = new Element("user");
			user.setAttribute("id", id);
			
			Element friends = new Element("friends");
			
			user.addContent(friends);
			friends.addContent(friendEle);
			fdoc.addContent(user);
			send(fdoc, socket);
			return;
		}
		else//
		{
			ResultSet set;
			//friend元素。在线则发送过去，不在线则直接放到文档上
			Element friend = new Element("friend");
			try
			{
				set = st.executeQuery("select PhotoID, Name from Users where ID = '" + id +"'");
				set.next();
				friend.addContent(new Element("name").setText(set.getString("Name"))).addContent(new Element("photoID")
				.setText(set.getString("PhotoID")));
				friend.addContent(new Element("type").setText("ask")).addContent(new Element("target").setText("add"))
				.addContent(new Element("id").setText(id));
				
				Socket fsocket = userMap.get(fid);
				
				if(fsocket == null)//不在线，写到他的个人文档去。
				{
					set = st.executeQuery("select Info from Users where ID = '" + fid + "'");
					if(set.next() == false)
					{
						Element warning = new Element("warning");
						warning.setText("用户不存在");
						
						Element ffriend = new Element("friend");
						ffriend.addContent(new Element("type").setText("fail")).addContent(new Element("target").setText("add"))
						.addContent(warning);
						
						//要发送回去
						Document fdoc = new Document();
						Element user = new Element("u"
								+ "ser");
						user.setAttribute("id", id);
						
						Element friends = new Element("friends");
						
						user.addContent(friends);
						friends.addContent(ffriend);
						fdoc.addContent(user);
						send(fdoc, socket);
						return;
					}
//					set.next();
					//9:30
					//得到好友个人文档的存储地址
					String info = set.getString("Info");
					
					Document fdoc = bulidXML(info);
					
					Element friends = fdoc.getRootElement().getChild("friends");
					
					friends.addContent(friend);
					
					saveXML(fdoc, info);
					
				}
				else
				{
					System.out.println("此人在线哦");
					//在线，发送消息
					Document fdoc = new Document();
					Element user = new Element("user");
					user.setAttribute("id", fid);
					
					Element friends = new Element("friends");
					
					user.addContent(friends);
					friends.addContent(friend);
					fdoc.addContent(user);
					send(fdoc, fsocket);
//						saveXML(fdoc, "ParselXML_addFriend_ask");
				}

			} catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	//成功添加
	private void addFriend_Success(Element friendEle)
	{

		//要分别向两个人发送消息。只是添加进列表就好了.想A发b的消息，向b发a的消息
		String fid = friendEle.getChildText("id");

		//想数据库添加记录
		if(Integer.parseInt(fid.trim()) < Integer.parseInt(id.trim()))
		{
			sql = "insert into Friendship(ID1, ID2) values('" + fid + "','" + id + "')";
		}
		else
		{
			sql = "insert into Friendship(ID1, ID2) values('" + id + "','" + fid + "')";
		}
		
		try
		{
			if(st.execute(sql))
			{
				String finfo = null;//记录对方的个人文件地址
				//向id发送消息
				ResultSet set = st.executeQuery("select * from Users where ID = '" + fid + "'");
				if(set.next())
				{
					Document doc = new Document();
					Element user1 = new Element("user");
					user1.setAttribute("id", id);
					Element friends1 = new Element("friends");
					Element friend1 = new Element("friend");
					friend1.addContent(new Element("type").setText("success")).addContent(new Element("target").setText("add"))
					.addContent(new Element("id").setText(id)).addContent(new Element("name").setText(set.getString("name")))
					.addContent(new Element("photoID").setText(set.getString("PhotoID")));
					
					finfo = set.getString("Info");
					
					doc.addContent(user1);
					user1.addContent(friends1);
					friends1.addContent(friend1);
					
					send(doc);
				}
				
				//向fid发送消息
				set = st.executeQuery("select * from Users where ID = '" + id + "'");
				Element friend2 = new Element("friend");
				friend2.addContent(new Element("type").setText("success")).addContent(new Element("target").setText("add"))
				.addContent(new Element("id").setText(id)).addContent(new Element("name").setText(set.getString("name")))
				.addContent(new Element("photoID").setText(set.getString("PhotoID")));
				//要判断是否在线
				Socket fsocket = userMap.get(fid);
				if(fsocket == null)
				{
					//不在线
					Document fdoc = bulidXML(finfo);
					fdoc.getRootElement().getChild("friends").addContent(friend2);
					saveXML(fdoc, finfo);
				}else
				{
					//在线
					Document fdoc = new Document();
					Element user2 = new Element("user");
					user2.setAttribute("id", fid);
					Element friends2 = new Element("friends");
					doc.addContent(user2);
					user2.addContent(friends2);
					friends2.addContent(friend2);
					
					send(fdoc);
				}
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//删除好友
	private void deleteFriend(Element friendEle)
	{
         //删除好友,首先还是判断是否盆友把。然后再去把记录删掉。
				
		String fid = friendEle.getChildText("id");
		
		Document document2 = new Document();
		Element user2 = new Element("user");
		user2.setAttribute("id", id);
		Element friends2 = new Element("friends");
		Element friend2 = new Element("friend");
		document2.addContent(user2);
		user2.addContent(friends2);
		friends2.addContent(friend2);
		
		Element type2 = new Element("type");
		Element target2 = new Element("target");
		target2.setText("delete");
		Element id2 = new Element("id");
		id2.setText(fid);
		friend2.addContent(type2).addContent(target2).addContent(id2);
		
		if(Integer.parseInt(id.trim()) < Integer.parseInt(fid.trim()))
		{
//			System.out.println("test");
			sql = "select * from Friendship where ID1 = '" + id + "' and ID2 = '" + fid + "'";
		}
		else
		{
			sql = "select * from Friendship where ID1 = '" + fid + "' and ID2 = '" + id + "'";
		}
		
		try
		{
			ResultSet set = st.executeQuery(sql);
			if(set.next() == false)
			{
				//不是好友。。
				//不怎么可能
			}
			else
			{
				set.deleteRow();
				type2.setText("success");
			}
		} catch (SQLException e)
		{
			Element warning2 = new Element("warning");
			warning2.setText("删除错误");
			friend2.addContent(warning2);
			type2.setText("fail");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		send(document2);
          
		
	}
	
	//邀请游戏
	private void inviteFriend_Ask(Element friendEle)
    {
    }
    
	private void inviteFriend_Sucess(Element friendEle)
    {
    }
    
	private void inviteFriend_Fail(Element friendEle)
    {
    	
    }
    

	
	private Document bulidXML(String info)
	{
		SAXBuilder builder = new SAXBuilder();
		Document document = null;
		try
		{
			document = builder.build(new File(info));
		} catch (JDOMException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}
	
	private void clearXML(Document document, String info)
	{
		Element user1 = document.getRootElement();
		user1.getChild("friends").removeChildren("friend");
		user1.getChild("messages").removeChildren("message");
		user1.getChild("personal").removeChildren("target");
		user1.getChild("personal").removeChildren("type");
		user1.getChild("personal").removeChildren("photoID");
		user1.getChild("personal").removeChildren("name");
		saveXML(document, info);
	
	}
	
	
	//保存xml文件的
	private void saveXML(Document document, String info)
	{
		FileWriter writer;
		try
		{
			writer = new FileWriter(info);
			
			Format f = Format.getPrettyFormat();
			f.setEncoding("UTF-8");
			
			XMLOutputter put = new XMLOutputter(f);
			
			put.output(document, writer);
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//判断是否好友
    private boolean isFriend(String fid)
    {
		if(Integer.parseInt(id.trim()) < Integer.parseInt(fid.trim()))
		{
//			System.out.println("test");
			sql = "select * from Friendship where ID1 = '" + id + "' and ID2 = '" + fid + "'";
		}
		else
		{
			sql = "select * from Friendship where ID1 = '" + fid + "' and ID2 = '" + id + "'";
		}
		
		try
		{
			if(st.executeQuery(sql).next() == false)
			{
				return false;
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

          return true;
	
    }
    
    
//显示其他用户信息	
	private void showFriend(Element friendEle)
	{
		//首先要判断是否好友
		String fid = friendEle.getChild("id").getText();
		
//		System.out.println(Integer.parseInt(id.trim()) + " : " + Integer.parseInt(fid.trim()));
		
//		if(Integer.parseInt(id.trim()) < Integer.parseInt(fid.trim()))
//		{
//			System.out.println("test");
//			sql = "select * from Friendship where ID1 = '" + id + "' and ID2 = '" + fid + "'";
//		}
//		else
//		{
//			sql = "select * from Friendship where ID1 = '" + fid + "' and ID2 = '" + id + "'";
//		}

		try
		{		
			ResultSet set = st.executeQuery("select * from Users where ID = '" + fid + "'");
			
			String photoID = null;
			String name = null;
			String info = null;
			String id = null;
			if(set.next())
			{
				photoID = set.getString("PhotoID");
				name = set.getString("Name");
				info = set.getString("Info");
				id = set.getString("ID");
			}
			//看下这用户是否存在
			if(id == null)
			{
				//不存在
				friendEle.getChild("type").setText("fail");
				friendEle.addContent(new Element("warning").setText("用户不存在"));
				return;
			}
//			Boolean isFriend = st.executeQuery(sql).next();
//			System.out.println(isFriend);
			if(isFriend(fid))
			{
				//如果是真。则是显示好友信息
				friendEle.getChild("type").setText("success");
				friendEle.setAttribute("isFriend", "true");
				
				Element photoIDEle = new Element("photoID");
				photoIDEle.setText(photoID);
				Element nameEle = new Element("name");
				nameEle.setText(name);
				friendEle.addContent(photoIDEle).addContent(nameEle);
				
				if(info != null)
				{
					
					SAXBuilder builder = new SAXBuilder();
					Document fdoc = builder.build(new File(info));
					Element fach = fdoc.getRootElement().getChild("personal").getChild("ach");
					
					fdoc.getRootElement().getChild("personal").removeChild("ach");
					
					if(fach != null)
					{
						friendEle.addContent(fach);
					}
				}
				
			}
			else
			{
				//填加朋友时的搜索
				friendEle.setAttribute("isFriend", "false");
				friendEle.getChild("type").setText("success");
				friendEle.addContent(new Element("photoID").setText(photoID))
				.addContent(new Element("name").setText(name));

			}
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//登出
	private void logout(String id)
	{
		//如果以后知道可以得到ip地址，可以用数据库存储用户的ip地址，不需要用到map
		userMap.remove(id);
//		Document doc = new Document();
		Document document2 = new Document();
		
		Element user = new Element("user");
		user.setAttribute("id", id);
		
		Element personal = new Element("personal");
		
		Element type = new Element("type");
		type.setText("success");
		
		Element traget = new Element("target");
		traget.setText("logout");
		
		document2.addContent(user);
		user.addContent(personal);
		personal.addContent(type).addContent(traget);

		send();
		
		flag = false;
	}
	
	//改变
	private void change()
	{
		
		Element personal = doc.getRootElement().getChild("personal");
		
		try
		{
			ResultSet set = st.executeQuery("select * from Users where id = '" + id + "'");

			set.next();

			if(personal.getChild("password") != null)
			{
//				System.out.println(set.getString("Password").trim());
//				System.out.println(personal.getChildText("password").trim());
				if(set.getString("Password").trim().equals(personal.getChildText("password").trim()))
				{
					set.updateString("Password", personal.getChildText("newPassword"));
					personal.getChild("password").setText(personal.getChildText("newPassword"));
					personal.removeChild("newPassword");
				}
				else
				{
					Element warning = new Element("warning");
					warning.setText("密码错误");
					
					personal.addContent(warning);
					personal.getChild("type").setText("fail");
					
					send(doc);
					return;
				}
			}
			if(personal.getChild("photoID") != null)
			{
				set.updateInt("PhotoID", Integer.parseInt(personal.getChildText("photoID")));
			}
			if(personal.getChild("name") != null)
			{
				set.updateString("Name", personal.getChildText("name"));
			}
			
			set.updateRow();
			
			personal.getChild("type").setText("sucess");

			//先不用send。等下有没有其他的东东。
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//登陆
	private void login(String id, Element password)
	{
		
		
		sql = "select Password from Users where ID = '" + id + "'";
		
		//发送出去的document
		Document document = new Document();
		Element user = new Element("user");
		user.setAttribute("id", id);
	
		document.addContent(user);
		
		Element personal2 = new Element("personal");
		user.addContent(personal2);
		
		Element type = new Element("type");
		
		Element target = new Element("target");
		target.setText("login");
		
		
		try
		{
			//查询
			ResultSet set = st.executeQuery(sql);
			
			if(set.next())
			{
				if(password.getText().trim().equals(set.getString(1).trim()))
				{
					//登陆成功
					type.setText("success");
					
					//得到个人信息
					sql = "select * from Users where ID = '" + id + "'";
					set = st.executeQuery(sql);
					set.next();
					String info = set.getString(5);
					if(info != null)//如果有文档的话
					{
//						File file = new File(set.getString(5));
						
						
						try
						{
							//转码
							InputStreamReader isr = new InputStreamReader(new FileInputStream(info),"gbk");
							
							SAXBuilder bulider = new SAXBuilder();
							
							//文件中存放了一些个人信息，留言
						    Document document3 = bulider.build(isr);
							Element user3 = document3.getRootElement();
							Element personal3 = user3.getChild("personal");
							
//							personal3.addContent(target);
							//添加名字，头像，还有登陆成功的信息
							personal3.addContent(type).addContent(target).addContent(new Element("name").setText(set.getString(2)))
							.addContent(new Element("photoID").setText(set.getString(4)));
							
							
							//添加好友信息
							sql = "select * from Friendship where ID1 = '" + id + "' or ID2 = '" + id + "'";
							
							ResultSet set2 = st.executeQuery(sql);
							
							Element friends = user3.getChild("friends");
							ArrayList<String> fids = new ArrayList<String>();
							String fid;
							while(set2.next())
							{
//								int i = 0;
								//得到朋友id
								if(id.trim().equals(set2.getString(1).trim()))
								{
									fid = set2.getString(2).trim();
								}
								else
								{
									fid = set2.getString(1).trim();
								}
								fids.add(fid);
							}
							for(int e = 0; e < fids.size(); e++)
							{
								fid = fids.get(e);
								
								//写入信息
								Element friend = new Element("friend");
								friends.addContent(friend);
								
								Element ftype = new Element("type");
								ftype.setText("success");
								
								Element ftarget = new Element("target");
								ftarget.setText("show");
								
								sql = "select Name, PhotoID from Users where ID = '" + fid + "'";
								ResultSet fset = st.executeQuery(sql);
//								System.out.println(set.isClosed());
								fset.next();
								
								Element fname = new Element("name");
								fname.setText(fset.getString(1));
								
								Element fphoto = new Element("photoID");
								fphoto.setText(fset.getString(2));
								
								Element fidE = new Element("id");
								fidE.setText(fid);
								
								friend.addContent(ftype).addContent(ftarget).addContent(fname).addContent(fphoto).addContent(fidE);
							}			
							
							//完成，发出去
							send(document3);

							//发完要把message和friend删掉。。
							//要写清空函数！！
							if(info != null)
							{
								clearXML(document3, info);
							}
							
							//保存socket
							userMap.put(id, socket);
							
							return;

						} 
						catch (JDOMException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
					//如果没有文档怎么办，以后再办
					//。。。。。。。
					
					
					
					
				}else
				{
					//密码错误啦
					personal2.addContent(target);
					type.setText("fail");
					personal2.addContent(type);
					
					Element warning = new Element("warning");
					warning.setText("密码错误");
					personal2.addContent(warning);
				}
			}
			else//根本没有这个账号？
			{
				personal2.addContent(target);

				type.setText("fail");
				personal2.addContent(type);
				
				Element warning = new Element("warning");
				warning.setText("账号不存在");
				personal2.addContent(warning);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		send(document);
//		
//		//处理登陆
//		System.out.println(password.getText());
//		
//		Document document = new Document();
//		
//		Element user = new Element("user");
//		user.setAttribute("id", id);
//
//		Element personal2 = new Element("personal");
//		
//		Element type = new Element("type");
//		type.setText("success");
//		
//		Element target = new Element("target");
//		target.setText("login");
//		
//		document.addContent(user);
//		user.addContent(personal2);
//		personal2.addContent(target);
//		personal2.addContent(type);
//		
//		send(document);
//		

		
	}
	
	//注册
	private void register()
	{
		Element user = doc.getRootElement();
		Element personal = user.getChild("personal");
		
		String password = personal.getChildText("password");
		String name = personal.getChildText("name");
		String photoID = personal.getChildText("photoID");
		
		try
		{
			ResultSet set = st.executeQuery("select * from Users");
			
			set.moveToInsertRow();
			
			set.moveToInsertRow();
			set.updateString("Name", name);
			set.updateString("Password", password);
			set.updateString("PhotoID", photoID);
			set.insertRow();
			set.last();
			id = set.getString(1);
			

			String info = infoAddress + id + ".xml";

			set.updateString(5, info);
			set.updateRow();
			
			Document doc2 = new Document();
			Element user2 = new Element("user");
			user2.setAttribute("id", id);
			
		
			Element personal2 = new Element("personal");
			Element friends2 = new Element("friends");
			Element messages2 = new Element("messages");
//			Element personal2 = new Element("personal");
//			Element personal2 = new Element("personal");
			
			user2.addContent(personal2).addContent(friends2).addContent(messages2);
			doc2.addContent(user2);
			
			//存储 
			
			File file = new File(info);
			
			FileWriter writer = new FileWriter(file);
			
			XMLOutputter out = new XMLOutputter();
			
			out.output(doc2, writer);
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		user.setAttribute("id", id);
		user.getChild("type").setText("success");
		
		send(doc);
	}
	
	private void send()
	{
		send(doc);
	}
	
	private void send(Document doc, Socket soc)
	{
		try
		{
			OutputStream os = soc.getOutputStream();
			
			ObjectOutputStream oos = new ObjectOutputStream(os);
			
			oos.writeObject(doc);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void send(Document doc)
	{
		OutputStream os;
		try
		{	
			
//			doc.getRootElement().getChild("messages").getChild("message").getChild("content").setText("我打中文test1");
			
//存放起来。测试			
//			FileWriter writer = new FileWriter("text2.xml");
//
//			Format f = Format.getPrettyFormat();
//			f.setEncoding("UTF-8");
//			
//			XMLOutputter put = new XMLOutputter(f);
//			
//			put.output(doc, writer);
//			
			
			os = socket.getOutputStream();	
			
			ObjectOutputStream oos = new ObjectOutputStream(os);
		
			oos.writeObject(doc);
			
//			System.out.println("send one message");
			
//			Format f = Format.getRawFormat();
//			f.setEncoding("UTF8");
//			
//			XMLOutputter out = new XMLOutputter(f);
//			out.output(doc, os);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ParselXML----------ioexception");
		}

	}
}
