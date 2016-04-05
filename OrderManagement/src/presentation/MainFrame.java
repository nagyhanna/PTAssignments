package presentation;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import businessLogicLayer.AdminsBLL;
import businessLogicLayer.ClientsBLL;
import businessLogicLayer.OrderBLL;
import businessLogicLayer.ProductsBLL;
import entities.Admins;
import mainPack.JDBCMySQLConnection;
import net.proteanit.sql.DbUtils;

public class MainFrame {

	private StartFrame startFrame;

	private AdminFrame adminFrame;
	private ClientFrame clientFrame;
	private ClientsBLL clientsBLL;
	private int id;

	public MainFrame() {
		startFrame = new StartFrame();
	}

	public void run() {

		startFrame.addActionListenerToButtons(new ActionListenerToButtons());
		
	}

	private class ActionListenerToButtons implements ActionListener {

		JButton[] buttons;

		private JPasswordField[] passwords;
		private JTextField[] textFields;

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			buttons = startFrame.getButtons();
			passwords = startFrame.getPasswordFields();
			textFields = startFrame.getTextFields();
			if (e.getSource() == buttons[0]) {
				// log in as admin
				AdminsBLL adminsBLL = new AdminsBLL();
				String username = textFields[0].getText();
				String password = passwords[1].getText();

				if (adminsBLL.getExistentAdmin(username, password) != -1) {
					JOptionPane.showMessageDialog(null, "welcome " + username);

					adminFrame = new AdminFrame();
					adminFrame.setVisible(true);
					adminFrame.addActionListenerToButtons(new ActionListenerToAdminFrame());

				} else {
					JOptionPane.showMessageDialog(null, "There is no such an admin\nWrong username OR password");
				}

			}
			if (e.getSource() == buttons[1]) {
				// sign in

				clientsBLL = new ClientsBLL();
				String username = textFields[1].getText();
				String password = passwords[0].getText();
				String email = textFields[2].getText();
				String country = textFields[3].getText();
				if(!username.equals("") && !password.equals("") && email.equals("")){
				clientsBLL.insertNewClient(username, password, email, country);
				}else{
					JOptionPane.showMessageDialog(null, " Please give a username/password/email! \n"
							+ "These informations are mandatory!" );
				}
			}
			if (e.getSource() == buttons[2]) {
				// log in as client
				clientsBLL = new ClientsBLL();
				String username = textFields[0].getText();
				String password = passwords[1].getText();
				System.out.println(username + " " + password);
				if (clientsBLL.getExistentClient(username, password) != -1) {
					JOptionPane.showMessageDialog(null, "welcome " + username);
					id = clientsBLL.getExistentClient(username, password);
					clientFrame = new ClientFrame();
					clientFrame.setVisible(true);
					clientFrame.addActionListenerToButtons(new ActionListenerToCLientFrame());

				} else {
					JOptionPane.showMessageDialog(null, "There is no such client\nWrong username OR password");
				}

			}

		}

	}

	public class ActionListenerToCLientFrame implements ActionListener {
		private JButton[] buttons;
		private JTextField[] textFields;
		private JTable table;
		OrderBLL order;

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			buttons = clientFrame.getButtons();
			textFields = clientFrame.getTextFields();
			table = clientFrame.getTable();
			order = new OrderBLL();

			if (e.getSource() == buttons[0]) {
				ClientsBLL clientBLL = new ClientsBLL();
				String username = textFields[0].getText();
				String password = textFields[1].getText();
				clientBLL.updateClient(id, username, password);

			}
			if (e.getSource() == buttons[1]) {
				// all products
				clientFrame.setModelTable(new DefaultTableModel());
				ProductsBLL productsBLL = new ProductsBLL();
				ResultSet resultSet = productsBLL.getResultSet();
				clientFrame.setModelTable(DbUtils.resultSetToTableModel(resultSet));
				textFields[4].setText(String.valueOf(order.calculateTotalPrice(id)));
				clientFrame.setModelTable2(new DefaultTableModel());
				ResultSet resultSet0 = order.getOrderResultSet(id);
				
				clientFrame.setModelTable2(DbUtils.resultSetToTableModel(resultSet0));

			}
			if (e.getSource() == buttons[2]) {
				// order

				try {
					int productId = Integer.parseInt(textFields[2].getText());
					int wantedQuantity = Integer.parseInt(textFields[3].getText());
					order.order(id, productId, wantedQuantity);
					textFields[4].setText(String.valueOf(order.calculateTotalPrice(id)));
				} catch (NumberFormatException exep) {

					JOptionPane.showMessageDialog(null, "Error-give a valid number");
				}
			}
			if (e.getSource() == buttons[3]) {
				// filter by brand
				clientFrame.setModelTable(new DefaultTableModel());
				ProductsBLL productsBLL = new ProductsBLL();
				ResultSet resultSet = productsBLL.getFilteredResultSet(String.valueOf(textFields[5].getText()));
				clientFrame.setModelTable(DbUtils.resultSetToTableModel(resultSet));

			}
			if (e.getSource() == buttons[4]) {
				// delete order
				try {
					int productId = Integer.parseInt(textFields[2].getText());
					order.deleteOrder(id, productId);
					textFields[4].setText(String.valueOf(order.calculateTotalPrice(id)));

				} catch (NumberFormatException exep) {

					JOptionPane.showMessageDialog(null, "Error-give a valid number");
				}
			}

			if (e.getSource() == buttons[5]) {
				JOptionPane.showMessageDialog(null, "THANK YOU FOR THE SHOPPING \n");

				try {
					// the file path
					File file = new File("The Bill.txt");
					// if the file not exist create one
					if (!file.exists()) {
						file.createNewFile();
					}
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(" Product ID | Product Name | Product Price | Product Quantity \n");
					
					for (int i = 0; i < clientFrame.getModelTable2().getRowCount(); i++) {
						
						for (int j = 0; j < clientFrame.getModelTable2().getColumnCount(); j++) {
							bw.write(clientFrame.getModelTable2().getValueAt(i, j) + "            ");
						} 
						bw.write("\n -------------------------------------------------------- \n");
					} 
					bw.write("TOTAL PRICE: " + order.calculateTotalPrice(id));
					bw.close(); 
					fw.close();
					JOptionPane.showMessageDialog(null, "Data Exported");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				order.deleteAllOrders(id);

			}
		}

	}

	public class ActionListenerToAdminFrame implements ActionListener {
		private JButton[] buttons;
		private JTextField[] textFields;
		private JTable table;

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			buttons = adminFrame.getButtons();
			textFields = adminFrame.getTextFields();
			table = adminFrame.getTable();
			
			ResultSet resultSet;
			if (e.getSource() == buttons[0]) {
				// all products
				adminFrame.setModelTable(new DefaultTableModel());
				ProductsBLL productsBLL = new ProductsBLL();
				resultSet = productsBLL.getResultSet();
				adminFrame.setModelTable(DbUtils.resultSetToTableModel(resultSet));
			}

			if (e.getSource() == buttons[1]) {
				// all client
				adminFrame.setModelTable(new DefaultTableModel());
				ClientsBLL clientsBLL = new ClientsBLL();
				resultSet = clientsBLL.getResultSet();
				adminFrame.setModelTable(DbUtils.resultSetToTableModel(resultSet));
			}
			if (e.getSource() == buttons[2]) {
				// delete client
				try {
					ClientsBLL clientsBLL = new ClientsBLL();
					String clientId = textFields[0].getText();
					int clientid = Integer.parseInt(clientId);

					clientsBLL.deleteClient(clientid);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error-give a valid number");
				}
			}
			if (e.getSource() == buttons[3]) {
				// delete product
				try {
					ProductsBLL productsBLL = new ProductsBLL();
					String productId = textFields[0].getText();
					int productid = Integer.parseInt(productId);
					productsBLL.deleteProduct(productid);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error-give a valid number");
				}
			}
			if (e.getSource() == buttons[4]) {
				// add product
				try {
					ProductsBLL productsBLL = new ProductsBLL();
					productsBLL.insertNewProduct(Integer.parseInt(textFields[0].getText()), textFields[1].getText(),
							textFields[2].getText(), textFields[3].getText(), textFields[4].getText(),
							textFields[5].getText(), Double.parseDouble(textFields[6].getText()),
							Integer.parseInt(textFields[7].getText()));
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error-give proper data");
				}

			}
			if (e.getSource() == buttons[5]) {
				// update
				try {
					ProductsBLL productsBLL = new ProductsBLL();
					productsBLL.updateProduct(Integer.parseInt(textFields[0].getText()), textFields[1].getText(),
							textFields[2].getText(), textFields[3].getText(), textFields[4].getText(),
							textFields[5].getText(), Double.parseDouble(textFields[6].getText()),
							Integer.parseInt(textFields[7].getText()));
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error-give proper data");
				}
			}
			if (e.getSource() == buttons[6]) {
				// update quantity
				try {
					ProductsBLL productsBLL = new ProductsBLL();
					productsBLL.updateProductQuantity(Integer.parseInt(textFields[0].getText()),
							Integer.parseInt(textFields[7].getText()));

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error-give proper data");
				}
			}

		}

	}

}
