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
		  //Database iniy
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


			if (type_res.equals("room")){

				// Add validation logic here
			
		
				// Clear fields after saving

				JOptionPane.showMessageDialog(null, 1);
			}
			else if (type_res.equals("book")){
				
				try{
					preparedStatement = connection.prepareStatement("INSERT INTO Borrow_book(book_id, student_id, date_start) VALUES (?, ?, ?)");
					preparedStatement.setString(1, res_id);
					preparedStatement.setString(2, stud_id);
					preparedStatement.setDate(3, date);
		
					preparedStatement.execute();

					System.out.println("Added Borrow_Book");

				}catch(Exception exe){
					System.out.println(exe);
				}
				
				JOptionPane.showMessageDialog(null, "Borrow_relation added");
			} else if (type_res.equals("computer")){

						JOptionPane.showMessageDialog(null, 3);
			} else {
				JOptionPane.showMessageDialog(null, "Please select one from book, room, computer");
			}
		}
		else if(e.getActionCommand().equals(RET)){
			
			JOptionPane.showMessageDialog(null, "test ret");
		}
	}
	
}

