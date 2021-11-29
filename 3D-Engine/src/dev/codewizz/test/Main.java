  package dev.codewizz.test;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import dev.codewizz.entities.Camera;
import dev.codewizz.entities.Entity;
import dev.codewizz.models.RawModel;
import dev.codewizz.models.TexturedModel;
import dev.codewizz.renderEngine.DisplayManager;
import dev.codewizz.renderEngine.Loader;
import dev.codewizz.renderEngine.Renderer;
import dev.codewizz.shaders.StaticShader;
import dev.codewizz.textures.ModelTexture;

public class Main {

	public static void main(String[] args) {
		
		DisplayManager.create();
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer r = new Renderer(shader);
		Camera cam = new Camera();
		
		
		float[] vertices = {
				-0.5f, 0.5f, 0,
				-0.5f, -0.5f, 0,
				0.5f, -0.5f, 0,
				0.5f, 0.5f, 0f
		};
				  
		int[] indices = {
				0,1,3,
				3,1,2
		};
		
		float[] textureCoords = {
				0, 0,
				0, 1,
				1, 1, 
				1, 0
		};
		
		
		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("texture"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		Entity e = new Entity(texturedModel, new Vector3f(0, 0, 1), 0, 0, 0, 1);
		
		while(!Display.isCloseRequested()) {
			cam.move();
			r.prepare();
			shader.start();
			shader.loadViewMatrix(cam);
			r.render(e, shader);
			shader.stop();
			DisplayManager.update();
		}
		
		shader.cleanup();
		loader.cleanup();
		DisplayManager.close();
	}
}
