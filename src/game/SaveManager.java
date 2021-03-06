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

import game.controls.KeyEventListener;
import game.objectSupers.Build;
import game.objectSupers.Entity;
import game.objectSupers.Tile;
import game.visualls.Window;
import game.world.Chunk;
import game.world.Position;
import game.world.World;

public class SaveManager {

	public static void saveGame(String name) {

		ArrayList<Entity> entitys = new ArrayList<>();
//TODO SAVE BUILDS
		for (Chunk chunk : World.chunks) {
			entitys.addAll(chunk.entitys);
		}

		File Dir = new File("src/saves/" + name);
		if (!Dir.exists()) {
			Dir.mkdir();
		}

		try {
			File registryFile = new File("src/saves/" + name + "/reg.dat");
			File settingsFile = new File("src/saves/" + name + "/settings.dat");
			File tileFile = new File("src/saves/" + name + "/tile.dat");
			File entityFile = new File("src/saves/" + name + "/entity.dat");
			File buildsFile = new File("src/saves/" + name + "/build.dat");
			entityFile.delete();
			entityFile.createNewFile();
			settingsFile.delete();
			settingsFile.createNewFile();
			buildsFile.delete();
			buildsFile.createNewFile();
			tileFile.delete();
			tileFile.createNewFile();
			registryFile.delete();
			registryFile.createNewFile();
			FileWriter entitysWriter = new FileWriter(entityFile);
			FileWriter settingsWriter = new FileWriter(settingsFile);
			FileWriter tileWriter = new FileWriter(tileFile);
			FileWriter registryWriter = new FileWriter(registryFile);
			FileWriter buildsWriter = new FileWriter(buildsFile);

			HashMap<String, String> registry = new HashMap<>();

			System.out.println("Saving Entitys");

			for (Entity entity : entitys) {
				registry.putIfAbsent(entity.getClass().getCanonicalName(), entity.getClass().getSimpleName());
				entitysWriter.write(entity.getClass().getSimpleName() + " " + entity.getPosition().realX + " "
						+ entity.getPosition().realY + " " + "\n");
			}
			entitysWriter.flush();
			entitysWriter.close();
			System.out.println("Saved " + entitys.size() + " Entitys");

			int buildCounter = 0;
			System.out.println("Saving Tiles and Builds");
			int tileRegCounter = 0;
			tileWriter.write(Globals.X_CHUNK_COUNT + " " + Globals.Y_CHUNK_COUNT + " " + Globals.CHUNK_SIZE + "\n");
			for (Chunk chunk : World.chunks) {
				String temp = "";
				for (Tile tile : chunk.tiles) {
					if (registry.putIfAbsent(tile.getClass().getCanonicalName(), tileRegCounter + "") == null)
						tileRegCounter++;
					temp = temp + " " + registry.get(tile.getClass().getCanonicalName()); // TODO Maybe add Tile
																							// metadata
					if (tile.hasBuild()) {
						Build build = tile.getBuild();
						registry.putIfAbsent(build.getClass().getCanonicalName(), build.getClass().getSimpleName());
						buildsWriter.write(build.getClass().getSimpleName() + " " + build.getPosition().realX + " "
								+ build.getPosition().realY + " " + "\n");
						buildCounter++;
					}

				}
				tileWriter.write(temp.trim() + "\n");
			}
			tileWriter.flush();
			tileWriter.close();
			buildsWriter.flush();
			buildsWriter.close();
			System.out.println(
					"Saved " + (Globals.X_CHUNK_COUNT * Globals.Y_CHUNK_COUNT * Globals.CHUNK_SIZE * Globals.CHUNK_SIZE)
							+ " Tiles and " + buildCounter + " Builds");

			System.out.println("Saving Registry");
			for (Entry<String, String> entry : registry.entrySet()) {
				registryWriter.write(entry.getKey() + " " + entry.getValue() + "\n");
			}
			registryWriter.flush();
			registryWriter.close();
			System.out.println("Saved " + registry.size() + " Registry entrys");
			System.out.println("Saved game to " + name);

			System.out.println("Saving Settings");
			settingsWriter.write(Window.camera.pos.realX + " " + Window.camera.pos.realY + "\n");
			settingsWriter.write(KeyEventListener.stringify() + "\n");
			System.out.println("Saved Settings");

			settingsWriter.flush();
			settingsWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void loadGame(String name) {
		File registryFile = new File("src/saves/" + name + "/reg.dat");
		File tileFile = new File("src/saves/" + name + "/tile.dat");
		File entityFile = new File("src/saves/" + name + "/entity.dat");
		File buildFile = new File("src/saves/" + name + "/build.dat");
		File settingsFile = new File("src/saves/" + name + "/settings.dat");

		try {
			BufferedReader registryReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(registryFile)));
			BufferedReader tileReader = new BufferedReader(new InputStreamReader(new FileInputStream(tileFile)));
			BufferedReader entityReader = new BufferedReader(new InputStreamReader(new FileInputStream(entityFile)));
			BufferedReader buildReader = new BufferedReader(new InputStreamReader(new FileInputStream(buildFile)));
			BufferedReader settingsReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(settingsFile)));

			HashMap<String, Constructor<?>> registry = new HashMap<>();
			String line;
			System.out.println("Loading Registry");
			while ((line = registryReader.readLine()) != null) {
				String[] data = line.split(" ");
				registry.put(data[1], Class.forName(data[0]).getConstructors()[0]);
			}
			System.out.println("Loaded " + registry.size() + " Registry entrys");

			System.out.println("Loaded Tiles and Builds");
			line = tileReader.readLine();// map size data (not used right now)
			int chunkCounter = 0;
			while ((line = tileReader.readLine()) != null) {
				String[] data = line.split(" ");
				for (int i = 0; i < data.length; i++) {
					Position posTemp = World.chunks[chunkCounter].tiles[i].getPosition();
					World.chunks[chunkCounter].tiles[i] = (Tile) registry.get(data[i]).newInstance();
					World.chunks[chunkCounter].tiles[i].setPosition(posTemp);
				}
				chunkCounter++;
			}

			int buildCounter = 0;

			while ((line = buildReader.readLine()) != null) {
				String[] data = line.split(" ");
				Build build = (Build) registry.get(data[0]).newInstance();
				Tile tile = World.getTile(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
				tile.setBuild(build);
				buildCounter++;
				// if (data.length > 3) meta
			}

			System.out.println("Loaded "
					+ (Globals.X_CHUNK_COUNT * Globals.Y_CHUNK_COUNT * Globals.CHUNK_SIZE * Globals.CHUNK_SIZE)
					+ " Tiles and " + buildCounter + " Builds");

			int entityCounter = 0;

			System.out.println("Loading Entitys");
			if (entityReader.ready())
				while ((line = entityReader.readLine()) != null) {
					String[] data = line.split(" ");
					if (data.length > 1) {
						@SuppressWarnings("unused")
						Entity entity = World.addEntity((Entity) registry.get(data[0]).newInstance(),
								Integer.parseInt(data[1]), Integer.parseInt(data[2]));
						entityCounter++;
						// if (data.length > 3) meta
					}
				}
			System.out.println("Loaded " + entityCounter + " Entitys");

			System.out.println("Loading Settigns");
			line = settingsReader.readLine();
			String[] lineparts = line.split(" ");
			Window.camera.pos.setPosition(Integer.parseInt(lineparts[0]), Integer.parseInt(lineparts[1]));
			line = settingsReader.readLine();
			KeyEventListener.loadBindings(line.trim());

			System.out.println("Loaded Settigns");

			settingsReader.close();
			buildReader.close();
			registryReader.close();
			tileReader.close();
			entityReader.close();

		} catch (IOException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
