import javax.swing.*;

public class LibSouth extends JPanel {
	
	private JButton butt_stud = new JButton("Display Student");
	private JButton butt_book = new JButton("Display Due_Book");
	//we need this obj in the listner for display the queies 
	private JTextArea display_center_panel;
	
	public LibSouth(JTextArea display_center_panel) {
		this.add(butt_stud);
		this.add(butt_book);
		this.display_center_panel = display_center_panel;
		
		ListenerLibDisplay listener = new ListenerLibDisplay(this.display_center_panel);
		
		butt_stud.setActionCommand(listener.STUD);
		butt_stud.addActionListener(listener);
		butt_book.setActionCommand(listener.BOOK);
		butt_book.addActionListener(listener);
		
	}

}
