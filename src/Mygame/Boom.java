package Mygame;

public class Boom {
     int x, y ;
     //ը������
     int life = 9 ;
     boolean isLive = true ;
     public Boom(int x,int y){
    	 this.x=x;
    	 this.y=y;
     }
     //��������ֵ
     public void lifeDown(){
    	 if(life>0){
    		 life--;
    	 }else{
    		 this.isLive = false ;
    	 }
     }
}
