package util;
//�ͻ��˵õ���Ϣ
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
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
//				FileWriter writer = new FileWriter("test1.xml");
//
//				XMLOutputter put = new XMLOutputter(Format.getPrettyFormat());
//				
//				put.output(doc, writer);
				
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
						logout(personal, id);
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
				//���doc
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
	
	//��½��һ��Ҫ�õ�����Ϣ�У��û�������Ϣ�������б����ԣ���Ӻ��ѵ���Ϣ
	@SuppressWarnings({ "unchecked", "unused" })
	private void login(Element personal, String id)
	{
		String type = personal.getChildText("type");
		//��½ʧ��
		if(type.equals("fail"))
		{
			String str = personal.getChildText("worning");
			System.out.println(str);
			//��½ʧ�ܣ�strΪʧ��ԭ��
			//�����д����............
		}
		else if(type.equals("sucess"))
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
			    			//�˼�Ҫ�����ˣ���ô��
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
	
	//ע��
	private void register(Element personal, String id)
	{
		
	}
	
	
	private void change(Element personal, String id)
	{
		
	}
}
