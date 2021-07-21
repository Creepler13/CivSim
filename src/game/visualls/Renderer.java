package game.visualls;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import game.Globals;
import game.objectSupers.Entity;
import game.objectSupers.Model;
import game.objectSupers.Tile;
import game.registrys.ImageRegistry;
import game.visualls.ui.uiComponents.UI;
import game.visualls.ui.uiComponents.UIComponent;
import game.world.Chunk;
import game.world.World;

public class Renderer {

	public static BufferedImage mainI, gameI, uiI, debugI;
	public static Graphics2D mainG, gameG, uiG, debugG;

	public static void update() {

		resetImages();

		renderGameObjects();
		renderUI();

		drawImage(mainG, gameI, 0, 0, mainI.getWidth(), mainI.getHeight(), 0, 0, gameI.getWidth(), gameI.getHeight());

		drawImage(mainG, uiI, 0, 0, mainI.getWidth(), mainI.getHeight(), 0, 0, uiI.getWidth(), uiI.getHeight());
		drawImage(mainG, debugI, 0, 0, mainI.getWidth(), mainI.getHeight(), 0, 0, debugI.getWidth(),
				debugI.getHeight());

		Window.panel.setBackground(mainI);
	}

	public static void init() {
		resetImages();
		resetDebugGraphics();
	}

	private static void resetImages() {
		mainI = new BufferedImage(Window.panel.getWidth(), Window.panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
		mainG = (Graphics2D) mainI.getGraphics();
		gameI = new BufferedImage(Window.camera.getWidth(), Window.camera.getHeight(), BufferedImage.TYPE_INT_ARGB);
		gameG = (Graphics2D) gameI.getGraphics();
		uiI = new BufferedImage(Window.panel.getWidth(), Window.panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
		uiG = (Graphics2D) uiI.getGraphics();
	}

	private static void renderGameObjects() {

		int width = Window.camera.getWidth();
		int height = Window.camera.getHeight();
		int xchunks = width / Globals.REAL_CHUNK_SIZE + 2;
		int ychunks = height / Globals.REAL_CHUNK_SIZE + 2;

		gameG.setColor(Color.BLACK);

		gameG.setStroke(new BasicStroke(2));

		int x = Window.camera.pos.chunkX * Globals.REAL_CHUNK_SIZE - Window.camera.pos.realX;
		int y = Window.camera.pos.chunkY * Globals.REAL_CHUNK_SIZE - Window.camera.pos.realY;

		for (int xx = 0; xx < xchunks; xx++) {
			for (int yy = 0; yy < ychunks; yy++) {

				int chunkOnCameraX = x + Globals.REAL_CHUNK_SIZE * xx;
				int chunkOnCameraY = y + Globals.REAL_CHUNK_SIZE * yy;

				Chunk chunk = World.getChunk(Window.camera.pos.chunkX + xx, Window.camera.pos.chunkY + yy);

				gameG.drawRect(chunkOnCameraX, chunkOnCameraY, Globals.REAL_CHUNK_SIZE, Globals.REAL_CHUNK_SIZE);

				for (Tile tile : chunk.tiles) {
					int tileOnCameraX = chunkOnCameraX + tile.getPosition().tileX * Globals.TILE_SIZE;
					int tileOnCameraY = chunkOnCameraY + tile.getPosition().tileY * Globals.TILE_SIZE;

					Model model = tile.getModel();

					drawImage(gameG, model.getImage(), tileOnCameraX, tileOnCameraY, tileOnCameraX + Globals.TILE_SIZE,
							tileOnCameraY + Globals.TILE_SIZE, 0, 0, model.getWidth(), model.getHeight());
				}

			}

		}

		for (int xx = 0; xx < xchunks; xx++) {
			for (int yy = 0; yy < ychunks; yy++) {

				Chunk chunk = World.getChunk(Window.camera.pos.chunkX + xx, Window.camera.pos.chunkY + yy);

				for (Entity entity : chunk.entitys) {

					Model model = entity.getModel();
					if (model == null)
						continue;

					int entityOnCameraX = entity.getPosition().realX - Window.camera.pos.realX;
					int entityOnCameraY = entity.getPosition().realY - Window.camera.pos.realY;

					drawImage(gameG, model.getImage(), entityOnCameraX + model.getXOffset(),
							entityOnCameraY + model.getYOffset(), entityOnCameraX + model.getInGameWidth(),
							entityOnCameraY + model.getInGameHeight(), 0, 0, model.getWidth(), model.getHeight());
				}
			}
		}

	}

	public static ArrayList<UI> openUIs = new ArrayList<>();

	public static UI openUI(UI ui) {
		openUIs.add(ui);
		for (UIComponent uicomp : ui.getAllChildComponents()) {
			uicomp.onUIOpened(ui);
		}
		ui.onUIOpened(ui);
		return ui;
	}

	public static UI closeUI(UI ui) {
		openUIs.remove(ui);
		for (UIComponent uicomp : ui.getAllChildComponents()) {
			uicomp.onUIClosed(ui);
		}
		ui.onUIClosed(ui);
		return ui;
	}

	private static void renderUI() {

		for (UI ui : openUIs) {

			int uiX = ui.getX();
			int uiY = ui.getY();
			int uiWidth = ui.getWidth();
			int uiHeigth = ui.getHeight();

			drawImage(uiG, ui.getImage(), uiX, uiY, uiWidth, uiHeigth, 0, 0, ui.getResourceWidth(),
					ui.getResourceHeight());

			for (UIComponent component : ui.getAllChildComponents()) {

				int uicompWidth = component.getWidth();
				int uicompHeigth = component.getHeight();

				drawImage(uiG, component.getImage(), component.getRealX(), component.getRealY(), uicompWidth,
						uicompHeigth, 0, 0, component.getResourceWidth(), component.getResourceHeight());
			}
		}

	}

	public static Graphics2D getDebugGraphics() {
		return debugG;
	}

	public static void resetDebugGraphics() {
		debugI = new BufferedImage(Window.panel.getWidth(), Window.panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
		debugG = (Graphics2D) debugI.getGraphics();
	}

	public static int scaleToWindow(int i) {
		return (int) (i * Window.panel.scale);
	}

	private static void drawImage(Graphics2D g, Image img, int d1, int d2, int d3, int d4, int s1, int s2, int s3,
			int s4) {
		if (img == null) {
			img = ImageRegistry.getImage(ImageType.TILE, "missing");
			s3 = 64;
			s4 = 64;
		}
		g.drawImage(img, d1, d2, d3, d4, s1, s2, s3, s4, null);
	}

	public static void ImageToFile(BufferedImage img, String fileane) {
		File outputfile = new File("src/screenshots/" + fileane + ".png");
		try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
