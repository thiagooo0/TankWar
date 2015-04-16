package server;
//�����û�������xml�ĵ�.
//Ӧ�����ý�������;֮���ֱ���������ݿ���д���Ȼ�󷵻���Ϣ��
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
	public static HashMap<String, Socket> userMap = new HashMap<String, Socket>();//�洢�û��Ͷ�Ӧ���׽���
    private Socket socket;
    private Document doc;
    private String id;
	
	//���û����жϣ��Ƿ��һ�ε�½����Ҫ��ʲô��������
	public void init(Socket socket)
	{
		try
		{
			this.socket = socket;
			//�ȵ�������
			InputStream is = socket.getInputStream();
			ObjectInputStream oos = new ObjectInputStream(is);
			 //�õ��ļ�����
			doc = (Document)oos.readObject();
			Element user = doc.getRootElement();
			id = user.getAttributeValue("id");
			
			//�Ƿ������Ϣ���޸�
			 Element personal = user.getChild("personal"); 
			if(personal != null)
			{
				Element target = personal.getChild("target");
				Element type = personal.getChild("type");
				
				if("ask".equals(type.getText()))
				{
					String targetText = target.getText();

					//��½
					if("login".equals(targetText))
					{
						login(id, personal);
						return;
					}
					//ע��
					else if("register".equals(targetText))
					{
						register();
                        return;
					}
					//�ǳ�
					else if("logout".equals(targetText))
					{
						logout();
						return;
					}
					//�ı�
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
	//��½
	private void login(String id, Element personal)
	{
		Element password = personal.getChild("password");
		//�����½
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
