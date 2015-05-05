package Mygame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class Mypanel extends JPanel implements KeyListener,Runnable{
	int x = 0 ;
	int y = 0 ;
	int color ;
	int direct = 0 ;
	//�����ҵ�̹��
    static Mytank mt = null ;
    Drawtank dt = null ;
    Drawmap dm = null ;
    //����ľͷ��
    Vector<Wood> wds = new Vector<Wood>() ;
    int woodSize = 20 ;
   
    //����̹����
    Vector<OtherTank> ots = new Vector<OtherTank>();
    int enSize = 5 ;
    
    //����ը����
    Vector<Boom> booms = new Vector<Boom>();
    
    
    
    //���屬ըͼƬ
    //Image image1 = null ;
    
	public Mypanel(){
		x = 200 ;
		y = 160 ;
		//��ʼ���ҵ�̹��
		
		mt = new Mytank(x,y);
		mt.setColor(1);
		//��ʼ������̹��
		for(int i = 0 ; i<enSize ; i++){
			//����һ�����˵�̹��
			OtherTank ot  = new OtherTank((i+1)*50,0);
			
			ot.setColor(0);
			ot.setDirect(3);
			//��MyPane�ĵ���̹�����������õ���̹��
			ot.setOts(ots);
			mt.setOts(ots);
			ot.setWds(wds);
			//����̹��
			Thread t = new Thread(ot);
			t.start();
			
			Shot s = new Shot(ot.x+10,ot.y+30,3,4);
			ot.ss.add(s) ;
			Thread t2 = new Thread(s);
			t2.start();
			//����
			ots.add(ot);
		}
		//��ʼ��ľͷ
		for(int i = 0 ; i<woodSize ; i++){
			Wood wd = new Wood((i+2)*20,50);
			wds.add(wd);
			
			
		}
		
		//��ʼ��ͼƬ
		
		//image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource(name));
		
	}
	//����
	public void paint(Graphics g){
		super.paint(g);
		g.fillRect(0, 0, 500, 500);
		//System.out.print(a);
		if(mt.isLive){
		dt = new Drawtank(mt.getX(),mt.getY(), g , mt.getColor() , mt.getDirect() );
		}
		//dm = new Drawmap(0,g,100,100);
		
		//��ss��ȡ��ÿ���ӵ��������ӵ�
		for(int i=0;i<mt.ss.size();i++)
		{
			
			Shot myShot = mt.ss.get(i);
		//���ӵ���
		   if(myShot!=null&&myShot.isLive==true){
			g.draw3DRect(myShot.x, myShot.y, 1, 1, false);
		   }
		   if(myShot.isLive==false){
			   mt.ss.remove(myShot);
		   }
		}
		
		//����ը��
		for(int i = 0 ; i<booms.size();i++){
			Boom b = booms.get(i);
			if(b.life>6){
				//����ͼƬ
				
				g.drawOval(b.x, b.y, 50, 50);
			}else if(b.life>3){
				
				g.drawOval(b.x, b.y, 30, 30);
			}else{
				
				g.drawOval(b.x, b.y, 10, 10);
			}
			b.lifeDown();
			
			if(b.life==0){
				booms.remove(b);
			}
			
			
		}
		//��ľͷ
		for(int i = 0 ; i<wds.size() ; i++){
			Wood wd = wds.get(i);
			
			if(wd.isLive){
				
				dm = new Drawmap(wd.type , g , wd.getX() , wd.getY());
				//dm = new Drawmap(wd.type+1, g , wd.getX(),wd.getY()+4);
			}
		}
		//����̹��
		for(int i = 0 ; i<ots.size(); i++){
			
			OtherTank ot = ots.get(i);
			
			if(ot.isLive){
		    dt = new Drawtank(ot.getX(),ot.getY(),g,ot.getColor(),ot.getDirect());
			//˳�㻭�����ӵ�
		    for(int j =0 ;j<ot.ss.size() ;j++){
		    	Shot otShot = ot.ss.get(j);
		    	if(otShot.isLive){
					g.draw3DRect(otShot.x, otShot.y, 1, 1, false);
				   }
		    	else{
					   ot.ss.remove(otShot);
				   }
		    }
			
			}
			}
		
	}

	public void keyTyped(KeyEvent e) {
		
		
	}
	
	public void keyPressed(KeyEvent e) {
		//����
		if(e.getKeyCode()==KeyEvent.VK_UP){
			
			this.mt.setDirect(0);
			this.mt.moveUp();
	
			//����
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			this.mt.setDirect(3);
			this.mt.moveDown();
	
			//����
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			this.mt.setDirect(1);
			this.mt.moveLeft();
			//����
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			this.mt.setDirect(2);
			this.mt.moveRight();
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE )
		{  
			
			if(this.mt.ss.size()<=5){
			this.mt.shotOtherTank();
		  }
			
		}
		this.repaint();
	}
	
	public void keyReleased(KeyEvent e) {
		
	}
	//���ез�̹��
	 public void hitOtherTank(Shot s , OtherTank ot){
		 //�жϸ�̹�˷���
		 switch(ot.direct){
		 case 0:
		 case 3:
			 if(s.x>ot.x&&s.x<ot.x+25&&s.y>ot.y&&s.y<ot.y+25){
				 //����
				 s.isLive = false ;
				 ot.isLive = false ;
				 //����ը��������Vector
				 Boom b = new Boom(ot.x,ot.y);
				 booms.add(b);
			 }
			 break ;
		 case 1:
		 case 2:
			 if(s.x>ot.x&&s.x<ot.x+25&&s.y>ot.y&&s.y<ot.y+20){
				 //����
				 s.isLive = false ;
				 ot.isLive = false ;
				 //����ը��������Vector
				 Boom b = new Boom(ot.x,ot.y);
				 booms.add(b);
			 }
			 break ;
		 }
	 }
	 
	 public void hitMytank(Shot s , Mytank mt){
		 switch(mt.direct){
		 case 0:
		 case 3:
			 if(s.x>mt.x&&s.x<mt.x+25&&s.y>mt.y&&s.y<mt.y+25){
				 //����
				 s.isLive = false ;
				 mt.isLive = false ;
				 System.out.print("touch");
				 //����ը��������Vector
				 Boom b = new Boom(mt.x,mt.y);
				 booms.add(b);
			 }
			 break;
		 case 1:
		 case 2:
			 if(s.x>mt.x&&s.x<mt.x+25&&s.y>mt.y&&s.y<mt.y+20){
				 //����
				 s.isLive = false ;
				 mt.isLive = false ;
				 System.out.print("touch le");
				 //����ը��������Vector
				 Boom b = new Boom(mt.x,mt.y);
				 booms.add(b);
			 }
			 break;
		 }
	 
	 }
	@Override
	public void run() {
		
		while(true){
		    try{
		    	Thread.sleep(100);
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
		    //�ж��Ƿ����
		    for(int i = 0 ; i<mt.ss.size();i++){
		    	//�����ӵ�
		    	Shot myShot = mt.ss.get(i);
		    	if(myShot.isLive){
		    		//��������̹��
		    		for(int j = 0 ; j<ots.size();j++){
		    			OtherTank ot = ots.get(j);
		    			
		    			if(ot.isLive){
		    				this.hitOtherTank(myShot,ot);
		    			}
		    		}
		    	}
		    }
		    for(int j = 0 ; j<ots.size();j++){
    			OtherTank ot = ots.get(j);
    			for(int a = 0 ; a<ot.ss.size();a++){
    				Shot otShot = ot.ss.get(a);
    				if(otShot.isLive){
    					if(mt.isLive){
    						this.hitMytank(otShot, mt);
    					}
    				}
    				
    				
    			}
		    }
		
			
		    
		    
		    
		    this.repaint();
		}
	}
	
	
}
