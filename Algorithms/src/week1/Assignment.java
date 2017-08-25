package week1;

public class Assignment {

	public static void main(String[] args) {

		Multiplication m = new Multiplication();

		String a = "3141592653589793238462643383279502884197169399375105820974944592";
		String b = "2718281828459045235360287471352662497757247093699959574966967627";
		
		long response = m.karatsubaMultiplication(a, b);
		System.out.println("Response: " + response);

	}

}
