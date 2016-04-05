package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class StartFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton[] buttons;
	private JLabel[] labels;
	
	private JTextField[] textField;
	private JPasswordField[] passwordFields;
	private JTextField txtLogIn;
	private JTextField txtSignInAs;
	
	private JPanel panel;
	public StartFrame(){
		super("");
		
		textField= new JTextField[5];
		labels=new JLabel[6];
		buttons=new JButton[3];
		passwordFields=new JPasswordField[2];
		
		setVisible(true);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		//getContentPane().add(panel);
		panel.setLayout(null);
		
		
		textField[0] = new JTextField();
		textField[0].setBounds(85, 44, 119, 20);
		panel.add(textField[0]);
		textField[0].setColumns(10);
		
		textField[1] = new JTextField();
		textField[1].setBounds(292, 44, 119, 20);
		panel.add(textField[1]);
		textField[1].setColumns(10);
		
		textField[2] = new JTextField();
		textField[2].setBounds(292, 75, 119, 20);
		panel.add(textField[2]);
		textField[2].setColumns(10);
		
		textField[3] = new JTextField();
		textField[3].setBounds(292, 137, 119, 20);
		panel.add(textField[3]);
		textField[3].setColumns(10);
		
		labels[0] = new JLabel("username");
		labels[0].setBounds(10, 47, 71, 14);
		panel.add(labels[0]);
		
		labels[1] = new JLabel("password");
		labels[1].setBounds(10, 78, 71, 14);
		panel.add(labels[1]);
		
		labels[2] = new JLabel("username");
		labels[2].setBounds(214, 47, 77, 14);
		panel.add(labels[2]);
		
		labels[3] = new JLabel("email");
		labels[3].setBounds(214, 78, 77, 14);
		panel.add(labels[3]);
		
		labels[4] = new JLabel("password");
		labels[4].setBounds(214, 109, 77, 14);
		panel.add(labels[4]);
		
		labels[5] = new JLabel("country");
		labels[5].setBounds(214, 140, 77, 14);
		panel.add(labels[5]);
		
		buttons[0] = new JButton("Log in as Admin");
		buttons[0].setForeground(Color.BLACK);
		
		buttons[0].setBackground(Color.LIGHT_GRAY);
		buttons[0].setBounds(74, 106, 130, 23);
		panel.add(buttons[0]);
		
		buttons[1] = new JButton("Sign in");
		buttons[1].setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		buttons[1].setBackground(Color.RED);
		buttons[1].setBounds(292, 178, 119, 56);
		panel.add(buttons[1]);
		
		buttons[2] = new JButton("Log in as client");
		buttons[2].setBackground(Color.YELLOW);
		buttons[2].setForeground(Color.DARK_GRAY);
		buttons[2].setBounds(85, 155, 119, 50);
		panel.add(buttons[2]);
		
		passwordFields[0] = new JPasswordField();
		passwordFields[0].setBounds(292, 106, 119, 20);
		panel.add(passwordFields[0]);
		
		txtLogIn = new JTextField("");
		txtLogIn.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txtLogIn.setForeground(Color.BLUE);
		txtLogIn.setText("Log In ");
		txtLogIn.setEditable(false);
		txtLogIn.setBounds(83, 4, 101, 29);
		panel.add(txtLogIn);
		txtLogIn.setColumns(10);
		
		txtSignInAs = new JTextField();
		txtSignInAs.setForeground(Color.BLUE);
		txtSignInAs.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txtSignInAs.setEditable(false);
		txtSignInAs.setText("Sign in as new client");
		txtSignInAs.setBounds(223, -1, 184, 40);
		panel.add(txtSignInAs);
		txtSignInAs.setColumns(10);
		
		passwordFields[1] = new JPasswordField();
		passwordFields[1].setBounds(85, 75, 119, 20);
		panel.add(passwordFields[1]);
		
		
		
	}
	
	public JButton[] getButtons() {
		return this.buttons;
	}
	public void setButtons(JButton[] buttons) {
		this.buttons = buttons;
	}
	public JLabel[] getLabels() {
		return this.labels;
	}
	public void setLabels(JLabel[] labels) {
		this.labels = labels;
	}
	
	public JPasswordField[] getPasswordFields(){
		return this.passwordFields;
	}
	public JTextField[] getTextFields(){
		return this.textField;
	}
	
	public void addActionListenerToButtons(ActionListener actionListener){
		for(JButton button: this.buttons){
			button.addActionListener(actionListener);
		}
	}
	
	
	
	
	
	
	
	
	
}
