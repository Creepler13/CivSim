package game;

public class Globals {

	public static final int TPS = 10;
	public static final int FPS = 60;

	public static final int X_CHUNK_COUNT = 100;
	public static final int Y_CHUNK_COUNT = 100;

	public static final int CHUNK_SIZE = 8;
	public static final int TILE_SIZE = 16;
	public static final int REAL_CHUNK_SIZE = CHUNK_SIZE * TILE_SIZE;

	public static final int REAL_MAP_WIDTH = X_CHUNK_COUNT * REAL_CHUNK_SIZE;
	public static final int REAL_MAP_HEIGHT = Y_CHUNK_COUNT * REAL_CHUNK_SIZE;

}
