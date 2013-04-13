package theAntsPowerOf6.model;

import java.math.BigInteger;

public class Randomizer {
	
	private long x;
	private BigInteger s;
	private static BigInteger multi = BigInteger.valueOf(22695477); 
	private static BigInteger div = BigInteger.valueOf(65536); 
	private static BigInteger rem = BigInteger.valueOf(16384);

	
	public Randomizer() {
		s = BigInteger.valueOf(System.currentTimeMillis());
		for (int i = 0; i < 4; i++) {
			s = nextS();
		}
	}

	public Randomizer(long seed) {
		s = BigInteger.valueOf(seed);
		for (int i = 0; i < 4; i++) {
			s = nextS();
		}		
	}
	
	private BigInteger nextS() {
		BigInteger s1 = s.multiply(multi);
		s1 = s1.add(BigInteger.valueOf(1));
		return s1;
	}
	
	private long nextX() {
		BigInteger s2 = s.divide(div);
		s2 = s2.mod(rem);
		return s2.longValue();
	}
	
	public int nextInt(int n) {
		s = nextS();
		x = nextX();
		return (int) (x % n);
	}
}
