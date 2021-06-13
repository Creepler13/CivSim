package game.controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMoved implements MouseMotionListener {

	public CameraControlls CC;
	public Controlls C;

	public MouseMoved(CameraControlls CC, Controlls C) {
		this.CC = CC;
		this.C = C;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.CC.onDrag(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.C.onMouseMoved(e);
	}

}
