import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;

public class Rock extends BaseActor
{  
    private final Random random;
    private float shootDir;
    private boolean isReadytoShoot;   
    
    public Rock(float x, float y, Stage s)
    {
       super(x,y,s);
        
       loadTexture("assets/Myspaceship.png");

       random = MathUtils.random;
       isReadytoShoot = false;
       
       addAction( Actions.forever( Actions.rotateBy(30 + random.nextInt(30), 1) ) );   
       
       setSpeed(50 + random.nextInt(50));
       setMaxSpeed(50 + random.nextInt(50));
       setDeceleration(0);
       
       setMotionAngle(random.nextInt(360));
    }
    
    public void act(float dt)
    {
        super.act(dt);
        //float random = MathUtils.random(800);//shouldn't generate a random number in each loop
        if ((random.nextInt(1000)<=5)&&(isReadytoShoot)) 
           shoot();//reset the condition for shoot
        //shoot();
        applyPhysics(dt);
        wrapAroundWorld();
    }
    
    public void isReadytoAct(boolean isReady,float x,float y)
    {
        isReadytoShoot = isReady;
        shootDir = (new Vector2(x,y)).angle();
    }
    
    /*public void getSpaceshipPosition(float x , float y)
    {
        SpaceshipPositionX = x;
        SpaceshipPositionY = y;
    }*/
    
    public void shoot()
    {
        if ( getStage() == null)
            return;

        RockLaser rocklaser = new RockLaser(0,0, this.getStage());
        rocklaser.centerAtActor(this);
        rocklaser.setRotation(shootDir);//should be consistent with motion
        rocklaser.setMotionAngle(shootDir);//should be set toward ship
        isReadytoShoot = false;
    }
}
