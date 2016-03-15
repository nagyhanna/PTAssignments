package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * MainFrame class is JFrame includes the ButtonsPanel and the
 * InputOutputTextPanel
 */

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private ButtonsPanel buttonsPanel;
	private InputOutputTextPanel inputTextPanel;

	public MainFrame() {
		super("Operations on Polynomials");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel forLabels = new JPanel();
		JLabel label1 = new JLabel("First Polynomial->");
		JLabel label2 = new JLabel("Second Polynomial->");
		JLabel label3 = new JLabel("Solution->");
		JLabel label4 = new JLabel("Remainder(in case of Division)->");

		buttonsPanel = new ButtonsPanel();
		inputTextPanel = new InputOutputTextPanel();
		forLabels.add(label1);
		forLabels.add(Box.createRigidArea(new Dimension(10, 20)));
		forLabels.add(label2);
		forLabels.add(Box.createRigidArea(new Dimension(10, 20)));
		forLabels.add(label3);
		forLabels.add(Box.createRigidArea(new Dimension(10, 20)));
		forLabels.add(label4);
		forLabels.setLayout(new BoxLayout(forLabels, BoxLayout.Y_AXIS));

		add(forLabels, BorderLayout.WEST);
		add(buttonsPanel, BorderLayout.NORTH);
		add(inputTextPanel, BorderLayout.CENTER);

		pack();
		setResizable(true);
		setSize(600, 200);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public void addActionListenerToButtonsPanelButtons(ActionListener actionListener) {
		buttonsPanel.addActionListenerToButtons(actionListener);
	}

	public void addActionListenerToInputTextFields(ActionListener actionListener) {
		inputTextPanel.addActionListenerToTextFields(actionListener);
	}

	public JButton getAdditionButton() {
		return this.buttonsPanel.getAdditionButton();
	}

	public JButton getSubtractionButton() {
		return this.buttonsPanel.getSubtractionButton();
	}

	public JButton getDivisionButton() {
		return this.buttonsPanel.getDivisionButton();
	}

	public JButton getMultiplicationButton() {
		return this.buttonsPanel.getMultiplicationButton();
	}

	public JButton getDerivativeButton() {
		return this.buttonsPanel.getDerivativeButton();
	}

	public JButton getIntegrateButton() {
		return this.buttonsPanel.getIntegrateButton();
	}

	public JTextField getFirstPolynomInput() {
		return this.inputTextPanel.getFirstPolynomInput();
	}

	public JTextField getSecondPolynomInput() {
		return this.inputTextPanel.getSecondPolynomInput();
	}

	public JTextField getResultPolynomOutput() {
		return this.inputTextPanel.getResultPolynomOutput();
	}

	public void setResultPolynomOutput(String result) {

		this.inputTextPanel.setResultPolynomOutput(result);
	}

	public void setResultPolynomOutput2(String result) {

		this.inputTextPanel.setResultPolynomOutput2(result);
	}
}
