package game.release2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import static game.release2.GamGeoDash.PPM;

/**
 * Created by hafiz on 12/13/2016.
 */

public class Player implements InputProcessor {
    private Vector2 vPos;
    private float fLength;
    private World world;
    private Body pBody;
    private String sPath;
    private Sprite spPlayer;
    private float fSpeed, fJumpHeight;
    private boolean[] bKeys;

    public Player(Vector2 vPos, float fLength, World world, String sPath) {

        this.vPos = vPos;
        this.fLength = fLength;
        this.world = world;
        this.sPath = sPath;
        pBody = createBody(vPos, fLength);
        spPlayer = new Sprite(new Texture(sPath));
        fSpeed = 15;
        fJumpHeight = 900 * fSpeed;
        Gdx.input.setInputProcessor(this);
        bKeys = new boolean[512];
    }

    private Body createBody(Vector2 vPos, float fLength) {
        Body pBody;
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = true;
        def.position.set(vPos.x / PPM, vPos.y / PPM);
        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(fLength / 2 / PPM, fLength / 2 / PPM);
        pBody.createFixture(shape, 10f);
        shape.dispose();
        return pBody;
    }

    public void draw(SpriteBatch batch) {
        vPos.set(pBody.getPosition().x * PPM, pBody.getPosition().y * PPM);
        batch.draw(spPlayer, vPos.x - 16, vPos.y - 16, spPlayer.getOriginX(), spPlayer.getOriginY(),
                spPlayer.getWidth(), spPlayer.getHeight(), 1, 1, (float) Math.toDegrees(pBody.getAngle()));
        move();
    }

    public void move() {
//        if (bKeys[Input.Keys.SPACE]) {
//            //System.out.println(pBody.getMass() * PPM);
//            pBody.applyForceToCenter(0, pBody.getMass() * PPM * 2f, false);
//        } else if (bKeys[Input.Keys.LEFT] || bKeys[Input.Keys.RIGHT]) {
//            System.out.println("CHANGE");
//            fSpeed *= -1;
//        }
        world.setGravity(new Vector2(0, -pBody.getPosition().y * 15));
        pBody.setLinearVelocity(fSpeed, pBody.getLinearVelocity().y);
    }

    public Vector2 getPosition() {
        return pBody.getPosition().scl(PPM);
    }

    @Override
    public boolean keyDown(int keycode) {
        bKeys[keycode] = true;
        if (keycode == Input.Keys.SPACE) {
            System.out.println(pBody.getMass() * PPM);
            pBody.applyForceToCenter(0, fJumpHeight, false);
        } else if (keycode == Input.Keys.LEFT || keycode == Input.Keys.RIGHT) {
            //System.out.println("CHANGE");
            fSpeed *= -1;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        bKeys[keycode] = false;
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //pBody.applyForceToCenter(0, pBody.getMass() * PPM * 20f, false);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
}
