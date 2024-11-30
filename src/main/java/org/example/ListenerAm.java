import org.example.Database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.sql.*;


public class ListenerAm implements ActionListener {

	public static final String REG = "Register";
	public static final String DEL = "Delete";
	public static final String ASS = "Associate";

	private Database db = null;
	private Connection connection = null;
	private PreparedStatement preparedStatement;
	private AmCenter center = null;

	public ListenerAm(AmCenter center) {
		this.center = center;
		try{
			db = new Database();
			connection = db.getConnection();
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals(REG)) {
			addRegistration();
			center.clear_text_boxes();

			
		} else if (e.getActionCommand().equals(DEL)) {
			deleteRegistration();
			center.clear_text_boxes();

		} else if(e.getActionCommand().equals(ASS)){

			associationRegistration();
			center.clear_text_boxes();
		}
		
	}
	private void addRegistration() {
		try {
			if (center.get_card_code().length() != 0) {
				preparedStatement = connection.prepareStatement("INSERT INTO library_card(status) VALUES (?)");
				preparedStatement.setString(1, center.get_card_status());
				preparedStatement.execute();
				System.out.println("Added Card");

			}
			if (center.get_stu_id().length() != 0) {
				preparedStatement = connection.prepareStatement("INSERT INTO Student(student_id, first_name, last_name, address, email, phone, student_password, attends_uni, library_card) VALUES (?, ?, ?, 1, 'test@test.com', 1, 1, 1, 1)");
				preparedStatement.setString(1, center.get_stu_id());
				preparedStatement.setString(2, center.get_stud_name());
				preparedStatement.setString(3, center.get_stud_surname());
				preparedStatement.execute();
				System.out.println("Added Student");


			}
			if (center.get_res_id().length() != 0) {
				preparedStatement = connection.prepareStatement("INSERT INTO resource_card(r_number, resource, lib_card) VALUES (?, ?, 1)");
				preparedStatement.setString(1, center.get_res_id());
				//preparedStatement.setString(2, center.get_res_price());
				preparedStatement.setString(2, center.get_res_type());
				preparedStatement.execute();
				System.out.println("Added Resource");

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

		private void deleteRegistration() {
			try {
				if (center.get_stu_id().length() != 0) {
					preparedStatement = connection.prepareStatement("DELETE FROM Student where student_id=?");
					preparedStatement.setString(1, center.get_stu_id());
					preparedStatement.execute();
					System.out.println("Deleted Student");


				}
				if (center.get_card_code().length() != 0) {
					preparedStatement = connection.prepareStatement("DELETE FROM library_card where library_num=?");
					preparedStatement.setString(1, center.get_card_code());
					preparedStatement.execute();
					System.out.println("Deleted Card");


				}
				if (center.get_res_id().length() != 0) {
					preparedStatement = connection.prepareStatement("DELETE FROM resource_card where r_number=?");
					preparedStatement.setString(1, center.get_res_id());
					preparedStatement.execute();
					System.out.println("Deleted Resource");


				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	private void associationRegistration() {
		try {
			if (center.get_stu_id().length() != 0 && center.get_card_code().length() != 0) {
				preparedStatement = connection.prepareStatement("UPDATE Student SET library_card=? WHERE student_id=?");
				preparedStatement.setString(1, center.get_card_code());
				preparedStatement.setString(2, center.get_stu_id());
				preparedStatement.execute();
				System.out.println("Associated Student and Library card");

			}
			if (center.get_card_code().length() != 0 && center.get_res_id().length() != 0) {
				preparedStatement = connection.prepareStatement("UPDATE resource_card SET lib_card=? WHERE r_number=? ");
				preparedStatement.setString(1, center.get_card_code());
				preparedStatement.setString(2, center.get_res_id());
				preparedStatement.execute();
				System.out.println("Associated Library Card and Resource");


			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
