package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import game.objectSupers.Entity;
import game.objectSupers.Tile;
import game.world.Chunk;
import game.world.Position;

public class SaveManager {

	public static void saveGame(String name) {

		ArrayList<Entity> entitys = new ArrayList<>();
//TODO SAVE BUILDS
		for (Chunk chunk : Main.world.chunks) {
			entitys.addAll(chunk.entitys);
		}

		File Dir = new File("src/saves/" + name);
		if (!Dir.exists()) {
			Dir.mkdir();
		}

		try {
			File registryFile = new File("src/saves/" + name + "/reg.dat");
			File tileFile = new File("src/saves/" + name + "/tile.dat");
			File entityFile = new File("src/saves/" + name + "/entity.dat");
			entityFile.createNewFile();
			entityFile.delete();
			tileFile.createNewFile();
			tileFile.delete();
			registryFile.createNewFile();
			registryFile.delete();
			FileWriter entitysWriter = new FileWriter(entityFile);
			FileWriter tileWriter = new FileWriter(tileFile);
			FileWriter registryWriter = new FileWriter(registryFile);

			HashMap<String, String> registry = new HashMap<String, String>();
		
			System.out.println("Saving Entitys");
			
			for (Entity entity : entitys) {
				registry.putIfAbsent(entity.getClass().getCanonicalName(), entity.getClass().getSimpleName());
				entitysWriter.write(entity.getClass().getSimpleName() + " " + entity.getPosition().realX + " "
						+ entity.getPosition().realY + " " + entity.metadata.toString() + "\n");
			}
			entitysWriter.flush();
			entitysWriter.close();
			System.out.println("Saved " + entitys.size() + " Entitys");

			System.out.println("Saving Tiles");
			int tileRegCounter = 0;
			tileWriter.write(Globals.X_CHUNK_COUNT + " " + Globals.Y_CHUNK_COUNT + " " + Globals.CHUNK_SIZE + "\n");
			for (Chunk chunk : Main.world.chunks) {
				String temp = "";
				for (Tile tile : chunk.tiles) {
					if (registry.putIfAbsent(tile.getClass().getCanonicalName(), tileRegCounter + "") == null)
						tileRegCounter++;
					temp = temp + " " + registry.get(tile.getClass().getCanonicalName()); // TODO Maybe add Tile
																							// metadata
				}
				tileWriter.write(temp.trim() + "\n");
			}
			tileWriter.flush();
			tileWriter.close();
			System.out.println(
					"Saved " + (Globals.X_CHUNK_COUNT * Globals.Y_CHUNK_COUNT * Globals.CHUNK_SIZE * Globals.CHUNK_SIZE)
							+ " Tiles");

			System.out.println("Saving Registry");
			for (Entry<String, String> entry : registry.entrySet()) {
				registryWriter.write(entry.getKey() + " " + entry.getValue() + "\n");
			}
			registryWriter.flush();
			registryWriter.close();
			System.out.println("Saved " + registry.size() + " Registry entrys");
			System.out.println("Saved game to " + name);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void loadGame(String name) {
		File registryFile = new File("src/saves/" + name + "/reg.dat");
		File tileFile = new File("src/saves/" + name + "/tile.dat");
		File entityFile = new File("src/saves/" + name + "/entity.dat");
		try {
			BufferedReader registryReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(registryFile)));
			BufferedReader tileReader = new BufferedReader(new InputStreamReader(new FileInputStream(tileFile)));
			BufferedReader entityReader = new BufferedReader(new InputStreamReader(new FileInputStream(entityFile)));

			HashMap<String, Constructor<?>> registry = new HashMap<String, Constructor<?>>();
			String line;
			System.out.println("Loading Registry");
			while ((line = registryReader.readLine()) != null) {
				String[] data = line.split(" ");
				registry.put(data[1], Class.forName(data[0]).getConstructors()[0]);
			}
			System.out.println("Loaded " + registry.size() + " Registry entrys");

			System.out.println("Loaded Tiles");
			line = tileReader.readLine();// map size data (not used right now)
			int chunkCounter = 0;
			while ((line = tileReader.readLine()) != null) {
				String[] data = line.split(" ");
				for (int i = 0; i < data.length; i++) {
					Position posTemp = Main.world.chunks[chunkCounter].tiles[i].getPosition();
					Main.world.chunks[chunkCounter].tiles[i] = (Tile) registry.get(data[i]).newInstance();
					Main.world.chunks[chunkCounter].tiles[i].setPosition(posTemp);
				}
				chunkCounter++;
			}
			System.out.println("Loaded "
					+ (Globals.X_CHUNK_COUNT * Globals.Y_CHUNK_COUNT * Globals.CHUNK_SIZE * Globals.CHUNK_SIZE)
					+ " Tiles");

			System.out.println("Loading Entitys");
			while ((line = entityReader.readLine()) != null) {
				String[] data = line.split(" ");
				Entity entity = Main.world.addEntity((Entity) registry.get(data[0]).newInstance(),
						Integer.parseInt(data[1]), Integer.parseInt(data[2]));
				if (data.length > 3)
					entity.metadata = new JSON(data[3]);
			}
			System.out.println("Loaded Entitys");

			registryReader.close();
			tileReader.close();
			entityReader.close();

		} catch (IOException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
