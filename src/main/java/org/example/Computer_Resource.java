import org.example.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Computer_Resource extends JDialog {
    private JTextField barcode;
    private JTextField model;
    private JTextField rack_num;

    private JButton saveButton;
    private JButton deleteButton;
    private JButton cancelButton;

    //DB STUFF
    private Database db = null;
    private Connection connection = null;
    private PreparedStatement preparedStatement;

    public Computer_Resource(JFrame parent) {
        super(parent, "Add New Computer", true);

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
        add(new JLabel("Barcode:"));
        barcode = new JTextField();
        add(barcode);

        add(new JLabel("Model:"));
        model = new JTextField();
        add(model);

        add(new JLabel("Rack Number:"));
        rack_num = new JTextField();
        add(rack_num);

        // Save and Cancel buttons
        saveButton = new JButton("Save Computer");
        deleteButton = new JButton("Delete Computer");
        cancelButton = new JButton("Cancel");

        add(saveButton);
        add(deleteButton);
        add(cancelButton);

        // Action listeners
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveComputer();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteComputer();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void saveComputer() {
        String code = barcode.getText();
        String modl = model.getText();
        String rack = rack_num.getText();

        //Validation
        if (code.isEmpty() || modl.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Code and Model are required fields!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try{
            preparedStatement = connection.prepareStatement("INSERT INTO Computer(barcode, model, rack_num) VALUES (?, ?, ?)");
            preparedStatement.setString(1, code);
            preparedStatement.setString(2, modl);
            preparedStatement.setString(3, rack);
            preparedStatement.execute();
            System.out.println("Added Computer");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
            System.out.println(e);
        }



        // Clear fields after saving
        clearFields();

        // Close the dialog
        dispose();
    }

    private void deleteComputer() {
        String code = barcode.getText();

        //Validation
        if (code.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Barcode is a required field!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try{
            preparedStatement = connection.prepareStatement("DELETE FROM Computer WHERE barcode=?");
            preparedStatement.setString(1, code);

            preparedStatement.execute();
            System.out.println("Deleted Computer");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
            System.out.println(e);
        }



        // Clear fields after saving
        clearFields();

        // Close the dialog
        dispose();
    }

    private void clearFields() {
        barcode.setText("");
        model.setText("");
        rack_num.setText("");
    }
}