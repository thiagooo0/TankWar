package Mygame;

import java.awt.*;

public class Drawtank {
	
     public Drawtank(int x ,int y, Graphics g ,int color, int direct){
    	 //判断类型
    	switch(color){
    	//敌方坦克
    	case 0 :
    		g.setColor(Color.CYAN);
    		break ;
    	//我的坦克
    	case 1 :
    		g.setColor(Color.YELLOW);
    		break ;
    	}
    	//判断方向
    	switch(direct){
    	//向上
    	case 0 :
    		g.fill3DRect(x, y, 5, 25, false);
    		g.fill3DRect(x+20, y, 5, 25, false);
    		g.fill3DRect(x+5, y+5,15, 15, false);
    		g.fillOval(x+7, y+7, 10, 10);
    		g.drawLine(x+12, y+7, x+12, y-5);
    		break ;
    	//向左
    	case 1 :
    		g.fill3DRect(x, y, 25, 5, false);
    		g.fill3DRect(x, y+20, 25, 5, false);
    		g.fill3DRect(x+5, y+5,15, 15, false);
    		g.fillOval(x+7, y+7, 10, 10);
    		g.drawLine(x+12, y+12, x-7, y+12);
    		break ;
    	//向右
    	case 2 :
    		g.fill3DRect(x, y, 25, 5, false);
    		g.fill3DRect(x, y+20, 25, 5, false);
    		g.fill3DRect(x+5, y+5,15, 15, false);
    		g.fillOval(x+7, y+7, 10, 10);
    		g.drawLine(x+12, y+12, x+31, y+12);
    		break ;
    	//向下
    	case 3 :
    		g.fill3DRect(x, y, 5, 25, false);
    		g.fill3DRect(x+20, y, 5, 25, false);
    		g.fill3DRect(x+5, y+5,15, 15, false);
    		g.fillOval(x+7, y+7, 10, 10);
    		g.drawLine(x+12, y+10, x+12, y+29);
    		break ;
    	}
     }
     
}
