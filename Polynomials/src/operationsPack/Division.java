package operationsPack;

import javax.swing.JOptionPane;

import modelElements.CoefficientDegree;
import modelElements.Polynomial;

public class Division extends Operations {

	private Polynomial remainder;
	private Polynomial quotient;

	public Division() {

	}

	public Polynomial execute(Polynomial... p1) {
		Polynomial remainder = new Polynomial(p1[0].getSize());
		// Polynomial quotient = new Polynomial(p1.getSize() - p2.getSize());
		Polynomial quotient = new Polynomial();
		// System.out.println("size of p1: " + p1[0].getSize());
		// System.out.println("size of p2: " + p1[1].getSize());
		for (int i = 0; i < remainder.getSize(); i++) {
			remainder.setCoeff(p1[0].getCoeff(i), i);
			remainder.setDegree(p1[0].getDegree(i), i);
		}
		double coef = p1[1].getCoeff(p1[1].getSize() - 1);
		if(coef == 0){
			quotient.addCoeffDegree(new CoefficientDegree(0,0));

			return quotient;
			
		}
		int d,k;
		
		if(p1[1].getSize() == 1){
			
			for(k=0;k<p1[0].getSize();k++){
				double c= p1[0].getCoeff(k);
				
				p1[0].setCoeff((double) c / p1[1].getCoeff(0) , k);
				//p1[0].setDegree(k, k);
			}
			return p1[0];
			
		}else{
		d = p1[1].getSize() - 1;
		}
		Addition newAdd = new Addition();
		Subtraction newSub = new Subtraction();
		Multiplication newMult = new Multiplication();
		Polynomial resMult = new Polynomial();

		while (remainder.getSize() - 1 >= d) {

			double newCoeff = (remainder.getCoeff(remainder.getSize() - 1)) / coef;
			int newDegree = remainder.getSize() - 1 - d;

			Polynomial aux = new Polynomial(remainder.getSize() - d);
			aux.setCoeff(newCoeff, newDegree);
			aux.setDegree(newDegree, newDegree);

			quotient = newAdd.execute(quotient, aux);

			resMult = newMult.execute(aux, p1[1]);

			remainder = newSub.execute(remainder, resMult);

		}

		for (int i = 0; i < remainder.getSize(); i++) {
			p1[2].setCoeff(remainder.getCoeff(i), i);
			p1[2].setDegree(remainder.getDegree(i), i);

		}
		return quotient;
	}

}