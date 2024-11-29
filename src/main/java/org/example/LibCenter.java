import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LibCenter extends JPanel{
	private JTextArea display = new JTextArea(10,20);
	JScrollPane scroll;
	
	public LibCenter(){
		display.setEditable(false);
		scroll = new JScrollPane(display);
		scroll.setBorder(BorderFactory.createTitledBorder("Display"));
		this.add(scroll);
	}

}
