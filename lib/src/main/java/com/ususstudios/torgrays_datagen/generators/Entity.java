package com.ususstudios.torgrays_datagen.generators;

public record Entity(
	String path,
	int col,
	int row,
	String lootTable
) {
	public Entity(
			Class<?> entityClass,
			int col,
			int row,
			String lootTable) {
		this(entityClass.getName(), col, row, lootTable);
	}
}