package UI;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class user_card extends JPanel {
	public user_card() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		ImageIcon icon = new ImageIcon("F:\\Image\\dota2\\6b2ed2a6gw1ec5jlnemlfj20bo0boaaq.jpg");
		icon.setImage(icon.getImage().getScaledInstance(59,59,Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(icon);
		lblNewLabel.setBounds(10, 10, 59, 59);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ks.Invisible");
		lblNewLabel_1.setFont(new Font("ËÎÌו", Font.BOLD, 13));
		lblNewLabel_1.setBounds(77, 10, 108, 24);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uFF08\u5728\u7EBF\uFF09");
		lblNewLabel_2.setBounds(79, 28, 79, 28);
		add(lblNewLabel_2);
		
		
		
		setSize(198,81);
		
	}
	
	
	
	
	
	
}
