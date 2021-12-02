  package dev.codewizz.test;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import dev.codewizz.entities.Camera;
import dev.codewizz.entities.Entity;
import dev.codewizz.entities.Light;
import dev.codewizz.models.OBJLoader;
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
		
		
		RawModel model = OBJLoader.loadObjModel("dragon", loader);
		ModelTexture texture = new ModelTexture(loader.loadTexture("dragonTexture"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		Entity e = new Entity(texturedModel, new Vector3f(0, -5, -25), 0, 0, 0, 1);
		
		texture.setShineDamper(10);
		texture.setReflectivity(1);
		
		Light light = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1));
		
		while(!Display.isCloseRequested()) {
			e.increaseRotation(0, 1, 0);
			cam.move();
			r.prepare();
			shader.start();
			shader.loadLight(light);
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
