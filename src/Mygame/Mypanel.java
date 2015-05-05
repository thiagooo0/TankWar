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
	//定义我的坦克
    static Mytank mt = null ;
    Drawtank dt = null ;
    Drawmap dm = null ;
    //建立木头组
    Vector<Wood> wds = new Vector<Wood>() ;
    int woodSize = 20 ;
   
    //定义坦克组
    Vector<OtherTank> ots = new Vector<OtherTank>();
    int enSize = 5 ;
    
    //定义炸弹组
    Vector<Boom> booms = new Vector<Boom>();
    
    
    
    //定义爆炸图片
    //Image image1 = null ;
    
	public Mypanel(){
		x = 200 ;
		y = 160 ;
		//初始化我的坦克
		
		mt = new Mytank(x,y);
		mt.setColor(1);
		//初始化敌人坦克
		for(int i = 0 ; i<enSize ; i++){
			//创建一辆敌人的坦克
			OtherTank ot  = new OtherTank((i+1)*50,0);
			
			ot.setColor(0);
			ot.setDirect(3);
			//将MyPane的敌人坦克向量交给该敌人坦克
			ot.setOts(ots);
			mt.setOts(ots);
			ot.setWds(wds);
			//启动坦克
			Thread t = new Thread(ot);
			t.start();
			
			Shot s = new Shot(ot.x+10,ot.y+30,3,4);
			ot.ss.add(s) ;
			Thread t2 = new Thread(s);
			t2.start();
			//加入
			ots.add(ot);
		}
		//初始化木头
		for(int i = 0 ; i<woodSize ; i++){
			Wood wd = new Wood((i+2)*20,50);
			wds.add(wd);
			
			
		}
		
		//初始化图片
		
		//image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource(name));
		
	}
	//画笔
	public void paint(Graphics g){
		super.paint(g);
		g.fillRect(0, 0, 500, 500);
		//System.out.print(a);
		if(mt.isLive){
		dt = new Drawtank(mt.getX(),mt.getY(), g , mt.getColor() , mt.getDirect() );
		}
		//dm = new Drawmap(0,g,100,100);
		
		//从ss中取出每颗子弹并画出子弹
		for(int i=0;i<mt.ss.size();i++)
		{
			
			Shot myShot = mt.ss.get(i);
		//画子弹啦
		   if(myShot!=null&&myShot.isLive==true){
			g.draw3DRect(myShot.x, myShot.y, 1, 1, false);
		   }
		   if(myShot.isLive==false){
			   mt.ss.remove(myShot);
		   }
		}
		
		//画出炸弹
		for(int i = 0 ; i<booms.size();i++){
			Boom b = booms.get(i);
			if(b.life>6){
				//画个图片
				
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
		//画木头
		for(int i = 0 ; i<wds.size() ; i++){
			Wood wd = wds.get(i);
			
			if(wd.isLive){
				
				dm = new Drawmap(wd.type , g , wd.getX() , wd.getY());
				//dm = new Drawmap(wd.type+1, g , wd.getX(),wd.getY()+4);
			}
		}
		//敌人坦克
		for(int i = 0 ; i<ots.size(); i++){
			
			OtherTank ot = ots.get(i);
			
			if(ot.isLive){
		    dt = new Drawtank(ot.getX(),ot.getY(),g,ot.getColor(),ot.getDirect());
			//顺便画敌人子弹
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
		//向上
		if(e.getKeyCode()==KeyEvent.VK_UP){
			
			this.mt.setDirect(0);
			this.mt.moveUp();
	
			//向下
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			this.mt.setDirect(3);
			this.mt.moveDown();
	
			//向左
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			this.mt.setDirect(1);
			this.mt.moveLeft();
			//向右
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
	//打中敌方坦克
	 public void hitOtherTank(Shot s , OtherTank ot){
		 //判断该坦克方向
		 switch(ot.direct){
		 case 0:
		 case 3:
			 if(s.x>ot.x&&s.x<ot.x+25&&s.y>ot.y&&s.y<ot.y+25){
				 //击中
				 s.isLive = false ;
				 ot.isLive = false ;
				 //创建炸弹，放入Vector
				 Boom b = new Boom(ot.x,ot.y);
				 booms.add(b);
			 }
			 break ;
		 case 1:
		 case 2:
			 if(s.x>ot.x&&s.x<ot.x+25&&s.y>ot.y&&s.y<ot.y+20){
				 //击中
				 s.isLive = false ;
				 ot.isLive = false ;
				 //创建炸弹，放入Vector
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
				 //击中
				 s.isLive = false ;
				 mt.isLive = false ;
				 System.out.print("touch");
				 //创建炸弹，放入Vector
				 Boom b = new Boom(mt.x,mt.y);
				 booms.add(b);
			 }
			 break;
		 case 1:
		 case 2:
			 if(s.x>mt.x&&s.x<mt.x+25&&s.y>mt.y&&s.y<mt.y+20){
				 //击中
				 s.isLive = false ;
				 mt.isLive = false ;
				 System.out.print("touch le");
				 //创建炸弹，放入Vector
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
		    //判断是否击中
		    for(int i = 0 ; i<mt.ss.size();i++){
		    	//遍历子弹
		    	Shot myShot = mt.ss.get(i);
		    	if(myShot.isLive){
		    		//遍历敌人坦克
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
