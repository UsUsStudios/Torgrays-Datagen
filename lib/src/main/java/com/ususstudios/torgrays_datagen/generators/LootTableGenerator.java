package com.ususstudios.torgrays_datagen.generators;

import com.ususstudios.torgrays_datagen.DataGenerationException;
import com.ususstudios.torgrays_datagen.Generator;
import com.ususstudios.torgrays_datagen.dataclasses.LootTableItem;
import org.json.JSONObject;

/**
 * A class you can extend to register loot tables to generate.
 */
public abstract class LootTableGenerator extends Generator {
	public LootTableGenerator() {super("loot tables");}
	
	/**
	 * Registers a single-select loot table to be generated.
	 * A single-select loot table is one that picks a single option out of a list of options (with specified chances).
	 * @param jsonName The filename of the created JSON file.
	 * @param name The registered name of the loot table.
	 * @param lootTable A list of all the loot table items that can be picked.
	 * @throws DataGenerationException If the chances do not add up to 100%.
	 */
	public void registerSingleSelect(
			String jsonName,
			String name,
			LootTableItem[] lootTable) throws DataGenerationException {
		JSONObject object = new JSONObject();
		object.put("name", name);
		
		JSONObject parentObject = new JSONObject();
		parentObject.put("chance", 1);
		
		float chanceSum = 0;
		for (LootTableItem item : lootTable) {
			chanceSum += item.chance;
			JSONObject itemObject = new JSONObject();
			itemObject.put("object", item.object);
			itemObject.put("chance", item.chance);
			itemObject.put("amount", item.amount);
			parentObject.append("loot", itemObject);
		}
		
		if (chanceSum != 1) throw new DataGenerationException("The sum of all chances in a single-select loot table " +
				"must be 1, this sum is " + chanceSum + ".");
		
		object.append("loot", parentObject);
		
		generatedJson.put(jsonName, object);
	}
	
	/**
	 * Registers a multi-select loot table to be generated.
	 * A multi-select loot table is one that has a percent chance of picking each option out of a list of options (with
	 * specified chances) and can pick as many as necessary.
	 * @param jsonName The filename of the created JSON file.
	 * @param name The registered name of the loot table.
	 * @param lootTable A list of all the loot table items that can be picked.
	 */
	public void registerMultiSelect(
			String jsonName,
			String name,
			LootTableItem[] lootTable) {
		JSONObject object = new JSONObject();
		object.put("name", name);
		
		for (LootTableItem item : lootTable) {
			JSONObject parentObject = new JSONObject();
			parentObject.put("chance", 1);
			
			JSONObject itemObject = new JSONObject();
			itemObject.put("object", item.object);
			itemObject.put("chance", item.chance);
			itemObject.put("amount", item.amount);
			parentObject.append("loot", itemObject);
			object.append("loot", parentObject);
		}
		
		generatedJson.put(jsonName, object);
	}
	
	/**
	 * Registers a multi-select loot table to be generated.
	 * A multi-select loot table is one that has a percent chance of picking each option out of a list of options (with
	 * specified chances) and can pick as many as necessary.
	 * @param jsonName The filename of the created JSON file.
	 * @param name The registered name of the loot table.
	 * @param lootTable A list of all the loot table items that can be picked.
	 * @param min The minimum amount of items that can be selected. Set as -1 if there is no minimum.
	 * @param max The maximum amount of items that can be selected. Set as -1 if there is no maximum.
	 */
	public void registerMultiSelect(
			String jsonName,
			String name,
			LootTableItem[] lootTable,
			int min,
			int max) {
		JSONObject object = new JSONObject();
		object.put("name", name);
		if (min != -1) object.put("min", min);
		if (max != -1) object.put("max", max);
		
		for (LootTableItem item : lootTable) {
			JSONObject parentObject = new JSONObject();
			parentObject.put("chance", 1);
			
			JSONObject itemObject = new JSONObject();
			itemObject.put("object", item.object);
			itemObject.put("chance", item.chance);
			itemObject.put("amount", item.amount);
			parentObject.append("loot", itemObject);
			object.append("loot", parentObject);
		}
		
		generatedJson.put(jsonName, object);
	}
	
	public abstract void registerAll() throws DataGenerationException;
	
	@Override
	public boolean verify(JSONObject object) {
		return true;
	}
}
