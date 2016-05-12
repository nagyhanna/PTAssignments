package gui;

import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import model.Account;
import model.Bank;
import model.Person;

public class BankFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OperationPanel opPanel;
	private String[] coloumns;
	private Object[][] data;

	private Map<Person, Set<Account>> client;
	

	public BankFrame() {
		
		opPanel = new OperationPanel();
		add(opPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setResizable(true);
		setBounds(400, 400, 780, 400);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setTableModel1(Bank bank) {
		client = bank.getClient();
		coloumns = new String[] { "Client ID", "Client Name" };
		System.out.println("size:" + client.size());
		System.out.println(client.toString());
		data = new Object[client.size()][2];
		int i = 0;
		for (Person person : client.keySet()) {
			data[i][0] = person.getId();
			data[i][1] = person.getName();
			i++;
		}
		opPanel.setClientTable(data, coloumns);
	}

	public void setTableModel2(Bank bank) {
		client = bank.getClient();
		coloumns = new String[] { "Account ID", "Client ID", "Client Name", "Account type", "Depozit Money" };
		int i = 0;
		data = new Object[100][5];
		//System.out.println(client.values().toString());
		//System.out.println(client.values().size());
		for (Person person : client.keySet()) {
			Set<Account> accounts = client.get(person);
			for (Account acc : accounts) {
				data[i][1] = person.getId();
				data[i][2] = person.getName();
				data[i][0] = acc.getAccId();
				data[i][3] = acc.getType();
				data[i][4] = acc.getMoney();
				i++;

			}
		}
		opPanel.setClientTable(data, coloumns);
	}

	public void addActionListenerToButtons(ActionListener actionListener) {
		opPanel.addActionListenerToButtons(actionListener);
	}
	public JButton getAddDeleteClientButton() {
		return opPanel.getAddDeleteClientButton();
	}

	public JButton getAddDeleteAccountButton() {
		return opPanel.getAddDeleteAccountButton();
	}

	public JButton getListClientsAccountsButton() {
		return opPanel.getListClientsAccountsButton();
	}

	public JButton getWithdrawMoneyButton() {
		return opPanel.getWithdrawMoneyButton();
	}

	public JButton getInsertMoneyButton() {
		return opPanel.getInsertMoneyButton();
	}

	public JButton getDeleteClientButton() {
		return opPanel.getDeleteClientButton();
	}


	public JButton getDeleteAccountButton() {
		return opPanel.getDeleteAccountButton();
	}

}
