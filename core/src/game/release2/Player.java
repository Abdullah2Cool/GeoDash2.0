package game.release2;

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

public class Player {
    private Vector2 vPos;
    private float fLength;
    private World world;
    private Body pBody;
    private String sPath;
    private Sprite spPlayer;

    public Player(Vector2 vPos, float fLength, World world, String sPath) {

        this.vPos = vPos;
        this.fLength = fLength;
        this.world = world;
        this.sPath = sPath;
        pBody = createBody(vPos, fLength);
    }

    private Body createBody(Vector2 vPos, float fLength) {
        Body pBody;
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(vPos.x / PPM, vPos.y / PPM);
        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(fLength / 2 / PPM, fLength / 2 / PPM);
        pBody.createFixture(shape, 1.0f);
        shape.dispose();
        return pBody;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(spPlayer, vPos.x - 16, vPos.y - 16, spPlayer.getOriginX(), spPlayer.getOriginY(),
                spPlayer.getWidth(), spPlayer.getHeight(), 1, 1, (float) Math.toDegrees(pBody.getAngle()));
    }
}
