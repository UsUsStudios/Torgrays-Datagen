package com.ususstudios.torgrays_datagen.dataclasses;

/**
 * A record for entities, used in the map generator.
 * @param path The path to the entity class.
 * @param col The column where the entity spawns.
 * @param row The row where the entity spawns.
 * @param lootTable The name of the loot table that should be used when the entity is killed/opened/used, etc. Can be
 *                 null if there is no loot table associated.
 */
public record Entity(
	String path,
	int col,
	int row,
	String lootTable
) {
	/**
	 * A record for entities, used in the map generator.
	 * @param entityClass The entity class, to get the path.
	 * @param col The column where the entity spawns.
	 * @param row The row where the entity spawns.
	 * @param lootTable The name of the loot table that should be used when the entity is killed/opened/used, etc. Can
	 *                     be null if there is no loot table associated.
	 */
	public Entity(
			Class<?> entityClass,
			int col,
			int row,
			String lootTable) {
		this(entityClass.getName(), col, row, lootTable);
	}
}