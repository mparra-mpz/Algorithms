package week1;

import java.math.BigInteger;

public class Assignment {

	public static void main(String[] args) {

		Multiplication m = new Multiplication();
		Karatsuba k = new Karatsuba();

		String a = "3141592653589793238462643383279502884197169399375105820974944592";
		String b = "2718281828459045235360287471352662497757247093699959574966967627";
		
		BigInteger bad = m.karatsubaMultiplication(a, b);
		System.out.println("Response: " + bad.toString());

		BigInteger x = new BigInteger(a);
		BigInteger y = new BigInteger(b);
		

		BigInteger response = k.karatsubaMultiplication(x, y);
		
		System.out.println("Response: " + response.toString());

	}

}
