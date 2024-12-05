import org.example.Database;

import javax.swing.*;

public class StudWest extends JPanel{
	public JTable display;
	JScrollPane scroll;

	public String[] book_column;

	public StudWest(){
		this.setVisible(false);

		book_column = new String[]{"Title", "Barcode", "Borrow Date", "Due Date"};
		String[][] data = {{"", "", "" ,""}};

		display = new JTable(data, book_column){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};

		scroll = new JScrollPane(display);
		scroll.setBorder(BorderFactory.createTitledBorder("Display"));
		this.add(scroll);
	}

	public void showPanel(){
		this.setVisible(true);
	}

	public void clearDisplay(){
		while(display.getColumnCount() != 0){
			display.removeColumn(display.getColumn(0));
		}
	}

}
