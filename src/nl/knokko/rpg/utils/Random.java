package nl.knokko.rpg.utils;

public final class Random {
	
	public static boolean chance(double chance){
		return canDivide(System.nanoTime(), chance);
	}
	
	public static boolean canDivide(long number, double diviner){
		double dn = number / diviner;
		long ln = (long)(dn);
		return dn == ln;
	}
	
	public static long randomLong(long max){
		long n = System.nanoTime();
		long div = n / (max + 1);
		long res = n / div;
		long mul = res * div;
		return n - mul;
	}
	
	public static byte randomByte(long max){
		return (byte) randomLong(max);
	}
	
	public static int randomInt(long max){
		return (int) randomLong(max);
	}
	
	public static int randomInt(long min, long max) {
		return (int) randomLong(min, max);
	}
	
	public static long randomLong(long min, long max){
		return min + randomLong(max - min);
	}
}
