package Mygame;

import java.util.Vector;

public class Tank {

	int x = 0 ;
     int y = 0 ;
     //坦克方向
     int direct = 0;
     //速度
     int speed = 2 ;    
     //颜色
     int color ;
     public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public Tank(int x ,int y){
    	 this.x = x ;
    	 this.y = y ;
     }
     public int getDirect() {
		return direct;
	}


	public void setDirect(int direct) {
		this.direct = direct;
	}


	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
  

    
     
    public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isTouchOtherTank() {
		// TODO Auto-generated method stub
		return false;
	}
	public void setOts(Vector<OtherTank> vv) {
		// TODO Auto-generated method stub
		
	}
}
