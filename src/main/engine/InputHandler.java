package main.engine;

import java.awt.Component;
import java.awt.event.*;
import javax.swing.event.*;

public class InputHandler implements KeyListener, MouseInputListener, MouseWheelListener
{
  private boolean[] keys = new boolean[256];
  private boolean[] buttons = new boolean[5];
  
  private int mouseX;
  private int mouseY;
  
  private int wheelMovement;

  public InputHandler(Component c) {
	  c.addKeyListener(this);
	  //c.addMouseListener(this);
	  //c.addMouseMotionListener(this);
	  //c.addMouseWheelListener(this);
  }
  
  public void setMouseListener(Component c)
  {
	  c.addMouseListener(this);
	  c.addMouseMotionListener(this);
	  c.addMouseWheelListener(this);
  }
  
  public boolean isKeyDown(int keyCode) {
	  if (keyCode > 0 && keyCode < 256)
		  return keys[keyCode];

	  return false;
  }
  
  public boolean isKeyUp(int keyCode) {
	  if (keyCode > 0 && keyCode < 256)
		  return !keys[keyCode];

	  return false;
  }
  
  public boolean isButtonDown(int buttonCode) {
	  if (buttonCode > 0 && buttonCode < 4)
		  return buttons[buttonCode];
    
	  return false;
  }
  
  public boolean isButtonUp(int buttonCode) {
	  if (buttonCode > 0 && buttonCode < 4)
		  return !buttons[buttonCode];
    
	  return false;
  }
  
  public void resetMouseWheel() {
	  wheelMovement = 0;
  }
  
  public int getWheelMovement()  {
	  return wheelMovement;
  }
  
  public boolean wheelMoved() {
	  if(wheelMovement > 0 || wheelMovement < 0)
		  return true;
	  return false;
  }
  
  public int getMouseX() {
	  return mouseX;
  }
  
  public int getMouseY() {
	  return mouseY;
  }
  
  public void keyPressed(KeyEvent e) {
	  if (e.getKeyCode() > 0 && e.getKeyCode() < 256)
		  keys[e.getKeyCode()] = true;
  }
  
  public void keyReleased(KeyEvent e) {
	  if (e.getKeyCode() > 0 && e.getKeyCode() < 256)
		  keys[e.getKeyCode()] = false;
  }
  
  public void mousePressed(MouseEvent e) {
	  if(e.getButton() > 0 && e.getButton() < 4)
		  buttons[e.getButton()] = true;
  }
  
  public void mouseReleased(MouseEvent e) {
	  if(e.getButton() > 0 && e.getButton() < 4)
		  buttons[e.getButton()] = false;
  }
  
  public void mouseWheelMoved(MouseWheelEvent e) {
	  wheelMovement = e.getWheelRotation();
  }
  
  public void mouseMoved(MouseEvent e) {
	  mouseX = e.getX();
	  mouseY = e.getY(); 
  }
  
  public void mouseDragged(MouseEvent e) {
	  mouseX = e.getX();
	  mouseY = e.getY();
  }

  //Unused
  public void mouseClicked(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  public void keyTyped(KeyEvent e) {}
}
