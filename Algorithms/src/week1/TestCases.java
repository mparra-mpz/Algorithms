package week1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class TestCases {

	public static void main(String[] args) {
		//Multiplication m = new Multiplication();
		Karatsuba k = new Karatsuba();
		String path = "/home/mparra/CODE/stanford-algs/testCases/course1/assignment1Multiplication/";

		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.getName().startsWith("input_")) {
				String input = path + file.getName();
				String output = path + file.getName().replace("input_", "output_");

				System.out.println(file.getName());

				try {
					BufferedReader br = new BufferedReader(new FileReader(input));
					String m1 = br.readLine();
					String m2 = br.readLine();
					br.close();

					br = new BufferedReader(new FileReader(output));
					String result = br.readLine();
					br.close();

					System.out.println(m1 + " x " + m2);
					BigInteger x = new BigInteger(m1);
					BigInteger y = new BigInteger(m2);
					BigInteger response = k.karatsubaMultiplication(x, y);
					System.out.println("RS: " + result);
					System.out.println("RP: " + response.toString());
					if (!result.equals(response.toString())) {
						System.out.println("F A I L ! ! ! ");
						break;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

}
