package game.controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.Globals;
import game.objectSupers.Entity;
import game.objectSupers.GameObject;
import game.objectSupers.Tile;
import game.visualls.Renderer;
import game.visualls.UI;
import game.visualls.Window;
import game.world.Chunk;
import game.world.Position;
import game.world.World;

public class GameObjectMouseEventHandler implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		UI ui = isOnUI(e);
		if (ui != null) {
			ui.onMouseClicked(e);
			return;
		}
		GameObject gm = getGameObject(e);
		if (gm != null)
			gm.onMouseClicked(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// has to be done in mousemoved evetn
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// has to be done in mousemoved evetn
	}

	@Override
	public void mousePressed(MouseEvent e) {
		UI ui = isOnUI(e);
		if (ui != null) {
			ui.onMousePressed(e);
			return;
		}
		GameObject gm = getGameObject(e);
		if (gm != null)
			gm.onMousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		UI ui = isOnUI(e);
		if (ui != null) {
			ui.onMouseReleased(e);
			return;
		}
		GameObject gm = getGameObject(e);
		if (gm != null)
			gm.onMouseClicked(e);
	}

	private UI isOnUI(MouseEvent e) {
		for (UI ui : Renderer.openUIs) {
			int x = ui.getX();
			int y = ui.getY();
			int width = ui.getWidth();
			int height = ui.getHeight();
			if (e.getX() >= x && e.getY() >= y && e.getX() < x + width && e.getY() < y + height)
				return ui;
		}
		return null;
	}

	private GameObject getGameObject(MouseEvent e) {

		Position cameraPosition = Window.camera.pos;

		Chunk chunk = World.getChunk(
				(int) (cameraPosition.chunkX + e.getX() / Window.panel.scale / Globals.REAL_CHUNK_SIZE),
				(int) (cameraPosition.chunkY + e.getY() / Window.panel.scale / Globals.REAL_CHUNK_SIZE));

		for (int i = chunk.entitys.size() - 1; i > -1; i--) {
			Entity entity = chunk.entitys.get(i);
			Position entityPosition = entity.getPosition();

			int xOnCamera = (int) ((entityPosition.realX - cameraPosition.realX) * Window.panel.scale);
			int yOnCamera = (int) ((entityPosition.realY - cameraPosition.realY) * Window.panel.scale);
			int widthOnCamera = (int) (entity.getModel().getInGameWidth() * Window.panel.scale);
			int heightOnCamera = (int) (entity.getModel().getInGameHeight() * Window.panel.scale);
			// scaled
			// bacause of
			// jBackgroundPanel and zoom its complicated;

			if (e.getX() >= xOnCamera && e.getY() >= yOnCamera && e.getX() < xOnCamera + widthOnCamera
					&& e.getY() < yOnCamera + heightOnCamera) {
				return entity;
			}

		}

		for (Tile tile : chunk.tiles) {
			int xOnCamera = (int) ((tile.getPosition().realX - cameraPosition.realX) * Window.panel.scale);
			int yOnCamera = (int) ((tile.getPosition().realY - cameraPosition.realY) * Window.panel.scale);
			int widthHeigthOnCamera = (int) (Globals.TILE_SIZE * Window.panel.scale);

			if (e.getX() >= xOnCamera && e.getY() >= yOnCamera && e.getX() < xOnCamera + widthHeigthOnCamera
					&& e.getY() < yOnCamera + widthHeigthOnCamera) {
				return tile;
			}

		}

		return null;

	}

}
