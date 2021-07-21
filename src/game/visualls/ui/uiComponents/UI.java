package game.visualls.ui.uiComponents;

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

}
