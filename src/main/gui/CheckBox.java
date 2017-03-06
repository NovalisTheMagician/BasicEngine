package main.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import main.engine.Engine;

public class CheckBox extends Container
{	
	private int x, y, width;
	private boolean isChecked;
	private Color borCl;
	private Color backCl;
	private Color checkMarkCl;
	
	public CheckBox(Engine e) 
	{
		super(e);
		this.x = 0;
		this.y = 0;
		this.width = 16;
		this.isChecked = false;
		this.borCl = Color.ORANGE;
		this.backCl = new Color(32, 32, 32);
		this.checkMarkCl = Color.GREEN;
	}
	
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setBounds(int x, int y, int width) 
	{
		this.x = x;
		this.y = y;
		this.width = width;
	}
	
	public void setColors(Color background, Color border, Color checkMark)
	{
		this.backCl = background;
		this.borCl = border;
		this.checkMarkCl = checkMark;
	}
	
	public boolean isChecked() {
		return isChecked;
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		if((mx > x && mx < x + width) && (my > y && my < y + width))
		{
			if(e.getButton() == MouseEvent.BUTTON1 && !isChecked)
				isChecked = true;
			
			else if(e.getButton() == MouseEvent.BUTTON1 && isChecked)
				isChecked = false;
		}
	}
	
	public void draw(Graphics2D g) 
	{
		g.setColor(backCl);
		g.fillRoundRect(x, y, width, width, 4, 4);
		
		g.setColor(borCl);
		g.drawRoundRect(x, y, width, width, 4, 4);
		
		if(isChecked) 
		{
			g.setColor(checkMarkCl);
			g.drawLine(x, y, x + (width / 2), y + (width / 2));
			g.drawLine(x + (width / 2), y + (width / 2),  (int) (x + (width * 1.5)), y - (width / 2));
		}
	}
}
