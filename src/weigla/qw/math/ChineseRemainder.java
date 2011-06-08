package weigla.qw.math;

import java.util.Arrays;

public class ChineseRemainder {
    // http://tupac.euv-frankfurt-o.de/www/kryptos/chinesischer.html
    public static int chineseRemainder(int[] a, int m[])
	    throws NoSolutionException {
	if (a.length != m.length) {
	    throw new IllegalArgumentException();
	}
	int n = m.length;

	for (int i = 0; i < n; i++) {
	    for (int j = 0; j < n; j++) {
		if (i != j) {
		    long[] gcd = GCD.gcd(m[i], m[j]);
		    if (gcd[0] != 1) {
			throw new NoSolutionException(
				"Die Module müssen für den chin. Restsatz relativ prim sein.");
		    }
		}
	    }
	}

	long product = 1;
	for (int i = 0; i < n; i++) {
	    product *= m[i];
	}

	long M[] = new long[n];
	for (int i = 0; i < n; i++) {
	    M[i] = product / m[i];
	}

	long Minv[] = new long[n];
	for (int i = 0; i < n; i++) {
	    Minv[i] = GCD.extendedGCDFactorFirst(M[i], m[i]);
	}

	int x = 0;
	for (int i = 0; i < Minv.length; i++) {
	    x += (a[i] * M[i] * Minv[i]) % product;
	}

	x %= product;

	if (x < 0) {
	    x += product;
	}
	return x;
    }

    public static void main(String[] args) throws NoSolutionException {
	System.out.println(ChineseRemainder.chineseRemainder(new int[] { 3, 2,
		1 }, new int[] { 4, 5, 3 }));
    }
}
