
import org.example.Database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;


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

			showCurrentBorrows();

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
			String[][] data = new String[50][4];
			int i = 0;
			while (rs.next() && i < data.length) {
				System.out.println("Adding data to the data section");
				data[i][0] = rs.getString("book_ref");
				data[i][1] = rs.getString("price");
				data[i][2] = rs.getString("rack_num");
				data[i][3] = rs.getString("copy_language");
				i++;
			}

			String[] book_column = new String[]{"Title", "Price", "Rack Number", "Language"};

			DefaultTableModel model = new DefaultTableModel(data, book_column);

			stud_west.display.setModel(model);

			System.out.println("Showing all books to borrow");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			System.out.println(e);
		}
	}

	private void showCurrentBorrows(){
		try {
			String[][] data = new String[50][4];
			//passed due data for book
			preparedStatement = connection.prepareStatement("SELECT * FROM Borrow_book\n" +
					"LEFT JOIN Student ON Student.student_id = Borrow_book.student_id \n" +
					"LEFT JOIN BookCopy ON BookCopy.barcode = Borrow_book.book_id\n" +
					"WHERE Student.student_id = ? AND student_password = ?");

			preparedStatement.setString(1, stud_east.student);
			preparedStatement.setString(2, stud_east.password);

			ResultSet rs_book = preparedStatement.executeQuery();

			int i = 0;
			while (rs_book.next() && i < data.length) {
				data[i][0] = rs_book.getString("book_ref");
				data[i][1] = rs_book.getString("barcode");
				data[i][2] = rs_book.getString("date_start");
				data[i][3] = rs_book.getString("date_due");
				i++;
			}
			// pass due data for pc
			preparedStatement = connection.prepareStatement("SELECT * FROM Borrow_computer\n" +
					"LEFT JOIN Student ON Student.student_id = Borrow_computer.student_id \n" +
					"LEFT JOIN Computer ON Computer.barcode = Borrow_computer.computer_id\n" +
					"WHERE Student.student_id = ? AND student_password = ?");

			preparedStatement.setString(1, stud_east.student);
			preparedStatement.setString(2, stud_east.password);
			
			ResultSet rs_pc = preparedStatement.executeQuery();
			System.out.println("Created and ran Query");
			int j = 0;
			while (rs_pc.next() && j < data.length) {
				data[i][0] = rs_pc.getString("model");
				data[i][1] = rs_pc.getString("barcode");
				data[i][2] = rs_pc.getString("date_start");
				data[i][3] = rs_pc.getString("date_due");
				i++;
				j++;
			}
			// pass due data for room
			preparedStatement = connection.prepareStatement("SELECT * FROM Borrow_room\n" +
					"LEFT JOIN Student ON Student.student_id = Borrow_room.student_id \n" +
					"LEFT JOIN Room ON Room.room_num = Borrow_room.room_num\n" +
					"WHERE Student.student_id = ? AND student_password = ?");

			preparedStatement.setString(1, stud_east.student);
			preparedStatement.setString(2, stud_east.password);

			ResultSet rs_room = preparedStatement.executeQuery();
			System.out.println("Created and ran Query");
			int z = 0;
			while (rs_room.next() && z < data.length) {
				System.out.println("Adding data to the data section");
				data[i][0] = rs_room.getString("room_num");
				data[i][1] = rs_room.getString("room_num");
				data[i][2] = rs_room.getString("date_start");
				data[i][3] = rs_room.getString("date_due");
				i++;
				z++;
			}

			DefaultTableModel model = new DefaultTableModel(data, stud_west.book_column);

			stud_west.display.setModel(model);

			System.out.println("Showing Borrow data");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			System.out.println(e);
		}
	}




}
