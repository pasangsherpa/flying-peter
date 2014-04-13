package com.pasang.Helpers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.pasang.GameObjects.Bird;
import com.pasang.GameWorld.GameWorld;
import com.pasang.ui.SimpleButton;

public class InputHandler implements InputProcessor {
	private Bird bird;
	private GameWorld world;

	private List<SimpleButton> menuButtons;

	private SimpleButton playButton;

	private float scaleFactorX;
	private float scaleFactorY;

	public InputHandler(GameWorld world, float scaleFactorX, float scaleFactorY) {
		this.world = world;
		bird = world.getBird();

		int midPointY = world.getMidPointY();

		this.scaleFactorX = scaleFactorX;
		this.scaleFactorY = scaleFactorY;

		menuButtons = new ArrayList<SimpleButton>();
		playButton = new SimpleButton(52, midPointY + 50, 29, 16,
				AssetLoader.playButtonUp, AssetLoader.playButtonDown);
		menuButtons.add(playButton);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);

		if (world.isMenu()) {
			playButton.isTouchDown(screenX, screenY);
		} else if (world.isReady()) {
			world.start();
		}

		bird.onClick();

		if (world.isGameOver() || world.isHighScore()) {
			world.restart();
		}

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);

		if (world.isMenu()) {
			if (playButton.isTouchUp(screenX, screenY)) {
				world.ready();
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean keyDown(int keycode) {

		if (keycode == Keys.SPACE) {

			if (world.isMenu()) {
				world.ready();
			} else if (world.isReady()) {
				world.start();
			}

			bird.onClick();

			if (world.isGameOver() || world.isHighScore()) {
				world.restart();
			}

		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	private int scaleX(int screenX) {
		return (int) (screenX / scaleFactorX);
	}

	private int scaleY(int screenY) {
		return (int) (screenY / scaleFactorY);
	}

	public List<SimpleButton> getMenuButtons() {
		return menuButtons;
	}
}