package org.example;

import java.sql.*;

public class Database {
    private Connection connection = null;

    public Database() throws ClassNotFoundException {
        try {
            ///----------------------------------------------
            //You need to add the user name and password here
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3307/Project2",
                        "user", "12345");
            //-------------------------------------------------
            System.out.println("Database is connected!");
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public Connection getConnection(){
        return connection;
    }
    public void closeConnection(){
        try{
            if(!connection.isClosed()){
                connection.close();
                System.out.println("database connection is closed.");
            }
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
    }

}

//Statement statement;
//statement = connection.createStatement();
//ResultSet resultSet;
//resultSet = statement.executeQuery(
//					"select * from Student");
//String first;
//String last;
//			while (resultSet.next()) {
//first = resultSet.getString("first_name").trim();
//last = resultSet.getString("last_name").trim();
//				System.out.println("first_name : " + first
//						+ " last_name : " + last);
//			}
//                    resultSet.close();
//			statement.close();
//			connection.close();
