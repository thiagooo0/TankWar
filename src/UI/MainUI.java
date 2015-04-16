package UI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;

import java.awt.Color;

import javax.swing.JRadioButtonMenuItem;

public class MainUI extends JFrame {
	private final JPanel panel = new JPanel();

	public MainUI(final User user) {
		
		user.isOnline = true;
		setTitle("\u6E38\u620F\u4E2D\u5FC3");
		setSize(900,600);
		getContentPane().setLayout(null);
		panel.setBounds(0, 0, 884, 541);
		getContentPane().add(panel);
		panel.setLayout(null);
		ImageIcon image1 = new ImageIcon("F:\\Image\\rBACFFJJnI_j2xZzAADvbz2AlDw707_600x.jpg");
		image1.setImage(image1.getImage().getScaledInstance(688,541,Image.SCALE_DEFAULT)); 
		panel.setOpaque(false);
		
		
		JButton btnTankWar = new JButton("单人冒险模式");
		/*ImageIcon image2 = new ImageIcon("F:\\Image\\dota2\\6b2ed2a6gw1ecb5c881pqj208c08cmxl.jpg");
		image2.setImage(image2.getImage().getScaledInstance(100,120,Image.SCALE_DEFAULT));
		btnTankWar.setIcon(image2);*/
	
		btnTankWar.setVerticalAlignment(SwingConstants.BOTTOM);
		btnTankWar.setBounds(63, 70, 127, 144);
		panel.add(btnTankWar);
		
		JButton btnNewButton = new JButton("团队冒险模式");
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton.setBounds(63, 318, 127, 144);
		panel.add(btnNewButton);
		
		JButton btnHeartstone = new JButton("单人PK模式");
		btnHeartstone.setVerticalAlignment(SwingConstants.BOTTOM);
		btnHeartstone.setBounds(479, 70, 127, 144);
		panel.add(btnHeartstone);
		
		JButton btnNewButton_1 = new JButton("团队PK模式");
		btnNewButton_1.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton_1.setBounds(479, 318, 127, 144);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(image1);
		lblNewLabel.setBounds(0, 0, 688, 541);
		panel.add(lblNewLabel);
		
		
		String name[] = {"oye","sb","sc","sd","miao","oye","sb","sc","sd","miao","oye","sb","sc","sd","miao"};
		JList list = new JList(name);
		list.setCellRenderer(new MyCellRenderer());
		JScrollPane jsp = new JScrollPane(list);
		jsp.setBounds(687, 40, 197, 501);
		panel.add(jsp);
		
		JLabel lblNewLabel_1 = new JLabel("\u597D\u53CB\u5217\u8868");
		lblNewLabel_1.setBackground(Color.CYAN);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(687, 0, 197, 42);
		panel.add(lblNewLabel_1);
		
		
		
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("用户信息");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("显示信息");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem menuItem = new JMenuItem("\u67E5\u8BE2\u6218\u7EE9");
		mnNewMenu.add(menuItem);
		
		JMenuItem menuItem_3 = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		mnNewMenu.add(menuItem_3);
		
		JMenu menu = new JMenu("\u5173\u4E8E");
		menuBar.add(menu);
		
		JMenuItem menuItem_1 = new JMenuItem("\u7248\u672C\u66F4\u65B0");
		menu.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("\u5236\u4F5C\u56E2\u961F");
		menu.add(menuItem_2);
		
		
		mntmNewMenuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ShowMessage sm1 = new ShowMessage(user);
			}
			
		});
		
		
		
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				HashMapUI hm1 = new HashMapUI(user);
			}
		});
		
		
		
		menuItem_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ChangePasswordUI cp1 = new ChangePasswordUI(user);
			}
		});
		
		
		
		
		
		
		
		setVisible(true);
		setResizable(false);
	}
	
	
	public static void main(String args[]){
		User u1 = new User("oye","123456",1001);
		
		u1.map.put(Achievement.solo_advanture,50);
		u1.map.put(Achievement.solo_fight,70);
		u1.map.put(Achievement.team_advanture,100);
		u1.map.put(Achievement.team_fight,90);
		
		MainUI m1 = new MainUI(u1);
		
	}
}
