package operationsPack;

import modelElements.Polynomial;

public class Multiplication extends Operations{
	private Polynomial result;

	public Multiplication() {
	
	}

	public Polynomial execute(Polynomial ...p1) {
		
		
		int p1Size = p1[0].getSize();
		int p2Size = p1[1].getSize();
		result = new Polynomial(p2Size+p1Size-1);
		//System.out.println(p1Size);
		//System.out.println(p2Size);
		for(int i=0;i<p1Size;i++){
			for(int j=0; j<p2Size;j++){
				
				double newCoeff= p1[0].getCoeff(i)*p1[1].getCoeff(j);
				//int newDegree=p1.getDegree(i)+p2.getDegree(j);
				//result.addCoeffDegree(new CoefficientDegree(newCoeff+result.getCoeff(i+j),i+j));
				result.setCoeff(newCoeff+result.getCoeff(i+j), i+j);
				result.setDegree(i+j,i+j);
			
			}
		//System.out.println("coeff" + i + " =" + result.getCoeff(i) + " degree" + i + " =" + result.getDegree(i));	
		}

		//for(int i=0;i<result.getSize(); i++){
		//	System.out.println("coeff" + i + " =" + result.getCoeff(i) + " degree" + i + " =" + result.getDegree(i));
		//}
		return result;
	}
}
