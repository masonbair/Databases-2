import org.example.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Room_Resource extends JDialog {
    private JTextField room_num;
    private JTextField number_of_people;


    private JButton saveButton;
    private JButton deleteButton;
    private JButton cancelButton;

    //DB STUFF
    private Database db = null;
    private Connection connection = null;
    private PreparedStatement preparedStatement;

    public Room_Resource(JFrame parent) {
        super(parent, "Add New Room", true);

        //Database stuff
        try{
            db = new Database();
            connection = db.getConnection();
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }


        setLayout(new GridLayout(8, 2, 10, 10));
        setSize(400, 400);
        setLocationRelativeTo(parent);

        // Create labels and text fields
        add(new JLabel("Room Number:"));
        room_num = new JTextField();
        add(room_num);

        add(new JLabel("Number of People:"));
        number_of_people = new JTextField();
        add(number_of_people);

        // Save and Cancel buttons
        saveButton = new JButton("Save Room");
        deleteButton = new JButton("Delete Room");
        cancelButton = new JButton("Cancel");

        add(saveButton);
        add(deleteButton);
        add(cancelButton);

        // Action listeners
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveRoom();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRoom();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void saveRoom() {
        String room = room_num.getText();
        String num_people = number_of_people.getText();

        //Validation
        if (room.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Room Number is a required field!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            preparedStatement = connection.prepareStatement("INSERT INTO Room(room_num, number_of_people) VALUES (?, ?)");
            preparedStatement.setString(1, room);
            preparedStatement.setString(2, num_people);

            preparedStatement.execute();
            System.out.println("Added Room");
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
            System.out.println(e);
        }



        // Clear fields after saving
        clearFields();

        // Close the dialog
        dispose();
    }

    private void deleteRoom() {
        String room = room_num.getText();

        //Validation
        if (room.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Room Number is a required field!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            preparedStatement = connection.prepareStatement("DELETE FROM Room WHERE room_num=?");
            preparedStatement.setString(1, room);

            preparedStatement.execute();
            System.out.println("Deleted Room");
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
            System.out.println(e);
        }

        // Clear fields after saving
        clearFields();

        // Close the dialog
        dispose();
    }

    private void clearFields() {
        room_num.setText("");
        number_of_people.setText("");
    }
}