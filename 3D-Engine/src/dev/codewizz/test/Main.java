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
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,0.5f,-0.5f,		
				
				-0.5f,0.5f,0.5f,	
				-0.5f,-0.5f,0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				0.5f,0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				-0.5f,-0.5f,0.5f,	
				-0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,0.5f,
				-0.5f,0.5f,-0.5f,
				0.5f,0.5f,-0.5f,
				0.5f,0.5f,0.5f,
				
				-0.5f,-0.5f,0.5f,
				-0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,0.5f
				
		};
		
		float[] textureCoords = {
				
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0

				
		};
		
		int[] indices = {
				0,1,3,	
				3,1,2,	
				4,5,7,
				7,5,6,
				8,9,11,
				11,9,10,
				12,13,15,
				15,13,14,	
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22

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
