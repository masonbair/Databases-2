import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ListenerAm implements ActionListener {

	public static final String REG = "Register";
	public static final String DEL = "Delete";
	public static final String ASS = "Associate";

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals(REG)) {
			
			JOptionPane.showMessageDialog(null, "test register");
			
		} else if (e.getActionCommand().equals(DEL)) {
			
			JOptionPane.showMessageDialog(null, "test delete");
		
		} else if(e.getActionCommand().equals(ASS)){
			
			JOptionPane.showMessageDialog(null, "test association");
		}
		
	}

}
