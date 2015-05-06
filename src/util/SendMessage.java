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
		
		if(userID != null)
		{
			userEle.setAttribute("id", userID);//����id
		}
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
	
	public void register(String name, String photoID, String password)
	{
		Element personal  = new Element("personal");
		Element target  = new Element("target");
		target.addContent("register");//Ŀ���ǵ�½
		Element type = new Element("type");
		type.addContent("ask");
		
		Element password2 = new Element("password");
		password2.setText(password);//��������
		Element name2 = new Element("name");
		name2.setText(name);
		Element photoID2 = new Element("photoID");
		photoID2.setText(photoID);
		
		userEle.addContent(personal);
		personal.addContent(target).addContent(password2).addContent(type).addContent(name2).addContent(photoID2);
		
		this.send();
	}
	
	//�ǳ�
	public void logout()
	{
		//test,�����õ�ʱ��ǵ�ɾ��
//		userEle.setAttribute("id", "31415");
		
		//
		Element personal  = new Element("personal");
		Element target  = new Element("target");
		target.addContent("logout");//Ŀ���ǵ�½
		Element type = new Element("type");
		type.addContent("ask");
		
		userEle.addContent(personal);
		personal.addContent(target).addContent(type);
		
		this.send();
	}
	
	//������Ϣ
	public void change(String password, String newPassword, String name, String photoID)
	{
		//test,�����õ�ʱ��ǵ�ɾ��
//		userEle.setAttribute("id","31415");
		
		//
		Element personal = new Element("personal");
		Element target  = new Element("target");
		target.addContent("change");
		Element type = new Element("type");
		type.addContent("ask");
		
		userEle.addContent(personal);
		personal.addContent(target).addContent(type);
		
		if(password != null)
		{
			Element passwordEle = new Element("password");
			passwordEle.setText(password);
			Element newPasswordEle = new Element("newPassword");
			newPasswordEle.setText(newPassword);
			
			personal.addContent(passwordEle).addContent(newPasswordEle);
		}
		if(name != null)
		{
			Element nameEle = new Element("name");
			nameEle.setText(name);
			
			personal.addContent(nameEle);
		}
		if(photoID != null)
		{
			Element photoIDEle = new Element("photoID");
			photoIDEle.setText(photoID);
			
			personal.addContent(photoIDEle);
		}
		this.send();
	}
	
	public void friend(String fid, String ftype, String ftarget)
	{
		//test,�����õ�ʱ��ǵ�ɾ��
//		userEle.setAttribute("id","31420");
		
		Element friends = new Element("friends");
		Element friend = new Element("friend");
		Element type = new Element("type");
		type.setText(ftype);
		Element target  = new Element("target");
		target.setText(ftarget);
	    Element fidEle = new Element("id");
	    fidEle.setText(fid);
	    
	    userEle.addContent(friends);
	    friends.addContent(friend);
	    friend.addContent(type).addContent(target).addContent(fidEle);
	    
	    this.send();
	   
	}
	
	public void showFriend(String fid)
	{
		friend(fid, "ask", "show");
		
//		Element friends = new Element("friends");
//		Element friend = new Element("friend");
//		Element type = new Element("type");
//		type.setText("ask");
//		Element target  = new Element("target");
//		target.setText("show");
//	    Element fidEle = new Element("id");
//	    fidEle.setText(fid);
	    
//	    userEle.addContent(friends);
//	    friends.addContent(friend);
//	    friend.addContent(type).addContent(target).addContent(fidEle);
	    
	    this.send();
	}
	
	
	//��Ӻ���
	public void addFriend_Ask(String fid)
	{

		friend(fid, "ask", "add");
	}
	
	public void addFriend_Success(String fid)
	{
		friend(fid, "sucess", "add");
	}
	
	//ɾ������
	public void deleteFriend(String fid)
	{

		friend(fid, "ask", "delete");
	}
	
	
//	//������Ϸ
//    public void inviteFriend_Ask(String fid)
//    {
//    	friend(fid, "ask", "invite");
//    }
//    //��������
//    public void inviteFriend_Sucess(String fid)
//    {
//    	friend(fid, "success", "invite");
//    }
//    //�ܾ�����
//    public void inviteFriend_Fail(String fid)
//    {
//    	friend(fid, "fail", "invite");
//    }
//    
	//����
	

	
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
			System.out.println("send one message");
		} catch (IOException e)
		{
			//��ЩҪ��Ҫ�ڽ����������أ�
			System.out.println("send message failed");
		}
		return true;
	}	
}
