package nl.knokko.rpg.render;

import java.awt.Color;

import nl.knokko.rpg.shaders.Shader;
import nl.knokko.rpg.utils.Loader;
import nl.knokko.rpg.utils.Utils;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;

public final class Renderer {
	
private static final float[] QUADS = {-1,1, -1,-1, 1,1, 1,-1};
	
	private static RawModel quad;
	private static Shader shader;
	
	public static void setUp(){
		quad = Loader.loadToVAO(QUADS);
		shader = new Shader();
	}
	
	public static void beforeRendering(Color backGround){
		shader.start();
		GL30.glBindVertexArray(quad.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		if(backGround != null){
			GL11.glClearColor(backGround.getRed() / 255f, backGround.getGreen() / 255f, backGround.getBlue() / 255f, 1);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		}
	}
	
	public static void renderTexture(Vector2f translation, Vector2f scale, int texture){
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		shader.loadTransformationMatrix(Utils.createTransformationMatrix(translation, scale));
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount());
	}
	
	public static void renderTextureBetween(Vector2f vector1, Vector2f vector2, int texture){
		Vector2f mid = new Vector2f((vector1.x + vector2.x) / 2, (vector1.y + vector2.y) / 2);
		Vector2f scale = new Vector2f(Math.abs(vector2.x - vector1.x) / 2, Math.abs(vector2.y - vector1.y) / 2);
		renderTexture(mid, scale, texture);
	}
	
	public static void renderRotatedTextureBetween(Vector2f vector1, Vector2f vector2, float rotation, int texture){
		Vector2f mid = new Vector2f((vector1.x + vector2.x) / 2, (vector1.y + vector2.y) / 2);
		Vector2f scale = new Vector2f(Math.abs(vector2.x - vector1.x) / 2, Math.abs(vector2.y - vector1.y) / 2);
		renderRotatedTexture(mid, scale, rotation, texture);
	}
	
	public static void renderRotatedTexture(Vector2f translation, Vector2f scale, float rotation, int texture){
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		shader.loadTransformationMatrix(Utils.createTransformationMatrix(translation, scale, rotation));
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount());
	}
	
	public static void afterRendering(){
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		shader.stop();
	}
	
	public static void cleanUp(){
		shader.cleanUp();
	}
}
