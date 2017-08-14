package com.mkemp.graphics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Timer;

public class Graphics extends ApplicationAdapter {
	private SpriteBatch batch;
	private TextureAtlas textureAtlas;
	private Sprite sprite;
	private int currentFrame = 1;
	private String currentAtlasKey = new String("0001");

	private Texture texture;
	private Pixmap pixmap;

	@Override
	public void create () {
		batch = new SpriteBatch();

		//texture = new Texture(Gdx.files.internal("data/jet.png")); // load from texture
		//createPixmapTexture(); // OR load pixmap into texture
		//sprite = new Sprite(texture); // used for both above

		textureAtlas = new TextureAtlas(Gdx.files.internal("data/spritesheet.atlas"));
		TextureAtlas.AtlasRegion region = textureAtlas.findRegion("0001");
		sprite = new Sprite(region);
		sprite.setPosition(120, 100);
		sprite.scale(2.5f);
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				currentFrame++;
				if (currentFrame > 20)
					currentFrame = 1;

				currentAtlasKey = String.format("%04d", currentFrame);
				sprite.setRegion(textureAtlas.findRegion(currentAtlasKey));
			}
		}
		,0,1/30.0f);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		sprite.draw(batch);
		//sprite.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		//sprite.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//texture.dispose();
		textureAtlas.dispose();
	}

	/**
	 * Create a pixmap and assign it to a texture.
	 */
	private void createPixmapTexture() {
		// A pixmap is a raw image in memory represented by pixels.
		pixmap = new Pixmap(256, 128, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.RED);
		pixmap.fill();

		// Draw two lines forming an x
		pixmap.setColor(Color.BLACK);
		pixmap.drawLine(0, 0, pixmap.getWidth()-1, pixmap.getHeight()-1);
		pixmap.drawLine(0, pixmap.getHeight()-1, pixmap.getWidth()-1, 0);

		// Draw a circle about the middle
		pixmap.setColor(Color.YELLOW);
		pixmap.drawCircle(pixmap.getWidth()/2, pixmap.getHeight()/2, pixmap.getHeight()/2 - 1);

		// Assign pixmap to texture and dispose of it.
		texture = new Texture(pixmap);
		pixmap.dispose();
	}
}
