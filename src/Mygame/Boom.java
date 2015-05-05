package Mygame;

public class Boom {
     int x, y ;
     //炸弹生命
     int life = 9 ;
     boolean isLive = true ;
     public Boom(int x,int y){
    	 this.x=x;
    	 this.y=y;
     }
     //减少生命值
     public void lifeDown(){
    	 if(life>0){
    		 life--;
    	 }else{
    		 this.isLive = false ;
    	 }
     }
}
