package week1;

import java.math.BigInteger;

public class Multiplication {

	public Multiplication() {
		super();
	}

	public boolean isBase2(int n) {
		if (n == 1)
			return true;
		if (n % 2 == 0)
			return this.isBase2(n / 2);
		return false;
	}

	private int getN(int size) {
		int i = 0;
		int n = 0;

		while (size > n) {
			n = (int) Math.pow(2, i);
			i++;
		}
		return n;
	}

	public BigInteger karatsubaMultiplication(String m1, String m2) {
		BigInteger response = new BigInteger("0");
		BigInteger bim1 = new BigInteger(m1);
		BigInteger bim2 = new BigInteger(m2);

		if (bim1.compareTo(response) == 0 || bim2.compareTo(response) == 0) {
			// System.out.println(m1 + " x " + m2 + " N: " + 1 + " Response: " + response);
			return response;
		}

		int m1Size = m1.length();
		int m2Size = m2.length();

		if (m1Size == 1 && m2Size == 1) {
			response = bim1.multiply(bim2);
			// System.out.println(m1 + " x " + m2 + " N: " + 1 + " Response: " + response);
			return response;
		}

		int mSize = (m1Size > m2Size) ? m1Size : m2Size;
		int n = this.getN(mSize);
		int n2 = n / 2;

		if (m1Size != n || m2Size != n) {
			m1 = String.format("%0" + n + "d", bim1.longValue());
			m2 = String.format("%0" + n + "d", bim2.longValue());
		}

		BigInteger a = new BigInteger(m1.substring(0, n2));
		BigInteger b = new BigInteger(m1.substring(n2, n));

		BigInteger c = new BigInteger(m2.substring(0, n2));
		BigInteger d = new BigInteger(m2.substring(n2, n));

		BigInteger ac = this.karatsubaMultiplication(a.toString(), c.toString());
		BigInteger bd = this.karatsubaMultiplication(b.toString(), d.toString());

		BigInteger aPlusb = a.add(b);
		BigInteger cPlusd = c.add(d);

		BigInteger gauss = this.karatsubaMultiplication(aPlusb.toString(), cPlusd.toString());

		BigInteger adPlusbc = gauss.subtract(ac.add(bd));

		BigInteger auxN = new BigInteger("10");
		auxN = auxN.pow(n);
		BigInteger auxN2 = new BigInteger("10");
		auxN2 = auxN2.pow(n2);

		ac = ac.multiply(auxN);
		adPlusbc = adPlusbc.multiply(auxN2);
		response = ac.add(adPlusbc);
		response = response.add(bd);

		// System.out.println(m1 + " x " + m2 + " N: " + n + " Response: " +
		// response.toString());
		return response;
	}

}
