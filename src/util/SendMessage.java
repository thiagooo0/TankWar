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
		
		if(userID != null)
		{
			userEle.setAttribute("id", userID);//填入id
		}
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
	
	public void register(String name, String photoID, String password)
	{
		Element personal  = new Element("personal");
		Element target  = new Element("target");
		target.addContent("register");//目标是登陆
		Element type = new Element("type");
		type.addContent("ask");
		
		Element password2 = new Element("password");
		password2.setText(password);//填入密码
		Element name2 = new Element("name");
		name2.setText(name);
		Element photoID2 = new Element("photoID");
		photoID2.setText(photoID);
		
		userEle.addContent(personal);
		personal.addContent(target).addContent(password2).addContent(type).addContent(name2).addContent(photoID2);
		
		this.send();
	}
	
	//登出
	public void logout()
	{
		//test,真正用的时候记得删掉
//		userEle.setAttribute("id", "31415");
		
		//
		Element personal  = new Element("personal");
		Element target  = new Element("target");
		target.addContent("logout");//目标是登陆
		Element type = new Element("type");
		type.addContent("ask");
		
		userEle.addContent(personal);
		personal.addContent(target).addContent(type);
		
		this.send();
	}
	
	//更改信息
	public void change(String password, String newPassword, String name, String photoID)
	{
		//test,真正用的时候记得删掉
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
		//test,真正用的时候记得删掉
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
	
	
	//添加好友
	public void addFriend_Ask(String fid)
	{

		friend(fid, "ask", "add");
	}
	
	public void addFriend_Success(String fid)
	{
		friend(fid, "sucess", "add");
	}
	
	//删除好友
	public void deleteFriend(String fid)
	{

		friend(fid, "ask", "delete");
	}
	
	
//	//邀请游戏
//    public void inviteFriend_Ask(String fid)
//    {
//    	friend(fid, "ask", "invite");
//    }
//    //接受邀请
//    public void inviteFriend_Sucess(String fid)
//    {
//    	friend(fid, "success", "invite");
//    }
//    //拒绝邀请
//    public void inviteFriend_Fail(String fid)
//    {
//    	friend(fid, "fail", "invite");
//    }
//    
	//聊天
	

	
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
			System.out.println("send one message");
		} catch (IOException e)
		{
			//这些要不要在界面上体现呢？
			System.out.println("send message failed");
		}
		return true;
	}	
}
