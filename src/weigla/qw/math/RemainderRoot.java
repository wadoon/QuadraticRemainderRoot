package weigla.qw.math;

import java.io.IOException;
import java.util.Arrays;

public class RemainderRoot {
    public static int root(int a, int n, int[] sol) throws NoSolutionException {
	if (PrimeNumberTest.statTest(n, 0.001)) {
	    return rootWithPrime(a, n, sol);
	}

	// try splitting up in prime factors
	int[] primfact = PrimFactors.factors(n);

	// Satz 2.6
	if (primfact.length == 2) {
	    int p = primfact[0];
	    int q = primfact[1];
	    return rootWithTwoPrimes(a, p, q, sol);
	}

	throw new NoSolutionException(
		"Lösung von allg. Quadradwurzeln ist nicht implementiert.");
    }

    private static int rootWithTwoPrimes(int a, int p, int q, int[] sol)
	    throws NoSolutionException {
	int[][] solutions = new int[2][2]; // 2x2 solutions
	rootWithPrime(a, p, solutions[0]);
	rootWithPrime(a, q, solutions[1]);

	final int[] primes = new int[] { p, q };
	sol[0] = ChineseRemainder.chineseRemainder(new int[] { solutions[0][0],
		solutions[1][0] }, primes);
	sol[1] = ChineseRemainder.chineseRemainder(new int[] { solutions[0][0],
		solutions[1][1] }, primes);
	sol[2] = ChineseRemainder.chineseRemainder(new int[] { solutions[0][1],
		solutions[1][0] }, primes);
	sol[3] = ChineseRemainder.chineseRemainder(new int[] { solutions[0][1],
		solutions[1][1] }, primes);
	return 4;
    }

    // Satz 2.2
    private static int rootWithPrime(int a, int n, int[] sol)
	    throws NoSolutionException {
	a = mod(a, n);
	// pruefung ob a ein quadratischer Rest ist
	// Satz 2.4s
	int test = modPow(a, (n - 1) / 2, n);
	if (test != 1) {
	    throw new NoSolutionException("a (" + a
		    + ") ist kein quadratischer Rest zu n (" + n + ")");
	}

	if (n % 4 == 3)// Satz 2.5
	{
	    int x1 = modPow(a, (n + 1) / 4, n) % n;
	    int x2 = n - x1;
	    sol[0] = x1;
	    sol[1] = x2;

	    if (!checkRoot(x1, a, n))
		throw new IllegalStateException("not computed right, " + x1
			+ "^2 != " + a + " mod " + n);
	    if (!checkRoot(x2, a, n))
		throw new IllegalStateException("not computed right, " + x2
			+ "^2 != " + a + " mod " + n);
	    return 2;
	}
	// brute force
	for (int i = 1; i < (n / 2) + 1; i++) {
	    if (checkRoot(i, a, n)) {
		int x2 = n - i;
		sol[0] = i;
		sol[1] = x2;
		return 2;
	    }
	}
	return 0;
    }

    private static boolean checkRoot(int root, int remainder, int modul) {
	remainder = mod(remainder, modul);
	int r = mod(root * root, modul);
	return r == remainder;
    }

    private static int mod(int a, int n) {
	a %= n;
	if (a < 0)
	    return a + n;
	return a;
    }

    private static int modPow(int base, int radix, int modul) {
	while (radix > 1) {
	    base = (base * base) % modul;
	    radix--;
	}
	return mod(base, modul);
    }

    public static void main(String[] args) throws IOException,
	    NoSolutionException {
	int[] sol = new int[4];
	System.out.println(root(4, 15, sol));
	System.out.println(Arrays.toString(sol));
    }
}
