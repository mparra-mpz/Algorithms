package week1;

import java.util.Random;

public class MultiplicationTest {

	public static void main(String[] args) {
		Multiplication m = new Multiplication();

		boolean success = true;

		int min = 1;
		int max = 1000000000;

		Random rand = new Random();

		for (int i = 1; i <= 1000; i++) {
			long a = (long) (rand.nextInt(max - min + 1) + min);
			long b = (long) (rand.nextInt(max - min + 1) + min);

			long value = a * b;
			System.out.println("Iteration: " + i);
			System.out.println("Karatsuba multiplication: " + a + " x " + b);
			long response = m.karatsubaMultiplication(Long.toString(a), Long.toString(b));
			System.out.println("Response: " + response + " Value: " + value);

			if (value != response) {
				success = false;
				break;
			}
		}

		if (success)
			System.out.println("Great work 1000 iterations correct!!!");
		else
			System.out.println("Wrong Something is failing!!!");
	}

}
