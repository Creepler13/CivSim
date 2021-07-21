package game.registrys;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import game.visualls.ImageType;

public class ImageRegistry {

	private static HashMap<String, BufferedImage> images = new HashMap<>();

	public static void loadImages() {
		try {
			File mainDir = new File("src/res");
			for (File dir : mainDir.listFiles()) {
				for (File fileToImg : dir.listFiles()) {
					if (fileToImg.isDirectory()) {
						for (File dirToImg : fileToImg.listFiles()) {
							images.put(dir.getName() + ":" + fileToImg.getName() + "/"
									+ dirToImg.getName().replace(".png", ""), ImageIO.read(dirToImg));
						}
					} else {
						images.put(dir.getName() + ":" + fileToImg.getName().replace(".png", ""),
								ImageIO.read(fileToImg));
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

	public static BufferedImage getImage(ImageType type, String dir, String name) {
		return images.get(type.toString() + ":" + dir + "/" + name);
	}

	public static String listImages() {
		String s = "";
		for (String key : images.keySet()) {
			s = s + key + ".png\n";
		}
		return s;
	}

}
