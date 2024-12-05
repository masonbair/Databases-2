
import org.example.Database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
	private StudWest stud_west;

	 public ListenerStud(StudEast SE, StudWest SW) {
		 this.stud_east = SE;
		 this.stud_west = SW;

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

			loadBookResources();

		} else if(e.getActionCommand().equals(RES)){
			loadBookResources();
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

	private void loadBookResources(){
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM BookCopy");


			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("Created and ran Query");
			String[][] data = new String[10][4];
			int i = 0;
			while (rs.next() && i < data.length) {
				System.out.println("Adding data to the data section");
				data[i][0] = rs.getString("book_ref");
				data[i][1] = rs.getString("price");
				data[i][2] = rs.getString("rack_num");
				data[i][3] = rs.getString("copy_language");
				i++;
			}

			DefaultTableModel model = new DefaultTableModel(data, stud_west.book_column);

			stud_west.display.setModel(model);

			System.out.println("Updated the students password");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			System.out.println(e);
		}
	}




}
