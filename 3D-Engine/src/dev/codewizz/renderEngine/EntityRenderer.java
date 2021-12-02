package dev.codewizz.renderEngine;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import dev.codewizz.entities.Entity;
import dev.codewizz.models.RawModel;
import dev.codewizz.models.TexturedModel;
import dev.codewizz.shaders.StaticShader;
import dev.codewizz.textures.ModelTexture;
import dev.codewizz.utils.Maths;

public class EntityRenderer {

	private StaticShader shader;
	
	public EntityRenderer(StaticShader shader, Matrix4f projectionMatrix) {
		this.shader = shader;
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
		
	}
	
	public void render(Map<TexturedModel, List<Entity>> entities) {
		for(TexturedModel model : entities.keySet()) {
			prepareTextureModel(model);
			List<Entity> batch = entities.get(model);
			for(Entity e : batch) {
				prepareInstance(e);
				GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel() .getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			}
			unbindModel();
		}
	}
	
	private void prepareTextureModel(TexturedModel model) {
		RawModel rawModel = model.getRawModel();
		ModelTexture t = model.getTexture();
		GL30.glBindVertexArray(rawModel.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		shader.loadShineVariables(t.getShineDamper(), t.getReflectivity());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getID() );
	}
	
	private void unbindModel() {
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}
	
	private void prepareInstance(Entity entity) {
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
		shader.loadTransformationMatrix(transformationMatrix);
		
	}
	
	
}
