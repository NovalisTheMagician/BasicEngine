package main.engine;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Sprite
{
	
	private BufferedImage image = null;
	
	public Sprite(String ref) 
	{
		image = loadImg(ref);
	}
	
	private BufferedImage loadImg(String ref)
	{
		try 
		{
			return ImageIO.read(new File(ref));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public void draw(Graphics2D g)
	{
		draw(g, 0, 0);
	}
	
	public void draw(Graphics2D g, float x, float y)
	{
		
		AffineTransform world = new AffineTransform();
		AffineTransform trans = new AffineTransform();
		
		trans.translate(x, y);
		
		world.concatenate(trans);
		
		g.drawImage(image, world, null);
	}
	
	public void draw(Graphics2D g, float x, float y, float angle)
	{
		AffineTransform world = new AffineTransform();
		AffineTransform trans = new AffineTransform();
		AffineTransform rot = new AffineTransform();
		
		trans.translate(x, y);
		rot.rotate(angle, (x + (image.getWidth()) / 2), (y + (image.getHeight()) / 2));
		
		world.concatenate(rot);
		world.concatenate(trans);
		
		g.drawImage(image, world, null);
	}
	
	public void draw(Graphics2D g, float x, float y, float scaleX, float scaleY, float angle)
	{
		AffineTransform world = new AffineTransform();
		AffineTransform trans = new AffineTransform();
		AffineTransform scale = new AffineTransform();
		AffineTransform rot = new AffineTransform();
		
		trans.translate(x, y);
		scale.scale(scaleX, scaleY);
		rot.rotate(angle * (Math.PI / 180), (x + (image.getWidth()) / 2) * scaleX, (y + (image.getHeight()) / 2) * scaleY);
		
		world.concatenate(rot);
		world.concatenate(scale);
		world.concatenate(trans);
		
		g.drawImage(image, world, null);
	}
	
	public BufferedImage getImage()
	{
		return image;
	}
}
