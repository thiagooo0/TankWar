package Mygame;

import java.util.*;

public class Mytank extends Tank{
    //���õط�̹��

	Vector<OtherTank> ots = new Vector<OtherTank>() ;
	//�õ�MyPanel�ĵ���̹������
		public void setOts(Vector<OtherTank> vv){
			this.ots = vv ;
		}
	//���
		boolean isLive = true ;
	//�ӵ�
	Shot s = null ;
	Vector<Shot> ss = new Vector<Shot>();
	int speed = 5 ; 
	public Mytank(int x ,int y){
		super(x,y);
		
	}
	
	//����
	public void shotOtherTank(){
		
		
		
		switch(this.direct){
		case 0:
			s = new Shot(x+10,y,0,speed);
			ss.add(s);
			break;
		case 1:
			s = new Shot(x,y+10,1,speed);
			ss.add(s);
			break;
		case 2:
			s = new Shot(x+20,y+10,2,speed);
			ss.add(s);
			break;
		case 3:
			s = new Shot(x+10,y+20,3,speed);
			ss.add(s);
			break;
		}
		
		Thread t = new Thread(s) ;
		t.start();
		
		
	}
	public boolean isTouchOtherTank(){
		//�жϷ���
		boolean b = false ;
		switch(this.direct){
		case 0://����
			
			//System.out.print(this.ots);
			for(int i = 0;i<ots.size();i++){
				//System.out.print("true");
				//����ots������еط�̹��
				OtherTank ot = ots.get(i);
				
					//������˷������ϻ�����
					if(ot.direct==0||ot.direct==3){
						if(this.x>=ot.x&&this.x<=ot.x+25&&this.y>=ot.y&&this.y<=ot.y+30){
							//System.out.print("true");
							return true ;
						}
						if(this.x+25>=ot.x&&this.x+25<=ot.x+25&&this.y>=ot.y&&this.y<=ot.y+30){
							//System.out.print("true");
							return true ;
						}
						
					}
					if(ot.direct==1||ot.direct==2){
						if(this.x>=ot.x&&this.x<=ot.x+30&&this.y>=ot.y&&this.y<=ot.y+25){
							//System.out.print("true");
							return true ;
						}
						if(this.x+25>=ot.x&&this.x+25<=ot.x+30&&this.y>=ot.y&&this.y<=ot.y+25){
							//System.out.print("true");
							return true ;
						}
					
					}
				}
			
			break;
		case 1:
			for(int i = 0;i<ots.size();i++){
				//����ots������еط�̹��
				OtherTank ot = ots.get(i);
				
					//������˷������ϻ�����
					if(ot.direct==0||ot.direct==3){
						if(this.x>=ot.x&&this.x<=ot.x+25&&this.y>=ot.y&&this.y<=ot.y+30){
							return true ;
						}
						if(this.x>=ot.x&&this.x<=ot.x+25&&this.y+25>=ot.y&&this.y+25<=ot.y+30){
							return true ;
						}
					}
					if(ot.direct==1||ot.direct==2){
						if(this.x>=ot.x&&this.x<=ot.x+30&&this.y>=ot.y&&this.y<=ot.y+25){
							return true ;
						}
						if(this.x>=ot.x&&this.x<=ot.x+30&&this.y+25>=ot.y&&this.y+25<=ot.y+25){
							return true ;
						}
					}
				}
			
			break;
		
		case 2:
			for(int i = 0;i<ots.size();i++){
				OtherTank ot = ots.get(i);
				
					//������˷������ϻ�����
					if(ot.direct==0||ot.direct==3){
						if(this.x+30>=ot.x&&this.x+30<=ot.x+25&&this.y>=ot.y&&this.y<=ot.y+30){
							return true ;
						}
						if(this.x+30>=ot.x&&this.x+30<=ot.x+25&&this.y+25>=ot.y&&this.y+25<=ot.y+30){
							return true ;
						}
					}
					if(ot.direct==1||ot.direct==2){
						if(this.x+30>=ot.x&&this.x+30<=ot.x+30&&this.y>=ot.y&&this.y<=ot.y+30){
							return true ;
						}
						if(this.x+30>=ot.x&&this.x+30<=ot.x+30&&this.y+25>=ot.y&&this.y+25<=ot.y+30){
							return true ;
						}
					}
				}
			
			break;
		case 3:
			for(int i = 0;i<ots.size();i++){
				OtherTank ot = ots.get(i);
				
					//������˷������ϻ�����
					if(ot.direct==0||ot.direct==3){
						if(this.x>=ot.x&&this.x<=ot.x+25&&this.y+30>=ot.y&&this.y+30<=ot.y+30){
							return true ;
						}
						if(this.x+25>=ot.x&&this.x+25<=ot.x+25&&this.y+30>=ot.y&&this.y+30<=ot.y+30){
							return true ;
						}
					}
					if(ot.direct==1||ot.direct==2){
						if(this.x>=ot.x&&this.x<=ot.x+30&&this.y+30>=ot.y&&this.y+30<=ot.y+25){
							return true ;
						}
						if(this.x+25>=ot.x&&this.x+25<=ot.x+30&&this.y+30>=ot.y&&this.y+30<=ot.y+25){
							return true ;
						}
					}
				}
			
			break;
	}
		
		return b ;
		
		
	}
    //̹������
	public void moveUp(){
		if(y>5&&!this.isTouchOtherTank()){
		y-=speed;
		}
		
	}
	//����
	public void moveRight(){
		if(x<465&&!this.isTouchOtherTank()){
		x+=speed;
		}
	
	}
	//̹������
	public void moveDown(){
		if(y<440&&!this.isTouchOtherTank()){
		y+=speed;
		}
	}
	//̹������
	public void moveLeft(){
		if(x>5&&!this.isTouchOtherTank()){
		x-=speed;
		}
	}
	public int getMTx(){
		return this.x;
	}
	public int getMTy(){
		
		return this.y;
	}
	
}
