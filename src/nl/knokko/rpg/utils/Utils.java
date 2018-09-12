package nl.knokko.rpg.utils;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public final class Utils {
	
	public static final byte[] BYTES = new byte[]{64,32,16,8,4,2,1};
	
	private static final Vector3f AXIS = new Vector3f(0, 0, 1);
	
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
	
	public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale){
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.scale(new Vector3f(scale.x, scale.y, 1f), matrix, matrix);
		return matrix;
	}
	
	public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale, float rotation){
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.scale(new Vector3f(scale.x, scale.y, 1f), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rotation), AXIS, matrix, matrix);
		return matrix;
	}
	
	public static Matrix4f createViewMatrix(Vector2f translation){
		Matrix4f matrix = new Matrix4f();
		Matrix4f.translate(new Vector3f(translation.x, translation.y, 1), matrix, matrix);
		return matrix;
	}
	
	public static float oldXToRelativeX(int oldX){
		return (oldX / 1600f - 0.5f) * 2;
	}
	
	public static float oldYToRelativeY(int oldY){
		return (oldY / 900f - 0.5f) * 2;
	}
}
