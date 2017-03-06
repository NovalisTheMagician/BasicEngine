package main.engine;

import java.awt.Graphics2D;

public interface IState {

	public void onEnter();
	public void onExit();
	
	public void init();
	public void update(int delta, InputHandler input);
	public void draw(Graphics2D g);
	
	public boolean requestExitGame();
	public boolean requestStateChange();
	public IState getNextState();
	
}
