package game.registrys;

import java.io.File;

public class ObjectRegistry {

	public static void loadObjects() {
		File dir = new File("src/game/objects");
		for (File file : dir.listFiles()) {
		}
	}

}
