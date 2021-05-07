package game.visualls;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageLoader {

	private static HashMap<String, Image> images = new HashMap<>();

	public static void loadImages() {
		try {
			File mainDir = new File("src/res");
			for (File dir : mainDir.listFiles()) {
				for (File fileToImg : dir.listFiles()) {
					if (fileToImg.isDirectory()) {
						for (File dirToImg : fileToImg.listFiles()) {
							images.put(dir.getName() + ":" + fileToImg.getName() + dirToImg.getName().replace(".png", ""),
									ImageIO.read(dirToImg));
						}
					} else {
						System.out.println(fileToImg.getName().replace(".png", ""));
						images.put(dir.getName() + ":" + fileToImg.getName().replace(".png", ""), ImageIO.read(fileToImg));
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Image getImage(ImageType type, String name) {
		return images.get(type.toString() + ":" + name);
	}

}
