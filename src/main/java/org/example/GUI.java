import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame{
	
	JPanel cards = new JPanel(new CardLayout());
	
	
    //panel
	AmFrame frame_am = new AmFrame();
	LibFrame frame_lib = new LibFrame();
	StudFrame frame_stud = new StudFrame();
	
	//cards butt
	private JButton butt_am = new JButton("Administrator");
	private JButton butt_lib= new JButton("Librarian");
	private JButton butt_stud = new JButton("Student");
	JPanel panel_butt = new JPanel();
	

	public GUI() {
		//Create the panel that contains the "cards".
		cards.add(frame_am, "Panel 1");
		cards.add(frame_lib, "Panel 2");
		cards.add(frame_stud, "Panel 3");

		// Add your card container to the frame
		Container pane = this.getContentPane();
		pane.add(cards, BorderLayout.CENTER);
		
		
		// south
		panel_butt.add(butt_am);
		panel_butt.add(butt_lib);
		panel_butt.add(butt_stud);	
		this.add(panel_butt, BorderLayout.SOUTH);
		
	
		//LISTENER SETTING
		ListenerGui listener = new ListenerGui(this);
		butt_am.setActionCommand(listener.FRAME_AM);
		butt_am.addActionListener(listener);
		butt_stud.setActionCommand(listener.FRAME_STUD);
		butt_stud.addActionListener(listener);
		butt_lib.setActionCommand(listener.FRAME_LIB);
		butt_lib.addActionListener(listener);
		
		//
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,600);
		this.setVisible(true);
	}
	
	
}
