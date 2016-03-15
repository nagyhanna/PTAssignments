package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import modelElements.CoefficientDegree;
import modelElements.Polynomial;
import operationsPack.Addition;
import operationsPack.AntiDerivate;
import operationsPack.Derivate;
import operationsPack.Division;
import operationsPack.Multiplication;
import operationsPack.Operations;
import operationsPack.Subtraction;

/**
 * Testing the operations
 */
public class TestOfOperations {

	Polynomial polynom1;
	Polynomial polynom2;
	Polynomial expectedResult;
	Polynomial actualResult;
	Polynomial expectedRemainder;
	Polynomial actualRemainder;

	Operations operation;

	/**
	 * polynom1 = 2x^3 +3x^2 -4 polynom2 = 1x^1 -3
	 */
	public TestOfOperations() {
		polynom1 = new Polynomial();
		polynom2 = new Polynomial();
		expectedResult = new Polynomial();

		polynom1.addCoeffDegree(new CoefficientDegree(-4, 0));
		polynom1.addCoeffDegree(new CoefficientDegree(0, 1));
		polynom1.addCoeffDegree(new CoefficientDegree(3, 2));
		polynom1.addCoeffDegree(new CoefficientDegree(2, 3));

		polynom2.addCoeffDegree(new CoefficientDegree(-3, 0));
		polynom2.addCoeffDegree(new CoefficientDegree(1, 1));

	}

	/**
	 * result= 2x^3 +3x^2 +1x^1 -7
	 */

	@Test
	public void testAddition() {

		operation = new Addition();

		expectedResult.addCoeffDegree(new CoefficientDegree(-7, 0));
		expectedResult.addCoeffDegree(new CoefficientDegree(1, 1));
		expectedResult.addCoeffDegree(new CoefficientDegree(3, 2));
		expectedResult.addCoeffDegree(new CoefficientDegree(2, 3)); 
		
		actualResult = operation.execute(polynom1, polynom2);
		assertTrue("addition result", expectedResult.equals(actualResult));
	}

	/**
	 * result = 2x^3 +3x^2 -1x^1 -1
	 */

	@Test
	public void testSubtraction() {

		operation = new Subtraction();

		expectedResult.addCoeffDegree(new CoefficientDegree(-1, 0));
		expectedResult.addCoeffDegree(new CoefficientDegree(-1, 1));
		expectedResult.addCoeffDegree(new CoefficientDegree(3, 2));
		expectedResult.addCoeffDegree(new CoefficientDegree(2, 3));

		actualResult = operation.execute(polynom1, polynom2);

		assertTrue("subtraction result", expectedResult.equals(actualResult));
	}

	@Test
	public void testMultiplication() {

		operation = new Multiplication();

		expectedResult.addCoeffDegree(new CoefficientDegree(12, 0));
		expectedResult.addCoeffDegree(new CoefficientDegree(-4, 1));
		expectedResult.addCoeffDegree(new CoefficientDegree(-9, 2));
		expectedResult.addCoeffDegree(new CoefficientDegree(-3, 3));
		expectedResult.addCoeffDegree(new CoefficientDegree(2, 4));

		actualResult = operation.execute(polynom1, polynom2);

		assertTrue("multiplication result", expectedResult.equals(actualResult));
	}

	@Test
	public void testDivision() {

		operation = new Division();

		expectedResult.addCoeffDegree(new CoefficientDegree(27, 0));
		expectedResult.addCoeffDegree(new CoefficientDegree(9, 1));
		expectedResult.addCoeffDegree(new CoefficientDegree(2, 2));

		expectedRemainder = new Polynomial(0);
		expectedRemainder.addCoeffDegree(new CoefficientDegree(77.0, 0));

		actualRemainder = new Polynomial(polynom2.getSize() - 1);
		actualResult = operation.execute(polynom1, polynom2, actualRemainder);

		assertTrue("division result", expectedResult.equals(actualResult));
		assertTrue("division resultR", expectedRemainder.equals(actualRemainder));
	}

	@Test
	public void testDerivate() {

		operation = new Derivate();

		expectedResult.addCoeffDegree(new CoefficientDegree(0, 0));
		expectedResult.addCoeffDegree(new CoefficientDegree(6, 1));
		expectedResult.addCoeffDegree(new CoefficientDegree(6, 2));

		actualResult = operation.execute(polynom1);

		System.out.println(actualResult.toString());
		System.out.println(expectedResult.toString());

		assertTrue("derivation result", expectedResult.equals(actualResult));
	}

	@Test
	public void testIntegration() {

		AntiDerivate an;
		an = new AntiDerivate();

		expectedResult.addCoeffDegree(new CoefficientDegree(0, 0));
		expectedResult.addCoeffDegree(new CoefficientDegree(-4, 1));
		expectedResult.addCoeffDegree(new CoefficientDegree(0, 2));
		expectedResult.addCoeffDegree(new CoefficientDegree(1.0, 3));
		expectedResult.addCoeffDegree(new CoefficientDegree((double) 2 / 4, 4));

		actualResult = an.execute(polynom1);

		System.out.println(actualResult.toString());
		System.out.println(expectedResult.toString());

		assertTrue("anti derivative (integration) result", expectedResult.equals(actualResult));
	}

}
