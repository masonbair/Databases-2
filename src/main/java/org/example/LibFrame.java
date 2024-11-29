import java.awt.*;

import javax.swing.*;

public class LibFrame extends JPanel {
	
	LibNorth north = new LibNorth();
	LibCenter center = new LibCenter();
	LibSouth south = new LibSouth();
	
	public LibFrame() {
		
		this.add(north, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
		this.add(south, BorderLayout.SOUTH);
	
	}
}
