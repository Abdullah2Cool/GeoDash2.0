package game.release2;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
    public static int PPM = 32;

    @Override
    public void create() {
        nWidth = Gdx.graphics.getWidth();
        nHeight = Gdx.graphics.getHeight();
        Scale = 0.8f;
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
        super.render();
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
        scrPlay.dispose();
    }
}
