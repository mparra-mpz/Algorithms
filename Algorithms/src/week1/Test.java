package week1;

import java.math.BigInteger;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		long start, stop;
		Karatsuba k = new Karatsuba();
		Random random = new Random();
		int N = Integer.parseInt(args[0]);
		BigInteger a = new BigInteger(N, random);
		BigInteger b = new BigInteger(N, random);

		start = System.currentTimeMillis();
		BigInteger c = k.karatsubaMultiplication(a, b);
		stop = System.currentTimeMillis();
		System.out.println(stop - start);

		start = System.currentTimeMillis();
		BigInteger d = a.multiply(b);
		stop = System.currentTimeMillis();
		System.out.println(stop - start);

		System.out.println((c.equals(d)));
	}

}
