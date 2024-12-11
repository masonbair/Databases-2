import org.example.Database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.sql.*;


public class ListenerAm implements ActionListener {

	public static final String REG = "Register";
	public static final String DEL = "Delete";
	public static final String ASS = "Associate";
	public static final String BOOK = "Add_Book";
	public static final String ROOM = "Add_Room";
	public static final String COMP = "Add_Computer";
	public static final String COPY = "Add_Book_Copy";
	public static final String STU = "Add_Student";


	private Database db = null;
	private Connection connection = null;
	private PreparedStatement preparedStatement;
	private AmCenter center = null;

	public ListenerAm(AmCenter center) {  // cust
		this.center = center;
		try{
			db = new Database();
			connection = db.getConnection(); // add db as instance
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals(REG)) {
			addRegistration();    // funcio go down
			center.clear_text_boxes();

			
		} else if (e.getActionCommand().equals(DEL)) {
			deleteRegistration();
			center.clear_text_boxes();

		} else if(e.getActionCommand().equals(ASS)){

			associationRegistration();
			center.clear_text_boxes();
		} else if(e.getActionCommand().equals(BOOK)){
			center.openAddBookDialog();
		} else if(e.getActionCommand().equals(ROOM)){
			center.openAddRoomDialog();
		} else if(e.getActionCommand().equals(COMP)){
			center.openAddComputerDialog();
		}else if(e.getActionCommand().equals(COPY)){
			center.openNewBookCopyDialog();
		}else if(e.getActionCommand().equals(STU)){
			center.openStudentDialog();
		}
		
	}
	private void addRegistration() {
		try {
			if (center.get_card_code().length() != 0) {
				preparedStatement = connection.prepareStatement("INSERT INTO library_card(status) VALUES (?)"); // init query
				preparedStatement.setString(1, center.get_card_status()); // (#numb of ?, get string)
				preparedStatement.execute(); // run on db
				System.out.println("Added Card");

			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

		private void deleteRegistration() {
			try {
				if (center.get_card_code().length() != 0) {
					preparedStatement = connection.prepareStatement("DELETE FROM library_card where library_num=?");
					preparedStatement.setString(1, center.get_card_code());
					preparedStatement.execute();
					System.out.println("Deleted Card");


				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	private void associationRegistration() {
		try {
			if (center.get_card_status().length() != 0 && center.get_card_code().length() != 0) {
				preparedStatement = connection.prepareStatement("UPDATE Student SET library_card=? WHERE student_id=?");
				preparedStatement.setString(1, center.get_card_code());
				preparedStatement.setString(2, center.get_card_status());
				preparedStatement.execute();
				System.out.println("Associated Student and Library card");

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}


}
