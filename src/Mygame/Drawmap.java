package Mygame;

import java.awt.Color;
import java.awt.Graphics;

public class Drawmap {
	 
	 public Drawmap(int type , Graphics g , int  x , int y){
		 switch(type){
		 case 0:
			g.setColor(Color.GRAY) ;
			g.fillRect(x, y, 10, 10);
		    g.setColor(Color.WHITE);
			g.drawLine(x+5, y, x, y+5);
			g.drawLine(x+5, y, x+10, y+5);
			g.drawLine(x, y+5, x+5, y+10); 
			g.drawLine(x+10, y+5, x+5, y+10);
			break;
			
		 case 1:
			g.setColor(Color.orange);
			g.fillRect(x, y, 20, 5);
			g.setColor(Color.yellow);
			g.drawLine(x+6, y, x+6, y+4);
			g.drawLine(x+14, y, x+14, y+4);
			g.drawLine(x, y, x, y+4);
			g.drawLine(x+20, y, x+20, y+4);
			g.drawLine(x, y, x+20, y);
			g.drawLine(x, y+4, x+20, y+4);
			
			g.setColor(Color.orange);
			g.fillRect(x, y+4, 20, 5); 
			g.setColor(Color.yellow);
			g.drawLine(x+10,y+4,x+10,y+8);
			g.drawLine(x, y+4, x, y+8);
			g.drawLine(x+20, y+4, x+20, y+8);
			g.drawLine(x, y+4, x+20, y+4);
			g.drawLine(x, y+8, x+20, y+8);
			
			g.setColor(Color.orange);
			g.fillRect(x, y+8, 20, 5);
			g.setColor(Color.yellow);
			g.drawLine(x+6, y+8, x+6, y+12);
			g.drawLine(x+14, y+8, x+14, y+12);
			g.drawLine(x, y+8, x, y+12);
			g.drawLine(x+20, y+8, x+20, y+12);
			g.drawLine(x, y+8, x+20, y+8);
			g.drawLine(x, y+12, x+20, y+12);
			
			g.setColor(Color.orange);
			g.fillRect(x, y+12, 20, 5); 
			g.setColor(Color.yellow);
			g.drawLine(x+10,y+12,x+10,y+16);
			g.drawLine(x, y+12, x, y+16);
			g.drawLine(x+20, y+12, x+20, y+16);
			g.drawLine(x, y+12, x+20, y+12);
			g.drawLine(x, y+16, x+20, y+16);
			
			g.setColor(Color.orange);
			g.fillRect(x, y+16, 20, 5);
			g.setColor(Color.yellow);
			g.drawLine(x+6, y+16, x+6, y+20);
			g.drawLine(x+14, y+16, x+14, y+20);
			g.drawLine(x, y+16, x, y+20);
			g.drawLine(x+20, y+16, x+20, y+20);
			g.drawLine(x, y+16, x+20, y+16);
			g.drawLine(x, y+20, x+20, y+20);
			break;
		 case 2:
			g.setColor(Color.orange);
			g.fillRect(x, y, 20, 5); 
			g.setColor(Color.yellow);
			g.drawLine(x+10,y,x+10,y+4);
			g.drawLine(x, y, x, y+4);
			g.drawLine(x+20, y, x+20, y+4);
			g.drawLine(x, y, x+20, y);
			g.drawLine(x, y+4, x+20, y+4);
			break;
		 }
		 
		 
		 


    	}
     
}
