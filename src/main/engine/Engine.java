package main.engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.KeyboardFocusManager;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public abstract class Engine extends JFrame {

	protected int windowWidth, windowHeight;
	private String title;
	private boolean isRunning = true;
	protected Graphics2D gd;
	
	private Canvas drawableSurface;
	
	private boolean fullScreen = false;
	private boolean prevFullScreen;

	private Color clearColor = Color.BLACK;

	private float delta = 1.f / 60.f;

	private BufferedImage backBuffer = null;
	
	public Engine() {
		this.title = "A Game";
		this.windowWidth = 640;
		this.windowHeight = 420;
    
		this.setupWindow(this.title, this.windowWidth, this.windowHeight);
	}

	public Engine(String title, int width, int height) {
		this.title = title;
		this.windowWidth = width;
		this.windowHeight = height;
    
		this.setupWindow(this.title, this.windowWidth, this.windowHeight);
	}
	
	public Engine(String title, int width, int height, boolean undec) {
		this.title = title;
		this.windowWidth = width;
		this.windowHeight = height;
    
		this.setUndecorated(undec);
		
		this.setupWindow(this.title, this.windowWidth, this.windowHeight);
	}
	
	public Engine(String title, int width, int height, boolean undec, boolean fullscreenSpec) {
		this.title = title;
		this.windowWidth = width;
		this.windowHeight = height;
    
		this.setUndecorated(undec);
		
		if(fullscreenSpec)
		{
			this.setLocation(0, 0);
			//this.setAlwaysOnTop(true);
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
		
		this.setupWindow(this.title, this.windowWidth, this.windowHeight);
	}

	public Engine(String title, int width, int height, int fps) {
		this.title = title;
		this.windowWidth = width;
		this.windowHeight = height;
    
		this.setupWindow(this.title, this.windowWidth, this.windowHeight);
	}
	
	public Engine(String title, int width, int height, int fps, boolean fullScreen) {
		this.title = title;
		this.windowWidth = width;
		this.windowHeight = height;
		
		this.fullScreen = fullScreen;
    
		this.setupWindow(this.title, this.windowWidth, this.windowHeight);
	}

	private void setupWindow(String title, int width, int height) {
		this.setTitle(title);
		
		drawableSurface = new Canvas();
		drawableSurface.setPreferredSize(new Dimension(width, height));
		
		KeyboardFocusManager kbfmgr = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		kbfmgr.addKeyEventDispatcher(new Keyboard());
		
		Container cp = this.getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(drawableSurface);
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
    
		backBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public final void run() throws InterruptedException {
		
		InputHandler input = new InputHandler(this);
		input.setMouseListener(drawableSurface);
    
		gd = (Graphics2D)drawableSurface.getGraphics();
		
		this.init();
		
		if(fullScreen)
			this.changeScreenMode(true);
		
		float currentTime = (float)((float)System.nanoTime() / 1e9);
		float accumulator = 0;
		
		float newTime = 0;
		float frameTime = 0;
		
		while(isRunning)
		{

			newTime = (float)((float)System.nanoTime() / 1e9);
			frameTime = newTime - currentTime;
			
			if(frameTime > 0.25f)
				frameTime = 0.25f;
			
			currentTime = newTime;
			
			accumulator += frameTime;
			
			while(accumulator >= delta)
			{
				update(delta, input);
				accumulator -= delta;
			}
			
			drawBackBuffer(gd);
			
			Thread.sleep(1);
		}
		
		this.changeScreenMode(false);
	}

	private void drawBackBuffer(Graphics2D g) {
		Graphics2D g2 = g;
		
		g2 = backBuffer.createGraphics();
    
		g2.setColor(clearColor);
		g2.fillRect(0, 0, windowWidth, windowHeight);
    
		this.draw(g2);
    
		g2.dispose();
		
		g.drawImage(backBuffer, 0, 0, null);
	}

	abstract protected void init();

	abstract protected void update(float delta, InputHandler input);

	abstract protected void draw(Graphics2D g);

	protected void exit() {
		this.isRunning = false;
	}
	
	public Component getSurface()
	{
		return drawableSurface;
	}

	protected void setClearColor(Color newColor) {
		this.clearColor = newColor;
	}
	
	protected void setCursorVisible(boolean vis) {
		if(!vis)
			this.setCursor(java.awt.Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(1,1,BufferedImage.TYPE_4BYTE_ABGR), new java.awt.Point(0,0), "NOCURSOR"));
		else
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	protected void changeScreenMode(boolean fullScreen) {
		if(fullScreen == prevFullScreen)
			return;
		
		prevFullScreen = fullScreen;
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device;
		device = ge.getDefaultScreenDevice();
		
		this.fullScreen = fullScreen;
		
		if(fullScreen) {
			if(device.isFullScreenSupported()) {
				
				device.setFullScreenWindow(this);
				
				if(device.isDisplayChangeSupported()) {
					
					DisplayMode[] displayMode = device.getDisplayModes();
					
					for(int i = 0; i < displayMode.length; i++) {
						if(displayMode[i].getWidth() == windowWidth && displayMode[i].getHeight() == windowHeight) {
							device.setDisplayMode(displayMode[i]);
							return;
						}
					}
					System.out.println("Couldn't find a suited DisplayMode!");
				}
			}
		}
		
		device.setFullScreenWindow(null);
	}
}
