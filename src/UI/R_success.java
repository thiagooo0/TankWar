package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class R_success extends JFrame {

	
	String user_name;
	String password;
	int user_id;
	int exit = 0;
	
	public R_success(String string, String string2,int id) {
		
		user_name = string;
		password = string2;
		user_id = id++;
		
		setTitle("\u6CE8\u518C\u6210\u529F");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 361, 203);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u606D\u559C\u4F60\uFF0C\u6CE8\u518C\u6210\u529F");
		label.setFont(new Font("ËÎÌו", Font.PLAIN, 17));
		label.setForeground(Color.RED);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(92, 10, 160, 35);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u60A8\u7684\u7528\u6237\u540D\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(67, 87, 93, 28);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u60A8\u7684\u5BC6\u7801\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(67, 125, 93, 28);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel(user_name);
		label_3.setBounds(166, 87, 127, 28);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel(password);
		label_4.setBounds(166, 125, 127, 28);
		panel.add(label_4);
		
		JButton button = new JButton("\u5173\u95ED");
		button.setBounds(135, 159, 93, 23);
		panel.add(button);
		
		JLabel label_5 = new JLabel("\u60A8\u7684\u5E10\u53F7\uFF1A");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(67, 55, 93, 28);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel(String.valueOf(user_id));
		label_6.setBounds(166, 55, 127, 28);
		panel.add(label_6);
		
		
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
				
				
			}
		});
		
		
		
	
		
		
		
		
		
		
		
		setSize(377, 242);
		setVisible(true);
		setResizable(false);
	
	}
	
	public static void main(String args[]){
		R_success r1 = new R_success("ks_invisible","123456",100);
	}
}
