package com.ususstudios.torgrays_datagen;

public abstract class Generator {
	String name;
	Generator(String name) {
		this.name = name;
	}
	abstract void register();
}
