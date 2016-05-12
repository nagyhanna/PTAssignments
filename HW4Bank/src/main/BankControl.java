package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import gui.BankFrame;
import model.Account;
import model.Bank;
import model.Person;
import model.SavingAccount;
import model.SpendingAccount;

public class BankControl {

	private BankFrame bankFrame;
	private Bank bank;

	public BankControl(Bank bank) {
		bankFrame = new BankFrame();
		this.bank = bank;
		bankFrame.addActionListenerToButtons(new ButtonsActionListener());
	}

	private class ButtonsActionListener implements ActionListener {

		private JButton addClientButton;
		private JButton addAccountButton;
		private JButton deleteClientButton;
		private JButton deleteAccountButton;
		private JButton listClientsAccountsButton;
		private JButton withdrawMoneyButton;
		private JButton insertMoneyButton;

		private JTextField textField1;
		private JTextField textField2;
		private JTextField textField3;
		private JTextField textField4;
		private JTextField textField5;
		private JTextField textField6;

		public ButtonsActionListener() {

			addClientButton = bankFrame.getAddDeleteClientButton();
			addAccountButton = bankFrame.getAddDeleteAccountButton();
			listClientsAccountsButton = bankFrame.getListClientsAccountsButton();
			withdrawMoneyButton = bankFrame.getWithdrawMoneyButton();
			insertMoneyButton = bankFrame.getInsertMoneyButton();
			deleteClientButton = bankFrame.getDeleteClientButton();
			deleteAccountButton = bankFrame.getDeleteAccountButton();

			textField1 = new JTextField();
			textField2 = new JTextField();
			textField3 = new JTextField();
			textField4 = new JTextField();
			textField5 = new JTextField();
			textField6 = new JTextField();

		}

		@Override
		public void actionPerformed(ActionEvent event) {

			Object[] inputFields = { "Client ID", textField1, "Client Name", textField2, "Account type", textField4,
					"Deposit Money", textField5 };
			Object[] inputFields1 = { "Client Name", textField2, "Account type", textField4, "Deposit Money",
					textField5 };
			Object[] inputFields2 = { "Client ID", textField1, "Client Name", textField2 };
			Object[] inputFields3 = { "Client ID", textField1, "Client Name", textField2, "Account ID", textField3,
					"saving or spending", textField4 };
			Object[] inputFields4 = { "Client ID", textField1, "Client Name", textField2, "Account ID", textField3,
					"Deposit Money", textField4 };

			Account acc;

			if (event.getSource() == addClientButton) {
				int option = JOptionPane.showConfirmDialog(null, inputFields1, "Add new client with accounts",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				try {
					if (option == JOptionPane.OK_OPTION) {
						Person p = new Person(textField2.getText());
						if (textField4.getText().equals("saving")) {
							acc = new SavingAccount(Double.parseDouble(textField5.getText()));
							bank.addAccforPers(p, acc);
						} else if (textField4.getText().equals("spending")) {
							acc = new SpendingAccount(Double.parseDouble(textField5.getText()));
							bank.addAccforPers(p, acc);
						} else {
							JOptionPane.showMessageDialog(null, "Type spending or saving");
						}
						textField2.setText(null);
						textField4.setText(null);
						textField5.setText(null);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Give proper data");
				}
			} else if (event.getSource() == addAccountButton) {
				int option = JOptionPane.showConfirmDialog(null, inputFields, "Add New Account",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				try {
					if (option == JOptionPane.OK_OPTION) {
						Person p = new Person(Integer.parseInt(textField1.getText()), textField2.getText());
						if (textField4.getText().equals("saving")) {
							acc = new SavingAccount(Double.parseDouble(textField5.getText()));
							bank.addAccforPers(p, acc);
						} else if (textField4.getText().equals("spending")) {
							acc = new SpendingAccount(Double.parseDouble(textField5.getText()));
							bank.addAccforPers(p, acc);
						}
						textField1.setText(null);
						textField2.setText(null);
						textField4.setText(null);
						textField5.setText(null);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Give proper data");
				}
			} else if (event.getSource() == listClientsAccountsButton) {
				bankFrame.setTableModel2(bank);

			} else if (event.getSource() == deleteClientButton) {
				int option = JOptionPane.showConfirmDialog(null, inputFields2, "Delete client",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				try {
					if (option == JOptionPane.OK_OPTION) {

						bank.deletePerson(
								new Person(Integer.parseInt(textField1.getText()), textField2.getText())) ;
						textField1.setText(null);
						textField2.setText(null);
						textField4.setText(null);
						textField5.setText(null);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Give proper data");
				}

			} else if (event.getSource() == deleteAccountButton) {
				int option = JOptionPane.showConfirmDialog(null, inputFields3, "Delete Account",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				try {
					if (option == JOptionPane.OK_OPTION) {

						Person person = new Person(Integer.parseInt(textField1.getText()), textField2.getText());

						if (textField4.getText().equals("saving")) {
							acc = new SavingAccount(Integer.parseInt(textField3.getText()), 0.0);
							bank.deleteAccount(person, acc);
								
						} else if (textField4.getText().equals("spending")) {
							acc = new SpendingAccount(Integer.parseInt(textField3.getText()), 0.0);
							bank.deleteAccount(person, acc);
								
						}
						textField1.setText(null);
						textField2.setText(null);
						textField3.setText(null);

					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Give proper data");
				}

			} else if (event.getSource() == insertMoneyButton) {
				int option = JOptionPane.showConfirmDialog(null, inputFields4, "Insert Money",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				try {
					if (option == JOptionPane.OK_OPTION) {

						Person person = new Person(Integer.parseInt(textField1.getText()), textField2.getText());
						bank.depositMoney(Integer.parseInt(textField3.getText()),
								Double.parseDouble(textField4.getText()), person);
							
					}
					textField1.setText(null);
					textField2.setText(null);
					textField3.setText(null);
					textField4.setText(null);

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Give proper data");
				}

			}else if (event.getSource() == withdrawMoneyButton) {
				//inputFields4 = { "Client ID", textField1, "Client Name", textField2, "Account ID", textField3,
				//		"Deposit Money", textField4 };
				int option = JOptionPane.showConfirmDialog(null, inputFields4, "withdraw Money",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				try {
					if (option == JOptionPane.OK_OPTION) {

						Person person = new Person(Integer.parseInt(textField1.getText()), textField2.getText());
						if(bank.withdrawMoney(Integer.parseInt(textField3.getText()),
								Double.parseDouble(textField4.getText()), person) == -1){
							JOptionPane.showMessageDialog(null, "Unsuccessful withdraw!\n"
									+ "Possible issues:1) You are using a saving account\n and you exceeded the time limit(3 times/month)"
									+ "or the amount limit (500 $)"
									+ "\n 2) You do not have enough money on your account!");
						}else {
							JOptionPane.showMessageDialog(null, "Successful withdraw!");
						}
							
					}
					textField1.setText(null);
					textField2.setText(null);
					textField3.setText(null);
					textField4.setText(null);

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Give proper data");
				}
		}
	}
}}