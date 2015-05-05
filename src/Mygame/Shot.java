package Mygame;

public class Shot implements Runnable{
    int x;
    int y;
    int direct;
    int speed = 3 ;
    boolean isLive = true ;
    public Shot(int x ,int y,int direct,int speed)
    {
    	this.x = x ;
    	this.y = y ;
    	this.direct = direct ;
    	this.speed = speed ;
    }
	@Override
	public void run() {
		while(true){
			try{
				Thread.sleep(50);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			switch(direct){
			case 0:
			     y-=speed;
			     break ;
			case 1:
				 x-=speed;
				 break;
			case 2:
				 x+=speed;
				 break;
			case 3:
				 y+=speed;
				 break;
			}
			//System.out.println(x+" "+y);
			//×Óµ¯ËÀÍö
			if(x<0||x>500||y<0||y>500){
			    this.isLive = false ;
			    break;
			}
			
		}
		
		
	}
}
