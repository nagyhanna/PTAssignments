package gui;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * InputOutputTextPanel class is a JPanel which contains the 
 * textfields where the polynomials 
 * will be written and on these will the
 * result/s be displayed
 */

public class InputOutputTextPanel extends JPanel{

	private static final long serialVersionUID = 2244711167508244769L;

	private JTextField firstPolynomInput;
	private JTextField secondPolynomInput;
	private JTextField resultPolynomOutput1;
	private JTextField resultPolynomOutput2;
	
	
	
	public InputOutputTextPanel(){
		firstPolynomInput = new JTextField(30);
		secondPolynomInput = new JTextField(30);
		resultPolynomOutput1 = new JTextField(30);
		resultPolynomOutput2 = new JTextField(30);
		firstPolynomInput.setSelectionColor(Color.GREEN);
		

		
		add(firstPolynomInput);
		add(secondPolynomInput);
		add(resultPolynomOutput1);
		add(resultPolynomOutput2);
		
	    resultPolynomOutput1.setEditable(false);
	    resultPolynomOutput2.setEditable(false);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public JTextField getFirstPolynomInput(){
		return this.firstPolynomInput;
	}
	public JTextField getSecondPolynomInput(){
		return this.secondPolynomInput;
	}
	public JTextField getResultPolynomOutput(){
		return this.resultPolynomOutput1;
	}
	public void setResultPolynomOutput(String result){
	
		resultPolynomOutput1.setText(result);
	}
	
	public void setResultPolynomOutput2(String result){
		
		resultPolynomOutput2.setText(result);
	}
	
	public void addActionListenerToTextFields(ActionListener actionListener){
		
		firstPolynomInput.addActionListener(actionListener);
		secondPolynomInput.addActionListener(actionListener);
		resultPolynomOutput1.addActionListener(actionListener);
		resultPolynomOutput2.addActionListener(actionListener);
		
	}
	
	
}
