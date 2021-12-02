package dev.codewizz.renderEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import dev.codewizz.entities.Camera;
import dev.codewizz.entities.Entity;
import dev.codewizz.entities.Light;
import dev.codewizz.models.TexturedModel;
import dev.codewizz.shaders.StaticShader;

public class MasterRenderer {

	public static final float FOV = 70;
	public static final float NEAR_PLANE = 0.1f;
	public static final float FAR_PLANE = 1000;
	
	private StaticShader shader = new StaticShader();
	private EntityRenderer renderer;

	private Map<TexturedModel, List<Entity>> entities = new HashMap<>();
	private Matrix4f projectionMatrix;

	public MasterRenderer() {
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		createProjectionMatrix();
		renderer  = new EntityRenderer(shader, projectionMatrix);
	}
	
	public void render(Light sun, Camera cam) {
		prepare();
		shader.start();
		shader.loadLight(sun);
		shader.loadViewMatrix(cam);

		renderer.render(entities);

		shader.stop();
		entities.clear();

	}

	public void processEntity(Entity e) {
		TexturedModel model = e.getModel();
		List<Entity> batch = entities.get(model);
		if (batch != null) {
			batch.add(e);
		} else {
			List<Entity> newBatch = new ArrayList<>();
			newBatch.add(e);
			entities.put(model, newBatch);
		}
	}

	private void createProjectionMatrix() {
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;

		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0;
	}

	public void cleanup() {
		shader.cleanup();
	}

	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClearColor(0.1f, 0.1f, 0.1f, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
}
