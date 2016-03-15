package operationsPack;

import modelElements.Polynomial;

public class Derivate extends Operations {

	private Polynomial result;

	public Derivate() {

	}

	public Polynomial execute(Polynomial... p1) {

		result = new Polynomial(p1[0].getSize() - 1);
		for (int i = 1; i < p1[0].getSize(); i++) {
			double newCoeff = p1[0].getCoeff(i) * p1[0].getDegree(i);
			//System.out.println("1Dcoeff" + i + " =" + p1[0].getCoeff(i) + " 1DDegree" + i + " =" + p1[0].getDegree(i));
			result.setCoeff(newCoeff, i - 1);
			result.setDegree(i - 1, i - 1);

		}
		//for (int i = 0; i < result.getSize(); i++) {
			//System.out.println("Dcoeff" + i + " =" + result.getCoeff(i) + " degree" + i + " =" + result.getDegree(i));
		//}

		return result;
	}

}
