package main.engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Util {

	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void drawFont(Graphics2D g, Font font, Color color, String str, float x, float y) {
		g.setFont(font);
		g.setColor(color);
		g.drawString(str, x, y);
	}
}
