package operationsPack;

import modelElements.CoefficientDegree;
import modelElements.Polynomial;

public class Addition extends Operations {

	
	private Polynomial result;

	public Addition() {

	}

	public Polynomial execute(Polynomial... p1) {
		result = new Polynomial();
		int p1Size = p1[0].getSize();
		int p2Size = p1[1].getSize();
		// System.out.println(p1Size);
		// System.out.println(p2Size);
		if (p1Size > p2Size) {
			int i = 0;
			while (i < p2Size) {
				double newCoeff = p1[0].getCoeff(i) + p1[1].getCoeff(i);

				int newDegree = p1[0].getDegree(i);
				CoefficientDegree newCoeffD = new CoefficientDegree(newCoeff, newDegree);
				result.addCoeffDegree(newCoeffD);
				i++;
			}
			int diff = p1Size - p2Size;
			while (diff > 0) {
				double newCoeff = p1[0].getCoeff(i);
				int newDegree = p1[0].getDegree(i);
				CoefficientDegree newCoeffD = new CoefficientDegree(newCoeff, newDegree);
				result.addCoeffDegree(newCoeffD);
				i++;
				diff--;
			}
		} else {
			int i = 0;
			while (i < p1Size) {
				double newCoeff = p1[0].getCoeff(i) + p1[1].getCoeff(i);

				int newDegree = p1[0].getDegree(i);
				CoefficientDegree newCoeffD = new CoefficientDegree(newCoeff, newDegree);
				result.addCoeffDegree(newCoeffD);
				i++;
			}
			int diff = p2Size - p1Size;
			while (diff > 0) {
				double newCoeff = p1[1].getCoeff(i);

				int newDegree = p1[1].getDegree(i);
				CoefficientDegree newCoeffD = new CoefficientDegree(newCoeff, newDegree);
				result.addCoeffDegree(newCoeffD);
				i++;
				diff--;
			}
		}
		int i = result.getSize() - 1;
		while (result.getCoeff(i) == 0) {
			result.remove(i);
			i--;
		}
		return result;
	}

}
