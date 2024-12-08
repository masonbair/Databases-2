import java.awt.FlowLayout;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StudEast extends JPanel {
	private JLabel txt_pw = new JLabel("Set new pw");
	public JTextField pw = new JTextField(15);
	private JButton butt_pw = new JButton ("Set PW");
	private JPanel panel_pw =  new JPanel(new FlowLayout(FlowLayout.TRAILING));
	
	private JLabel txt_res = new JLabel("Select resource");
	public JLabel res = new JLabel();
	private JButton butt_res = new JButton ("Display");
	private JPanel panel_res =  new JPanel(new FlowLayout(FlowLayout.TRAILING));
	
	private JLabel txt_borr = new JLabel("Display your borrows");
	public JLabel borr = new JLabel();
	private JButton butt_borr = new JButton("Display");
	private JPanel panel_borr =  new JPanel(new FlowLayout(FlowLayout.TRAILING));

	public String student;
	public String password;

	public StudWest studWest;
	
	public StudEast(StudWest SW) {
		studWest = SW;
		this.setVisible(false);
		
		this.setLayout(new GridLayout(3,1));
		
		//gui
		panel_pw.add(txt_pw);
		panel_pw.add(pw);
		panel_pw.add(butt_pw);
		
		panel_res.add(txt_res);
		panel_res.add(res);
		panel_res.add(butt_res);
		
		panel_borr.add(txt_borr);
		panel_borr.add(borr);
		panel_borr.add(butt_borr);
		
		this.add(panel_pw);
		this.add(panel_res);
		this.add(panel_borr);
		
		//Listner
		ListenerStud listener = new ListenerStud(this, studWest);
		
		butt_pw.setActionCommand(listener.PW);
		butt_pw.addActionListener(listener);
		
		butt_res.setActionCommand(listener.RES);
		butt_res.addActionListener(listener);
		
		butt_borr.setActionCommand(listener.BOR);
		butt_borr.addActionListener(listener);
		
	}

	public void showPanel(){
		this.setVisible(true);
	}
}
