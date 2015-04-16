package util;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.jdom.Document;
import org.jdom.Element;

//�ͻ��˷�����Ϣ������������
//ʹ�÷���������ʵ�������ö�Ӧ����

public class SendMessage
{
	public static String userID ;//�����û���id
	private Document document;//�ĵ���
	private Element userEle;//����ĵ��ĸ�Ԫ��
	public SendMessage()
	{
		//����ʵ����ʱ���Ҫ
		document = new Document();
		userEle = new Element("user");//��Ԫ����user
		document.addContent(userEle);//�Ѹ�Ԫ�طŵ�
	}
	
	
	//�����½�¼�
	public void login(String id, String password)
	{
		userEle.setAttribute("id", id);//����id
		
		Element personal  = new Element("personal");
		Element target  = new Element("target");
		target.addContent("login");//Ŀ���ǵ�½
		Element type = new Element("type");
		type.addContent("ask");
		
		Element pw = new Element("password");
		pw.addContent(password);//��������
		
		userEle.addContent(personal);
		personal.addContent(target).addContent(pw).addContent(type);
		
		this.send();//biu������ȥ��
	}
	
	//�����ļ�
	public boolean send()
	{
		OutputStream os;
		try
		{
			//�õ�������
			os = ClientConnectThread.socket.getOutputStream();	
			
			ObjectOutputStream oos = new ObjectOutputStream(os);

			//�����ĵ������ȥ
			oos.writeObject(document);
		} catch (IOException e)
		{
			//��ЩҪ��Ҫ�ڽ����������أ�
			System.out.println("send message failed");
		}
		return true;
	}	

}
