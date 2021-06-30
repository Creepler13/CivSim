package game.visualls.ui.uiComponents;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class UIComponent {

	private ArrayList<UIComponent> childComponents = new ArrayList<>();

	public abstract Image getBackground();

	public void addComponent(UIComponent component) {
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

	public abstract int getX();

	public abstract int getY();

	public abstract int getWidth();

	public abstract int getHeight();

	public abstract int getResourceWidth();

	public abstract int getResourceHeight();

	public void onMouseClicked(MouseEvent e, UIComponent comp) {

	}

	public void onMousePressed(MouseEvent e, UIComponent comp) {

	}

	public void onMouseReleased(MouseEvent e, UIComponent comp) {

	}

	public void onMouseEntered(MouseEvent e, UIComponent comp) {

	}

	public void onMouseExited(MouseEvent e, UIComponent comp) {

	}

	public void onUIClosed(UI ui) {

	}

	public void onUIOpened(UI ui) {

	}

}
