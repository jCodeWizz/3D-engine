package dev.codewizz.engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import dev.codewizz.entities.Camera;
import dev.codewizz.entities.Entity;
import dev.codewizz.entities.Light;
import dev.codewizz.models.TexturedModel;
import dev.codewizz.renderEngine.DisplayManager;
import dev.codewizz.renderEngine.Loader;
import dev.codewizz.renderEngine.MasterRenderer;
import dev.codewizz.terrains.Terrain;
import dev.codewizz.textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
	
		List<Entity> entities = new ArrayList<Entity>();
		TexturedModel m = loader.loadTexturedModel("tree", loader);
		Random random = new Random();
		for(int i=0;i<500;i++){
			entities.add(new Entity(m, new Vector3f(random.nextFloat()*800 - 400,0,random.nextFloat() * -600),0,0,0,3));
		}
		
		Light light = new Light(new Vector3f(20000,20000,2000),new Vector3f(1,1,1));
		
		Terrain terrain = new Terrain(-1, -1,loader,new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain2 = new Terrain(0,-1,loader,new ModelTexture(loader.loadTexture("grass")));
		
		Camera camera = new Camera();	
		MasterRenderer renderer = new MasterRenderer();
		
		while(!Display.isCloseRequested()){
			camera.move();
			
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain2);
			for(Entity entity:entities){
				renderer.processEntity(entity);
			}
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}

		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
