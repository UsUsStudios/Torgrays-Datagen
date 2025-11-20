package com.ususstudios.torgrays_datagen;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * To generate data, you must extend this class like such:
 * <pre>
 * public class DataGenerator extends MainDataGenerator {
 *     &#064;Override
 *     public void registerAll() {
 *         // Run the register(Generator) function with an instance of each of the generator classes you created
 *     }
 * }
 * </pre>
 * You must also call MainDataGenerator.run() on an instance of the class to generate all data. This is usually done
 * in the main method.
 */
public abstract class MainDataGenerator {
	ArrayList<Generator> generators = new ArrayList<>();
	private void generate() throws DataGenerationException {
		for (Generator generator : generators) {
			switch (generator.name) {
				case "maps", "events", "loot_tables":
					generator.registerAll();
					for (Map.Entry<String, JSONObject> object : generator.generatedJson.entrySet()) {
						if (!generator.verify(object.getValue()))
							throw new DataGenerationException(
								"Invalid data for generator of type '" + generator.name + "': " +
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
	
	private void createFile(Generator generator, Map.Entry<String, JSONObject> object) throws DataGenerationException {
		try {
			File file = new File(System.getProperty("user.dir") + "/src/main/resources/values/" +
					generator.name, object.getKey() + ".json");
			File parentDir = file.getParentFile();
			if (parentDir != null && !parentDir.exists()) {
				if (!parentDir.mkdirs()) throw new DataGenerationException(
						"File made unsuccessfully: " + file.getAbsolutePath() + " (directory " + parentDir.getPath() +
								" unsuccessfully created.");
			}
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write(object.getValue().toString(2));
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public abstract void registerAll();
	
	/**
	 * Registers a generator to be generated once MainDataGenerator.run() is called.
	 * @param generator an instance of the generator you would like to register
	 */
	public void register(Generator generator) {
		generators.add(generator);
	}
	
	/**
	 * Registers all generators and then generates their data. Usually
	 * @throws DataGenerationException when a problem occurs with the data generation
	 */
	public void run() throws DataGenerationException {
		registerAll();
		generate();
	}
}
