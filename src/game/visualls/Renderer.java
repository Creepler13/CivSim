package game.visualls;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.Globals;
import game.objectSupers.Entity;
import game.objectSupers.Model;
import game.objectSupers.Tile;
import game.visualls.ui.uiComponents.UI;
import game.visualls.ui.uiComponents.UIComponent;
import game.world.Chunk;
import game.world.World;

public class Renderer {

	public static void update() {
		BufferedImage i = new BufferedImage(Window.camera.getWidth(), Window.camera.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) i.getGraphics();

		renderGameObjects(g);
		renderUI(g);

		Window.panel.setBackground(i);
	}

	private static void renderGameObjects(Graphics2D g) {

		int width = Window.camera.getWidth();
		int height = Window.camera.getHeight();
		int xchunks = width / Globals.REAL_CHUNK_SIZE + 2;
		int ychunks = height / Globals.REAL_CHUNK_SIZE + 2;

		g.setColor(Color.BLACK);

		g.setStroke(new BasicStroke(2));

		int x = Window.camera.pos.chunkX * Globals.REAL_CHUNK_SIZE - Window.camera.pos.realX;
		int y = Window.camera.pos.chunkY * Globals.REAL_CHUNK_SIZE - Window.camera.pos.realY;

		for (int xx = 0; xx < xchunks; xx++) {
			for (int yy = 0; yy < ychunks; yy++) {

				int chunkOnCameraX = x + Globals.REAL_CHUNK_SIZE * xx;
				int chunkOnCameraY = y + Globals.REAL_CHUNK_SIZE * yy;

				Chunk chunk = World.getChunk(Window.camera.pos.chunkX + xx, Window.camera.pos.chunkY + yy);

				g.drawRect(chunkOnCameraX, chunkOnCameraY, Globals.REAL_CHUNK_SIZE, Globals.REAL_CHUNK_SIZE);

				for (Tile tile : chunk.tiles) {
					int tileOnCameraX = chunkOnCameraX + tile.getPosition().tileX * Globals.TILE_SIZE;
					int tileOnCameraY = chunkOnCameraY + tile.getPosition().tileY * Globals.TILE_SIZE;

					Model model = tile.getModel();

					g.drawImage(model.getImage(), tileOnCameraX, tileOnCameraY, tileOnCameraX + Globals.TILE_SIZE,
							tileOnCameraY + Globals.TILE_SIZE, 0, 0, model.getWidth(), model.getHeight(), null);
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

					g.drawImage(model.getImage(), entityOnCameraX + model.getXOffset(),
							entityOnCameraY + model.getYOffset(), entityOnCameraX + model.getInGameWidth(),
							entityOnCameraY + model.getInGameHeight(), 0, 0, model.getWidth(), model.getHeight(), null);
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

	private static void renderUI(Graphics2D g) {

		for (UI ui : openUIs) {

			int uiX = (int) (ui.getX() * Window.camera.zoom);
			int uiY = (int) (ui.getY() * Window.camera.zoom);
			int uiWidth = (int) (ui.getWidth() * Window.camera.zoom);
			int uiHeigth = (int) (ui.getHeight() * Window.camera.zoom);

			g.drawImage(ui.getBackground(), uiX, uiY, uiWidth, uiHeigth, 0, 0, ui.getResourceWidth(),
					ui.getResourceHeight(), null);

			for (UIComponent component : ui.getAllChildComponents()) {

				int uicompX = (int) (component.getX() * Window.camera.zoom);
				int uicompY = (int) (component.getY() * Window.camera.zoom);
				int uicompWidth = (int) (component.getWidth() * Window.camera.zoom);
				int uicompHeigth = (int) (component.getHeight() * Window.camera.zoom);

				g.drawImage(component.getBackground(), uiX + uicompX, uiY + uicompY, uicompWidth, uicompHeigth, 0, 0,
						component.getResourceWidth(), component.getResourceHeight(), null);
			}
		}

		// Render Debug Overlay

		g.drawImage(debugI, 0, 0, Window.panel.getWidth(), Window.panel.getHeight(), 0, 0, debugI.getWidth(),
				debugI.getHeight(), null);

	}

	private static BufferedImage debugI = new BufferedImage(Window.camera.getWidth(), Window.camera.getHeight(),
			BufferedImage.TYPE_INT_ARGB);
	private static Graphics2D debugG2d = (Graphics2D) debugI.getGraphics();

	public static Graphics2D getDebugGraphics() {
		return debugG2d;
	}

	public static void resetDebugGraphics() {
		debugI = new BufferedImage(Window.camera.getWidth(), Window.camera.getHeight(), BufferedImage.TYPE_INT_ARGB);
		debugG2d = (Graphics2D) debugI.getGraphics();
	}

	public static int scaleToWindow(int i) {
		return (int) (i * Window.panel.scale);
	}

}
