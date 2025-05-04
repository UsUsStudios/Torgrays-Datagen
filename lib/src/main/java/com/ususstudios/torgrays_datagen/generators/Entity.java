package com.ususstudios.torgrays_datagen.generators;

public record Entity(
	String path,
	int col,
	int row,
	String lootTable
) {}