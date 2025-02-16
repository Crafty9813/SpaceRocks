import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class MenuScreen extends BaseScreen
{
    public void initialize()
    {
        BaseActor ocean = new BaseActor(0,0, mainStage);
        ocean.loadTexture( "assets/space.png" );
        ocean.setSize(800,600);
        
        
        
        BaseActor start = new BaseActor(0,0, mainStage);
        start.loadTexture( "assets/Menu.png" );
        start.centerAtPosition(400,300);
        start.moveBy(0,-100);
        
    }

    public void update(float dt)
    {
        if (Gdx.input.isKeyPressed(Keys.S)) 
            SpaceGame.setActiveScreen( new LevelScreen() );
    }


}
