/**
 * 16 apr. 2020
 */
package com.pong.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pong.game.SceneManager;
import com.pong.game.components.Paddle;

/**
 * @author Boris Mulder
 *
 */
public class GameScene implements UIScene
{
	private final Stage stage;
	private final GlyphLayout layout;
	private final int yOffset = 3;
	private final int yPadding = 26;
	private final int yPaddleOffset = 10;
	private final int yScoreOffset = 15;
	private final int xScoreOffset = 30;
	
	private SceneManager sceneManager;
	private SpriteBatch spritebatch;
	private BitmapFont bitmapfont;
	private ShapeRenderer shaperenderer;
	
	private int gameWidth;
	private int gameHeight;
	private int clientWidth;
	private int clientHeight;
	private int backgroundX;
	private int backgroundY;
	
	private Paddle localPlayer;
	private int localPlayerScore = 0;
	private int netPlayerScore = 0;
	
	
	public GameScene()
	{
		stage = new Stage();
		layout = new GlyphLayout();
	}

	@Override
	public void createScene(SceneManager manager)
	{
		spritebatch = manager.getSpriteBatch();
		bitmapfont = manager.getBitmapFont();
		shaperenderer = manager.getShapeRenderer();
		
		sceneManager = manager;
		
		clientWidth = Gdx.graphics.getWidth();
		clientHeight = Gdx.graphics.getHeight();
		
		gameWidth = (int)(clientWidth * 0.95f);
		gameHeight = (int)(clientHeight * 0.90f);
		
		backgroundX = (int)((clientWidth / 2f) - (gameWidth / 2f));
		backgroundY = (int)((clientHeight / 2f) - (gameHeight / 2f));
		
		localPlayer = new Paddle(backgroundX + yPaddleOffset, (clientHeight / 2) - (Paddle.PaddleHeight / 2));
	}

	@Override
	public Stage getStage()
	{
		return stage;
	}

	@Override
	public void act(float delta)
	{
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))
		{
			sceneManager.showScene(UIScenes.Main);
		}
		
		if(Gdx.input.isKeyPressed(Keys.UP))
		{
			localPlayer.goUp(delta);
			netPlayerScore++;
			localPlayerScore++;
		}
		
		if(Gdx.input.isKeyPressed(Keys.DOWN))
		{
			localPlayer.goDown(delta);
			netPlayerScore--;
			localPlayerScore--;
		}
	}

	@Override
	public void draw()
	{
		drawBackground();
		drawScore();
		drawLocalPlayer();
	}
	
	private void drawScore()
	{
		String localScore = String.format("%s", localPlayerScore);
		String netScore = String.format("%s", netPlayerScore);
		
		spritebatch.begin();
		bitmapfont.setColor(Color.LIGHT_GRAY);
		
		layout.setText(bitmapfont, localScore);
		bitmapfont.draw(spritebatch, localScore, (backgroundX + gameWidth / 2) - layout.width - xScoreOffset, gameHeight - yScoreOffset);
		
		layout.setText(bitmapfont, netScore);
		bitmapfont.draw(spritebatch, netScore, (backgroundX + gameWidth / 2) + layout.width + xScoreOffset, gameHeight - yScoreOffset);
		
		
		spritebatch.end();
	}
	
	private void drawLocalPlayer()
	{
		shaperenderer.begin(ShapeType.Filled);
		shaperenderer.setColor(Color.WHITE);
		shaperenderer.rect(localPlayer.getX(), localPlayer.getY(), Paddle.PaddleWidth, Paddle.PaddleHeight);
		shaperenderer.end();
	}
	
	private void drawBackground()
	{
		Gdx.gl.glLineWidth(9);
		shaperenderer.begin(ShapeType.Line);
		shaperenderer.setColor(Color.WHITE);
		shaperenderer.rect(backgroundX, backgroundY, gameWidth, gameHeight);
		shaperenderer.end();
		
		
		shaperenderer.begin(ShapeType.Filled);
		for(int i = 0; i <= 20; i++)
		{
			int x = backgroundX + gameWidth / 2;
			int y = backgroundY + yOffset + (i * yPadding);
			
			shaperenderer.rect(x, y, 14, 14);
		}
		shaperenderer.end();
	}
}
