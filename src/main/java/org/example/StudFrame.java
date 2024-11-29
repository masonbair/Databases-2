import java.awt.BorderLayout;

import javax.swing.*;

public class StudFrame extends JPanel {
	
	public ListenerStud listenerStud = new ListenerStud();
	
	StudEast east;
	StudWest west;
	
	public StudFrame() {
		east = new StudEast();
		west = new StudWest();
		this.add(east, BorderLayout.EAST);
		this.add(west, BorderLayout.WEST);
	}
	
}
