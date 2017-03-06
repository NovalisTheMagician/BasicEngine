package main.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class Label 
{
	private int x, y, width, height;
	private String text;
	private Color backCl;
	private Color textCl;
	private Color borCl;
	private Font font;
	
	public Label(String text)
	{
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.text = text;
		this.backCl = Color.BLACK;
		this.textCl = Color.RED;
		this.borCl = new Color(128, 128, 128);
		this.font = new Font("Arial", Font.PLAIN, 12);
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
	
	public void setColors(Color background, Color text, Color border)
	{
		this.backCl = background;
		this.textCl = text;
		this.borCl = border;
	}
	
	public void setFont(Font font)
	{
		this.font = font;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public void draw(Graphics2D g)
	{
		FontMetrics fm = g.getFontMetrics(font);
		g.setFont(font);
		g.setColor(backCl);
		g.fillRoundRect(x, y, width, height, 8, 8);
		
		g.setColor(borCl);
		g.drawRoundRect(x, y, width, height, 8, 8);
		
		g.setColor(textCl);
		g.drawString(text, (x + (width / 2)) - (fm.stringWidth(text) / 2), (y + (height / 2)) + (font.getSize() / 2));
	}
}
