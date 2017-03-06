package main.gui;

import java.util.EventObject;

public class ButtonEvent extends EventObject
{
	private static final long serialVersionUID = 42L;
	
	public ButtonEvent(Object source)
	{
		super(source);
	}
}
