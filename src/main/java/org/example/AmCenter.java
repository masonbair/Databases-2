import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;


public class AmCenter extends JPanel{

	private JLabel txt_card_code = new JLabel("Card barcode");
	private JLabel txt_card_status = new JLabel("Card status or Student_id");
	private JLabel txt_card_res = new JLabel("Card Resource");

	public JTextField card_code = new  JTextField ();
	public JTextField card_status = new  JTextField ();
	public JTextField card_res = new  JTextField ();

	JPanel card_1 = new JPanel(new GridLayout(2,1));
	JPanel card_2 = new JPanel(new GridLayout(2,1));
	JPanel card_3 = new JPanel(new GridLayout(2,1));


	public AmCenter() {

		this.setLayout(new GridLayout(3,3));

		//card_line
		card_1.add(txt_card_code);
		card_1.add(card_code);

		card_2.add(txt_card_status);
		card_2.add(card_status);

		card_3.add(txt_card_res);
		card_3.add(card_res);

		this.add(card_1);
		this.add(card_2);
		this.add(card_3);


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
		card_code.setText("");
		card_status.setText("");
		card_res.setText("");
	}
// modify button
	public void openStudentDialog(){
		JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		Student_Resource addStu = new Student_Resource(parentFrame);
		addStu.setVisible(true);
	}
	public void openAddBookDialog() {
		// Get the parent frame 
		JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		Book_Resource addBookDialog = new Book_Resource(parentFrame);
		addBookDialog.setVisible(true);
	}
	public void openAddComputerDialog() {
		// Get the parent frame 
		JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		Computer_Resource addComputerDialog = new Computer_Resource(parentFrame);
		addComputerDialog.setVisible(true);
	}

	public void openAddRoomDialog() {
		// Get the parent frame 
		JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		Room_Resource addRoomDialog = new Room_Resource(parentFrame);
		addRoomDialog.setVisible(true);
	}

	public void openNewBookCopyDialog() {
		// Get the parent frame 
		JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		Copy_Book_Resource addRoomDialog = new Copy_Book_Resource(parentFrame);
		addRoomDialog.setVisible(true);
	}
}
