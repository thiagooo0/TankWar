package UI;

import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class fri_card extends JPanel {
	public fri_card() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		ImageIcon icon = new ImageIcon("F:\\Image\\dota2\\6b2ed2a6gw1ecb5c881pqj208c08cmxl.jpg");
		icon.setImage(icon.getImage().getScaledInstance(48,50,Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(icon);
		lblNewLabel.setBounds(10, 10, 48, 50);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("SB\u00B7\u90ED\u7ECD\u5B8F");
		lblNewLabel_1.setFont(new Font("ËÎÌו", Font.BOLD, 12));
		lblNewLabel_1.setBounds(70, 10, 106, 30);
		add(lblNewLabel_1);
		
		JLabel label = new JLabel("\uFF08\u79BB\u7EBF\uFF09");
		label.setBounds(69, 36, 107, 21);
		add(label);
		
		
		
		this.setSize(185,64);
	}
	
}
