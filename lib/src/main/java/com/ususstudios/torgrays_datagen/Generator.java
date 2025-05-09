package com.ususstudios.torgrays_datagen;

import org.json.JSONObject;
import java.util.HashMap;

public abstract class Generator {
	String name;
	public HashMap<String, JSONObject> generatedJson = new HashMap<>();
	protected Generator(String name) {
		this.name = name;
	}
	
	public abstract boolean verify(JSONObject object);
	
	public abstract void registerAll() throws DataGenerationException;
}
