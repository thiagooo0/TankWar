package UI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class User
{

	public Boolean isOnline;//�ж�ʱ������
	public Boolean isChange;//�Ƿ��иı�
	public int userID;//�û�id
	public String userName;//�û���
	public String password;//����
	public int userPhoto;//ͷ���
	public ArrayList<User> list;//�����б�,����ͨ��user��������ź������ϣ�
	public HashMap<Achievement, Integer> map;//���ս����keyΪģʽ��integerΪһ��ʤ�ʡ�
	
	public User(String user_name,String password,int userID){
		this.userName = user_name;
		this.password = password;
		this.userID = userID;
		userPhoto = 1;
		map = new HashMap<Achievement,Integer>();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*void test()
	{
		User user = new User();
		list = new ArrayList<>();
		list.add(user);
		int size = list.size();
		user = list.get(1);
		list.clear();
		
		map.put(Achievement.solo_advanture, 1);
		
		Iterator<Entry<Achievement, Integer>> ite = map.entrySet().iterator();
		while(ite.hasNext())
		{
			Entry<Achievement, Integer> entry = ite.next();		
			entry.getKey();
			entry.getValue();
		}

		
		
	}*/
	
	
	
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
	public HashMap<Achievement, Integer> getMap()
	{
		return map;
	}
	public void setMap(HashMap<Achievement, Integer> map)
	{
		this.map = map;
	}
	
	
}