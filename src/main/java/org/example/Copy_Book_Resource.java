import org.example.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Copy_Book_Resource extends JDialog {
    private JTextField book_ref_id;
    private JTextField price;
    private JTextField rack_num;
    private JButton saveButton;
    private JButton cancelButton;

    //DB STUFF
    private Database db = null;
    private Connection connection = null;
    private PreparedStatement preparedStatement;

    public Copy_Book_Resource(JFrame parent) {
        super(parent, "Add New Copy of Book", true);

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


        add(new JLabel("Original Book ID:"));
        book_ref_id = new JTextField();
        add(book_ref_id);

        add(new JLabel("Price for Copy:"));
        price = new JTextField();
        add(price);

        add(new JLabel("Rack Number:"));
        rack_num = new JTextField();
        add(rack_num);


        // Save and Cancel buttons
        saveButton = new JButton("Save Copy of Book");
        cancelButton = new JButton("Cancel");

        add(saveButton);
        add(cancelButton);

        // Action listeners
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCopyOfBook();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void saveCopyOfBook() {
        // Validate and process book information
        String ref_id = book_ref_id.getText();
        String cost = price.getText();
        String rack = rack_num.getText();

        // Add validation logic here
        if (ref_id.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Reference ID is a required field!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            preparedStatement = connection.prepareStatement("INSERT INTO BookCopy(book_ref, price, rack_num) VALUES (?, ?, ?)");
            preparedStatement.setString(1, ref_id);
            preparedStatement.setString(2, cost);
            preparedStatement.setString(3, rack);

            preparedStatement.execute();
            System.out.println("Added Copy of Book");
        }catch(Exception e){
            System.out.println(e);
        }


        // Clear fields after saving
        clearFields();

        // Close the dialog
        dispose();
    }

    private void clearFields() {
        book_ref_id.setText("");
        price.setText("");
        rack_num.setText("");
    }
}