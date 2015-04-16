package UI;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;


public class LoadUI extends JFrame {
	private JTextField textField;
	private JPasswordField passwordField;
	
	
	
	
	public LoadUI() {
		setTitle("\u6E38\u620F\u4E2D\u5FC3\u767B\u5F55");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 443, 273);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ÕÊºÅ");
		lblNewLabel.setFont(new Font("ËÎÌå", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(61, 80, 65, 28);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ÃÜÂë");
		lblNewLabel_1.setFont(new Font("ËÎÌå", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(61, 118, 65, 28);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(136, 83, 191, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(136, 118, 191, 28);
		panel.add(passwordField);
		
		JButton button = new JButton("\u767B\u5F55");
		button.setBounds(88, 188, 84, 28);
		panel.add(button);
		
		JLabel label = new JLabel("\u6E38\u620F\u4E2D\u5FC3");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Ó×Ô²", Font.PLAIN, 40));
		label.setBounds(84, 10, 240, 53);
		panel.add(label);
		
		JButton button_1 = new JButton("×¢²á");
		button_1.setBounds(182, 188, 84, 28);
		panel.add(button_1);
		
		JButton button_2 = new JButton("ÓÎ¿ÍµÇÂ¼");
		button_2.setBounds(306, 188, 118, 28);
		panel.add(button_2);
		
		
		
		
		//¡°µÇÂ¼¡±°´Å¥   ¼àÌýÆ÷
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				User u1 = new User("oye","123456",1001);
				User u2 = new User("ynot","123123",1002);
				User u3 = new User("babybaby","dianjie",1003);
				User u4 = new User("sb","guoshaohong",1004);
				
				
				
				if(textField.getText().equals(String.valueOf(u1.userID).replaceAll("\\s*$",""))&&String.valueOf(passwordField.getPassword()).equals(u1.password.replaceAll("\\s*$",""))){
					
					MainUI m1 = new MainUI(u1);
					setVisible(false);
				}
				
				else{
					
					
					JOptionPane.showMessageDialog(null, "ÕÊºÅ»òÃÜÂë´íÎó", "´íÎóÌáÊ¾", JOptionPane.ERROR_MESSAGE);
				}
				
			
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//¡°×¢²á¡±°´Å¥  ¼àÌýÆ÷
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				RegisterUI r1 = new RegisterUI();
			}
		});
		
		
		
		
	
		//¡°ÓÎ¿ÍµÇÂ¼¡±°´Å¥  ¼àÌýÆ÷
		button_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
	
		
		
		
		
		setResizable(false);
		setVisible(true);
		setSize(449, 301);
	}
	
	
	
	public static void main(String args[]){
		
		
		
		LoadUI l1 = new LoadUI();
	}
}
