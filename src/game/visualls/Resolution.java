package game.visualls;

public enum Resolution {

	R960x540(960, 540), R1920x1080(1920, 1080);

	public int width, height;

	private Resolution(int width, int height) {
		this.height = height;
		this.width = width;
	}
}
