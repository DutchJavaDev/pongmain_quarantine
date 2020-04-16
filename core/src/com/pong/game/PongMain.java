package com.pong.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;



public class PongMain extends ApplicationAdapter {
	
	private final SceneManager SceneManager = new SceneManager();
	
	@Override
	public void create () {
		VisUI.load();
		VisUI.setDefaultTitleAlign(Align.center);
		SceneManager.create();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		SceneManager.actScene();
		SceneManager.drawScene();
	}
	
	@Override
	public void dispose () {
		SceneManager.dispose();
		VisUI.dispose();
	}
}
