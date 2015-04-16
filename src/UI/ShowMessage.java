package UI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JTextField;

public class ShowMessage extends JFrame {
	private JTextField textField;
	public ShowMessage(final User user) {
		
		
		
		setTitle("\u663E\u793A\u4FE1\u606F");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 262);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(29, 33, 48, 50);
		panel.add(lblNewLabel);
		
	
		JButton btnNewButton = new JButton("更换头像");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				
			}
		});
		btnNewButton.setBounds(10, 100, 93, 23);
		panel.add(btnNewButton);
		
		JLabel label = new JLabel("\u5E10\u53F7\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 25));
		label.setBounds(153, 33, 76, 38);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(153, 115, 93, 38);
		panel.add(label_1);
		
		JLabel lblNewLabel_1 = new JLabel(String.valueOf(user.userID));
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(250, 33, 106, 38);
		panel.add(lblNewLabel_1);
		
		JButton button = new JButton("\u786E\u8BA4\u4FEE\u6539");
		button.setBounds(169, 202, 93, 38);
		panel.add(button);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setBounds(250, 115, 163, 38);
		panel.add(textField);
		textField.setText(user.userName);
		textField.setColumns(10);
		
		
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(textField.getText().length() <= 12){
					user.userName = textField.getText();
					setVisible(false);
					
				}
				else if(textField.getText().length() > 12){
					JOptionPane.showMessageDialog(null, "用户名不允许超过12个字符", "错误提示", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		});
		
		
		
		
		
		
		
		
		
		
		setResizable(false);
		setVisible(true);
		setSize(442,286);
		setResizable(false);
	}
}
