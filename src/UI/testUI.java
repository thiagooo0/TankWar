package UI;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

public class testUI extends JFrame {
	/*private JTextField txtText;
	public testUI() {
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 262);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblOye = new JLabel("label");
		lblOye.setFont(new Font("ÀŒÃÂ", Font.PLAIN, 20));
		lblOye.setHorizontalAlignment(SwingConstants.CENTER);
		lblOye.setBounds(23, 26, 139, 53);
		panel.add(lblOye);
		
		JButton btnNewButton = new JButton("ks_invisible¿Îœﬂ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(23, 128, 139, 53);
		panel.add(btnNewButton);
		
		txtText = new JTextField();
		txtText.setFont(new Font("ÀŒÃÂ", Font.PLAIN, 22));
		txtText.setHorizontalAlignment(SwingConstants.CENTER);
		txtText.setText("text");
		txtText.setBounds(208, 128, 139, 53);
		panel.add(txtText);
		txtText.setColumns(10);
		txtText.setEditable(false);
		
		final JPopupMenu j1 = new JPopupMenu();
		JMenu m1 = new JMenu("oye");
		JMenuItem i1 = new JMenuItem("ooo");
		m1.add(i1);
		j1.add(m1);
		
		
		
		
		txtText.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				j1.show( 
	                     arg0.getComponent(), arg0.getX(), arg0.getY() );  
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				txtText.setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				txtText.setBackground(Color.gray);
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		setSize(434,262);
		setVisible(true);*/
	
	
	
		
	
	public testUI(){
		JFrame f = new JFrame("oye");
		f.add(new user_card());
		f.setSize(186,186);
		f.setVisible(true);
	
		
		f.setVisible(true);
		f.setSize(195,540);
		
		
	
	}
	
	public static void main(String args[]){
		testUI t1 = new testUI();
	}
}
