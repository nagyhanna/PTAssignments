package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

public class AdminFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton[] buttons;
	
	private JTextField[] textFields;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel panel;
	
	
	
	public AdminFrame(){
		//setVisible(true);
		//setBounds(200, 200, 450, 400);
		panel=new JPanel();
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLayout(new BorderLayout());
		
	
		
		buttons= new JButton[7];
		
		textFields= new JTextField[8];
		scrollPane = new JScrollPane();
	
		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		panel.add(scrollPane);
		scrollPane.setBounds(129, 11, 620, 149);
		panel.setLayout(null);
		
		buttons[0]=new JButton("All products");
		buttons[0].setBounds(118, 171, 111, 23);
		panel.add(buttons[0]);
		
		buttons[1]=new JButton("All clients");
		buttons[1].setBounds(230, 171, 105, 23);
		panel.add(buttons[1]);
		
		buttons[2]=new JButton("Delete client");
		buttons[2].setBounds(118, 205, 111, 23);
		panel.add(buttons[2]);
		
		buttons[3]=new JButton("Delete product");
		buttons[3].setBounds(230, 205, 130, 23);
		panel.add(buttons[3]);
		
		buttons[4]=new JButton("Add product");
		buttons[4].setBounds(118, 239, 111, 23);
		panel.add(buttons[4]);
		
		buttons[5]=new JButton("Update product");
		buttons[5].setBounds(230, 239, 130, 23);
		panel.add(buttons[5]);
		
		buttons[6]=new JButton("Update product quantity");
		buttons[6].setBounds(335, 171, 170, 23);
		panel.add(buttons[6]);
		
		
		textFields[0]=new JTextField("Id");
		textFields[1]=new JTextField("product name");
		textFields[2]=new JTextField("product brand");
		textFields[3]=new JTextField("product colour");
		textFields[4]=new JTextField("product size");
		textFields[5]=new JTextField("product ram");
		textFields[6]=new JTextField("product price");
		textFields[7]=new JTextField("product quantity");
		
		
		textFields[0].setBounds(22, 23, 86, 20);
		panel.add(textFields[0]);
		textFields[0].setColumns(10);
		
		
		textFields[1].setBounds(22, 54, 86, 20);
		panel.add(textFields[1]);
		textFields[1].setColumns(10);
		
		
		textFields[2].setBounds(22, 85, 86, 20);
		panel.add(textFields[2]);
		textFields[2].setColumns(10);
		
		
		textFields[3].setBounds(22, 116, 86, 20);
		panel.add(textFields[3]);
		textFields[3].setColumns(10);
		
		
		textFields[4].setBounds(22, 147, 86, 20);
		panel.add(textFields[4]);
		textFields[4].setColumns(10);
		
		
		textFields[5].setBounds(22, 172, 86, 20);
		panel.add(textFields[5]);
		textFields[5].setColumns(10);
		
		
		textFields[6].setBounds(22, 206, 86, 20);
		panel.add(textFields[6]);
		textFields[6].setColumns(10);
		
		
		textFields[7].setBounds(22, 237, 90, 30);
		panel.add(textFields[7]);
		textFields[7].setColumns(10);
		
		add(panel, BorderLayout.CENTER);
		
		
		pack();
		setResizable(true);
		setBounds(400, 400, 780, 350);
		setLocationRelativeTo(null);
		//setVisible(true);
		
		
	}
	
	public void addActionListenerToButtons(ActionListener actionListener){
		for(JButton button: this.buttons){
			button.addActionListener(actionListener);
		}
	}
	
	public JButton[] getButtons(){
		return this.buttons;
		
	}
	
	public JTextField[] getTextFields(){
		return this.textFields;
	}
	
	public JTable getTable(){
		return this.table;
	}
	
	public void setModelTable(TableModel dataModel){
		this.table.setModel(dataModel);
	}
	
	
	
}
