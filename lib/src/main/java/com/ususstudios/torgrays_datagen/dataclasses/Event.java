package com.ususstudios.torgrays_datagen.dataclasses;

import java.util.HashMap;

/**
 * A record for events, used in the map generator.
 * @param path The path to the event class.
 * @param col The column of the event.
 * @param row The row of the event.
 * @param parameters The parameters of the event.
 */
public record Event(String path, int col, int row, HashMap<String, Object> parameters) {
	/**
	 * A record for events, used in the map generator.
	 * @param eventClass The event class.
	 * @param col The column of the event.
	 * @param row The row of the event.
	 * @param parameters The parameters of the event.
	 */
	public Event(Class<?> eventClass, int col, int row, HashMap<String, Object> parameters) {
		this(eventClass.getName(), col, row, parameters);
	}
	
	/**
	 * A record for events, used in the map generator.
	 * @param eventClass The event class.
	 * @param col The column of the event.
	 * @param row The row of the event.
	 */
	public Event(Class<?> eventClass, int col, int row) {
		this(eventClass.getName(), col, row, null);
	}
	
	/**
	 * A record for events, used in the map generator.
	 * @param path The path to the event class.
	 * @param col The column of the event.
	 * @param row The row of the event.
	 */
	public Event(String path, int col, int row) {
		this(path, col, row, null);
	}
}
