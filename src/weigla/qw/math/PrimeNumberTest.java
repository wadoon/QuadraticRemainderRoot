package weigla.qw.math;

import java.util.Random;

public class PrimeNumberTest {
    public static boolean statTest(int n, double prob) {
	if ((n & 1) == 0) {
	    return false;
	}

	if (n <= 10000)// kleiner Bruteforce
	{
	    for (int i = 3; i < Math.sqrt(n); i++) {
		if (n % i == 0)
		    return false;
	    }
	    return true;
	}

	int k = 1;
	double p = 0.5;
	for (; k < 17; k++) {// überschreitung maschinengenauigkeit
	    if (p <= prob)
		break;
	    p /= 2;
	}

	Random r = new Random();
	for (int i = 0; i < k; i++) {
	    int ai = r.nextInt((int) n - 1);
	    long t = (long) (Math.pow(ai, (n - 1) / 2)) % n;
	    if (t == 1)
		return true;
	}
	return false;
    }

    public static void main(String[] args) {
	System.out.println(PrimeNumberTest.statTest(3, 0.02));
	System.out.println(PrimeNumberTest.statTest(96, 0.2));
	System.out.println(PrimeNumberTest.statTest(17, 0.02));
	System.out.println(PrimeNumberTest.statTest(23, 0.2));
	System.out.println(PrimeNumberTest.statTest(27, 0.2));
	System.out.println(PrimeNumberTest.statTest(32, 0.01));
	System.out.println(PrimeNumberTest.statTest(56, 0.002));
    }
}
