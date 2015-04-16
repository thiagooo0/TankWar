package util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class User
{
    /***此类用于更新用户数据
     *  设想中要生成两个静态的user实例，一个接受信息(getUser)，一个发送信息(setUser)
     *  对于界面，监听器监监听到改变时改变setUser，设定一个线程监听getUser，如果getUser发生改变，则更新数据。
     *  对于后台，收到数据时改变getUser，设一个线程监听setUser，如果setUser发生变化，则发送数据。
     *  
     *  应该建一个链表存放自己和好友的id
     *  
     *  保留：get：userID model matchList isOnline
     *  
     */
	
	public static User getUser = new User();//用于接受数据
	public static User setUser = new User();//用于发送数据
	
	public static int uiWorking = 0;
	public static int bgWorking = 0;
	
	public static Boolean isChange = false;//是否有改变，应该先检查此变量。	如果是真，则此对象有更新。如果是假，则没有变化。对于任何操作，都应该先把isChange改为真。
	
	
	/**
	 * 对于好友，判断时候在线。
	 * 如果对于自己来说。为真则是已经登录了。
	 * 对于登录注册操作，inOnline为假。应该在setUser中填上id，密码等信息。如果get中返回id，名称等信息，则是成功，可以打开主界面了。如果没有id，则是id错误。没有用户名则是密码错误。
	 * 对于注销，set中把isOnline设为假，得到get方法中isOnline为假，id为空时即注销成功。
	 * 所以如果get中，isOnline为假，无id，就是游客了。
	 */
	private Boolean isOnline = false;
	
	//是否进行匹配，当要进行匹配的时候，改成真。如果是单机就不用啦，直接打开游戏页面。如果是其他模式，还填上模式model。当get中的isMacth变为真是即可打开游戏界面。
	private Boolean isMatch = false;//是否开始进行匹配，或游戏

    //比配游戏的模式
	private Model model = null;//匹配模式
	
//用户信息
	private int userID;//用户id
	private String userPassword;//用户密码
	private String userName;//用户名
	private int userPhoto;//头像号
	private HashMap<Model, Integer> modelMap = null;//存放战绩，key为模式，integer为胜率。
	
//添加或删除好友。当你添加好友时，setUser.add设为真。删除时，setUser.delete为真。当被添加为好友时，getUser.add为真。被删除时，为真。
//如果确认添加好友，同样，在serUser上加上id和把add设为真。然后服务器就会发回好友资料。
	private boolean add = false;
	private boolean delete = false;
	
	
//好友列表,里面通过user对象来存放好友资料。在用于接受user实例中，此列表用于存放添加好友  
	private ArrayList<User> list = null;

	private HashMap<Calendar, String> messageMap = null;//存放聊天记录，Calendar为时间类。
	

	/**
	 *  isTeam用来判断是否组队了。组队要填三个元素，model，isTeam，modellist。房主放在链表的第一位。
	 *  作为房主。要邀请则set中选模式model，isTeam该成真，链表中写上相应的好友id，后台读取后把所有都清空，然后读取get中，如果isTeam未真
	 *  房主为自己则是组队成功了，链表后面跟的id则是接受邀请的好友。把model和链表放到set中，清空get。
	 *  新加邀请时从set中读取链表和model进行比较，写到set上
	 * 
	 *  作为非房主。如果get到model有，isTeam为真，链表中有自己且自己不是房主则是被邀请了。
	 *  如果接受，则是把model，isTeam，链表写到set中，把get清空即可，后台读取后把set中的isTeam设为假。
	 *  拒绝则采用取消组队的方法。
	 *  如果有成员变动，则get中isTeam变为真，然后如果set中有链表，而且房主一样，则是由成员变动了。有更改模式或者收到别的房主邀请的情况。
	 *  
	 *  如果取消组队只需要把set中isTeam为真，model有，链表只写房主。get到model有，isTeam为真，链表中只有自己则是组队结束，清空set中的链表和model。
	 *  
	 *  总的来说，组队的列表是存放在set中的。
	 **/
	private Boolean isTeam = false;//是否组队
	private ArrayList<Integer> matchList = null;//队伍成员ID链表
	
	
	//用于清空getUser
	public void clearGet()
	{
		isChange = false;
		isMatch = false;
		userPassword = null;
		userName = null;
		userPhoto = 0;
		modelMap = null;
		add = false;
		delete = false;
		list = null;
		messageMap = null;
		isTeam = false;
	}
	
	//用于清空setUser()
	public void clearSet()
	{
		clearGet();
		isOnline = false;
		userID = 0;
		model = null;
		matchList = null;
	}
	
	
	//下面是各变量的get，set方法
	
	public Boolean getIsMatch()
	{
		return isMatch;
	}

	public void setIsMatch(Boolean isMatch)
	{
		this.isMatch = isMatch;
	}

	public Model getmodel()
	{
		return model;
	}

	public void setmodel(Model model)
	{
		this.model = model;
	}

	public String getUserPassword()
	{
		return userPassword;
	}

	public void setUserPassword(String userPassword)
	{
		this.userPassword = userPassword;
	}

	public boolean isAdd()
	{
		return add;
	}

	public void setAdd(boolean add)
	{
		this.add = add;
	}

	public boolean isDelete()
	{
		return delete;
	}

	public void setDelete(boolean delete)
	{
		this.delete = delete;
	}

	public Boolean getIsTeam()
	{
		return isTeam;
	}

	public void setIsTeam(Boolean isTeam)
	{
		this.isTeam = isTeam;
	}

	public ArrayList<Integer> getmodelList()
	{
		return matchList;
	}

	public void setmodelList(ArrayList<Integer> modelList)
	{
		this.matchList = modelList;
	}
	public Boolean getIsOnline()
	{
		return isOnline;
	}
	public void setIsOnline(Boolean isOnline)
	{
		this.isOnline = isOnline;
	}
	public Boolean getIsChange()
	{
		return isChange;
	}
	public void setIsChange(Boolean isChange)
	{
		this.isChange = isChange;
	}
	public int getUserID()
	{
		return userID;
	}
	public void setUserID(int userID)
	{
		this.userID = userID;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public int getUserPhoto()
	{
		return userPhoto;
	}
	public void setUserPhoto(int userPhoto)
	{
		this.userPhoto = userPhoto;
	}
	public ArrayList<User> getList()
	{
		return list;
	}
	public void setList(ArrayList<User> list)
	{
		this.list = list;
	}

	public HashMap<Calendar, String> getMessageMap()
	{
		return messageMap;
	}

	public void setMessageMap(HashMap<Calendar, String> messageMap)
	{
		this.messageMap = messageMap;
	}

	public HashMap<Model, Integer> getmodelMap()
	{
		return modelMap;
	}

	public void setmodelMap(HashMap<Model, Integer> modelMap)
	{
		this.modelMap = modelMap;
	}
	
	
}
