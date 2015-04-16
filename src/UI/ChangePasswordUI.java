package UI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class ChangePasswordUI extends JFrame {
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	public ChangePasswordUI(final User user) {
		setTitle("\u4FEE\u6539\u5BC6\u7801");
		
		
		
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 473, 188);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u539F\u5BC6\u7801:");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(22, 23, 122, 30);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u65B0\u5BC6\u7801:");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(22, 69, 122, 30);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u786E\u8BA4\u65B0\u5BC6\u7801:");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(22, 118, 122, 30);
		panel.add(label_2);
		
		JButton btnNewButton = new JButton("\u786E\u8BA4");
		btnNewButton.setBounds(149, 155, 132, 23);
		panel.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(165, 23, 198, 30);
		panel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(165, 69, 198, 30);
		panel.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(165, 118, 198, 30);
		panel.add(passwordField_2);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(String.valueOf(passwordField.getPassword()).equals(user.password)){
					if(String.valueOf(passwordField_1.getPassword()).length() >= 6 && String.valueOf(passwordField_1.getPassword()).length() <= 10){
						if(String.valueOf(passwordField_1.getPassword()).equals(String.valueOf(passwordField_2.getPassword()))){
							//修改成功
							System.out.println("修改成功");
							setVisible(false);
						}
						else{
							JOptionPane.showMessageDialog(null, "两次输入的密码不一致", "错误提示", JOptionPane.ERROR_MESSAGE);
						}
						
					}
					else{
						JOptionPane.showMessageDialog(null, "密码长度介乎于6~8个字符", "错误提示", JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "原密码错误", "错误提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		setResizable(false);
		setVisible(true);
		setSize(489,226);
	}
}
