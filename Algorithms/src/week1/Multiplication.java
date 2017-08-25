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

	public long karatsubaMultiplication(String m1, String m2) {
		long response = 0;

		int m1Size = m1.length();
		int m2Size = m2.length();

		int n = (m1Size > m2Size) ? m1Size : m2Size;
		int n2 = (int) Math.ceil(n / 2.0);

		if (m1Size != m2Size) {
			m1 = String.format("%0" + n + "d", Long.parseLong(m1));
			m2 = String.format("%0" + n + "d", Long.parseLong(m2));
		}

		if (m1Size == 1 && m2Size == 1) {
			response = Long.parseLong(m1) * Long.parseLong(m2);
		} else {
			int half = n / 2;

			String a = m1.substring(0, half);
			String b = m1.substring(half, n);

			String c = m2.substring(0, half);
			String d = m2.substring(half, n);

			long ac = this.karatsubaMultiplication(a, c);
			long bd = this.karatsubaMultiplication(b, d);

			String aPlusb = Long.toString(Long.parseLong(a) + Long.parseLong(b));
			String cPlusd = Long.toString(Long.parseLong(c) + Long.parseLong(d));

			long gauss = this.karatsubaMultiplication(aPlusb, cPlusd);

			long adPlusbc = gauss - ac - bd;

			response = (long) ((Math.pow(10, n) * ac) + (Math.pow(10, n2) * adPlusbc) + (bd));
		}
		
		System.out.println(m1 + " x " + m2 + " N: " + n + " Response: " + response);
		return response;
	}

}
