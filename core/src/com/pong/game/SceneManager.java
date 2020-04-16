/**
 * 14 apr. 2020
 */
package com.pong.game;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.pong.game.scenes.*;

/**
 * @author Boris Mulder
 *
 */

public class SceneManager
{
	private final HashMap<UIScenes, UIScene> Scenes = new HashMap<UIScenes, UIScene>();
	
	private UIScenes currentScene = UIScenes.Main;
	private SpriteBatch spritebatch;
	private ShapeRenderer shaperenderer;
	private BitmapFont bitmapfont;
	
	public void create()
	{
		spritebatch = new SpriteBatch();
		bitmapfont = new BitmapFont();
		shaperenderer = new ShapeRenderer();
		
		Scenes.put(UIScenes.Main, new MainScene());
		Scenes.put(UIScenes.Game, new GameScene());
		
		Scenes.forEach((k,v) -> v.createScene(this));
		
		showScene(currentScene);
	}
	
	public void showScene(UIScenes scene)
	{
		currentScene = scene;
		Gdx.input.setInputProcessor(Scenes.get(currentScene).getStage());
	}
	
	public void actScene() {
		Scenes.get(currentScene).act(Gdx.graphics.getDeltaTime());
	}
	
	public void drawScene() {
		Scenes.get(currentScene).draw();
	}
	
	public SpriteBatch getSpriteBatch()
	{
		return spritebatch;
	}
	
	public BitmapFont getBitmapFont()
	{
		return bitmapfont;
	}
	
	public ShapeRenderer getShapeRenderer()
	{
		return shaperenderer;
	}
	
	public void dispose()
	{
		Scenes.clear();
	}
}
