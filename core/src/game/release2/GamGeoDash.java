package game.release2;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GamGeoDash extends Game {
    ScrPlay scrPlay;
    int nWidth, nHeight;
    float Scale;
    OrthographicCamera camera;
    World world;
    Box2DDebugRenderer b2dr;
    SpriteBatch batch;
    public static int PPM = 32;

    @Override
    public void create() {
        nWidth = Gdx.graphics.getWidth();
        nHeight = Gdx.graphics.getHeight();
        Scale = 0.8f;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, nWidth / 2 / Scale, nHeight / 2 / Scale);
        camera.position.set(nWidth / 2, nHeight / 2, 0);
        world = new World(new Vector2(0, -9.8f), false);
        b2dr = new Box2DDebugRenderer();
        scrPlay = new ScrPlay(this);
        setScreen(scrPlay);
    }

    @Override
    public void setScreen(Screen screen) {
        super.setScreen(screen);
    }

    @Override
    public void render() {
        updateView();
        super.render();
    }
    public void updateView() {
        world.step(1 / 60f, 6, 2);
        camera.position.x = scrPlay.player.getPosition().x + 350;
        camera.position.y = scrPlay.player.getPosition().y + 120;
        //System.out.println(camera.position.x);
        camera.position.x = MathUtils.clamp(camera.position.x, 350, 14000);
        camera.position.y = MathUtils.clamp(camera.position.y, 200, 400);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        //b2dr.render(world, camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        camera.setToOrtho(false, nWidth / 2 / Scale, nHeight / 2 / Scale);
    }

    @Override
    public void dispose() {
        super.dispose();
        world.dispose();
        b2dr.dispose();
        batch.dispose();
        scrPlay.dispose();
    }
}
