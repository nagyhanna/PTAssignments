package mainController;

import java.util.ArrayList;
import java.util.List;

import modelElements.CoefficientDegree;
import modelElements.Polynomial;
import operationsPack.Addition;
import operationsPack.AntiDerivate;
import operationsPack.Derivate;
import operationsPack.Division;
import operationsPack.Multiplication;
import operationsPack.Subtraction;

public class MainContr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Polynomial pol1= new Polynomial();
		//Polynomial pol2 = new Polynomial();
		//List<CoefficientDegree> pr1 = new ArrayList<CoefficientDegree>();
		//List<CoefficientDegree> pr2 = new ArrayList<CoefficientDegree>();

		//pr1.add(new CoefficientDegree(-4, 0));
		//pr1.add(new CoefficientDegree(0, 1));
		//Polynomial pol1 = new Polynomial(pr1);
		//pol1.addCoeffDegree(new CoefficientDegree(2, 2));
		//pol1.addCoeffDegree(new CoefficientDegree(2, 3));
		// pol1.addCoeffDegree(new CoefficientDegree(5,4));
		// pol1.addCoeffDegree(new CoefficientDegree(3,5));
		// pol1.addCoeffDegree(new CoefficientDegree(0,6));
		// pol1.addCoeffDegree(new CoefficientDegree(5,7));
		// System.out.println(pol1.toString());
		//pol2.addCoeffDegree(new CoefficientDegree(0, 0));
		//pol2.addCoeffDegree(new CoefficientDegree(0, 0));
		// pol2.addCoeffDegree(new CoefficientDegree(0,2));
		// pol2.addCoeffDegree(new CoefficientDegree(3,3));
		// pol2.addCoeffDegree(new CoefficientDegree(2,4));

		//Addition newAdd = new Addition();
		//Polynomial resultAdd = newAdd.execute(pol1, pol2);
		//System.out.println(resultAdd.toString());

		// Subtraction newSub= new Subtraction();
		// Polynomial aux=newSub.execute(pol1,pol2);
		// System.out.println(aux.toString());

		// Multiplication newMulti= new Multiplication();
		// newMulti.execute(pol1, pol2);
		// Derivate newDer= new Derivate();
		// Polynomial resultDer=newDer.execute(pol1);
		// System.out.println(resultDer.toString());

		// AntiDerivate newInteg=new AntiDerivate();
		// Polynomial resultInteg= newInteg.execute(pol1);
		// System.out.println(resultInteg.toString());

		// AntiDerivate anti2=new AntiDerivate();
		// anti2.execute();

	//	Division div = new Division();
	//	Polynomial resDiv = new Polynomial();
	//	Polynomial remainder = new Polynomial(pol2.getSize() - 1);
		//resDiv = div.execute(pol1, pol2, remainder);
		//System.out.println("division: " + resDiv.toString());
		//System.out.println("remainder: " + remainder.toString());

		//System.out.println(pol1.getSize());
		
		
		OperationsController stm = new OperationsController();
		stm.runOperations();
		
		
		//String in = "1x^2 -3.0x^1 +9";
	//	String[] parts = in.split(" ");
		//for (String coe : parts) {
			// System.out.println(coe);
			//String[] coepart = coe.split("x\\^");
			//for (String p : coepart) {
				// System.out.println(p);
			//}
		//}
		//String t1 = "2x^4 +4x^1 +3";
		//String t2 = "3x^3 +3x^1";
		/// Polynomial t11= Polynomial.stringToPolynomial(t1);
		// Polynomial t12= Polynomial.stringToPolynomial(t2);
		// System.out.println("t1: "+t11.toString());
		// System.out.println("t2: "+t12.toString());

		// Polynomial addt= newAdd.execute(t11,t12);
		// System.out.println(addt.toString());
		// Polynomial news= Polynomial.stringToPolynomial("+2.0x^3 -3.0x^2 +8x^1
		// +3");
		// System.out.println(news.toString());

		// for (int i = 0; i < aux.getSize(); i++) {
		// System.out.println("Acoeff" + i + " =" + aux.getCoeff(i) + " degree"
		// + i + " =" + aux.getDegree(i));
		// }
	}

}
