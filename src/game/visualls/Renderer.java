package game.visualls;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.Globals;
import game.world.Chunk;
import game.world.Model;
import game.world.Tile;

public class Renderer {

	private Window window;

	public Renderer(Window window) {
		this.window = window;
	}

	public void update() {

		int width = 500;
		int height = 500;
		int xchunks = width / Globals.REAL_CHUNK_SIZE + 2;
		int ychunks = height / Globals.REAL_CHUNK_SIZE + 2;

		BufferedImage i = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g = (Graphics2D) i.getGraphics();

		g.setColor(Color.BLACK);

		g.setStroke(new BasicStroke(2));

		int x = this.window.camera.pos.chunkX * Globals.REAL_CHUNK_SIZE - this.window.camera.pos.realX;
		int y = this.window.camera.pos.chunkY * Globals.REAL_CHUNK_SIZE - this.window.camera.pos.realY;

		System.out.println(xchunks);

		for (int xx = 0; xx < xchunks; xx++) {
			for (int yy = 0; yy < ychunks; yy++) {

				int chunkOnCameraX = x + Globals.REAL_CHUNK_SIZE * xx;
				int chunkOnCameraY = y + Globals.REAL_CHUNK_SIZE * yy;

				Chunk chunk = this.window.world.getChunk(this.window.camera.pos.chunkX + xx,
						this.window.camera.pos.chunkY + yy);

				g.drawRect(chunkOnCameraX, chunkOnCameraY, Globals.REAL_CHUNK_SIZE, Globals.REAL_CHUNK_SIZE);

				System.out.println(chunk.position);

				for (Tile tile : chunk.tiles) {

					int tileOnCameraX = chunkOnCameraX + tile.position.tileX * Globals.TILE_SIZE;
					int tileOnCameraY = chunkOnCameraY + tile.position.tileY * Globals.TILE_SIZE;

					Model model = tile.getModel();

					g.drawImage(model.getImage(), tileOnCameraX, tileOnCameraY, tileOnCameraX + Globals.TILE_SIZE,
							tileOnCameraY + Globals.TILE_SIZE, 0, 0, model.getWidth(), model.getHeight(), null);
				}

			}
		}

		this.window.panel.setBackground(i);
	}

}