package game.controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMoved implements MouseMotionListener {

	public MouseMoved() {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		CameraControlls.onDrag(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

}
