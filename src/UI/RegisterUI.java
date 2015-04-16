package UI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RegisterUI extends JFrame {
	static int id = 1001;
	private final JPanel panel = new JPanel();
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	
	
	public RegisterUI() {
		
		setTitle("\u7528\u6237\u6CE8\u518C");
		getContentPane().setLayout(null);
		panel.setBounds(0, 0, 431, 390);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel l1 = new JLabel("用户名");
		l1.setFont(new Font("宋体", Font.PLAIN, 17));
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBounds(33, 45, 73, 27);
		panel.add(l1);
		
		textField = new JTextField();
		textField.setBounds(121, 46, 146, 27);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel l2 = new JLabel("密码");
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		l2.setFont(new Font("宋体", Font.PLAIN, 17));
		l2.setBounds(33, 112, 73, 27);
		panel.add(l2);
		
		JLabel l3 = new JLabel("确认\u5BC6\u7801");
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		l3.setFont(new Font("宋体", Font.PLAIN, 17));
		l3.setBounds(33, 168, 73, 27);
		panel.add(l3);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(121, 112, 146, 27);
		panel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(121, 172, 146, 27);
		panel.add(passwordField_1);
		
		JButton button_1 = new JButton("\u63D0\u4EA4");
		button_1.setFont(new Font("宋体", Font.PLAIN, 20));
		button_1.setBounds(95, 283, 93, 39);
		panel.add(button_1);
		
		JButton button_2 = new JButton("重置");
		button_2.setFont(new Font("宋体", Font.PLAIN, 20));
		button_2.setBounds(228, 283, 93, 39);
		panel.add(button_2);
		
		JLabel label_2 = new JLabel("<html>\u957F\u5EA6\u4E0D\u8D8512个字\u6BCD\u62166\u4E2A\u4E2D\u6587<html");
		label_2.setBounds(277, 34, 145, 39);
		panel.add(label_2);
		
		JLabel l1_1 = new JLabel("<html>密码长度为6~10位<html>");
		l1_1.setBounds(277, 111, 125, 27);
		panel.add(l1_1);
		
		final JLabel label = new JLabel("*\u7528\u6237\u540D\u4E0D\u6807\u51C6");
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		label.setForeground(Color.RED);
		label.setBounds(121, 77, 146, 25);
		label.setVisible(false);
		panel.add(label);
		
		
		
		//“提交”按钮监听器
		button_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				
				
				if(textField.getText().equals(""))
					JOptionPane.showMessageDialog(null, "用户名为空", "错误提示", JOptionPane.ERROR_MESSAGE);
				else if(textField.getText().length() > 12){
					label.setVisible(true);
				}
				else if(String.valueOf(passwordField.getPassword()).length() >= 6 && String.valueOf(passwordField.getPassword()).length() <= 10 && !String.valueOf(passwordField.getPassword()).isEmpty() && String.valueOf(passwordField.getPassword()).equals(String.valueOf(passwordField_1.getPassword()))){
					R_success R1 = new R_success(textField.getText(),String.valueOf(passwordField.getPassword()),id++);
					User u1  = new User(textField.getText(),String.valueOf(passwordField.getPassword()),id);
					
					
					
					
					
					label.setVisible(false);
				}
				else{
					JOptionPane.showMessageDialog(null, "密码出错", "错误提示", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
				
					
			}
			
		});
		
		//“重置”按钮 监听器
		button_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				textField.setText("");
				passwordField.setText("");
				passwordField_1.setText("");
				label.setVisible(false);
			}
		});
		
	
		
		
		setSize(447,428);
		setResizable(false);
		setVisible(true);
	}
	
	
//	public static void main(String args[]){
//		RegisterUI r1 = new RegisterUI();
//		
//	}
}
