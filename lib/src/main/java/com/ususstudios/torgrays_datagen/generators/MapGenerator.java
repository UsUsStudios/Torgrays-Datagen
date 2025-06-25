package com.ususstudios.torgrays_datagen.generators;

import com.ususstudios.torgrays_datagen.Generator;
import com.ususstudios.torgrays_datagen.dataclasses.Entity;
import com.ususstudios.torgrays_datagen.dataclasses.Event;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class you can extend to register maps to generate.
 */
public abstract class MapGenerator extends Generator {
	public MapGenerator() {super("maps");}
	
	/**
	 * Registers a new map to be generated.
	 * @param jsonName The filename of the created JSON file.
	 * @param ground An array of rows for the ground of the map. Each row is a series of numbers that correspond to
	 *                  tile IDs, split by spaces.
	 * @param foreground An array of rows for the foreground of the map. Each row is a series of numbers that correspond
	 *                     to tile IDs, split by spaces.
	 * @param name The registered name of the map.
	 * @param music The name of the music that is to be played in this map. Set to {@code Default} to have the
	 *                 regular gloom cycle music.
	 * @param spawnX The column where the player should spawn when entering the map.
	 * @param spawnY The row where the player should spawn when entering the map.
	 * @param lightRadius The radius of vision of the player in this map.
	 * @param blueEffect Whether the map should display a blue effect.
	 * @param objects A list of Entities that will be the objects in this map.
	 * @param npcs A list of Entities that will be the NPCs in this map.
	 * @param monsters A list of Entities that will be the monsters in this map.
	 */
	public void register(
			String jsonName,
			String[] ground,
			String[] foreground,
	        String name,
	        String music,
	        int spawnX, int spawnY,
	        int lightRadius,
	        boolean blueEffect,
			Entity[] objects,
			Entity[] npcs,
			Entity[] monsters,
			Entity[] items,
			Event[] events) {
		JSONObject object = new JSONObject();
		object.put("map", new HashMap<>(){{
			put("ground", ground);
			put("foreground", foreground);
		}});
		object.put("name", name);
		object.put("music", music);
		object.put("spawn point", new HashMap<>(){{
			put("col", spawnX);
			put("row", spawnY);
		}});
		if (lightRadius != -1) object.put("light radius", lightRadius);
		object.put("blue effect", blueEffect);
		object.put("entities", new HashMap<>(){{
			ArrayList<HashMap<String, Object>> objectsList = new ArrayList<>();
			for (Entity entity : objects) {
				objectsList.add(new HashMap<>(){{
					put("path", entity.path());
					put("col", entity.col());
					put("row", entity.row());
					if (entity.lootTable() != null) put("loot table", entity.lootTable());
				}});
			}
			put("objects", objectsList);
			
			ArrayList<HashMap<String, Object>> npcsList = new ArrayList<>();
			for (Entity entity : npcs) {
				npcsList.add(new HashMap<>(){{
					put("path", entity.path());
					put("col", entity.col());
					put("row", entity.row());
					if (entity.lootTable() != null) put("loot table", entity.lootTable());
				}});
			}
			put("npcs", npcsList);
			
			ArrayList<HashMap<String, Object>> monstersList = new ArrayList<>();
			for (Entity entity : monsters) {
				monstersList.add(new HashMap<>(){{
					put("path", entity.path());
					put("col", entity.col());
					put("row", entity.row());
					if (entity.lootTable() != null) put("loot table", entity.lootTable());
				}});
			}
			put("monsters", monstersList);
			
			ArrayList<HashMap<String, Object>> itemsList = new ArrayList<>();
			for (Entity entity : items) {
				itemsList.add(new HashMap<>(){{
					put("path", entity.path());
					put("col", entity.col());
					put("row", entity.row());
					if (entity.lootTable() != null) put("loot table", entity.lootTable());
				}});
			}
			put("items", itemsList);
		}});
		
		ArrayList<HashMap<String, Object>> eventsList = new ArrayList<>();
		for (Event event : events) {
			eventsList.add(new HashMap<>(){{
				put("path", event.path());
				put("col", event.col());
				put("row", event.row());
				if (event.parameters() != null) {
					put("parameters", event.parameters());
				}
			}});
		}
		object.put("events", eventsList);
		
		generatedJson.put(jsonName, object);
	}
	
	@Override
	public boolean verify(JSONObject object) {
		return true;
	}
}
