package com.ususstudios.torgrays_datagen;

import java.io.IOException;
import java.util.HashMap;

public abstract class MainDataGenerator {
	HashMap<String, Generator> generators = new HashMap<>();
	public void generate() throws IOException {
	
	}
	
	public abstract void registerAll();
	
	public void register(Generator generator) {
		generators.put(generator.name, generator);
	}
	
	public void run() {
		registerAll();
	}
}
