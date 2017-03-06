package main.engine;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Animation {

	private BufferedImage imgStrip = null;
	private int numRows, numCols;
	private int imgWidth, imgHeight;
	private int currentFrameX, currentFrameY;
	private float time, frameTime;
	
	public Animation(String path, int numRows, int numCols) {
		this.imgStrip = Util.loadImage(path);
		this.numRows = numRows;
		this.numCols = numCols;
		
		this.imgWidth = imgStrip.getWidth() / numRows;
		this.imgHeight = imgStrip.getHeight() / numCols;
		
		this.currentFrameX = 0;
		this.currentFrameY = 0;
		
		this.time = 0.0f;
		this.frameTime = 1f / 15f;
	}
	
	public Animation(BufferedImage image, int numRows, int numCols) {
		this.imgStrip = image;
		this.numRows = numRows;
		this.numCols = numCols;
		
		this.imgWidth = image.getWidth() / numRows;
		this.imgHeight = image.getHeight() / numCols;
		
		this.currentFrameX = 0;
		this.currentFrameY = 0;
		
		this.time = 0.0f;
		this.frameTime = 1f / 30f;
	}
	
	public Animation(BufferedImage image, int numRows, int numCols, float frameTime) {
		this.imgStrip = image;
		this.numRows = numRows;
		this.numCols = numCols;
		
		this.imgWidth = image.getWidth() / numRows;
		this.imgHeight = image.getHeight() / numCols;
		
		this.currentFrameX = 0;
		this.currentFrameY = 0;
		
		this.time = 0.0f;
		this.frameTime = frameTime;
	}
	
	public void update(float delta) {
		time += delta;
		
		while(time > frameTime) {
			time -= frameTime;
			
			currentFrameX++;
			if(currentFrameX >= numRows) {
				currentFrameX = 0;
				currentFrameY++;
				if(currentFrameY >= numCols) {
					//currentFrameX = 0;
					currentFrameY = 0;
				}
			}
		}
	}
	
	public void draw(Graphics2D g, int x, int y) {
		g.drawImage(imgStrip, x, y, x + imgWidth, y + imgHeight, currentFrameX * imgWidth, currentFrameY * imgHeight, (currentFrameX * imgWidth) + imgWidth, (currentFrameY * imgHeight) + imgHeight, null);
	}
}
