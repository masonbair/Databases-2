import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;

public class AmFrame extends JPanel{

	AmCenter center = new AmCenter();

	private JButton butt_reg = new JButton("Register");
	private JButton butt_del = new JButton("Delete");
	private JButton butt_ass = new JButton("Associate");
	private JButton book_button = new JButton("Modify Book");
	private JButton room_button = new JButton("Modify Room");
	private JButton comp_button = new JButton("Modify Computer");
	private JButton cp_book_button = new JButton("Modify Book Copy");
	private JButton student_button =  new JButton("Modify Students");
	private JPanel panel_but = new JPanel(new GridLayout(3,3));

	public AmFrame() {
		//The JLabels represent empty spaces. new buttons can be added in its place.
		panel_but.add(student_button);
		panel_but.add(cp_book_button);
		panel_but.add(new JLabel(""));
		panel_but.add(book_button);
		panel_but.add(room_button);
		panel_but.add(comp_button);
		panel_but.add(butt_reg);
		panel_but.add(butt_del);
		panel_but.add(butt_ass);


		this.add(center, BorderLayout.CENTER);
		this.add(panel_but, BorderLayout.SOUTH);

		ListenerAm listener = new ListenerAm(center);
		butt_reg.setActionCommand(listener.REG);
		butt_reg.addActionListener(listener);

		butt_del.setActionCommand(listener.DEL);
		butt_del.addActionListener(listener);

		butt_ass.setActionCommand(listener.ASS);
		butt_ass.addActionListener(listener);

		book_button.setActionCommand(listener.BOOK);
		book_button.addActionListener(listener);

		room_button.setActionCommand(listener.ROOM);
		room_button.addActionListener(listener);

		comp_button.setActionCommand(listener.COMP);
		comp_button.addActionListener(listener);

		cp_book_button.setActionCommand(listener.COPY);
		cp_book_button.addActionListener(listener);

		student_button.setActionCommand(listener.STU);
		student_button.addActionListener(listener);
	}
}