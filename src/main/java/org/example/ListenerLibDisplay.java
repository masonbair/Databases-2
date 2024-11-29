import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ListenerLibDisplay implements ActionListener {
	
	
	
	public static final String STUD = "DisplayStudent";
	public static final String BOOK = "DisplayBook";

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals(STUD)) {
			
			JOptionPane.showMessageDialog(null, "test stud display");
		
		} else if(e.getActionCommand().equals(BOOK)){
			
			JOptionPane.showMessageDialog(null, "test book display");
		}
		
	}

}
