package com.pasang.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	private static final String PREFS_HIGH_SCORE = "highScore";

	public static Texture texture, logoTexture;
	public static Animation birdAnimation;
	public static TextureRegion bird, birdDown, birdUp, bg, grass, skullUp,
			skullDown, bar, playButtonUp, playButtonDown, title, appTitle,
			logo, ready, gameOver, highScore, scoreboard, star, noStar, retry;
	public static Sound dead, flap, coin, fall;
	public static BitmapFont font, whiteFont, shadow;
	public static Preferences prefs;

	public static void load() {
		prefs = Gdx.app.getPreferences("FlyingPeter");
		if (!prefs.contains(PREFS_HIGH_SCORE)) {
			prefs.putInteger(PREFS_HIGH_SCORE, 0);
		}

		logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
		logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		logo = new TextureRegion(logoTexture, 0, 0, 512, 128);

		texture = new Texture(Gdx.files.internal("data/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		appTitle = new TextureRegion(texture, 0, 55, 135, 24);
		appTitle.flip(false, true);

		playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
		playButtonUp.flip(false, true);

		playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
		playButtonDown.flip(false, true);

		ready = new TextureRegion(texture, 59, 83, 34, 7);
		ready.flip(false, true);

		retry = new TextureRegion(texture, 59, 110, 33, 7);
		retry.flip(false, true);

		gameOver = new TextureRegion(texture, 59, 92, 46, 7);
		gameOver.flip(false, true);

		scoreboard = new TextureRegion(texture, 111, 83, 97, 37);
		scoreboard.flip(false, true);

		star = new TextureRegion(texture, 152, 70, 10, 10);
		noStar = new TextureRegion(texture, 165, 70, 10, 10);

		star.flip(false, true);
		noStar.flip(false, true);

		highScore = new TextureRegion(texture, 59, 101, 48, 7);
		highScore.flip(false, true);

		title = new TextureRegion(texture, 0, 55, 135, 24);
		title.flip(false, true);

		bg = new TextureRegion(texture, 0, 0, 136, 43);
		bg.flip(false, true);

		grass = new TextureRegion(texture, 0, 43, 143, 11);
		grass.flip(false, true);

		birdDown = new TextureRegion(texture, 136, 0, 19, 15);
		birdDown.flip(false, true);

		bird = new TextureRegion(texture, 155, 0, 19, 15);
		bird.flip(false, true);

		birdUp = new TextureRegion(texture, 174, 0, 19, 15);
		birdUp.flip(false, true);

		TextureRegion[] birds = { birdDown, bird, birdUp };
		birdAnimation = new Animation(0.06f, birds);
		birdAnimation.setPlayMode(Animation.LOOP_PINGPONG);

		skullUp = new TextureRegion(texture, 193, 0, 24, 14);
		// Create by flipping existing skullUp
		skullDown = new TextureRegion(skullUp);
		skullDown.flip(false, true);

		bar = new TextureRegion(texture, 136, 16, 22, 3);
		bar.flip(false, true);

		coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
		flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
		dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
		fall = Gdx.audio.newSound(Gdx.files.internal("data/fall.wav"));

		font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
		font.setScale(.25f, -.25f);

		whiteFont = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
		whiteFont.setScale(.1f, -.1f);

		shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
		shadow.setScale(.25f, -.25f);
	}

	public static void setHighScore(int score) {
		prefs.putInteger(PREFS_HIGH_SCORE, score);
		prefs.flush();
	}

	public static int getHighScore() {
		return prefs.getInteger(PREFS_HIGH_SCORE);
	}

	public static void dispose() {
		// Dispose of the textures
		logoTexture.dispose();
		texture.dispose();
		// Dispose sounds
		coin.dispose();
		flap.dispose();
		dead.dispose();
		fall.dispose();
		// Dispose fonts
		font.dispose();
		whiteFont.dispose();
		shadow.dispose();
	}
}
