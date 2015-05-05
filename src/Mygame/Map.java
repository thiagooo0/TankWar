package Mygame;

import java.util.Vector;

public class Map {
	//µØÍ¼Î»ÖÃ
	int x = 0 ;
    int y = 0 ;
    //ÑÕÉ«
    int color ;
    
    public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	
	public Map(int x ,int y){
   	 this.x = x ;
   	 this.y = y ;
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


}
