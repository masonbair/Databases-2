import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JOptionPane;
import org.example.Database;
import java.sql.*;
import java.util.Date;  
import java.time.LocalDate;


public class ListenerLibDisplay implements ActionListener {
	public static final String STUD = "DisplayStudent";
	public static final String BOOK = "DisplayBook";
	JTextArea display_center_panel = null;

	//Other necessary variables
	public Database db = null;
	public Connection connection = null;
	private PreparedStatement preparedStatement;

	public ListenerLibDisplay(JTextArea display_center_panel){
		this.display_center_panel = display_center_panel;
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
		
		if (e.getActionCommand().equals(STUD)) {
			try{
				this.display_center_panel.setText("");
				preparedStatement = connection.prepareStatement("SELECT * FROM Student");
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
                	int studId = resultSet.getInt("student_id");
                	String studname = resultSet.getString("first_name");
                	String studSur = resultSet.getString("last_name");
                	String studEmail = resultSet.getString("email");

                	String resultText = String.format("Student ID: %d, first name: %s, last name: %s, email: %s%n",
                    	studId, studname, studSur,studEmail);
                	this.display_center_panel.append(resultText);
				}
            }catch (Exception exec) {
				JOptionPane.showMessageDialog(null, exec);
				System.out.println(exec);
			}		
		
		} else if(e.getActionCommand().equals(BOOK)){
			try{
				this.display_center_panel.setText("");
				//init date
				LocalDate localDate = LocalDate.now();  // Get the current date
				java.sql.Date date = java.sql.Date.valueOf(localDate);  // Convert to java.sql.Date
				//query
				preparedStatement = connection.prepareStatement("SELECT * FROM Borrow_book WHERE date_due < ?");
				preparedStatement.setDate(1, date);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					int bookId = resultSet.getInt("book_id");
					int studentId = resultSet.getInt("student_id");
					Date dateStart = resultSet.getDate("date_start");
					Date dateDue = resultSet.getDate("date_due");

					String resultText = String.format("Book ID: %d, Student ID: %d, Date Start: %s, Date Due: %s%n",
							bookId, studentId, dateStart.toString(), dateDue.toString());
					this.display_center_panel.append(resultText);
				}
			}catch(Exception exe) {
				JOptionPane.showMessageDialog(null, exe);
				System.out.println(exe);
			}		
		}
		
	}

}
