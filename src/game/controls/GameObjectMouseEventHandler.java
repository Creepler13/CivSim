package game.controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.Globals;
import game.objectSupers.Entity;
import game.objectSupers.GameObject;
import game.objectSupers.Tile;
import game.visualls.Renderer;
import game.visualls.Window;
import game.visualls.ui.uiComponents.UI;
import game.visualls.ui.uiComponents.UIComponent;
import game.world.Chunk;
import game.world.Position;
import game.world.World;

public class GameObjectMouseEventHandler implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		UI ui = isOnUI(e);
		if (ui != null) {
			getUIComponent(ui, e).onMouseClicked(e);
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
			getUIComponent(ui, e).onMousePressed(e);
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
			getUIComponent(ui, e).onMouseReleased(e);
			return;
		}
		GameObject gm = getGameObject(e);
		if (gm != null)
			gm.onMouseClicked(e);

		CameraControlls.onMouseRelease(e);
	}

	public static UI isOnUI(MouseEvent e) {

		for (UI ui : Renderer.openUIs) {
			int x = ui.getRealX();
			int y = ui.getRealY();
			int width = ui.getWidth();
			int height = ui.getHeight();

			if (e.getX() >= x && e.getY() >= y && e.getX() < x + width && e.getY() < y + height)
				return ui;
		}
		return null;
	}

	public static UIComponent getUIComponent(UIComponent ui, MouseEvent e) {

		for (UIComponent cC : ui.getChildComponents()) {
			int x = cC.getRealX();
			int y = cC.getRealY();
			int width = (cC.getWidth());
			int height = (cC.getHeight());

			if (e.getX() >= x && e.getX() < x + width && e.getY() >= y && e.getY() < y + height) {
				return getUIComponent(cC, e);
			}

		}
		return ui;
	}

	private GameObject getGameObject(MouseEvent e) {

		Position cameraPosition = Window.camera.pos;

		Chunk chunk = World.getChunk(
				(int) ((cameraPosition.realX + e.getX() * Window.camera.zoom) / Globals.REAL_CHUNK_SIZE),
				(int) ((cameraPosition.realY + e.getY() * Window.camera.zoom) / Globals.REAL_CHUNK_SIZE));

		for (int i = chunk.entitys.size() - 1; i > -1; i--) {
			Entity entity = chunk.entitys.get(i);
			Position entityPosition = entity.getPosition();

			int xOnCamera = (int) ((entityPosition.realX - cameraPosition.realX) * Window.camera.zoom);
			int yOnCamera = (int) ((entityPosition.realY - cameraPosition.realY) * Window.camera.zoom);
			int widthOnCamera = (int) (entity.getModel().getInGameWidth() * Window.camera.zoom);
			int heightOnCamera = (int) (entity.getModel().getInGameHeight() * Window.camera.zoom);

			if (e.getX() >= xOnCamera && e.getY() >= yOnCamera && e.getX() < xOnCamera + widthOnCamera
					&& e.getY() < yOnCamera + heightOnCamera) {
				return entity;
			}

		}

		Tile t = World.getTile((int) ((cameraPosition.realX + e.getX() * Window.camera.zoom)),
				(int) ((cameraPosition.realY + e.getY() * Window.camera.zoom)));

		return t;

	}

}
