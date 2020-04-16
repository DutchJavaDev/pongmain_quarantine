/**
 * 14 apr. 2020
 */
package com.pong.game.scenes;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pong.game.SceneManager;

/**
 * @author Boris Mulder
 *
 */
public interface UIScene
{
	void createScene(SceneManager manager);
	
	Stage getStage();
	
	void act(float delta);
	
	void draw();
}
