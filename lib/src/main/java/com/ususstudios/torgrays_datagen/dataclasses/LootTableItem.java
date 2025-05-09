package com.ususstudios.torgrays_datagen.dataclasses;

import com.ususstudios.torgrays_datagen.DataGenerationException;

/**
 * A loot table item that can be used in the LootTableGenerator.
 */
public class LootTableItem {
	public String object;
	public float chance;
	public int amount;
	
	/**
	 * Create a new LootTableItem.
	 * @param object The path to the item that should be picked. (Should extend net.dinglezz.entity.Entity)
	 * @param chance The chance that the item would be picked.
	 * @param amount The amount of items that should be picked.
	 * @throws DataGenerationException If the value for the chance argument is greater than 1 or 0 or less, or if the
	 * amount argument is less than 1.
	 */
	public LootTableItem(
			String object,
			float chance,
			int amount
	) throws DataGenerationException {
		this.object = object;
		if (chance <= 1 && 0 < chance) this.chance = chance;
		else throw new DataGenerationException("Invalid value for argument 'chance': value must be a positive float " +
				"that is less than or equal to 1.");
		
		if (0 < amount) this.amount = amount;
		else throw new DataGenerationException("Invalid value for argument 'amount': value must be above zero.");
	}
	
	/**
	 * Create a new LootTableItem.
	 * @param objectClass The class of the item that should be picked. (Should extend net.dinglezz.entity.Entity)
	 * @param chance The chance that the item would be picked.
	 * @param amount The amount of items that should be picked.
	 * @throws DataGenerationException If the value for the chance argument is greater than 1 or 0 or less, or if the
	 * amount argument is less than 1.
	 */
	public LootTableItem(
			Class<?> objectClass,
			float chance,
			int amount
	) throws DataGenerationException {
		this(objectClass.getName(), chance, amount);
	}
}
