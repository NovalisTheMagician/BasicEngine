package main.test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import main.engine.Engine;
import main.engine.InputHandler;
import main.engine.Keyboard;

public class FullscreenTest extends Engine 
{

	private static final long serialVersionUID = 1L;

	public FullscreenTest()
	{
		super("FullscreenTest", 1920, 1080, true, true);
	}
	
	@Override
	protected void init() 
	{
		setClearColor(new Color(0xff0000));
	}

	@Override
	protected void update(float delta, InputHandler input)
	{
		if(Keyboard.getState().isKeyDown(KeyEvent.VK_ESCAPE))
			exit();
		
		if(input.isKeyDown('E'))
			exit();
	}

	@Override
	protected void draw(Graphics2D g) 
	{

	}

	public static void main(String[] args) throws InterruptedException
	{
		new FullscreenTest().run();
	}

}
