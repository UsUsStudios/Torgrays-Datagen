package com.ususstudios.torgrays_datagen.dataclasses;

import com.ususstudios.torgrays_datagen.DataGenerationException;

/**
 * A special (combination multi-select + single-select) loot table item dataclass that can be used in the
 * LootTableGenerator.
 */
public class SpecialLootTableItem {
	public float chance;
	public LootTableItem[] lootTable;
	
	/**
	 * Creates a special (combination multi-select + single-select) loot table item.
	 * @param chance The chance that this loot table item be picked.
	 * @param lootTable The items of the single-select loot table.
	 */
	public SpecialLootTableItem(float chance, LootTableItem[] lootTable) throws DataGenerationException {
		this.chance = chance;
		this.lootTable = lootTable;
		
		float chanceSum = 0;
		for (LootTableItem lootTableItem : lootTable) {
			chanceSum += lootTableItem.chance;
		}
		
		if (chanceSum != 1) throw new DataGenerationException("The sum of all chances in this special loot table " +
				"must be 1, this sum is " + chanceSum + ".");
	}
}
