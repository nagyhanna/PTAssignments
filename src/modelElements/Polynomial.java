package modelElements;

import java.util.ArrayList;
import java.util.List;

import org.omg.Messaging.SyncScopeHelper;

public class Polynomial {

	private List<CoefficientDegree> pairs = new ArrayList<CoefficientDegree>();

	public Polynomial() {

	}

	public Polynomial(List<CoefficientDegree> pairs) {
		this.pairs = new ArrayList<CoefficientDegree>();
		this.pairs = pairs;

	}

	public Polynomial(int size) {
		for (int i = 0; i < size; i++) {
			this.addCoeffDegree(new CoefficientDegree(0, i));
		}
	}

	public void setCoeff(double coeff, int i) {
		pairs.get(i).setCoefficient(coeff);
	}

	public void setCoeffAndDegree(double coeff, int degree) {
		pairs.add(new CoefficientDegree(coeff, degree));
	}

	public void setDegree(int degree, int i) {
		pairs.get(i).setDegree(degree);
	}

	public double getCoeff(int i) {
		if (this.pairs.size() < i) {
			return 0;
		} else
			return this.pairs.get(i).getCoefficient();

	}

	public int getDegree(int i) {
		return pairs.get(i).getDegree();
	}

	public void addCoeffDegree(CoefficientDegree p) {
		pairs.add(p);

	}

	public int getSize() {
		return pairs.size();
	}

	public void remove(int i) {
		pairs.remove(i);
	}

	/*
	 * 1.0x^3 + 0.0x^2 -8.0x^1 -2.0 =0
	 */
	public String toString() {
		String poliString = new String();

		for (int i = this.getSize() - 1; i >= 0; i--) {
			if (this.getCoeff(i) >= 0) {
				if (i == 0) {
					poliString = poliString + " + " + this.getCoeff(i);
				} else if (i == this.getSize() - 1) {
					poliString = this.getCoeff(i) + "x^" + this.getDegree(i);// +"
																				// +
																				// ";
				} else
					poliString = poliString + " + " + this.getCoeff(i) + "x^" + this.getDegree(i);

			} else {
				if (i == 0) {
					poliString = poliString + " " + this.getCoeff(i);
				} else if (i == this.getSize() - 1) {
					poliString = this.getCoeff(i) + "x^" + this.getDegree(i);// +"
																				// +
																				// ";
				} else
					poliString = poliString + " " + this.getCoeff(i) + "x^" + this.getDegree(i);
			}
		}
		if (this.getSize() > 1) {
			poliString = poliString + " = 0";
		}
		return poliString;

	}

	public boolean equals(Polynomial p1) {
		boolean equal = true;
		int i;
		i = 0;
		al: while (equal && (i < this.getSize())) {

			if (this.getSize() != p1.getSize()) {
				equal = false;
				break;
			} else {
				for (CoefficientDegree coef_deg : p1.pairs) {

					if (coef_deg.getCoefficient() != this.getCoeff(i)) {
						//System.out.println("they are not equal " + coef_deg.getCoefficient() + " " + this.getCoeff(i));
						equal = false;
						break al;
					}

					i++;
				}
			}
		}

		return equal;

	}

	public static Polynomial stringToPolynomial(String input) {
		Polynomial pol1 = new Polynomial();
		String[] monomial = input.split(" |x\\^");
		//System.out.println(monomial.length);
		int i = monomial.length - 1;
		int length;
		
		if(monomial.length == 1){
			pol1.addCoeffDegree(new CoefficientDegree(Double.parseDouble(monomial[0]),0));
			return pol1;
		}else 
		 length = Integer.parseInt(monomial[1]);
		
		//System.out.println(length);
		Double coeff;
		int degree;
		int j = 0;
		for (j = 1; j <= length; j++) {

			if (monomial.length % 2 == 0 && (i == monomial.length - 1)) {
				pol1.addCoeffDegree(new CoefficientDegree(0, 0));

			} else if (monomial.length % 2 != 0 && (i == monomial.length - 1)) {
				coeff = Double.parseDouble(monomial[i]);
				pol1.addCoeffDegree(new CoefficientDegree(coeff, 0));
				i--;
			}

			//System.out.println(i + " " + (i - 1));
			degree = Integer.parseInt(monomial[i]);
			coeff = Double.parseDouble(monomial[i - 1]);

			if (degree == j) {
				pol1.addCoeffDegree(new CoefficientDegree(coeff, j));
				i -= 2;
			} else {
				pol1.addCoeffDegree(new CoefficientDegree(0, j));
			}

		}

		return pol1;
	}

}
