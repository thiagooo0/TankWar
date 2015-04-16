package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JList;

public class ChangePhotoUI extends JFrame {
	public ChangePhotoUI() {
		setTitle("\u4FEE\u6539\u5934\u50CF");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 441, 264);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton button = new JButton("\u786E\u8BA4\u4FEE\u6539");
		button.setBounds(238, 186, 98, 35);
		panel.add(button);
		
		String why[]  = {"头像一","头像二","头像三"};
		JList list = new JList(why);
		list.setCellRenderer(new MyCellRenderer2());
		
		list.setBounds(0, 0, 157, 264);
		panel.add(list);
		
		
		setVisible(true);
		setSize(457,302);
	}
	
	
	
	public static void main(String args[]){
		//User u1 = new User("oye","123456",1001);
		ChangePhotoUI cp1 = new ChangePhotoUI();
	}
}
