import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LibNorth extends JPanel{
	private JLabel txt_stud = new JLabel("Student_id");
	public JTextField stud_id = new JTextField(10);
	private JPanel panel_stud= new JPanel(new GridLayout(2,1));
	
	private JLabel txt_type = new JLabel("Resorce_type");
	public JTextField type = new JTextField(10);
	private JPanel panel_type= new JPanel(new GridLayout(2,1));
	
	private JLabel txt_res = new JLabel("Resource_id");
	public JTextField res_id = new JTextField(10);
	private JPanel panel_res = new JPanel(new GridLayout(2,1));
	
	private JPanel panel_info= new JPanel(new GridLayout(1,3));
	
	private JButton butt_borr = new JButton("Borrow");
	private JButton butt_ret = new JButton("Returned");
	private JPanel panel_butt= new JPanel(new FlowLayout(FlowLayout.CENTER));
	
	
	
	public LibNorth() {
		
		this.setLayout(new BorderLayout());
		
		panel_stud.add(txt_stud);
		panel_stud.add(stud_id);
		
		panel_type.add(txt_type);
		panel_type.add(type);
		
		panel_res.add(txt_res);
		panel_res.add(res_id);
		
		panel_info.add(panel_stud);
		panel_info.add(panel_type);
		panel_info.add(panel_res);
		
		panel_butt.add(butt_borr);
		panel_butt.add(butt_ret);
		
		this.add(panel_info, BorderLayout.NORTH);
		this.add(panel_butt, BorderLayout.SOUTH);
		
		ListenerLibOp listener = new ListenerLibOp(this);
		butt_borr.setActionCommand(listener.BOR);
		butt_borr.addActionListener(listener);
		butt_ret.setActionCommand(listener.RET);
		butt_ret.addActionListener(listener);
		
	}

}
