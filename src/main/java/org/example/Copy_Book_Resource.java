import org.example.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Copy_Book_Resource extends JDialog {
    private JTextField barcode;
    private JTextField book_ref_id;
    private JTextField price;
    private JTextField rack_num;
    private JTextField language;


    private JButton saveButton;
    private JButton deleteButton;
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

        setLayout(new GridLayout(9, 2, 10, 10));
        setSize(400, 400);
        setLocationRelativeTo(parent);


        add(new JLabel("Barcode:"));
        barcode = new JTextField();
        add(barcode);

        add(new JLabel("Original Book Name:"));
        book_ref_id = new JTextField();
        add(book_ref_id);

        add(new JLabel("Price for Copy:"));
        price = new JTextField();
        add(price);

        add(new JLabel("Rack Number:"));
        rack_num = new JTextField();
        add(rack_num);

        add(new JLabel("Language for copy:"));
        language = new JTextField();
        add(language);


        // Save and Cancel buttons
        saveButton = new JButton("Save Copy of Book");
        deleteButton = new JButton("Delete Copy");
        cancelButton = new JButton("Cancel");

        add(saveButton);
        add(deleteButton);
        add(cancelButton);

        // Action listeners
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCopyOfBook();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCopy();
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
        String bar = barcode.getText();
        String ref_id = book_ref_id.getText();
        String cost = price.getText();
        String rack = rack_num.getText();
        String lang =  language.getText();

        // Add validation logic here
        if (bar.isEmpty() || ref_id.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Reference ID and Barcode is a required field!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            preparedStatement = connection.prepareStatement("INSERT INTO BookCopy(barcode, book_ref, price, rack_num, copy_language) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, bar);
            preparedStatement.setString(2, ref_id);
            preparedStatement.setString(3, cost);
            preparedStatement.setString(4, rack);
            preparedStatement.setString(5, lang);

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

    private void deleteCopy(){
        // Validate and process book information
        String bar = barcode.getText();

        // Add validation logic here
        if (bar.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Barcode is a required field!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            preparedStatement = connection.prepareStatement("DELETE FROM BookCopy WHERE barcode=?");
            preparedStatement.setString(1, bar);

            preparedStatement.execute();
            System.out.println("Deleted Copy of Book");
        }catch(Exception e){
            System.out.println(e);
        }


        // Clear fields after saving
        clearFields();

        // Close the dialog
        dispose();
    }

    private void clearFields() {
        barcode.setText("");
        book_ref_id.setText("");
        price.setText("");
        rack_num.setText("");
        language.setText("");
    }
}