package nl.knokko.rpg.shaders;

import org.lwjgl.util.vector.Matrix4f;

public class Shader extends ShaderProgram {
	
	private static final String VERTEX_FILE = "nl/knokko/rpg/shaders/vertex.shader";
    private static final String FRAGMENT_FILE = "nl/knokko/rpg/shaders/fragment.shader";
    
    private int location_transformationMatrix;

	public Shader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}
	
	public void loadTransformationMatrix(Matrix4f matrix){
		super.loadMatrix(location_transformationMatrix, matrix);
	}
}
