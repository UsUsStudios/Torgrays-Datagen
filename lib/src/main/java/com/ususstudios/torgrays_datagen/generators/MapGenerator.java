package com.ususstudios.torgrays_datagen.generators;

import com.ususstudios.torgrays_datagen.Generator;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class MapGenerator extends Generator {
	public MapGenerator() {super("maps");}
	
	public void register(
			String jsonName,
			String[] map,
	        String name,
	        int numberKey,
	        String music,
	        int spawnX, int spawnY,
	        int lightRadius,
	        boolean blueEffect,
			Entity[] objects,
			Entity[] npcs,
			Entity[] monsters) {
		JSONObject object = new JSONObject();
		object.put("map", map);
		object.put("name", name);
		object.put("numberKey", numberKey);
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
					if (entity.lootTable() != null) put("loot table", entity.path());
				}});
			}
			put("objects", objectsList);
			
			ArrayList<HashMap<String, Object>> npcsList = new ArrayList<>();
			for (Entity entity : npcs) {
				npcsList.add(new HashMap<>(){{
					put("path", entity.path());
					put("col", entity.col());
					put("row", entity.row());
					if (entity.lootTable() != null) put("loot table", entity.path());
				}});
			}
			put("npcs", npcsList);
			
			ArrayList<HashMap<String, Object>> monstersList = new ArrayList<>();
			for (Entity entity : monsters) {
				monstersList.add(new HashMap<>(){{
					put("path", entity.path());
					put("col", entity.col());
					put("row", entity.row());
					if (entity.lootTable() != null) put("loot table", entity.path());
				}});
			}
			put("monsters", monstersList);
		}});
		
		generatedJson.put(jsonName, object);
	}
	
	public abstract void registerAll();
	
	@Override
	public boolean verify(JSONObject object) {
		return true;
	}
}
