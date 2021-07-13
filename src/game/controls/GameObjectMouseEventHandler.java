package game.controls;

import java.awt.Graphics2D;
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
			ui.onMouseClicked(e, getUIComponent(ui, e));
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
			ui.onMousePressed(e, getUIComponent(ui, e));
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
			ui.onMouseReleased(e, getUIComponent(ui, e));
			return;
		}
		GameObject gm = getGameObject(e);
		if (gm != null)
			gm.onMouseClicked(e);

		CameraControlls.onMouseRelease(e);
	}

	private UI isOnUI(MouseEvent e) {
		Renderer.resetDebugGraphics();
		Graphics2D g = Renderer.getDebugGraphics();

		for (UI ui : Renderer.openUIs) {
			int x = (int) (ui.getX() * Window.camera.zoom);
			int y = (int) (ui.getY() * Window.camera.zoom);
			int width = (int) (ui.getWidth() * Window.camera.zoom);
			int height = (int) (ui.getHeight() * Window.camera.zoom);

			g.drawRect(x, y, width, height);

			if (e.getX() >= x && e.getY() >= y && e.getX() < x + width && e.getY() < y + height)
				return ui;
		}
		return null;
	}

	private UIComponent getUIComponent(UIComponent ui, MouseEvent e) {

		for (UIComponent cC : ui.getChildComponents()) {
			int x = (int) ((cC.getX() + ui.getX()) * Window.camera.zoom);
			int y = (int) ((cC.getY() + ui.getY()) * Window.camera.zoom);
			int width = (int) ((cC.getWidth()) * Window.camera.zoom);
			int height = (int) ((cC.getHeight()) * Window.camera.zoom);

			if (e.getX() >= x && e.getX() < x + width && e.getY() >= y && e.getY() < y + height) {
				System.exit(0);
				return getUIComponent(ui, e);
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
