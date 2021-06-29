package game.visualls.ui.uiComponents;

import java.util.ArrayList;

import game.visualls.Renderer;

public abstract class UI extends UIComponent {

	public void open() {
		Renderer.openUI(this);
	}

	public void close() {
		Renderer.closeUI(this);
	}

	public int getUILayer() {
		return Renderer.openUIs.indexOf(this);
	}

	private ArrayList<UIComponent> childComponents = new ArrayList<>();

	public ArrayList<UIComponent> getAllChildComponents() {
		ArrayList<UIComponent> temp = new ArrayList<>();
		for (UIComponent uiComponent : childComponents) {
			temp.add(uiComponent);
			temp.addAll(uiComponent.getAllChildComponents());
		}
		return temp;
	}

	public void addComponent(UIComponent component) {
		childComponents.add(component);
	}

	public void removeComponent(UIComponent component) {
		childComponents.remove(component);
	}

	public ArrayList<UIComponent> getChildComponents() {
		return childComponents;
	}

}
