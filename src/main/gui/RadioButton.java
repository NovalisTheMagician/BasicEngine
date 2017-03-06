package main.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import main.engine.Engine;

public class RadioButton extends Container
{
	private int x, y, width, height, index;
	private Color backCl;
	private Color borCl;
	private boolean[] isVisible;
	
	public RadioButton(Engine e, int index) 
	{
		super(e);
		this.x = 0;
		this.y = 0;
		this.width = 64;
		this.height = 64;
		this.index = index;
		this.backCl = Color.GRAY;
		this.borCl = Color.BLACK;
		this.isVisible = new boolean[index];
		for(int i = 0; i < isVisible.length; i++)
		{
			isVisible[i] = false;
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
	
	public void setColor(Color background, Color border)
	{
		this.backCl = background;
		this.borCl = border;
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		for(int i = 0; i < index; i++) 
		{
			for(int j = i; j < index; j++) 
			{
				if(isVisible[j])
					isVisible[i] = false;
			}
			
			if((mx > ((x + (width / 2)) - 6) && mx < ((((x + (width / 2)) - 6)) + width) - 30) && (my > (((y - 16) + 32 * (i + 1))) && my < ((y - 16) + 32 * (i + 1)) + height - 5))
			{
				if(e.getButton() == MouseEvent.BUTTON1 && !isVisible[i])
					isVisible[i] = true;
				else if(e.getButton() == MouseEvent.BUTTON1 && isVisible[i])
					isVisible[i] = false;
			}
		}
		
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		g.setColor(backCl);
		g.fillRoundRect(x, y, width, height + (32 * (index - 1)), 8, 8);
		
		g.setColor(borCl);
		for(int i = 0; i < index; i++) 
		{
			g.drawOval((x + (width / 2)) - 8, ((y - 16) + 32 * (i + 1)), 16, 16);
			
			if(isVisible[i])
				g.fillOval((x + (width / 2)) - 6, ((y - 16) + 32 * (i + 1)) + 2, 12, 12);			
		}
	}
	
}
