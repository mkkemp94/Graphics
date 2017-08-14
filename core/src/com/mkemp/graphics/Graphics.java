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

public class Graphics extends ApplicationAdapter {
	private SpriteBatch batch;
	private Sprite sprite;

	private Texture texture;
	private Pixmap pixmap;


	@Override
	public void create () {
		batch = new SpriteBatch();

		texture = new Texture(Gdx.files.internal("data/jet.png"));
		//createPixmapTexture();

		sprite = new Sprite(texture);
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
		texture.dispose();
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
