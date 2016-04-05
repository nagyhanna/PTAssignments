package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

public class ClientFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton[] buttons;

	private JTextField[] textFields;
	private JTable table;
	private JTable table2;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane2;
	private JPanel panel;


	public ClientFrame() {
		panel = new JPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttons = new JButton[6];

		textFields = new JTextField[6];
		scrollPane = new JScrollPane();

		table = new JTable();
		scrollPane.setViewportView(table);
		panel.add(scrollPane);
		scrollPane.setBounds(129, 11, 620, 149);
		panel.setLayout(null);

		buttons[0] = new JButton("CHANGE PROFILE");
		buttons[0].setBounds(10, 114, 111, 23);
		panel.add(buttons[0]);

		buttons[1] = new JButton("All products");
		buttons[1].setForeground(new Color(255, 0, 0));
		buttons[1].setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 12));
		buttons[1].setBounds(199, 171, 165, 73);
		panel.add(buttons[1]);

		buttons[2] = new JButton("Order");
		buttons[2].setForeground(Color.RED);
		buttons[2].setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
		buttons[2].setBounds(10, 246, 123, 43);
		panel.add(buttons[2]);

		buttons[3] = new JButton("Filter");
		buttons[3].setBounds(259, 255, 105, 23);
		panel.add(buttons[3]);

		buttons[4] = new JButton("delete from cart");
		buttons[4].setBounds(10, 300, 155, 23);
		panel.add(buttons[4]);

		buttons[5] = new JButton("SEND ORDER");
		buttons[5].setBounds(638, 171, 116, 118);
		panel.add(buttons[5]);

		

		textFields[0] = new JTextField("NEW NAME");
		textFields[1] = new JTextField("NEW PASSWORD");
		textFields[2] = new JTextField("product ID");

		textFields[3] = new JTextField("product Quantity");
		textFields[4] = new JTextField("total Price");
		textFields[4].setEditable(false);
		textFields[5] = new JTextField("type a brand");
		
		textFields[0].setBounds(12, 52, 109, 20);
		panel.add(textFields[0]);
		textFields[0].setColumns(10);

		textFields[1].setBounds(12, 83, 109, 20);
		panel.add(textFields[1]);
		textFields[1].setColumns(10);

		textFields[2].setBounds(22, 172, 86, 20);
		panel.add(textFields[2]);
		textFields[2].setColumns(10);

		textFields[3].setBounds(22, 203, 86, 20);
		panel.add(textFields[3]);
		textFields[3].setColumns(10);

		textFields[4].setBounds(143, 259, 86, 20);
		panel.add(textFields[4]);
		textFields[4].setColumns(10);

		textFields[5].setBounds(259, 280, 105, 20);
		panel.add(textFields[5]);
		textFields[5].setColumns(10);

		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(367, 175, 242, 114);
		panel.add(scrollPane2);

		table2 = new JTable();
		scrollPane2.setViewportView(table2);

		/*
		 * textFields[6].setBounds(22, 206, 86, 20); panel.add(textFields[6]);
		 * textFields[6].setColumns(10);
		 * 
		 * 
		 * textFields[7].setBounds(22, 237, 90, 30); panel.add(textFields[7]);
		 * textFields[7].setColumns(10);
		 */
		add(panel, BorderLayout.CENTER);

		pack();
		setResizable(true);
		setBounds(400, 400, 780, 400);
		setLocationRelativeTo(null);
		// setVisible(true);

	}

	public void addActionListenerToButtons(ActionListener actionListener) {
		for (JButton button : this.buttons) {
			button.addActionListener(actionListener);
		}
	}

	public JButton[] getButtons() {
		return this.buttons;

	}

	public JTextField[] getTextFields() {
		return this.textFields;
	}

	public JTable getTable() {
		return this.table;
	}

	public void setModelTable(TableModel dataModel) {
		this.table.setModel(dataModel);
	}

	public void setModelTable2(TableModel dataModel) {
		this.table2.setModel(dataModel);
	}

	public TableModel getModelTable2() {
		return this.table2.getModel();
	}
	// public static void main(String[] args) {

	// ClientFrame clientFrame = new ClientFrame();
	// clientFrame.setVisible(true);

	// }

}
