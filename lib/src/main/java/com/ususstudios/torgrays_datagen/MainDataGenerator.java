package com.ususstudios.torgrays_datagen;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public abstract class MainDataGenerator {
	ArrayList<Generator> generators = new ArrayList<>();
	public void generate() throws DataGenerationException {
		for (Generator generator : generators) {
			switch (generator.name) {
				case "maps", "events", "loot tables":
					for (Map.Entry<String, JSONObject> object : generator.objects.entrySet()) {
						if (!generator.verify(object.getValue()))
							throw new DataGenerationException(
								"Invalid data for generator1 of type '" + generator.name + "': " +
								object.getValue().toString(2) + " This is likely not your fault, " +
								"please make an issue about this on GitHub.");
						createFile(generator, object);
					}
					break;
				default:
					throw new DataGenerationException("Invalid generator1 type: '" + generator.name + "' (valid: " +
							"'maps', 'events', 'loot_tables'). This is likely not your fault, please make an issue " +
							"about this on GitHub.");
			}
		}
	}
	
	public void createFile(Generator generator, Map.Entry<String, JSONObject> object) throws DataGenerationException {
		try {
			File file = new File(System.getProperty("user.dir") + "/src/main/resources/values/" +
					generator.name, object.getKey() + ".json");
			File parentDir = file.getParentFile();
			if (parentDir != null && !parentDir.exists()) {
				if (!parentDir.mkdirs()) throw new DataGenerationException(
						"File made unsuccessfully: " + file.getAbsolutePath() + " (directory " + parentDir.getPath() +
								" unsuccessfully created.");
			}
			var _ = file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write(object.getValue().toString(2));
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public abstract void registerAll();
	
	public void register(Generator generator) {
		generators.add(generator);
	}
	
	public void run() throws DataGenerationException {
		registerAll();
		generate();
	}
}
