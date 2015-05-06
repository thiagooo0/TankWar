package util;
//�ͻ��˵õ���Ϣ
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
//������ʱ���շ��������͹�������Ϣ���رճ����ʱ��Ҫ����close����
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
				
				doc = (Document)oos.readObject();//�ȵ��ĵ�����
				
//				���ڲ��ԣ���xml��ӡ����
				FileWriter writer = new FileWriter("text1.xml");

				Format f = Format.getPrettyFormat();
				f.setEncoding("GBK");
				XMLOutputter put = new XMLOutputter(f);
				
				put.output(doc, writer);
				
				
				//�����Ĵ���
				Element user = doc.getRootElement();
				
				String id = user.getAttributeValue("id");//������id

				Element personal = user.getChild("personal");//������������Ϣ
				Element friends = user.getChild("friends");//
				Element messages = user.getChild("messages");//
				Element team = user.getChild("team");//
				
				
				if(personal != null)//��Ϊ����֤���и�����Ϣ
				{
					//Ŀ����ʲô
					Element target = personal.getChild("target");
					String str = target.getText();
					
					//��½
					if(str.equals("login"))
					{
						login(personal, id);
						friends = null;
					}
					//�ǳ�
					else if(str.equals("logout"))
					{
						logout(personal);
					}
					//ע��
					else if(str.equals("register"))
					{
						register(personal, id);
						continue;
					}
					//�޸��û���Ϣ
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
						
						//��������չʾ����
						if(target.equals("show"))
						{
							//��������Ӻ���ʱ���������п����ǲ鿴�������ϣ�Ҫ�������
							showFriend(friendEle);
						}
						//��Ӻ��Ѳ���
						else if(target.equals("add"))
						{
							if(type.equals("ask"))
							{
								//��Ӻ�������
								addFriend_Ask(friendEle);
							}
							if(type.equals("success"))
							{
								//ȷ����Ӻ��ѡ�����ӾͲ��÷�����
//								addFriendSuccess();
							}
							if(type.equals("fail"))
							{
								//��Ӻ�������
								addFriend_Fail(friendEle);
							}
						}
						//ɾ�����Ѳ���
						else if(target.equals("delete"))
						{
							if(type.equals("success"))
							{
//								ɾ�����ѵĲ���
								deleteFriend_Success(friendEle);
							}
							
							if(type.equals("fail"))
							{
								deleteFriend_Fail(friendEle);
							}

						}
//						//������Ϸ
//						else if(target.equals("invite"))
//						{
//							if(type.equals("ask"))
//							{
//								//���������Ϸ
////								inviteFriendAsk();
//							}
//							if(type.equals("sucess"))
//							{
//								//��������
////								inviteFriendSucess();
//							}
//							if(type.equals("fail"))
//							{
//								//����������
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
				//���doc
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
				System.out.println("�������������ϢŶ");
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
				System.out.println("�������������û�����ϢŶ");
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
			//ʧ��
			String warning = friendEle.getChildText("warning");
			System.out.println(warning);
		}
	}
	
	//��½��һ��Ҫ�õ�����Ϣ�У��û�������Ϣ�������б����ԣ���Ӻ��ѵ���Ϣ
	@SuppressWarnings({ "unchecked", "unused" })
	private void login(Element personal, String id)
	{
		String type = personal.getChildText("type");
		//��½ʧ��
		if(type.equals("fail"))
		{
			String str = personal.getChildText("warning");
			System.out.println("warning" + str);
			//��½ʧ�ܣ�strΪʧ��ԭ��
			//�����д����............
		}
		
		else if(type.equals("success"))
		{
			SendMessage.userID = id;
			
			//��½�ɹ����õ��û������û�ͷ��
			String name = personal.getChildText("name");
			String photoID = personal.getChildText("photoID");
			
			//���ڲ��Եذ���Щ��Ϣ�����ȥ
			System.out.println("id:" + id +" ;name:" + name + " ;photoID:" + photoID);
			
			//ս��
			List<Element> achlist = personal.getChildren("ach");
			for(int i = 0; i < achlist.size(); i++)
			{
				Element ach = (Element)achlist.get(i);
				String mode = ach.getChildText("mode");//ģʽ
				String gross = ach.getChildText("gross");//�ܾ���
				String ratio = ach.getChildText("ratio");//ʤ��
				//....use it
				
				
			}
			
			Element friends = doc.getRootElement().getChild("friends");
			//�����Ѱ�
			if(friends != null)
			{
				List<Element> flist = friends.getChildren("friend");
			    for(int i = 0; i < flist.size(); i++)
			    {
			    	//���ѵ���Ϣ
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
			    			//�˼�Ҫ�����ˣ���ô��
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
			
			//δ����Ϣ
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
			//��Ҫ��ʲô��
			
		}
		System.out.println("OUT!");
	}
	
	//ע��
	private void register(Element personal, String id)
	{
		
		if("success".equals(personal.getChildText("type")))
		SendMessage.userID = id;
		
		String name = personal.getChildText("name");
		String photoID = personal.getChildText("photoID");
		
		System.out.println("hey, " + name + "photo:" + photoID);
		System.out.println("here is your id:" + id);
		
	}
	
	
	//������Ϣ
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
			{//����������
				String password = passwordEle.getText();
				System.out.println(password);
			}
			if(photoIDEle != null)
			{//������ͼƬid
				String photoID = photoIDEle.getText();
				System.out.println(photoID);
			}
			if(nameEle != null)
			{//����������
				String name = nameEle.getText();
				System.out.println(name);
			}
		}
	}
}
