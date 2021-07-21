package game.visualls.ui.uiComponents;

import java.awt.event.MouseEvent;

public class ScrollPanel extends UIComponent {

	@Override
	public void onMouseWheelMoved(MouseEvent e) {

	}

	private int innerWidth = 0, innerHeight = 0;

	@Override
	public void onComponentAdded(UIComponent component) {
		setInnerSize();
	}

	@Override
	public void onComponentRemoved(UIComponent component) {
		setInnerSize();
	}

	private void setInnerSize() {
		for (UIComponent comp : getChildComponents()) {
			if (comp.getX() + comp.getWidth() > innerWidth)
				innerWidth = comp.getX() + comp.getWidth();
			if (comp.getY() + comp.getHeight() > innerHeight)
				innerHeight = comp.getY() + comp.getHeight();
		}
	}

}
