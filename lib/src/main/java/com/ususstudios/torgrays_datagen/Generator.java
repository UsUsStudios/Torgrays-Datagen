package com.ususstudios.torgrays_datagen;

import org.json.JSONObject;

import java.util.HashMap;

public abstract class Generator {
	String name;
	HashMap<String, JSONObject> objects = new HashMap<>();
	Generator(String name) {
		this.name = name;
	}
	
	public abstract boolean verify(JSONObject object);
}
