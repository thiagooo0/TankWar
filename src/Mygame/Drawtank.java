package Mygame;

import java.awt.*;

public class Drawtank {
	
     public Drawtank(int x ,int y, Graphics g ,int color, int direct){
    	 //�ж�����
    	switch(color){
    	//�з�̹��
    	case 0 :
    		g.setColor(Color.CYAN);
    		break ;
    	//�ҵ�̹��
    	case 1 :
    		g.setColor(Color.YELLOW);
    		break ;
    	}
    	//�жϷ���
    	switch(direct){
    	//����
    	case 0 :
    		g.fill3DRect(x, y, 5, 25, false);
    		g.fill3DRect(x+20, y, 5, 25, false);
    		g.fill3DRect(x+5, y+5,15, 15, false);
    		g.fillOval(x+7, y+7, 10, 10);
    		g.drawLine(x+12, y+7, x+12, y-5);
    		break ;
    	//����
    	case 1 :
    		g.fill3DRect(x, y, 25, 5, false);
    		g.fill3DRect(x, y+20, 25, 5, false);
    		g.fill3DRect(x+5, y+5,15, 15, false);
    		g.fillOval(x+7, y+7, 10, 10);
    		g.drawLine(x+12, y+12, x-7, y+12);
    		break ;
    	//����
    	case 2 :
    		g.fill3DRect(x, y, 25, 5, false);
    		g.fill3DRect(x, y+20, 25, 5, false);
    		g.fill3DRect(x+5, y+5,15, 15, false);
    		g.fillOval(x+7, y+7, 10, 10);
    		g.drawLine(x+12, y+12, x+31, y+12);
    		break ;
    	//����
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
