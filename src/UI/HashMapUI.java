package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Color;

public class HashMapUI extends JFrame {
	private final JPanel panel = new JPanel();
	public HashMapUI(User user) {
		setTitle("\u67E5\u770B\u6218\u7EE9");
		
		
		setResizable(false);
		setVisible(true);
		setSize(443,285);
		getContentPane().setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 427, 247);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblV = new JLabel("1 V 1\uFF1A");
		lblV.setHorizontalAlignment(SwingConstants.CENTER);
		lblV.setFont(new Font("宋体", Font.PLAIN, 20));
		lblV.setBounds(32, 30, 152, 38);
		panel.add(lblV);
		
		JLabel lblV_1 = new JLabel("4 V 4\uFF1A");
		lblV_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblV_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblV_1.setBounds(32, 78, 152, 38);
		panel.add(lblV_1);
		
		JLabel label_1 = new JLabel("\u5355\u4EBA\u5192\u9669\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(32, 126, 152, 38);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u56E2\u961F\u5192\u9669\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(32, 174, 152, 38);
		panel.add(label_2);
		
		JLabel label = new JLabel(user.map.get(Achievement.solo_fight) + "%");
		label.setForeground(Color.RED);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(194, 30, 152, 38);
		panel.add(label);
		
		JLabel label_3 = new JLabel(user.map.get(Achievement.team_fight) + "%");
		label_3.setForeground(Color.RED);
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		label_3.setBounds(194, 78, 152, 38);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel(user.map.get(Achievement.solo_advanture) + "%");
		label_4.setForeground(Color.RED);
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));
		label_4.setBounds(194, 126, 152, 38);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel(user.map.get(Achievement.team_advanture) + "%");
		label_5.setForeground(Color.RED);
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setFont(new Font("宋体", Font.PLAIN, 20));
		label_5.setBounds(194, 174, 152, 38);
		panel.add(label_5);
	}
	
	
	
}
