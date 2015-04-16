package util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class User
{
    /***�������ڸ����û�����
     *  ������Ҫ����������̬��userʵ����һ��������Ϣ(getUser)��һ��������Ϣ(setUser)
     *  ���ڽ��棬��������������ı�ʱ�ı�setUser���趨һ���̼߳���getUser�����getUser�����ı䣬��������ݡ�
     *  ���ں�̨���յ�����ʱ�ı�getUser����һ���̼߳���setUser�����setUser�����仯���������ݡ�
     *  
     *  Ӧ�ý�һ���������Լ��ͺ��ѵ�id
     *  
     *  ������get��userID model matchList isOnline
     *  
     */
	
	public static User getUser = new User();//���ڽ�������
	public static User setUser = new User();//���ڷ�������
	
	public static int uiWorking = 0;
	public static int bgWorking = 0;
	
	public static Boolean isChange = false;//�Ƿ��иı䣬Ӧ���ȼ��˱�����	������棬��˶����и��¡�����Ǽ٣���û�б仯�������κβ�������Ӧ���Ȱ�isChange��Ϊ�档
	
	
	/**
	 * ���ں��ѣ��ж�ʱ�����ߡ�
	 * ��������Լ���˵��Ϊ�������Ѿ���¼�ˡ�
	 * ���ڵ�¼ע�������inOnlineΪ�١�Ӧ����setUser������id���������Ϣ�����get�з���id�����Ƶ���Ϣ�����ǳɹ������Դ��������ˡ����û��id������id����û���û��������������
	 * ����ע����set�а�isOnline��Ϊ�٣��õ�get������isOnlineΪ�٣�idΪ��ʱ��ע���ɹ���
	 * �������get�У�isOnlineΪ�٣���id�������ο��ˡ�
	 */
	private Boolean isOnline = false;
	
	//�Ƿ����ƥ�䣬��Ҫ����ƥ���ʱ�򣬸ĳ��档����ǵ����Ͳ�������ֱ�Ӵ���Ϸҳ�档���������ģʽ��������ģʽmodel����get�е�isMacth��Ϊ���Ǽ��ɴ���Ϸ���档
	private Boolean isMatch = false;//�Ƿ�ʼ����ƥ�䣬����Ϸ

    //������Ϸ��ģʽ
	private Model model = null;//ƥ��ģʽ
	
//�û���Ϣ
	private int userID;//�û�id
	private String userPassword;//�û�����
	private String userName;//�û���
	private int userPhoto;//ͷ���
	private HashMap<Model, Integer> modelMap = null;//���ս����keyΪģʽ��integerΪʤ�ʡ�
	
//��ӻ�ɾ�����ѡ�������Ӻ���ʱ��setUser.add��Ϊ�档ɾ��ʱ��setUser.deleteΪ�档�������Ϊ����ʱ��getUser.addΪ�档��ɾ��ʱ��Ϊ�档
//���ȷ����Ӻ��ѣ�ͬ������serUser�ϼ���id�Ͱ�add��Ϊ�档Ȼ��������ͻᷢ�غ������ϡ�
	private boolean add = false;
	private boolean delete = false;
	
	
//�����б�,����ͨ��user��������ź������ϡ������ڽ���userʵ���У����б����ڴ����Ӻ���  
	private ArrayList<User> list = null;

	private HashMap<Calendar, String> messageMap = null;//��������¼��CalendarΪʱ���ࡣ
	

	/**
	 *  isTeam�����ж��Ƿ�����ˡ����Ҫ������Ԫ�أ�model��isTeam��modellist��������������ĵ�һλ��
	 *  ��Ϊ������Ҫ������set��ѡģʽmodel��isTeam�ó��棬������д����Ӧ�ĺ���id����̨��ȡ������ж���գ�Ȼ���ȡget�У����isTeamδ��
	 *  ����Ϊ�Լ�������ӳɹ��ˣ�����������id���ǽ�������ĺ��ѡ���model������ŵ�set�У����get��
	 *  �¼�����ʱ��set�ж�ȡ�����model���бȽϣ�д��set��
	 * 
	 *  ��Ϊ�Ƿ��������get��model�У�isTeamΪ�棬���������Լ����Լ����Ƿ������Ǳ������ˡ�
	 *  ������ܣ����ǰ�model��isTeam������д��set�У���get��ռ��ɣ���̨��ȡ���set�е�isTeam��Ϊ�١�
	 *  �ܾ������ȡ����ӵķ�����
	 *  ����г�Ա�䶯����get��isTeam��Ϊ�棬Ȼ�����set�����������ҷ���һ���������ɳ�Ա�䶯�ˡ��и���ģʽ�����յ���ķ�������������
	 *  
	 *  ���ȡ�����ֻ��Ҫ��set��isTeamΪ�棬model�У�����ֻд������get��model�У�isTeamΪ�棬������ֻ���Լ�������ӽ��������set�е������model��
	 *  
	 *  �ܵ���˵����ӵ��б��Ǵ����set�еġ�
	 **/
	private Boolean isTeam = false;//�Ƿ����
	private ArrayList<Integer> matchList = null;//�����ԱID����
	
	
	//�������getUser
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
	
	//�������setUser()
	public void clearSet()
	{
		clearGet();
		isOnline = false;
		userID = 0;
		model = null;
		matchList = null;
	}
	
	
	//�����Ǹ�������get��set����
	
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
