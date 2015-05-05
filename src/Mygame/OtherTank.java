package Mygame;

import java.util.Vector;

public class OtherTank extends Tank implements Runnable{
	boolean isLive = true;
	int time = 0 ;
	int Mtx = 0;
	int Mty = 0 ;
	//�����������ʵ�Mypanel�ϵ�����ľͷ
	Vector<Wood> wds = new Vector<Wood>();
	//�����������ʵ�MyPanel�����е��˵�̹��
	Vector<OtherTank> ots = new Vector<OtherTank>();
	//Vector<Boom> booms = new Vector<Boom>();
	
	//����������ŵ����ӵ�
	Vector<Shot> ss = new Vector<Shot>() ;
	//��������ӵ�
	//www.github.com
	
	public OtherTank(int x ,int y){
		super(x,y);
	}
	@Override
	
	//�õ�MyPanel�ĵ���̹������
	public void setOts(Vector<OtherTank> vv){
		this.ots = vv ;
	}
	
	public void setWds(Vector<Wood> vv){
		this.wds = vv ;
	}
	
	//�ж�ǰ���Ƿ���ľͷ
	public boolean isTouchWood(){
		boolean b = false ; 
		switch(this.direct){
		case 0 :
			//System.out.print("in");
			for(int i = 0 ; i < wds.size() ; i++){
				Wood wd = wds.get(i);
				//System.out.print(wd.x+" "+wd.y);
				if(this.x>=wd.x&&this.x<=wd.x+20&&this.y>=wd.y&&this.y<=wd.y+20){
					return true ;
				}
				if(this.x+25>=wd.x&&this.x+25<=wd.x+20&&this.y>=wd.y&&this.y<=wd.y+20){
					return true ;
				}
			}
		    break;
		case 1 :
			for(int i = 0 ; i < wds.size() ; i++){
				Wood wd = wds.get(i);
				if(this.x>=wd.x&&this.x<=wd.x+20&&this.y>=wd.y&&this.y<=wd.y+20){
					return true ;
				}
				if(this.x>=wd.x&&this.x<=wd.x+20&&this.y+25>=wd.y&&this.y+25<=wd.y+20){
					return true ;
				}
			}
		    break;
		case 2 :
			for(int i = 0 ; i < wds.size() ; i++){
				Wood wd = wds.get(i);
				if(this.x+30>=wd.x&&this.x<=wd.x+20&&this.y>=wd.y&&this.y<=wd.y+20){
					return true ;
				}
				if(this.x+30>=wd.x&&this.x+30<=wd.x+20&&this.y+25>=wd.y&&this.y+25<=wd.y+20){
					return true ;
				}
			}
			break;
		case 3 :
			for(int i = 0 ; i < wds.size() ; i++){
				Wood wd = wds.get(i);
				if(this.x>=wd.x&&this.x<=wd.x+20&&this.y+30>=wd.y&&this.y+30<=wd.y+20){
					return true ;
				}
				if(this.x+25>=wd.x&&this.x+25<=wd.x+20&&this.y+30>=wd.y&&this.y+30<=wd.y+20){
					return true ;
				}
			}
			break;
			
		}
		return b ;
	}
	
	
	
	
	//�ж��Ƿ�ײ�����̹��
	public boolean isTouchOtherTank(){
		//��ȡ�ҵ�̹�˵�x��yλ��
		Mtx = Mypanel.mt.getMTx();
		Mty = Mypanel.mt.getMTy();
		
		boolean b = false ;
		switch(this.direct){
		case 0://����
			//System.out.print(this.ots);
			for(int i = 0;i<ots.size();i++){
				OtherTank ot = ots.get(i);
				//��������Լ�
				if(ot!=this){
					
					//������˷������ϻ�����
					if(ot.direct==0||ot.direct==3||Mypanel.mt.direct==0||Mypanel.mt.direct==3){
						if((this.x>=ot.x&&this.x<=ot.x+25&&this.y>=ot.y&&this.y<=ot.y+30)
							||(this.x>=Mtx&&this.x<=Mtx+25&&this.y>=Mty&&this.y<=Mty+30)){
							return true ;
						}
						if((this.x+25>=ot.x&&this.x+25<=ot.x+25&&this.y>=ot.y&&this.y<=ot.y+30)
							||(this.x+25>=Mtx&&this.x+25<=Mtx+25&&this.y>=Mty&&this.y<=Mty+30)){
							return true ;
						}
						
					}
					if(ot.direct==1||ot.direct==2||Mypanel.mt.direct==1||Mypanel.mt.direct==2){
						if((this.x>=ot.x&&this.x<=ot.x+30&&this.y>=ot.y&&this.y<=ot.y+25)
							||(this.x>=Mtx&&this.x<=Mtx+30&&this.y>=Mty&&this.y<=Mty+25)){
							return true ;
						}
						if((this.x+25>=ot.x&&this.x+25<=ot.x+30&&this.y>=ot.y&&this.y<=ot.y+25)
							||(this.x+25>=Mtx&&this.x+25<=Mtx+30&&this.y>=Mty&&this.y<=Mty+25)){
							return true ;
						}
					
					}
				}
			}
			break;
		case 1:
			for(int i = 0;i<ots.size();i++){
				OtherTank ot = ots.get(i);
				//��������Լ�
				if(ot!=this){
					//������˷������ϻ�����
					if(ot.direct==0||ot.direct==3||Mypanel.mt.direct==0||Mypanel.mt.direct==3){
						if((this.x>=ot.x&&this.x<=ot.x+25&&this.y>=ot.y&&this.y<=ot.y+30)
							||(this.x>=Mtx&&this.x<=Mtx+25&&this.y>=Mty&&this.y<=Mty+30)){
							return true ;
						}
						if((this.x>=ot.x&&this.x<=ot.x+25&&this.y+25>=ot.y&&this.y+25<=ot.y+30)
							||(this.x>=Mtx&&this.x<=Mtx+25&&this.y+25>=Mty&&this.y+25<=Mty+30)){
							return true ;
						}
					}
					if(ot.direct==1||ot.direct==2||Mypanel.mt.direct==1||Mypanel.mt.direct==2){
						if((this.x>=ot.x&&this.x<=ot.x+30&&this.y>=ot.y&&this.y<=ot.y+25)
							||(this.x>=Mtx&&this.x<=Mtx+30&&this.y>=Mty&&this.y<=Mty+25)){
							return true ;
						}
						if((this.x>=ot.x&&this.x<=ot.x+30&&this.y+25>=ot.y&&this.y+25<=ot.y+25)
							||(this.x>=Mtx&&this.x<=Mtx+30&&this.y+25>=Mty&&this.y+25<=Mty+25)){
							return true ;
						}
					}
				}
			}
			break;
		
		case 2:
			for(int i = 0;i<ots.size();i++){
				OtherTank ot = ots.get(i);
				//��������Լ�
				if(ot!=this){
					//������˷������ϻ�����
					if(ot.direct==0||ot.direct==3||Mypanel.mt.direct==0||Mypanel.mt.direct==3){
						if((this.x+30>=ot.x&&this.x+30<=ot.x+25&&this.y>=ot.y&&this.y<=ot.y+30)
							||(this.x+30>=Mtx&&this.x+30<=Mtx+25&&this.y>=Mty&&this.y<=Mty+30)){
							return true ;
						}
						if((this.x+30>=ot.x&&this.x+30<=ot.x+25&&this.y+25>=ot.y&&this.y+25<=ot.y+30)
						    ||(this.x+30>=Mtx&&this.x+30<=Mtx+25&&this.y+25>=Mty&&this.y+25<=Mty+30)){
							return true ;
						}
					}
					if(ot.direct==1||ot.direct==2||Mypanel.mt.direct==1||Mypanel.mt.direct==2){
						if((this.x+30>=ot.x&&this.x+30<=ot.x+30&&this.y>=ot.y&&this.y<=ot.y+30)
							||(this.x+30>=Mtx&&this.x+30<=Mtx+30&&this.y>=Mty&&this.y<=Mty+30)){
							return true ;
						}
						if((this.x+30>=ot.x&&this.x+30<=ot.x+30&&this.y+25>=ot.y&&this.y+25<=ot.y+30)
							||(this.x+30>=Mtx&&this.x+30<=Mtx+30&&this.y+30>=Mty&&this.y+25<=Mty+30)){
							return true ;
						}
					}
				}
			}
			break;
		case 3:
			for(int i = 0;i<ots.size();i++){
				OtherTank ot = ots.get(i);
				//��������Լ�
				if(ot!=this){
					//������˷������ϻ�����
					if(ot.direct==0||ot.direct==3||Mypanel.mt.direct==0||Mypanel.mt.direct==3){
						if((this.x>=ot.x&&this.x<=ot.x+25&&this.y+30>=ot.y&&this.y+30<=ot.y+30)
							||(this.x>=Mtx&&this.x<=Mtx+25&&this.y+30>=Mty&&this.y+30<=Mty+30)){
							return true ;
						}
						if((this.x+25>=ot.x&&this.x+25<=ot.x+25&&this.y+30>=ot.y&&this.y+30<=ot.y+30)
							||(this.x+25>=Mtx&&this.x+25<=Mtx+25&&this.y+30>=Mty&&this.y+30<=Mty+30)){
							return true ;
						}
					}
					if(ot.direct==1||ot.direct==2||Mypanel.mt.direct==1||Mypanel.mt.direct==2){
						if((this.x>=ot.x&&this.x<=ot.x+30&&this.y+30>=ot.y&&this.y+30<=ot.y+25)
							||(this.x>=Mtx&&this.x<=Mtx+30&&this.y+30>=Mty&&this.y+30<=Mty+25)){
							return true ;
						}
						if((this.x+25>=ot.x&&this.x+25<=ot.x+30&&this.y+30>=ot.y&&this.y+30<=ot.y+25)
							||(this.x+25>=Mtx&&this.x+25<=Mtx+30&&this.y+30>=Mty&&this.y+30<=Mty+25)){
							return true ;
						}
					}
				}
			}
			break;
		}
		
		return b ;
	}
	
	
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try{
				Thread.sleep(50);
			}catch (Exception e){
				e.printStackTrace();
			}
			
			switch(this.direct){
			case 0:
				//̹����������
				for(int i = 0 ; i <30 ; i++){
				if(y>5&&!this.isTouchOtherTank()&&!this.isTouchWood()){	
				y-=speed ;
				}
				try{
					Thread.sleep(50);
				}catch (Exception e){
					e.printStackTrace();
				}
				}
				break;
			case 1 :
				//����
				for(int i = 0 ; i <30 ; i++){
					if(x>5&&!this.isTouchOtherTank()&&!this.isTouchWood()){
					x-=speed ;
					}
					try{
						Thread.sleep(50);
					}catch (Exception e){
						e.printStackTrace();
					}
					}
				
				break ;
			case 2 :
				//����
				
				for(int i = 0 ; i <30 ; i++){
					if(x<465&&!this.isTouchOtherTank()&&!this.isTouchWood()){
					x+=speed ;
					}
					try{
						Thread.sleep(50);
					}catch (Exception e){
						e.printStackTrace();
					}
					}
				break ;
			case 3 :
				//����
				
				for(int i = 0 ; i <30 ; i++){
					if(y<440&&!this.isTouchOtherTank()&&!this.isTouchWood()){
					y+=speed ;
					}
					try{
						Thread.sleep(50);
					}catch (Exception e){
						e.printStackTrace();
					}
					}
				break ;
			}
			this.time++;
			if(time%2==0)
			{
				if(isLive){
		    		if(ss.size()<3){
		    			Shot s =null ;
		    			switch(direct){
		    			case 0:
		    				s = new Shot(x+10,y,0,4);
		    				ss.add(s);
		    				break;
		    			case 1:
		    				s = new Shot(x,y+10,1,4);
		    				ss.add(s);
		    				break;
		    			case 2:
		    				s = new Shot(x+20,y+10,2,4);
		    				ss.add(s);
		    				break;
		    			case 3:
		    				s = new Shot(x+10,y+20,3,4);
		    				ss.add(s);
		    				break;
		    			}
		    			
		    			
		    			Thread t = new Thread(s) ;
		    			t.start();
		    				
		    			
		    		}
		    	}
			}
			//��̹���������
			this.direct = (int )(Math.random()*4);
			//�ж�̹���Ƿ�����,�˳�����
			if(this.isLive == false){
				this.x = 500;
				this.y = 500;
				break ;
			}
			
		}
	}

}
