package main.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.engine.Engine;

public class Input extends Container
{

	private int x, y, width, height;
	private Color textCl, backCl, borCl;
	private Font font;
	
	private int maxLength;
	
	private String text;
	
	private String hint;
	
	private boolean isFocus;
	
	public Input(Engine e)
	{
		super(e);
		
		x = y = 0;
		width = 128;
		height = 32;
		
		textCl = new Color(0, 0, 0);
		backCl = new Color(255, 255, 255);
		borCl = new Color(88, 88, 88);
		
		font = new Font("Arial", Font.PLAIN, 12);
		
		maxLength = 14;
		
		text = "";
		
		hint = "Write a text here...";
		
		isFocus = false;
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
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public String getText()
	{
		return text;
	}
	
	public void setHint(String hint)
	{
		this.hint = hint;
	}
	
	public void setFont(Font font)
	{
		this.font = font;
	}
	
	public void setMaxLength(int length)
	{
		this.maxLength = length;
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		g.setColor(backCl);
		g.fillRoundRect(x, y, width, height, 6, 6);
		
		g.setColor(borCl);
		g.drawRoundRect(x, y, width, height, 6, 6);
		
		FontMetrics fm = g.getFontMetrics(font);
		int h = fm.getHeight();
		
		String t = text;
		if(t.isEmpty() && !isFocus)
		{
			t = hint;
			g.setColor(new Color(188, 188, 188));
		}
		else if(isFocus)
		{
			t += '_';
			g.setColor(textCl);
		}
		else
			g.setColor(textCl);
		
		g.setFont(font);
		g.drawString(t, x + 4, y + (h / 2) + (height / 2));
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(!isFocus)
			return;
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			return;
		
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
		{
			if(!text.isEmpty())
				text = text.substring(0, text.length() - 1);
		}
		else if(e.getKeyChar() != KeyEvent.CHAR_UNDEFINED)
			if(text.length() < maxLength)
				text += e.getKeyChar();
		
		super.keyPressed(e);
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		super.mousePressed(e);
		
		if((mx > x && mx < x + width) && (my > y && my < y + height))
		{
			if(e.getButton() == MouseEvent.BUTTON1)
				isFocus = true;
		}
		else if(e.getButton() == MouseEvent.BUTTON1)
			isFocus = false;
	}
}
