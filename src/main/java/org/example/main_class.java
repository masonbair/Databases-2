import org.example.Database;

import java.sql.*;

public class main_class {
	public static void main(String[] args) throws ClassNotFoundException {

		//This sets up the database session
		Database db = new Database();
		db.closeConnection();
		//This sets up the new GUI session
		GUI gui = new GUI();
		

	}
}
