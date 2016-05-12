package gui;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.event.*;

public class OperationPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton addClientButton;
	private JButton deleteClientButton;
	private JButton addAccountButton;
	private JButton deleteAccountButton;
	private JButton listClientsAccountsButton;
	private JButton withdrawMoneyButton;
	private JButton insertMoneyButton;

	private JTable table;
	private JScrollPane scrollPane;

	public OperationPanel() {

		setLayout(null);
		addClientButton = new JButton("Add client");
		addAccountButton = new JButton("Add account");
		deleteClientButton = new JButton("Delete client");
		deleteAccountButton = new JButton("Delete account");
		listClientsAccountsButton = new JButton("listClientsAccountsButton");

		withdrawMoneyButton = new JButton("withdraw Money");
		insertMoneyButton = new JButton("insert Money ");

		addClientButton.setBounds(10, 200, 200, 33);
		addAccountButton.setBounds(10, 250, 200, 33);
		deleteClientButton.setBounds(250, 200, 200, 33);
		deleteAccountButton.setBounds(480, 200, 200, 33);
		listClientsAccountsButton.setBounds(10, 296, 200, 33);
		withdrawMoneyButton.setBounds(250, 250, 200, 33);
		insertMoneyButton.setBounds(250, 296, 200, 33);

		add(addClientButton);
		add(addAccountButton);
		add(deleteClientButton);
		add(deleteAccountButton);
		add(listClientsAccountsButton);
		add(withdrawMoneyButton);
		add(insertMoneyButton);

		table = new JTable();

		table.setPreferredScrollableViewportSize(new Dimension(100, 50));
		table.setFillsViewportHeight(true);

		scrollPane = new JScrollPane(table);
		add(scrollPane);
		scrollPane.setBounds(129, 11, 620, 149);
		scrollPane.setViewportView(table);

	}

	public void addActionListenerToButtons(ActionListener actionListener) {
		addClientButton.addActionListener(actionListener);
		addAccountButton.addActionListener(actionListener);
		listClientsAccountsButton.addActionListener(actionListener);
		withdrawMoneyButton.addActionListener(actionListener);
		insertMoneyButton.addActionListener(actionListener);
		deleteClientButton.addActionListener(actionListener);
		deleteAccountButton.addActionListener(actionListener);
	}

	public void setClientTable(Object[][] data, String[] coloumns) {
		table.setModel(new DefaultTableModel(data, coloumns));
	}

	public void setAccountTable(Object[][] data) {
		String[] coloumns = new String[] { "First Name", "Last Name", "CNP", "Account type", " Account no.",
				"Depozit" };
		table.setModel(new DefaultTableModel(data, coloumns));

	}

	public JButton getAddDeleteClientButton() {
		return this.addClientButton;
	}

	public JButton getAddDeleteAccountButton() {
		return addAccountButton;
	}

	public JButton getListClientsAccountsButton() {
		return listClientsAccountsButton;
	}

	public JTable getTable() {
		return table;
	}

	public JButton getWithdrawMoneyButton() {
		return withdrawMoneyButton;
	}

	public JButton getInsertMoneyButton() {
		return insertMoneyButton;
	}

	public JButton getDeleteClientButton() {
		return deleteClientButton;
	}


	public JButton getDeleteAccountButton() {
		return deleteAccountButton;
	}


}