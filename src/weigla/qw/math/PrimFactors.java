package weigla.qw.math;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PrimFactors {
    public static int[] factors(int n) {
	List<Integer> factors = new LinkedList<Integer>();
	List<Integer> primes = getPrimes((int) (n / 2));
	long num = n;

	Iterator<Integer> iter = primes.iterator();
	while (num > 1 && iter.hasNext()) {
	    int p = iter.next();
	    if (num % p == 0) {
		factors.add(p);
		num = num / p;
	    }
	}

	int[] l = new int[factors.size()];
	int i = 0;
	for (int m : factors) {
	    l[i++] = m;
	}
	return l;
    }

    public static List<Integer> getPrimes(int n) {
	LinkedList<Integer> primes = new LinkedList<Integer>();
	boolean b[] = new boolean[n];
	for (int i = 2; i < n; i++) {
	    if (!b[i]) {
		primes.add(i);
		for (int j = i; j < n; j += i) {
		    b[i] = true;
		}
	    }
	}
	return primes;
    }

    public static void main(String[] args) {
	System.out.println(Arrays.toString(PrimFactors.factors(20)));
	System.out.println(Arrays.toString(PrimFactors.factors(25)));
	System.out.println(Arrays.toString(PrimFactors.factors(30)));
	System.out.println(Arrays.toString(PrimFactors.factors(314233)));
    }
}
