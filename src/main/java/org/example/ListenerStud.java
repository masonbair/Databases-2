import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ListenerStud implements ActionListener {
	 
	 final String PW = "set_pw";
	 final String BOR = "display_bor";
	 final String RES = "display_res";
	 
	 public ListenerStud() {
		
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals(PW)) {
			
			JOptionPane.showMessageDialog(null, "test pw");
			
		} else if (e.getActionCommand().equals(BOR)) {
			
			JOptionPane.showMessageDialog(null, "test bor");
		
		} else if(e.getActionCommand().equals(RES)){
			
			JOptionPane.showMessageDialog(null, "test res");
		}
		 
	}

}
