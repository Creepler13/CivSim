package game.visualls.ui;

import java.awt.Image;

import game.objectSupers.Entity;
import game.registrys.ImageRegistry;
import game.visualls.ImageType;
import game.visualls.Renderer;
import game.visualls.UI;
import game.visualls.Window;

public class EntityDataUI extends UI {

	@Override
	public Image getUI() {
		return ImageRegistry.getImage(ImageType.TILE, "ground");
	}

	public EntityDataUI(Entity entity) {
		this.entity = entity;
	}

	private Entity entity;

	@Override
	public int getWidth() {

		return Renderer.scaleToWindow(16);
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return Renderer.scaleToWindow(16);
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return Renderer.scaleToWindow(entity.getPosition().realX - Window.camera.pos.realX);
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return Renderer.scaleToWindow(entity.getPosition().realY - Window.camera.pos.realY);
	}

}
