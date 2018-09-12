package nl.knokko.rpg.utils;

public final class Utils {
	
	public static final byte[] BYTES = new byte[]{64,32,16,8,4,2,1};
	
	public static int intFromDouble(double d){
		int i = (int)d;
		if(d - i >= 0.5){
			++i;
		}
		return i;
	}
	
	public static long longFromDouble(double d){
		long l = (long)d;
		if(d - l >= 0.5)
			++l;
		return l;
	}
	
	public static boolean[] toBinair(byte b){
		boolean[] bools = new boolean[8];
		if(b >= 0)
			bools[7] = true;
		else {
			b++;
			b *= -1;
		}
		byte t = 0;
		while(t < 7){
			if(b >= BYTES[t]){
				b -= BYTES[t];
				bools[t] = true;
			}
			++t;
		}
		return bools;
	}
	
	public static byte fromBinair(boolean[] bools){
		byte b = 0;
		int t = 0;
		while(t < 7){
			if(bools[t])
				b += BYTES[t];
			++t;
		}
		if(!bools[7]){
			b *= -1;
			b--;
		}
		return b;
	}
}
