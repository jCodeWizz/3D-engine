package dev.codewizz.shaders;

import org.lwjgl.util.vector.Matrix4f;

import dev.codewizz.entities.Camera;
import dev.codewizz.entities.Light;
import dev.codewizz.utils.Maths;

public class TerrainShader extends ShaderProgram {

	private static final String VERTEX_FILE = "src/dev/codewizz/shaders/terrainVertexShader.txt";
	private static final String FRAGMENT_FILE = "src/dev/codewizz/shaders/terrainFragmentShader.txt";

	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private int location_lightPosition;
	private int location_lightColour;
	private int location_shineDamper;
	private int location_reflectivity;
	
	public TerrainShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);

	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
		super.bindAttribute(2, "normal");
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_lightPosition = super.getUniformLocation("lightPosition");
		location_lightColour = super.getUniformLocation("lightColour");
		location_reflectivity = super.getUniformLocation("reflectivity");
		location_shineDamper = super.getUniformLocation("shineDamper");
	}
	
	public void loadShineVariables(float damper, float reflectivity) {
		super.loadFloat(location_reflectivity, reflectivity);
		super.loadFloat(location_shineDamper, damper);
	}
	
	
	public void loadLight(Light light) {
		super.loadVector(location_lightPosition, light.getPosition());
		super.loadVector(location_lightColour, light.getColour());
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	public void loadViewMatrix(Camera cam) {
		Matrix4f viewMatrix = Maths.createViewMatrix(cam);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}
	
	public void loadProjectionMatrix(Matrix4f matrix) {
		super.loadMatrix(location_projectionMatrix, matrix);
		
	}
}