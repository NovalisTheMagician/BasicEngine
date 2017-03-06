package main.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import main.engine.Engine;

public class Slider extends Container
{
	private int x, y, width, height;
	private boolean move;
	
	public Slider(Engine e) 
	{
		super(e);
		x = 60;
		y = 40;
		width = 10;
		height = 20;
		move = false;
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		if((mx > x && mx < x + width) && (my > y && my < y + height) && e.getButton() == MouseEvent.BUTTON1 && lastButton != MouseEvent.BUTTON1 && !move)
		{
			move = true;
		} 
		else if(move && e.getButton() == MouseEvent.BUTTON1)
		{
			move = false;
		}
		else
			move = false;
	}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		super.mouseMoved(e);
		
		if(move)
		{		
			x = mx - 7;
			if(x < 60) {
				x = 60;
			} 
			
			if(x > 530) {
				x = 530;
			}	
		}
	}
	
	public void draw(Graphics2D g) 
	{		
		g.setColor(Color.WHITE);
		g.drawLine(50, 50, 550, 50);
		
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
		g.setFont(new Font("serial", Font.ITALIC, 15));
		g.drawString("min", 15, 55);
		g.drawString("max", 565, 55);
		
		g.setColor(new Color(100,149,237));
		g.fillOval(300 - (x / 2), 350 - (x / 2), x, x);
		
	}
}
