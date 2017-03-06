package main.gui;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.engine.Engine;

public class Container implements KeyListener, MouseListener, MouseMotionListener
{

	protected int mx, my;
	protected int lastButton;
	protected int lastKey, curKey;
	protected boolean prevPress;
	
	public Container(Engine e)
	{
		Component ck, cm;
		
		ck = e;
		cm = e.getSurface();
		
		ck.addKeyListener(this);
		cm.addMouseListener(this);
		cm.addMouseMotionListener(this);
		
		mx = my = 0;
		
		lastKey = curKey = -1;
		prevPress = false;
	}
	
	public void draw(Graphics2D g)
	{
		
	}
	
	public void mouseDragged(MouseEvent e)
	{
		
	}

	public void mouseMoved(MouseEvent e) 
	{
		mx = e.getX();
		my = e.getY();
	}

	public void mouseClicked(MouseEvent e) 
	{
		
	}

	public void mouseEntered(MouseEvent e)
	{
		
	}

	public void mouseExited(MouseEvent e) 
	{
		
	}

	public void mousePressed(MouseEvent e)
	{
		lastButton = e.getButton();
	}

	public void mouseReleased(MouseEvent e)
	{
		lastButton = -1;
	}

	public void keyPressed(KeyEvent e)
	{
		lastKey = curKey;
		prevPress = true;
		curKey = e.getKeyCode();
	}

	public void keyReleased(KeyEvent e) 
	{
		lastKey = -1;
		prevPress = false;
		//lastKey = e.getKeyCode();
	}

	public void keyTyped(KeyEvent e)
	{
		
	}
	
}
