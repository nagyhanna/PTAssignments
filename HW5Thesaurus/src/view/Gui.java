package view;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Gui {

	private JFrame frame;
	private JPanel panel;
	private JButton addButton;
	private JButton deleteWordButton;
	private JButton deleteSynonymButton;
	private JButton searchButton;
	private JButton listButton;
	private JTable table;
	private JScrollPane scrollPane;

	public Gui() {
		frame = new JFrame("hg");
		panel = new JPanel();

		// frame.setLayout(null);
		panel.setLayout(null);

		addButton = new JButton("add");
		deleteWordButton = new JButton("delete word");
		deleteSynonymButton = new JButton("delete synonym");
		searchButton = new JButton("search");
		listButton = new JButton("list");

		addButton.setBounds(10, 200, 200, 33);
		deleteWordButton.setBounds(10, 250, 200, 33);
		deleteSynonymButton.setBounds(250, 250, 200, 33);
		searchButton.setBounds(250, 200, 200, 33);
		listButton.setBounds(10, 296, 200, 33);

		panel.add(addButton);
		panel.add(deleteWordButton);
		panel.add(deleteSynonymButton);
		panel.add(searchButton);
		panel.add(listButton);

		table = new JTable();

		table.setPreferredScrollableViewportSize(new Dimension(100, 50));
		table.setFillsViewportHeight(true);

		scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		scrollPane.setBounds(129, 11, 620, 149);
		scrollPane.setViewportView(table);

		frame.add(panel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setResizable(true);
		frame.setBounds(400, 400, 780, 400);
		frame.setVisible(true);

	}

	public JButton getDeleteSynonymButton() {
		return deleteSynonymButton;
	}

	public void setDeleteSynonymButton(JButton deleteSynonymButton) {
		this.deleteSynonymButton = deleteSynonymButton;
	}

	public void addActionListenerToButtons(ActionListener actionListener) {
		addButton.addActionListener(actionListener);
		listButton.addActionListener(actionListener);
		deleteWordButton.addActionListener(actionListener);
		searchButton.addActionListener(actionListener);
		deleteSynonymButton.addActionListener(actionListener);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JFrame getFrame() {
		return frame;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JButton getDeleteButton() {
		return deleteWordButton;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public JButton getListButton() {
		return listButton;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setTable(Object[][] data, String[] coloumns) {
		table.setModel(new DefaultTableModel(data, coloumns));
	}

}
