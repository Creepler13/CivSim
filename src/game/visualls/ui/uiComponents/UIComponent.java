package game.visualls.ui.uiComponents;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class UIComponent {

	private ArrayList<UIComponent> childComponents = new ArrayList<>();

	public void addComponent(UIComponent component, int x, int y) {
		component.setParent(this);
		component.setPosition(x, y);
		childComponents.add(component);
	}

	public void removeComponent(UIComponent component) {
		childComponents.remove(component);
	}

	public ArrayList<UIComponent> getChildComponents() {
		return childComponents;
	}

	public ArrayList<UIComponent> getAllChildComponents() {
		ArrayList<UIComponent> temp = new ArrayList<>();
		for (UIComponent uiComponent : childComponents) {
			temp.add(uiComponent);
			temp.addAll(uiComponent.getAllChildComponents());
		}
		return temp;
	}

	public UIComponent parent;

	private void setParent(UIComponent comp) {
		this.parent = comp;
	}

	public ArrayList<UIComponent> getAllParentComponents() {
		ArrayList<UIComponent> temp = new ArrayList<>();
		if (this.parent != null) {
			temp.add(this.parent);
			temp.addAll(this.parent.getAllParentComponents());
		}
		return temp;
	}

	private int x, y, realX, realY, width, heigth, resourceWidth, resourceHeight;
	private BufferedImage image;

	public int getX() {
		return x;
	};

	public int getY() {
		return y;
	};

	public int getRealX() {
		return realX;
	};

	public int getRealY() {
		return realY;
	};

	public int getWidth() {
		return width;
	};

	public int getHeight() {
		return heigth;
	};

	public int getResourceWidth() {
		return resourceWidth;
	}

	public int getResourceHeight() {
		return resourceHeight;
	}

	public void setImage(BufferedImage image, int resourceWidth, int resourceHeight) {
		this.image = image;
		this.resourceHeight = resourceHeight;
		this.resourceWidth = resourceWidth;
	}

	public void setSize(int width, int heigth) {
		this.width = width;
		this.heigth = heigth;
	}

	public BufferedImage getImage() {
		return image;
	};

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;

		this.realX = x;
		this.realY = y;
		for (UIComponent uiComponent : getAllParentComponents()) {
			this.realX = this.realX + uiComponent.getX();
			this.realY = this.realY + uiComponent.getY();
		}

	}

	public void onMouseClicked(MouseEvent e) {

	}

	public void onMousePressed(MouseEvent e) {

	}

	public void onMouseReleased(MouseEvent e) {

	}

	public void onMouseEntered(MouseEvent e) {

	}

	public void onMouseExited(MouseEvent e) {

	}

	public void mouseWheelMoved(MouseEvent e) {

	}

	public void onUIClosed(UI ui) {

	}

	public void onUIOpened(UI ui) {

	}

}
