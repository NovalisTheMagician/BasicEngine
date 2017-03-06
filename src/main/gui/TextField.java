package main.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import main.engine.Engine;

public class TextField extends Container {

	private int x, y, width, height;
	private Color textCl, backCl, borCl;
	private Font font;
	private ArrayList<String> strings;
	
	public TextField(Engine e) {
		super(e);
		
		x = y = 0;
		width = 512;
		height = 512;
		
		textCl = new Color(0, 0, 0);
		backCl = new Color(255, 255, 255);
		borCl = new Color(128, 128, 128);
		
		font = new Font("Arial", Font.PLAIN, 12);
		
		strings = new ArrayList<String>();
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		g.setColor(backCl);
		g.fillRoundRect(x, y, width, height, 6, 6);
		
		g.setColor(borCl);
		g.drawRoundRect(x, y, width, height, 6, 6);
		
		FontMetrics fm = g.getFontMetrics(font);
		g.setFont(font);
		g.setColor(textCl);
		
		for(int i = 0; i < strings.size(); ++i)
		{
			Rectangle rect = fm.getStringBounds(strings.get(i), g).getBounds();
			
			g.drawString(strings.get(i), x + 5, y + (i * rect.height + 3) + (rect.height / 2) + 5);
		}
	}
	
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setBounds(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void setColors(Color background, Color border, Color text)
	{
		backCl = background;
		borCl = border;
		textCl = text;
	}
	
	public void setBackground(Color col)
	{
		backCl = col;
	}
	
	public void setFont(Font font)
	{
		this.font = font;
	}
	
	public void addText(String text)
	{
		strings.add(text);
		if(strings.size() > 33)
			strings.remove(0);
	}
	
	public void clear()
	{
		strings.clear();
	}
}
