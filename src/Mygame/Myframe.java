package Mygame;

import javax.swing.*;
import java.awt.*;

public class Myframe extends JFrame {
	
    Mypanel mp = null;
    
	public Myframe(){
		mp = new Mypanel();
		Thread t = new Thread(mp);
		t.start();
		
		this.add(mp);
		this.addKeyListener(mp);
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(50, 100);
		this.setVisible(true);
		this.setResizable(false);
	}

}
