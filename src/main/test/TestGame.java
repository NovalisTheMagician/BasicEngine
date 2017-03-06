package main.test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import main.engine.Animation;
import main.engine.Engine;
import main.engine.InputHandler;

import main.gui.Button;
import main.gui.ButtonEvent;
import main.gui.ButtonListener;
import main.gui.CheckBox;
import main.gui.Input;
import main.gui.Slider;

public class TestGame extends Engine implements ButtonListener
{
	private static final long serialVersionUID = -3260491160961723646L;
	
	private Button b;
	private Input i;
	private CheckBox c;
	
	private Slider s;
	
	private Animation anim;
	
	public TestGame(String title) 
	{
		super(title, 800, 600);
	}
	
	public TestGame(String title, int width, int height, boolean fullScreen)
	{
		super(title, width, height, 60, fullScreen);
	}
	
	@Override
	public void init()
	{
		anim = new Animation("res/wizard_stand.png", 4, 1);
		
		gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gd.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		gd.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		this.setClearColor(new Color(0x2da3ff));
		
		b = new Button(this);
		b.addListener(this);
		b.setPosition(100, 100);
		b.setText("Test");
		b.setKeyShortcut('G');
		
		i = new Input(this);
		i.setPosition(232, 100);
		i.setHint("WOW a textbox...");
		i.setMaxLength(13);
		
		c = new CheckBox(this);
		c.setPosition(100, 200);
		
		s = new Slider(this);
	}
	
	@Override
	public void update(float delta, InputHandler input)
	{
		if(input.isKeyDown(KeyEvent.VK_ESCAPE))
			exit();
		
		anim.update(delta);
	}
	
	@Override
	public void draw(Graphics2D g)
	{	
		s.draw(g);
		b.draw(g);
		i.draw(g);
		c.draw(g);
		
		anim.draw(g, 200, 100);
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		TestGame game = new TestGame("Hallo Welt");
		game.run();
		System.exit(0);
	}

	@Override
	public void onClick(ButtonEvent e)
	{
		System.out.println(i.getText());
	}
}
