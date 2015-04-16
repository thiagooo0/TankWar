package UI;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class MyCellRenderer2 extends JLabel implements ListCellRenderer {
	final static ImageIcon photo1 = new ImageIcon("F:\\Image\\头像\\9fcdce0dgw1ejuoc3ciwuj20e60e6acq.jpg");
    final static ImageIcon photo2 = new ImageIcon("F:\\Image\\头像\\9fcdce0dgw1ejx0e8dngpj20e60e5acb.jpg");
	final static ImageIcon photo3 = new ImageIcon("F:\\Image\\头像\\9fcdce0djw1ekwmqu7gcej211t11tdmk.jpg"); 
	final static ImageIcon photo4 = new ImageIcon("F:\\Image\\头像\\a7042a98jw1ekqun8dk4cj20h00h0mzf.jpg");
	final static ImageIcon photo5 = new ImageIcon("F:\\Image\\头像\\be7c6b2dgw1ekb89udt0xj20c80gat9w.jpg");
    
    public Component getListCellRendererComponent(JList list,Object value,int index,boolean isSelected,boolean cellHasFocus){
		
    	

    	
    	/*photo1.setImage(photo1.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
    	photo2.setImage(photo2.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
    	photo3.setImage(photo3.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
    	photo4.setImage(photo4.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
    	photo5.setImage(photo5.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));*/
    	
    	String s = value.toString();
    	setText(s);
    	
    	
        
        
        
        
        
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
