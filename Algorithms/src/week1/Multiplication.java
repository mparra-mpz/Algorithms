package week1;

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

	public long karatsubaMultiplication(String m1, String m2) {
		if (Long.parseLong(m1) == 0 || Long.parseLong(m2) == 0)
			return 0;

		int m1Size = m1.length();
		int m2Size = m2.length();

		if (m1Size == 1 && m2Size == 1)
			return Long.parseLong(m1) * Long.parseLong(m2);

		int mSize = (m1Size > m2Size) ? m1Size : m2Size;
		int n = this.getN(mSize);
		int n2 = n / 2;

		if (m1Size != n || m2Size != n) {
			m1 = String.format("%0" + n + "d", Long.parseLong(m1));
			m2 = String.format("%0" + n + "d", Long.parseLong(m2));
		}

		String a = m1.substring(0, n2);
		String b = m1.substring(n2, n);

		String c = m2.substring(0, n2);
		String d = m2.substring(n2, n);

		long ac = this.karatsubaMultiplication(a, c);
		long bd = this.karatsubaMultiplication(b, d);

		String aPlusb = Long.toString(Long.parseLong(a) + Long.parseLong(b));
		String cPlusd = Long.toString(Long.parseLong(c) + Long.parseLong(d));

		long gauss = this.karatsubaMultiplication(aPlusb, cPlusd);

		long adPlusbc = gauss - ac - bd;

		long response = (long) ((Math.pow(10, n) * ac) + (Math.pow(10, n2) * adPlusbc) + (bd));

		// System.out.println(m1 + " x " + m2 + " N: " + n + " Response: " + response);
		return response;
	}

}
