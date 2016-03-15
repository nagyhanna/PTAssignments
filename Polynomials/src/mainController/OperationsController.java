package mainController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import gui.MainFrame;
import modelElements.CoefficientDegree;
import modelElements.Polynomial;
import operationsPack.Addition;
import operationsPack.AntiDerivate;
import operationsPack.Derivate;
import operationsPack.Division;
import operationsPack.Multiplication;
import operationsPack.Subtraction;

/**
 * OperationsController controls the whole operation-system including the
 * ActionListeners
 */
public class OperationsController {

	private MainFrame mainFrame;
	Polynomial p1, p2;

	public void runOperations() {

		mainFrame = new MainFrame();

		mainFrame.addActionListenerToButtonsPanelButtons(new OperationsActionListener());
		mainFrame.addActionListenerToInputTextFields(new InputTextActionListener());

	}

	private class OperationsActionListener implements ActionListener {

		JButton add = mainFrame.getAdditionButton();
		JButton sub = mainFrame.getSubtractionButton();
		JButton mul = mainFrame.getMultiplicationButton();
		JButton div = mainFrame.getDivisionButton();
		JButton der = mainFrame.getDerivativeButton();
		JButton integ = mainFrame.getIntegrateButton();
		Polynomial result;

		Polynomial remainder = new Polynomial(1);

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub

			if (event.getSource() == add) {
				
				try{
				//if(p1==null || p2 == null){
					//JOptionPane.showMessageDialog(null,
						//	"Addition cannot be performed\n! Enter a valid polynomial");
				//}
				
				Addition addition = new Addition();
				result = addition.execute(p1, p2);
				mainFrame.setResultPolynomOutput(result.toString());
			}
				catch (Exception e){
					JOptionPane.showMessageDialog(null,
							"Addition cannot be performed\n! Enter a valid polynomial");
				}
				}
			if (event.getSource() == sub) {
				
				try{
				Subtraction subtraction = new Subtraction();
				result = subtraction.execute(p1, p2);
				mainFrame.setResultPolynomOutput(result.toString());
				System.out.println(result.toString());

			}catch(Exception e){
				JOptionPane.showMessageDialog(null,
						"Subtraction cannot be performed\n! Enter a valid polynomial");
			}
			}
			if (event.getSource() == mul) {
				
				try{
				Multiplication multiplication = new Multiplication();
				result = multiplication.execute(p1, p2);
				mainFrame.setResultPolynomOutput(result.toString());
				System.out.println(result.toString());
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null,
							"Multiplication cannot be performed\n! Enter a valid polynomial");
				}
			}
			
			if (event.getSource() == div) {
				
			try{
				remainder = new Polynomial(p2.getSize() - 1);
				Division division = new Division();
				
				
				if (p2.getSize() > p1.getSize()) {

					JOptionPane.showMessageDialog(null,
							"Division cannot be performed\n between these two polys! Change the powers");
				} 
				
				if( p2.equals(new Polynomial(1))){
					JOptionPane.showMessageDialog(null,
							"Division cannot be performed\n between these two polys! Enter a valid denominator");
				}else {
					result = division.execute(p1, p2, remainder);
					mainFrame.setResultPolynomOutput(result.toString());
					mainFrame.setResultPolynomOutput2(remainder.toString());
				}
				
			}catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,
						"Division cannot be performed\n! Enter a valid polynomial");
			}
			}
			if (event.getSource() == der) {
				try{
				Derivate derivate = new Derivate();
				result = derivate.execute(p1);
				mainFrame.setResultPolynomOutput(result.toString());}
				catch(Exception e){
					JOptionPane.showMessageDialog(null,
							"Derivation cannot be performed\n! Enter a valid polynomial");
				}
			}
			if (event.getSource() == integ) {
				try{
				
				AntiDerivate antiderivate = new AntiDerivate();
				result = antiderivate.execute(p1);
				mainFrame.setResultPolynomOutput(result.toString());
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,
						"Integration cannot be performed\n! Enter a valid polynomial");
				}
			}
		}

	}

	private class InputTextActionListener implements ActionListener {

		JTextField firstPoly = mainFrame.getFirstPolynomInput();
		JTextField secondPoly = mainFrame.getSecondPolynomInput();

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub

			
			if (event.getSource() == firstPoly) {
				try{
				
				p1 = Polynomial.stringToPolynomial(firstPoly.getText());
				System.out.println(p1.toString());
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,
						" Enter a valid polynomial");
			}
				}

			if (event.getSource() == secondPoly) {
				
				try{
				p2 = Polynomial.stringToPolynomial(secondPoly.getText());
				System.out.println(p2.toString());
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,
						" Enter a valid polynomial");
			}

		}

	}

}
}