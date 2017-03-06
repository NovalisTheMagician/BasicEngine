package main.test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Renderer
{
	public class PointLight
	{
		public int x, y;
		public int color;
		public float radius;
		public float intensity;
	}
	
	public class SpotLight
	{
		public int x, y;
		public float length;
		public float theta;
	}
	
	public class PixelShader extends Thread
	{
		private int width, height;
		private BufferedImage color, light, comb;
		private int ambient;
		
		public PixelShader(int width, int height, BufferedImage color, BufferedImage light, BufferedImage comb, int ambient)
		{
			this.width = width;
			this.height = height;
			this.color = color;
			this.light = light;
			this.comb = comb;
			this.ambient = ambient;
		}
		
		public void run()
		{
			int colorRGB;
			int lightRGB;
			int newColor;
			
			for(int x = 0; x < width; ++x)
			{
				for(int y = 0; y < height; ++y)
				{
					colorRGB = color.getRGB(x, y);
					lightRGB = light.getRGB(x, y);
					
					newColor = ambient + (colorRGB * lightRGB);
					
					comb.setRGB(x, y, newColor);
				}
			}
		}
	}
	
	public static final int MAX_POINTLIGHTS = 255;
	public static final int MAX_SPOTLIGHTS = 255;
	public static final int MAX_LIGHTS = MAX_POINTLIGHTS + MAX_SPOTLIGHTS;
	
	private volatile BufferedImage colorRT, lightRT, combineRT;
	private Graphics2D colorG, lightG, combineG;
	
	private int width, height;
	
	private int ambientColor;
	
	private PointLight[] pointLights;
	private int numPointLights;
	
	private SpotLight[] spotLights;
	private int numSpotLights;
	
	public Renderer(int width, int height)
	{
		colorRT = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		lightRT = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		combineRT = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		colorG = (Graphics2D)colorRT.createGraphics();
		lightG = (Graphics2D)lightRT.createGraphics();
		combineG = (Graphics2D)combineRT.createGraphics();
		
		ambientColor = 0xffffffff;
		
		pointLights = new PointLight[MAX_POINTLIGHTS];
		numPointLights = 0;
		
		spotLights = new SpotLight[MAX_SPOTLIGHTS];
		numSpotLights = 0;
		
		this.width = width;
		this.height = height;
	}
	
	public void Clear()
	{
		colorG.setColor(new Color(0x000000));
		colorG.fillRect(0, 0, width, height);
		
		lightG.setColor(new Color(0x000000));
		lightG.fillRect(0, 0, width, height);
		
		combineG.setColor(new Color(0x000000));
		combineG.fillRect(0, 0, width, height);
	}
	
	public Graphics2D GetGraphics()
	{
		return colorG;
	}
	
	public void DrawLights()
	{
		if(numPointLights > 0)
			DrawPointLights();
		
		if(numSpotLights > 0)
			DrawSpotLights();
		
		/*
		int colorRGB;
		int lightRGB;
		int newColor;
		
		for(int x = 0; x < width; ++x)
		{
			for(int y = 0; y < height; ++y)
			{
				colorRGB = colorRT.getRGB(x, y);
				lightRGB = lightRT.getRGB(x, y);
				
				newColor = ambientColor + (colorRGB * lightRGB);
				
				combineRT.setRGB(x, y, newColor);
			}
		}
		*/
		
		new PixelShader(width, height, colorRT, lightRT, combineRT, ambientColor).start();
	}
	
	private void DrawPointLights()
	{
		for(int i = 0; i < numPointLights; ++i)
		{
			drawCircle(lightG, pointLights[i].x, pointLights[i].y, pointLights[i].radius, new Color(pointLights[i].color));
		}
	}
	
	private void DrawSpotLights()
	{
		
	}
	
	public void Render(Graphics2D device)
	{
		device.drawImage(combineRT, 0, 0, null);
	}
	
	public void SetAmbientColor(int ambientColor)
	{
		this.ambientColor = ambientColor;
	}
	
	public PointLight CreatePointLight()
	{
		if(numPointLights == MAX_POINTLIGHTS - 1)
			return null;
		
		PointLight pl = new PointLight();
		pointLights[numPointLights++] = pl;
		return pl;
	}
	
	public SpotLight CreateSpotLight()
	{
		if(numSpotLights == MAX_SPOTLIGHTS - 1)
			return null;
		
		SpotLight sl = new SpotLight();
		spotLights[numSpotLights++] = sl;
		return sl;
	}
	
	public void SaveRenderTargets()
	{
		try
		{
			ImageIO.write(colorRT, "png", new File("color.png"));
			ImageIO.write(lightRT, "png", new File("light.png"));
			ImageIO.write(combineRT, "png", new File("combine.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void Begin()
	{
		colorG = (Graphics2D)colorRT.createGraphics();
		lightG = (Graphics2D)lightRT.createGraphics();
		combineG = (Graphics2D)combineRT.createGraphics();
	}
	
	public void End()
	{
		colorG.dispose();
		lightG.dispose();
		combineG.dispose();
	}
	
	private void drawCircle(Graphics2D g, int x, int y, float radius, Color color)
	{
		final int NUMPOINTS = 50;
		
		int[] xs = new int[NUMPOINTS], ys = new int[NUMPOINTS];
		
		int _x, _y;
		float theta;
		float wegdeAngle = (float)((2.0 * Math.PI) / NUMPOINTS);
		
		for(int i = 0; i < NUMPOINTS; ++i)
		{
			theta = i * wegdeAngle;
			
			_x = (int)(x + radius * Math.cos(theta));
			_y = (int)(y - radius * Math.sin(theta));
			
			xs[i] = _x;
			ys[i] = _y;
		}
		
		g.setColor(color);
		g.fillPolygon(xs, ys, NUMPOINTS);
	}
}
