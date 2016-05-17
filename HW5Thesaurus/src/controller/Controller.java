package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.SingletonThesaurus;
import view.Gui;

public class Controller {

	private Gui gui;
	private SingletonThesaurus thesaurus;

	private Map<String, Set<String>> th;
	private String[] coloumns;
	private Object[][] data;

	public Controller(Gui gui, SingletonThesaurus thesaurus) {
		this.gui = gui;
		this.thesaurus = thesaurus;
		this.gui.addActionListenerToButtons(new ActionListenerToButtons());
	}

	public void setTableModel2() {

		th = thesaurus.getSynomymPairs();

		coloumns = new String[] { "word", "synonym" };
		int i = 0;
		data = new Object[100][5];
		// System.out.println(client.values().toString());
		// System.out.println(client.values().size());
		for (String word : th.keySet()) {
			Set<String> syns = th.get(word);
			for (String syn : syns) {
				data[i][0] = word.toString();
				data[i][1] = syn;
				i++;

			}
		}
		gui.setTable(data, coloumns);
	}

	private class ActionListenerToButtons implements ActionListener {

		private JButton addButton;
		private JButton deleteWordButton;
		private JButton deleteSynonymButton;
		private JButton searchButton;
		private JButton listButton;
		private JTextField textField1;
		private JTextField textField2;
		

		public ActionListenerToButtons() {
			addButton = gui.getAddButton();
			deleteWordButton = gui.getDeleteButton();
			deleteSynonymButton = gui.getDeleteSynonymButton();
			searchButton = gui.getSearchButton();
			listButton = gui.getListButton();
			textField1 = new JTextField();
			textField2 = new JTextField();
			
		}

		public void actionPerformed(ActionEvent event) {

			Object[] inputFields = { "word", textField1, "synonym", textField2 };
			Object[] inputFields1 = { "word", textField1 };

			if (event.getSource() == addButton) {
				int option = JOptionPane.showConfirmDialog(null, inputFields, "Add new word",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

				if (option == JOptionPane.OK_OPTION) {
					thesaurus.addSynonym(textField1.getText(), textField2.getText());
				} else {
					JOptionPane.showMessageDialog(null, "Type spending or saving");
				}
				textField2.setText(null);

			} else if (event.getSource() == listButton) {
				setTableModel2();
			} else if (event.getSource() == deleteWordButton) {
				int option = JOptionPane.showConfirmDialog(null, inputFields1, "delete word",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

				if (option == JOptionPane.OK_OPTION) {
					thesaurus.deleteWord(textField1.getText());
					textField1.setText(null);
				}

			} else if (event.getSource() == deleteSynonymButton) {
				int option = JOptionPane.showConfirmDialog(null, inputFields, "delete synonym word",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

				if (option == JOptionPane.OK_OPTION) {
					thesaurus.deleteSynonym(textField1.getText(), textField2.getText());
					textField1.setText(null);
				}

			} else if (event.getSource() == searchButton) {
				int option = JOptionPane.showConfirmDialog(null, inputFields1, "search word",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

				if (option == JOptionPane.OK_OPTION) {

					System.out.println(thesaurus.serchSynonims(textField1.getText()));
					JOptionPane.showMessageDialog(null,
							"the searched word: " + thesaurus.serchSynonims(textField1.getText()));
					textField1.setText(null);
				}

			}

		}
	}
}
