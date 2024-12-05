import org.example.Database;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

public class StudFrame extends JPanel {

	public StudEast east;
	public StudWest west;
	public JPanel loginPanel;

	public JLabel student_id;
	public JTextField student_field;
	public JLabel password_label;
	public JTextField password_field;
	public JButton login;

	//DB STUFF
	private Database db = null;
	private Connection connection = null;
	private PreparedStatement preparedStatement;

	private String student;
	private String password;

	public StudFrame() {

		//Database stuff
		try{
			db = new Database();
			connection = db.getConnection();
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}

		loginPanel = new JPanel();
		loginPanel.setLayout(new GridLayout(3, 2, 10, 10));

		student_id = new JLabel("Student ID:");
		student_field = new JTextField();
		loginPanel.add(student_id);
		loginPanel.add(student_field);


		//Adding the password
		password_label = new JLabel("Password:");
		password_field = new JPasswordField();
		loginPanel.add(password_label);
		loginPanel.add(password_field);

		// Add login panel to the page

		// Login button
		login = new JButton("Login");
		loginPanel.add(login);

		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadStudent();
			}
		});

		east = new StudEast();
		west = new StudWest();
		this.add(loginPanel);
		this.add(east, BorderLayout.EAST);
		this.add(west, BorderLayout.WEST);

	}

	private void loadStudent(){

		student = student_field.getText();
		password = password_field.getText();

		east.student = student;
		east.password = password;

		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM Student WHERE student_id=? AND student_password=?");
			preparedStatement.setString(1, student);
			preparedStatement.setString(2, password);

			System.out.println("Executing first Statement");
			ResultSet rs = preparedStatement.executeQuery();




			if(rs.next()){

				east.showPanel();
				west.showPanel();
				loginPanel.setVisible(false);
			}else{
				JOptionPane.showMessageDialog(this,"Username or Password incorrect: " + student);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e);
			System.out.println(e);
		}
	}

	private void changePassword(){
		String new_password = east.pw.getText();
		System.out.println("Name of student: " + student);

		try {
			preparedStatement = connection.prepareStatement("UPDATE Student SET student_password=? WHERE student_id=? AND student_password=?");
			preparedStatement.setString(1, new_password);
			preparedStatement.setString(2, student);
			preparedStatement.setString(3, password);

			preparedStatement.execute();

			System.out.println("Updated the students password");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e);
			System.out.println(e);
		}
	}
	
}
