
import org.example.Database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import java.sql.*;


public class ListenerStud implements ActionListener {
	 
	 final String PW = "set_pw";
	 final String BOR = "display_bor";
	 final String RES = "display_res";

	//Other necessary variables
	public Database db = null;
	public Connection connection = null;
	private PreparedStatement preparedStatement;

	private StudEast stud_east;
	 
	 public ListenerStud(StudEast SE) {
		 this.stud_east = SE;
		 try{
			 db = new Database();
			 connection = db.getConnection();
		 }catch(Exception e){
			 System.out.println(e.getStackTrace());
		 }

	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals(PW)) {
			
			addPassword();
			
		} else if (e.getActionCommand().equals(BOR)) {
			
			JOptionPane.showMessageDialog(null, "test bor");
		
		} else if(e.getActionCommand().equals(RES)){
			
			JOptionPane.showMessageDialog(null, "test res");
		}
	}

	private void addPassword(){
		String new_password = stud_east.pw.getText();
		System.out.println("Name of student: " + stud_east.student);

		try {
			preparedStatement = connection.prepareStatement("UPDATE Student SET student_password=? WHERE student_id=? AND student_password=?");
			preparedStatement.setString(1, new_password);
			preparedStatement.setString(2, stud_east.student);
			preparedStatement.setString(3, stud_east.password);

			preparedStatement.execute();

			System.out.println("Updated the students password");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			System.out.println(e);
		}
	}




}
