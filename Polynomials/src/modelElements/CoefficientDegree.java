package modelElements;

public class CoefficientDegree {

	private double coefficient;
	private int degree;

	public CoefficientDegree(double c, int d) {
		this.coefficient = c;
		this.degree = d;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

}
