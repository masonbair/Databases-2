import javax.swing.*;

public class StudWest extends JPanel{
	private JTextArea display = new JTextArea(8,20);
	JScrollPane scroll;
	
	public StudWest(){
		this.setVisible(false);
		display.setEditable(false);
		scroll = new JScrollPane(display);
		scroll.setBorder(BorderFactory.createTitledBorder("Display"));
		this.add(scroll);
	}

	public void showPanel(){
		this.setVisible(true);
	}

}
