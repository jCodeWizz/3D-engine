package dev.codewizz.models;

import dev.codewizz.textures.ModelTexture;

public class TexturedModel {

	private RawModel rawModel;
	private ModelTexture texture;  
	
	public TexturedModel(RawModel m, ModelTexture t) {
		this.rawModel = m;
		this.texture = t;
	}

	public RawModel getRawModel() {
		return rawModel;
	}

	public ModelTexture getTexture() {
		return texture;
	}
}
