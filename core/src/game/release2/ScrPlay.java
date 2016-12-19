package game.release2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by hafiz on 12/13/2016.
 */

public class ScrPlay implements Screen {
    private GamGeoDash game;
    Map map;
    Player player;
    SpriteBatch batch;

    public ScrPlay(GamGeoDash game) {
        this.game = game;
        map = new Map("map.tmx", game.world);
        player = new Player(new Vector2(50, 200), 32, this.game.world, "geoDash.png");
        batch = game.batch;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        map.draw(game.camera);
        batch.begin();
        player.draw(batch);
        batch.end();
        batch.begin();
        player.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.getMap().dispose();
        map.getTiledMapRenderer().dispose();
    }
}
