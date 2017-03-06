package main.engine;

import java.awt.Graphics2D;

public abstract class State implements IState {

	protected boolean exitGame = false;
	protected boolean nextStateQ = false;
	protected IState nextState = null;
	
	@Override
	abstract public void onEnter();

	@Override
	abstract public void onExit();

	@Override
	abstract public void init();

	@Override
	abstract public void update(int delta, InputHandler input);

	@Override
	abstract public void draw(Graphics2D g);

	@Override
	public boolean requestExitGame() {
		return exitGame;
	}

	@Override
	public boolean requestStateChange() {
		return nextStateQ;
	}

	@Override
	public IState getNextState() {
		return nextState;
	}
}
