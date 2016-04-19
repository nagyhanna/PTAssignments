import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SimulatorFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;

	public SimulatorFrame() {
		this.panel = new JPanel();
		add(panel);
		setSize(500, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void displayData(List<Task[]> listOfTasks) {

		panel.removeAll();
		panel.revalidate();
		int i = 0;
		while (i < listOfTasks.size()) {
			JList<Task> jtasks = new JList<Task>(listOfTasks.get(i));
			JScrollPane scrollPane = new JScrollPane(jtasks);
			panel.add(scrollPane);
			i++;
		}

		panel.repaint();
		panel.revalidate();

	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
}
