/**
 * 16 apr. 2020
 */
package com.pong.game.components;

/**
 * @author Boris Mulder
 *
 */
public class Paddle
{
	public static final int PaddleWidth = 20;
	public static final int PaddleHeight = 60;
	public static final int yVelocity = 8;
	
	private int xPosition;
	private int yPosition;
	
	public Paddle(int startX,int startY)
	{
		xPosition = startX;
		yPosition = startY;
	}
	
	public void goUp(float delta)
	{
		yPosition += (int) yVelocity + delta;
	}
	
	public void goDown(float delta)
	{
		yPosition -= (int) yVelocity + delta;
	}
	
	public int getX()
	{
		return xPosition;
	}
	
	public int getY()
	{
		return yPosition;
	}
}
