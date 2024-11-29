import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ListenerLibOp implements ActionListener {

	 final String BOR = "borrow";
	 final String RET = "returned";
	 
	 public ListenerLibOp() {
		
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals(BOR)) {
			
			JOptionPane.showMessageDialog(null, "test bor");
		
		} else if(e.getActionCommand().equals(RET)){
			
			JOptionPane.showMessageDialog(null, "test ret");
		}
	}
}
