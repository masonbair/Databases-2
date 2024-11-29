import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

//ONE LISTENER FOR FRAME?
public class ListenerGui implements ActionListener {
	
	private GUI gui;
	CardLayout cl;
	
	
	//commands
	public final String FRAME_AM = "frame_am";
	public final String FRAME_LIB  = "frame_lib";
	public final String FRAME_STUD = "frame_stud";
	
	
	
	public ListenerGui (GUI gui) {
		this.gui = gui;
		cl = (CardLayout)(gui.cards.getLayout());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getActionCommand().equals(FRAME_AM)) {
			
			cl.show(gui.cards, "Panel 1");
			
		} else if (e.getActionCommand().equals(FRAME_LIB)) {
			
			cl.show(gui.cards, "Panel 2");
			
		} else if(e.getActionCommand().equals(FRAME_STUD)) {
			
			cl.show(gui.cards, "Panel 3");
		
		}
	}
	
	
	
}
