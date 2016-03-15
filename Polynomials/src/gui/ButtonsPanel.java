package gui;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * ButtonsPanel class is a JPanel which contains the 
 * buttons for the operations;
 */

public class ButtonsPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private JButton additionButton;
	private JButton subtractionButton;
	private JButton divisionButton;
	private JButton multiplicationButton;
	private JButton derivativeButton;
	private JButton antiderivativeButton;
	
	
	public ButtonsPanel(){
		
		 additionButton= new JButton("to ADD");
		 subtractionButton= new JButton("to Substract");
		 divisionButton= new JButton("to Divide");
		 multiplicationButton= new JButton("to Multiply");
		 derivativeButton= new JButton("to Differentiate");
		 antiderivativeButton= new JButton("to Integrate");
		 
		 add(additionButton);
		 add(subtractionButton);
		
		 add(multiplicationButton);
		 add(derivativeButton);
		 add(antiderivativeButton);
		 add(divisionButton);
		 
		 setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		 
	}
	
	public JButton getAdditionButton(){
		return this.additionButton;
	}
	
	public JButton getSubtractionButton(){
		return this.subtractionButton;
	}
	
	public JButton getDivisionButton(){
		return this.divisionButton;
	}
	
	public JButton getMultiplicationButton(){
		return this.multiplicationButton;
	}
	
	public JButton getDerivativeButton(){
		return this.derivativeButton;
	}
	
	public JButton getIntegrateButton(){
		return this.antiderivativeButton;
	}
	
	public void addActionListenerToButtons(ActionListener actionListener){
		additionButton.addActionListener(actionListener);
		subtractionButton.addActionListener(actionListener);
		divisionButton.addActionListener(actionListener);
		multiplicationButton.addActionListener(actionListener);
		derivativeButton.addActionListener(actionListener);
		antiderivativeButton.addActionListener(actionListener);
	}
	
	
	
}
