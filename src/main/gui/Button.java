package main.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.engine.Engine;

public class Button extends Container
{

	private int x, y, width, height;
	private Color textCl, backCl, borCl;
	
	private Color hovTextCl, hovBackCl;
	private boolean isHover;
	
	private Font font;
	
	private String text;
	
	private ButtonListener listener;
	
	private int keyShortcut;
	
	public Button(Engine e) {
		super(e);
		
		x = y = 0;
		width = 128;
		height = 32;
		
		textCl = new Color(0, 0, 0);
		backCl = new Color(128, 128, 128);
		borCl = new Color(255, 255, 255);
		
		hovTextCl = new Color(128, 255, 128);
		hovBackCl = new Color(64, 64, 64);
		isHover = false;
		
		font = new Font("Arial", Font.PLAIN, 12);
		
		keyShortcut = -1;
		
		listener = null;
		
		text = "";
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		if(isHover)
			g.setColor(hovBackCl);
		else
			g.setColor(backCl);
		g.fillRoundRect(x, y, width, height, 6, 6);
		
		g.setColor(borCl);
		g.drawRoundRect(x, y, width, height, 6, 6);
		
		g.setFont(font);
		
		FontMetrics fm = g.getFontMetrics(font);
		int h = fm.getHeight();
		int w = fm.stringWidth(text);
		
		if(isHover)
			g.setColor(hovTextCl);
		else
			g.setColor(textCl);
		g.drawString(text, x - (w / 2) + (width / 2), y + (h / 2) + (height / 2));
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
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public void setKeyShortcut(int key)
	{
		keyShortcut = key;
	}
	
	public void removeKeyShortcut()
	{
		keyShortcut = -1;
	}

	public synchronized void addListener(ButtonListener listener)
	{
		this.listener = listener;
	}
	
	public synchronized void removeListener()
	{
		this.listener = null;
	}
	
	public synchronized void fireEvent()
	{
		if(listener == null)
			return;
		
		ButtonEvent e = new ButtonEvent(this);
		listener.onClick(e);
	}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		super.mouseMoved(e);
		
		if((mx > x && mx < x + width) && (my > y && my < y + height))
			isHover = true;
		else
			isHover = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		if((mx > x && mx < x + width) && (my > y && my < y + height))	
			if(e.getButton() == MouseEvent.BUTTON1 && e.getButton() != lastButton)
				fireEvent();
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == keyShortcut && !prevPress)
			fireEvent();
		
		super.keyPressed(e);
	}
}
