package UI;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class MyCellRenderer extends JLabel implements ListCellRenderer {
	final static ImageIcon longIcon = new ImageIcon("F:\\Image\\Í·Ïñ\\9fcdce0dgw1ejx0e8dngpj20e60e5acb");
    final static ImageIcon shortIcon = new ImageIcon("F:\\Image\\dota2\\6b2ed2a6gw1ecb5c881pqj208c08cmxl.jpg");
	
    
    public Component getListCellRendererComponent(JList list,Object value,int index,boolean isSelected,boolean cellHasFocus){
		
    	
    	//longIcon.setImage(longIcon.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
    	
    	String s = value.toString();
    	setText(s);
    	ImageIcon icon = new ImageIcon("F:\\Image\\Í·Ïñ\\9fcdce0dgw1ejx0e8dngpj20e60e5acb.jpg");
		icon.setImage(icon.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
    	
        setIcon((s.length() > 10) ? icon : icon);
        
        if (isSelected) {
        	setBackground(list.getSelectionBackground());
        	setForeground(list.getSelectionForeground());
        }
        else {
        	setBackground(list.getBackground());
        	setForeground(list.getForeground());
        }
          
          
        setEnabled(list.isEnabled());
        setFont(list.getFont());
        setOpaque(true);
        return this;

    }
}
