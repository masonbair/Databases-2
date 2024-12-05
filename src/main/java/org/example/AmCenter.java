import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;


public class AmCenter extends JPanel{
	
	private JLabel txt_stud_id = new JLabel("Student_id");
	private JLabel txt_stud_name = new JLabel("First_name");
	private JLabel txt_stud_surname = new JLabel("Last_name");
	private JLabel txt_res_id = new JLabel("Resource_card");
	private JLabel txt_res_type = new JLabel("Resource_card_type");
	private JLabel txt_res_price = new JLabel("Resource_status");
	private JLabel txt_card_code = new JLabel("Card_barcode");
	private JLabel txt_card_status = new JLabel("Card_status");
	private JLabel txt_card_res = new JLabel("Card_res");
	
	public JTextField stud_id = new  JTextField ();
	public JTextField stud_name = new  JTextField ();
	public JTextField stud_surname = new  JTextField ();
	public JTextField res_id = new  JTextField ();
	public JTextField res_type = new  JTextField ();
	public JTextField res_status = new  JTextField ();
	public JTextField card_code = new  JTextField ();
	public JTextField card_status = new  JTextField ();
	public JTextField card_res = new  JTextField ();

	public JTextField cp_book_barcode = new  JTextField ();
	public JTextField cp_book_reference = new  JTextField ();
	public JTextField cp_book_price = new  JTextField ();
	public JTextField cp_book_rack = new JTextField();


	
	JPanel stud_1 = new JPanel(new GridLayout(2,1));
	JPanel stud_2 = new JPanel(new GridLayout(2,1));
	JPanel stud_3 = new JPanel(new GridLayout(2,1));
	JPanel res_1 = new JPanel(new GridLayout(2,1));
	JPanel res_2 = new JPanel(new GridLayout(2,1));
	JPanel res_3 = new JPanel(new GridLayout(2,1));
	JPanel card_1 = new JPanel(new GridLayout(2,1));
	JPanel card_2 = new JPanel(new GridLayout(2,1));
	JPanel card_3 = new JPanel(new GridLayout(2,1));


	public AmCenter() {

		this.setLayout(new GridLayout(3,3));
		//stud_line
		stud_1.add(txt_stud_id);
		stud_1.add(stud_id);
		
		stud_2.add(txt_stud_name);
		stud_2.add(stud_name);
		
		stud_3.add(txt_stud_surname);
		stud_3.add(stud_surname);
		
		//res_line
		res_1.add(txt_res_id);
		res_1.add(res_id);
		
		res_2.add(txt_res_type);
		res_2.add(res_type);
		
		res_3.add(txt_res_price);
		res_3.add(res_status);
		
		//card_line
		card_1.add(txt_card_code);
		card_1.add(card_code);
		
		card_2.add(txt_card_status);
		card_2.add(card_status);
		
		card_3.add(txt_card_res);
		card_3.add(card_res);
		
		//add to this panel
		this.add(stud_1);
		this.add(stud_2);
		this.add(stud_3);
		
		this.add(card_1);
		this.add(card_2);
		this.add(card_3);
		
		this.add(res_1);
		this.add(res_2);
		this.add(res_3);

	}
	public String get_stu_id(){
		return stud_id.getText();
	}
	public String get_stud_name(){
		return stud_name.getText();
	}
	public String get_stud_surname(){
		return stud_surname.getText();
	}
	public String get_res_id(){
		return res_id.getText();
	}
	public String res_id(){
		return stud_id.getText();
	}
	public String get_res_type(){
		return res_type.getText();
	}
	public String get_res_status(){
		return res_status.getText();
	}
	public String get_card_code(){
		return card_code.getText();
	}
	public String get_card_status(){
		return card_status.getText();
	}
	public String get_card_res(){
		return card_res.getText();
	}

	public void clear_text_boxes(){
		stud_id.setText("");
		stud_name.setText("");
		stud_surname.setText("");
		res_id.setText("");
		res_type.setText("");
		res_status.setText("");
		card_code.setText("");
		card_status.setText("");
		card_res.setText("");
	}
// modify button 
	public void openAddBookDialog() {
		// Get the parent frame (you might need to adjust this depending on your main application structure)
		JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		Book_Resource addBookDialog = new Book_Resource(parentFrame);
		addBookDialog.setVisible(true);
	}
	public void openAddComputerDialog() {
		// Get the parent frame (you might need to adjust this depending on your main application structure)
		JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		Computer_Resource addComputerDialog = new Computer_Resource(parentFrame);
		addComputerDialog.setVisible(true);
	}

	public void openAddRoomDialog() {
		// Get the parent frame (you might need to adjust this depending on your main application structure)
		JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		Room_Resource addRoomDialog = new Room_Resource(parentFrame);
		addRoomDialog.setVisible(true);
	}

	public void openNewBookCopyDialog() {
		// Get the parent frame (you might need to adjust this depending on your main application structure)
		JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		Copy_Book_Resource addRoomDialog = new Copy_Book_Resource(parentFrame);
		addRoomDialog.setVisible(true);
	}
}
