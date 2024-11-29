import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;

public class AmFrame extends JPanel{
	
	AmCenter center = new AmCenter();
	
	private JButton butt_reg = new JButton("Register");
	private JButton butt_del = new JButton("Delete");
	private JButton butt_ass = new JButton("Associate");
	private JPanel panel_but = new JPanel();
	
	public AmFrame() {
		
		
		panel_but.add(butt_reg);
		panel_but.add(butt_del);
		panel_but.add(butt_ass);
		
		this.add(center, BorderLayout.CENTER);
		this.add(panel_but, BorderLayout.SOUTH);
		
		ListenerAm listener = new ListenerAm();
		butt_reg.setActionCommand(listener.REG);
		butt_reg.addActionListener(listener);
		
		butt_del.setActionCommand(listener.DEL);
		butt_del.addActionListener(listener);
		
		butt_ass.setActionCommand(listener.ASS);
		butt_ass.addActionListener(listener);
	}

}
