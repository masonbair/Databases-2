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
			JOptionPane.showMessageDialog(null, "test register");

			
		} else if (e.getActionCommand().equals(DEL)) {
			
			JOptionPane.showMessageDialog(null, "test delete");
		
		} else if(e.getActionCommand().equals(ASS)){
			
			JOptionPane.showMessageDialog(null, "test association");
		}
		
	}
	private void addRegistration(){
		try {

			preparedStatement = connection.prepareStatement("INSERT INTO Student(student_id, first_name, last_name, address, email, phone, student_password, attends_uni, library_card) VALUES (?, ?, ?, 1, 'test@test.com', 1, 1, 1, 1)");
			preparedStatement.setString(1, center.get_stu_id());
			preparedStatement.setString(2, center.get_stud_name());
			preparedStatement.setString(3, center.get_stud_surname());
			preparedStatement.execute();
			connection.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

}
