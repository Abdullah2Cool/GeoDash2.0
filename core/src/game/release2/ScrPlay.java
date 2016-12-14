package game.release2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import static game.release2.GamGeoDash.PPM;

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
        player = new Player(new Vector2(100, 200), 32, this.game.world, "geoDash.png");
        batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        updateView();
        map.draw(game.camera);
        game.b2dr.render(game.world, game.camera.combined.scl(PPM)); // render the outline of obstacles
        batch.begin();
        player.draw(batch);
        batch.end();
    }
    public void updateView() {
        game.world.step(1 / 60f, 6, 2);
        game.camera.update();
        batch.setProjectionMatrix(game.camera.combined);
        game.b2dr.render(game.world, game.camera.combined);
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
