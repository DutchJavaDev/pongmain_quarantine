/**
 * 14 apr. 2020
 */
package com.pong.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.pong.game.SceneManager;

/**
 * @author Boris Mulder
 *
 */
public class MainScene implements UIScene
{
	private final Stage stage;
	private final GlyphLayout layout;
	
	private SceneManager sceneManager;
	private SpriteBatch batch;
	private BitmapFont font;
	
	private int clientWidth;
	private int clientHeight;

	public MainScene()
	{
		stage = new Stage();
		layout = new GlyphLayout();
	}
	
	private void CreateUI()
	{
		clientWidth = Gdx.graphics.getWidth();
		clientHeight = Gdx.graphics.getHeight();
		
		final int windowWidth = clientWidth / 3;
		final int windowHeight = (int)(clientHeight * 0.55);
		
		final VisWindow window = new VisWindow("");
		
		window.setWidth(windowWidth);
		window.setHeight(windowHeight);
		
		final int windowX = (int)(clientWidth / 2f) - (int)(windowWidth / 2f);
		final int windowY = (int)(clientHeight / 2f) - (int)(windowHeight / 2f);
		
		window.setResizable(false);
		window.setMovable(false);
		
		final WindowStyle style = window.getStyle();
		style.background = null;
		
		window.setStyle(style);
		
		window.setPosition(windowX, windowY);
		
		final VisTextButton play = new VisTextButton("   Play   ");
		play.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				sceneManager.showScene(UIScenes.Game);
				
				// Request to server to search other player
				// Switch to lobby
				// and wait.......
				return true;
			}
		});
		
		
		final VisTextButton about = new VisTextButton("About");
		about.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				// Show about the game
				return true;
			}
		});
		
		final VisTextButton exit = new VisTextButton("Quit");
		exit.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				Gdx.app.exit();
				//Dialogs.showConfirmDialog(stage, title, text, buttons, returns, listener)
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		final Table table = new Table();

		table.add(play).fillX().uniformX();
		
		table.row().pad(10, 0, 10, 0);
		
		table.add(about).fillX().uniformX();
		
		table.row();
		
		table.add(exit).fillX().uniformX();
		
		window.add(table);
		stage.addActor(window);
	}
	
	@Override
	public void createScene(SceneManager manager)
	{
		batch = manager.getSpriteBatch();
		font = manager.getBitmapFont();
		sceneManager = manager;
		CreateUI();
	}

	@Override
	public Stage getStage()
	{
		return stage;
	}

	@Override
	public void act(float delta)
	{
		stage.act(delta);
	}

	@Override
	public void draw()
	{
		batch.begin();
		ServerStatus();
		batch.end();
		
		stage.draw();
	}
	
	private void ServerStatus()
	{
		font.draw(batch, "Server: ", 5, clientHeight - 5);
		font.setColor(Color.RED);
		
		layout.setText(font, "Server: ");
		
		font.draw(batch, "Offline", 10 + layout.width, clientHeight - 5);
		font.setColor(Color.WHITE);
	}

}
