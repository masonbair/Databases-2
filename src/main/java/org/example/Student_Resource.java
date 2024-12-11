

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import org.example.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Student_Resource extends JDialog {
    private JTextField first_name;
    private JTextField last_name;
    private JTextField address;
    private JTextField email;
    private JTextField phone;
    private JTextField password;
    private JTextField attendsUni;

    private JButton saveButton;
    private JButton deleteButton;
    private JButton cancelButton;

    //DB STUFF
    private Database db = null;
    private Connection connection = null;
    private PreparedStatement preparedStatement;

    public Student_Resource(JFrame parent) {
        super(parent, "Add New Student", true);

        //Database stuff
        try {
            db = new Database();
            connection = db.getConnection();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        setLayout(new GridLayout(9, 2, 10, 10));
        setSize(400, 400);
        setLocationRelativeTo(parent);

        // Create labels and text fields
        add(new JLabel("First Name:"));
        first_name = new JTextField();
        add(first_name);

        add(new JLabel("Last Name:"));
        last_name = new JTextField();
        add(last_name);

        add(new JLabel("Address:"));
        address = new JTextField();
        add(address);

        add(new JLabel("Email:"));
        email = new JTextField();
        add(email);

        add(new JLabel("Phone Number:"));
        phone = new JTextField();
        add(phone);

        add(new JLabel("Password:"));
        password = new JTextField();
        add(password);

        add(new JLabel("Attends Uni (y, n):"));
        attendsUni = new JTextField();
        add(attendsUni);


        // Save and Cancel buttons
        saveButton = new JButton("Save Student");
        deleteButton = new JButton("Delete Student");
        cancelButton = new JButton("Cancel");

        add(saveButton);
        add(deleteButton);
        add(cancelButton);

        // Action listeners
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveStudent();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //deleteStudent();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void saveStudent() {
        // Validate and process book information
        String first = first_name.getText();
        String last = last_name.getText();
        String addr = address.getText();
        String email_stu = email.getText();
        String phone_num = phone.getText();
        String psswd = password.getText();
        boolean attendsU = false;
        if(attendsUni.getText().equals("y")){
            attendsU = true;
        }

        // Add validation logic here
        if (first.isEmpty() && psswd.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "The name and password are required fields!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO Student(first_name, last_name, address, email, phone, student_password, attends_uni) VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, first);
            preparedStatement.setString(2, last);
            preparedStatement.setString(3, addr);
            preparedStatement.setString(4, email_stu);
            preparedStatement.setString(5, phone_num);
            preparedStatement.setString(6, psswd);
            preparedStatement.setBoolean(7,attendsU);

            preparedStatement.execute();
            System.out.println("Added Student");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
            System.out.println(e);
        }


        // Clear fields after saving
        clearFields();

        // Close the dialog
        dispose();
    }

    private void deleteBook(){
        String first = first_name.getText();
        String last = last_name.getText();
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM Student WHERE first_name=? AND last_name=?");
            preparedStatement.setString(1, first);
            preparedStatement.setString(2, last);

            System.out.println("Executing first Statement");
            preparedStatement.execute();
            System.out.println("Deleted Student Successful");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
            System.out.println(e);
        }

        // Clear fields after saving
        clearFields();

        // Close the dialog
        dispose();
    }

    private void clearFields() {
        first_name.setText("");
        last_name.setText("");
        email.setText("");
        phone.setText("");
        address.setText("");
        password.setText("");
        attendsUni.setText("");
    }
}