import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.sql.Connection;
import org.example.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;  
import java.sql.*;
import java.time.LocalDate;


public class ListenerLibOp implements ActionListener {

	 final String BOR = "borrow";
	 final String RET = "returned";

	 //DB instaces
	 private Database db = null;
	 private Connection connection = null;
	 private PreparedStatement preparedStatement;

	 LibNorth ln;
	 
	 public ListenerLibOp(LibNorth ln) {
		this.ln = ln;
		  //Database init
		  try {
            this.db = new Database();
            connection = db.getConnection();
        } catch (Exception exep) {
            System.out.println(exep.getStackTrace());
        }
		
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(BOR)) {
			if (ln.stud_id.equals("") || ln.res_id.equals("") || ln.type.equals("") ) {
					JOptionPane.showMessageDialog(null,
							"Type Res, Reference ID and Student ID are a required field! Validation Error");
					return;
			}
			String type_res = ln.type.getText();
			String stud_id = ln.stud_id.getText();
			String res_id  = ln.res_id.getText();
			LocalDate localDate = LocalDate.now();  // Get the current date
			java.sql.Date date = java.sql.Date.valueOf(localDate);  // Convert to java.sql.Date

			//clean
			ln.type.setText("");
			ln.stud_id.setText("");
			ln.res_id.setText("");

			if (type_res.equals("room")){
				try{
						preparedStatement = connection.prepareStatement("INSERT INTO Borrow_room(room_num, student_id, date_start) VALUES (?, ?, ?)");
						preparedStatement.setString(1, res_id);
						preparedStatement.setString(2, stud_id);
						preparedStatement.setDate(3, date);
			
						preparedStatement.execute();

						System.out.println("Added Borrow_Room");
						JOptionPane.showMessageDialog(null, "Borrow_relation added");
				}catch(SQLException sql_e){
						if (sql_e.getErrorCode() == 1001) {
							JOptionPane.showMessageDialog(null, "ERROR: " + sql_e.getMessage());
							System.out.println("ERROR: " + sql_e.getMessage());
						}
				}catch(Exception exe){
						System.out.println(exe);
				}
			}
			else if (type_res.equals("book")){
					
				try{
						preparedStatement = connection.prepareStatement("INSERT INTO Borrow_book(book_id, student_id, date_start) VALUES (?, ?, ?)");
						preparedStatement.setString(1, res_id);
						preparedStatement.setString(2, stud_id);
						preparedStatement.setDate(3, date);

						preparedStatement.execute();


						System.out.println("Added Borrow_Book");
						JOptionPane.showMessageDialog(null, "Borrow_relation added");

				}catch(SQLException sql_e){


						if (sql_e.getErrorCode() == 1001) {
							JOptionPane.showMessageDialog(null, "ERROR: " + sql_e.getMessage());
							System.out.println("ERROR: " + sql_e.getMessage());
						}else{
							JOptionPane.showMessageDialog(null, "The student or book does not exist");
							System.out.println("ERROR: " + sql_e.getMessage());
						}
				}catch(Exception exe){
						System.out.println(exe);

				}
					


			} else if (type_res.equals("computer")){
				try{
						preparedStatement = connection.prepareStatement("INSERT INTO Borrow_computer(computer_id, student_id, date_start) VALUES (?, ?, ?)");
						preparedStatement.setString(1, res_id);
						preparedStatement.setString(2, stud_id);
						preparedStatement.setDate(3, date);
			
						preparedStatement.execute();

						System.out.println("Added Borrow_Computer");
				}catch(SQLException sql_e){
						if (sql_e.getErrorCode() == 1001) {
							System.out.println("ERROR: " + sql_e.getMessage());
						}
				}catch(Exception exe){
						System.out.println(exe);
				}
			} else {
					JOptionPane.showMessageDialog(null, "Please select one from book, room, computer");
			}
		}
		else if(e.getActionCommand().equals(RET)){

			// WHAT APPEND IF I TRY TO RESTITUETE ITEMS THAT ARE NOT BORROW

			String type_res = ln.type.getText();
			String stud_id = ln.stud_id.getText();
			String res_id  = ln.res_id.getText();
			LocalDate localDate = LocalDate.now();  // Get the current date
            java.sql.Date date = java.sql.Date.valueOf(localDate);  // Convert to java.sql.Date

			//clean
			ln.type.setText("");
			ln.stud_id.setText("");
			ln.res_id.setText("");

			if(type_res.equals("") || stud_id.equals("") || res_id.equals("")){
				JOptionPane.showMessageDialog(null,"Complete all the felds! Validation Error");
				return;
			}

			if (type_res.equals("room")){
				try{
					String sql = "DELETE FROM Borrow_room WHERE student_id= ? AND room_id = ?";
					preparedStatement = connection.prepareStatement(sql);
					
					preparedStatement.setString(1, stud_id);  
					preparedStatement.setString(2, res_id);    
					
					// Execute the delete operation
					int rowsAffected = preparedStatement.executeUpdate();  
					
					// Check if the operation was successful
					if (rowsAffected > 0) {
						System.out.println("Record deleted successfully from borrow_room.");
					} else {
						System.out.println("No matching record found to delete.");
					}
				}catch(Exception exe){
					System.out.println(exe);
				}
			}
			else if (type_res.equals("book")){
				try{
					String sql = "DELETE FROM Borrow_book WHERE student_id = ? AND book_id = ?";
					preparedStatement = connection.prepareStatement(sql);
					
					preparedStatement.setString(1, stud_id);  
					preparedStatement.setString(2, res_id);    
					
					int rowsAffected = preparedStatement.executeUpdate();  
			
					if (rowsAffected > 0) {
						System.out.println("Record deleted successfully from borrow");
					} else {
						System.out.println("No matching record found to delete.");
					}
				}catch(Exception exe){
					System.out.println(exe);
				}
				
			} else if (type_res.equals("computer")){
				try{
					String sql = "DELETE FROM Borrow_computer WHERE student_id = ? AND computer_id = ?";
					preparedStatement = connection.prepareStatement(sql);
					
					preparedStatement.setString(1, stud_id);  
					preparedStatement.setString(2, res_id);    
					
					// Execute the delete operation
					int rowsAffected = preparedStatement.executeUpdate(); 
					
					// Check if the operation was successful
					if (rowsAffected > 0) {
						System.out.println("Record deleted successfully from borrow_computer.");
					} else {
						System.out.println("No matching record found to delete.");
					}
					
				}catch(Exception exe){
					System.out.println(exe);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Please select one from book, room, computer");
				return;
			}

			JOptionPane.showMessageDialog(null, "Item returned!");
		}
	}
	
}

