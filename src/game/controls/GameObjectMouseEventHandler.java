package game.controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.Globals;
import game.objectSupers.Entity;
import game.objectSupers.GameObject;
import game.visualls.Renderer;
import game.visualls.UI;
import game.visualls.Window;
import game.world.Chunk;
import game.world.Position;
import game.world.World;

public class GameObjectMouseEventHandler implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!isOnUI(e)) {
			GameObject gm = getGameObject(e);
			if (gm != null)
				gm.onMouseClicked(e);
		}
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
		if (!isOnUI(e)) {
			GameObject gm = getGameObject(e);
			if (gm != null)
				gm.onMousePressed(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		UI ui = isOnUI(e);
		
			GameObject gm = getGameObject(e);
			if (gm != null)
				gm.onMouseReleased(e);
		}
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

		int xchunks = Window.camera.getWidth() / Globals.REAL_CHUNK_SIZE + 2;
		int ychunks = Window.camera.getHeight() / Globals.REAL_CHUNK_SIZE + 2;

		Position cameraPosition = Window.camera.pos;

		for (int x = 0; x < xchunks; x++) {
			for (int y = 0; y < ychunks; y++) {

				Chunk chunk = World.getChunk(cameraPosition.chunkX + x, cameraPosition.chunkY + y);

				for (int i = chunk.entitys.size() - 1; i < chunk.entitys.size(); i--) {
					Entity entity = chunk.entitys.get(i);
					Position entityPosition = entity.getPosition();
					
					int xOnCamera = entityPosition.realX-cameraPosition.realX;
					int yOnCamera = entityPosition.realY-cameraPosition.realY;
					int widthOnCamera = entity.getModel().getInGameWidth();
					int heightOnCamera = entity.getModel().getInGameHeight();
					
					if (e.getX() >= x && e.getY() >= y && e.getX() < x + width && e.getY() < y + height)

				}

			}
		}

	}

}
