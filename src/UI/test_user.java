package UI;

public class test_user {
	
	//������   ��ţ��û��������룬ͷ����Ϊ��û��ƺû�ͷ���ܣ����Ի�ûʵ�����������ԣ�
	//marks ս��        fri �����б�
	private int id;
	private String user_name;
	private String password;
	//private int head;
	private int[] marks = {0,0};
	private boolean state;
	private int[] fri = new int[100];
	
	
	test_user(int id,String user_name,String password){
		this.id = id;
		this.user_name = user_name;
		this.password = password;
		this.state = false;
		for(int j = 0; j<100 ;j++){
			fri[j] = 0;
		}
	}
	
	
	public void SetUser_name(String user_name){
		this.user_name = user_name;
	}
	
	
	public String getUser_name(){
		return user_name;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return password;
	}
	
	
	//�޸�ս��
	public void setMarks(int i){
		if(i == 1){
			this.marks[0]++;
		}
		else if(i == 2){
			this.marks[1]++;
		}
	}
	
	public int[] getMarks(){
		return marks;
	}
	
	
	//��Ӻ���
	public void addFri(int id){
		int i;
		for(i = 0;fri[i] != 0;i++){
			
		}
		
		fri[i] = id;
	}
	
	
	//��ú��ѵ��б�
	public int[] getFri(){
		return fri;
	}
	
	public void display(){
		System.out.println(id);
		System.out.println(user_name);
		System.out.println(password);
		System.out.println(marks[0] + " " + marks[1]);
		System.out.println(state);
		for(int i = 0;fri[i] != 0;i++){
			System.out.println(fri[i]);
		}
	}
	
	
	
	
	public static void main(String args[]){
		test_user u1 = new test_user(1001,"oye","dianjiea");
		u1.addFri(1004);
		u1.addFri(1005);
		u1.display();
	}
}
