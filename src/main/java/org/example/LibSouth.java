import javax.swing.*;

public class LibSouth extends JPanel {
	
	private JButton butt_stud = new JButton("Display Student");
	private JButton butt_book = new JButton("Display Due_Book");
	
	public LibSouth() {
		this.add(butt_stud);
		this.add(butt_book);
		
		ListenerLibDisplay listener = new ListenerLibDisplay();
		
		butt_stud.setActionCommand(listener.STUD);
		butt_stud.addActionListener(listener);
		butt_book.setActionCommand(listener.BOOK);
		butt_book.addActionListener(listener);
		
	}

}
