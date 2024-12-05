

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import org.example.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Book_Resource extends JDialog {
    private JTextField titleField;
    private JTextField languageField;
    private JTextField pagesField;
    private JTextField yearField;
    private JTextField subjectField;
    private JTextField authorField;
    private JTextField publisherField;

    private JButton saveButton;
    private JButton deleteButton;
    private JButton cancelButton;

    //DB STUFF
    private Database db = null;
    private Connection connection = null;
    private PreparedStatement preparedStatement;

    public Book_Resource(JFrame parent) {
        super(parent, "Add New Book", true);

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
        add(new JLabel("Title:"));
        titleField = new JTextField();
        add(titleField);

        add(new JLabel("Language:"));
        languageField = new JTextField();
        add(languageField);

        add(new JLabel("Number of Pages:"));
        pagesField = new JTextField();
        add(pagesField);

        add(new JLabel("Year Produced:"));
        yearField = new JTextField();
        add(yearField);

        add(new JLabel("Subject:"));
        subjectField = new JTextField();
        add(subjectField);

        add(new JLabel("Author:"));
        authorField = new JTextField();
        add(authorField);

        add(new JLabel("Publisher:"));
        publisherField = new JTextField();
        add(publisherField);

        // Save and Cancel buttons
        saveButton = new JButton("Save Book");
        deleteButton = new JButton("Delete Book");
        cancelButton = new JButton("Cancel");

        add(saveButton);
        add(deleteButton);
        add(cancelButton);

        // Action listeners
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveBook();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void saveBook() {
        // Validate and process book information
        String title = titleField.getText();
        String language = languageField.getText();
        String pages = pagesField.getText();
        String year = yearField.getText();
        String subject = subjectField.getText();
        String author = authorField.getText();
        String publisher = publisherField.getText();

        // Add validation logic here
        if (title.isEmpty() || author.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Title and Author are required fields!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO Book(title, language, pages, year_prod, subject, author, publisher) VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, language);
            preparedStatement.setString(3, pages);
            preparedStatement.setString(4, year);
            preparedStatement.setString(5, subject);
            preparedStatement.setString(6, author);
            preparedStatement.setString(7, publisher);

            preparedStatement.execute();
            System.out.println("Added Book");
        } catch (Exception e) {
            System.out.println(e);
        }


        // Clear fields after saving
        clearFields();

        // Close the dialog
        dispose();
    }

    private void deleteBook(){
        String title = titleField.getText();
        try {
            preparedStatement = connection.prepareStatement("SELECT reference_id FROM Book WHERE title=?");
            preparedStatement.setString(1, title);

            System.out.println("Executing first Statement");
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("First successful");

            int bookNumber = -1;

            if(rs.next()){
                bookNumber = rs.getInt("reference_id");
                preparedStatement = connection.prepareStatement("DELETE FROM BookCopy WHERE book_ref=?");
                preparedStatement.setInt(1, bookNumber);
                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement("DELETE FROM Book WHERE reference_id=?");
                preparedStatement.setInt(1, bookNumber);
                preparedStatement.execute();

                System.out.println("Deleted Book");

            }else{
                JOptionPane.showMessageDialog(this,"No Book Found with the given title: " + title);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        // Clear fields after saving
        clearFields();

        // Close the dialog
        dispose();
    }

    private void clearFields() {
        titleField.setText("");
        languageField.setText("");
        pagesField.setText("");
        yearField.setText("");
        subjectField.setText("");
        authorField.setText("");
        publisherField.setText("");
    }
}