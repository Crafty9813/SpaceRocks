import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import java.util.Iterator;

public class LevelScreen extends BaseScreen {
    private Spaceship spaceship;
    private boolean gameOver;
    private float deltaX;
    private float deltaY;

    public LevelScreen() {
    }

    public void initialize() {
        BaseActor space = new BaseActor(0.0F, 0.0F, this.mainStage);
        space.loadTexture("assets/space.png");
        space.setSize(800.0F, 600.0F);
        BaseActor.setWorldBounds(space);
        this.spaceship = new Spaceship(400.0F, 300.0F, this.mainStage);
        new Rock(600.0F, 500.0F, this.mainStage);
        new Rock(600.0F, 300.0F, this.mainStage);
        new Rock(600.0F, 100.0F, this.mainStage);
        new Rock(400.0F, 100.0F, this.mainStage);
        new Rock(200.0F, 100.0F, this.mainStage);
        new Rock(200.0F, 300.0F, this.mainStage);
        new Rock(200.0F, 500.0F, this.mainStage);
        new Rock(400.0F, 500.0F, this.mainStage);
        this.gameOver = false;
    }

    public void update(float dt) {
        Iterator var2 = BaseActor.getList(this.mainStage, "Rock").iterator();

        while(var2.hasNext()) {
            BaseActor rockActor = (BaseActor)var2.next();
            this.deltaX = this.spaceship.getX() - rockActor.getX();
            this.deltaY = this.spaceship.getY() - rockActor.getY();
            if (this.deltaX * this.deltaX + this.deltaY * this.deltaY < 30000.0F) {
                rockActor.isReadytoAct(true, this.deltaX, this.deltaY);
            }

            Spaceship var10000;
            BaseActor rocklaserActor;
            if (rockActor.overlaps(this.spaceship)) {
                Explosion boom;
                if (this.spaceship.shieldPower <= 0) {
                    boom = new Explosion(0.0F, 0.0F, this.mainStage);
                    boom.centerAtActor(this.spaceship);
                    this.spaceship.remove();
                    this.spaceship.setPosition(-1000.0F, -1000.0F);
                    rocklaserActor = new BaseActor(0.0F, 0.0F, this.uiStage);
                    rocklaserActor.loadTexture("assets/message-lose.png");
                    rocklaserActor.centerAtPosition(400.0F, 300.0F);
                    rocklaserActor.setOpacity(0.0F);
                    rocklaserActor.addAction(Actions.fadeIn(1.0F));
                    this.gameOver = true;
                } else {
                    var10000 = this.spaceship;
                    var10000.shieldPower -= 34;
                    boom = new Explosion(0.0F, 0.0F, this.mainStage);
                    boom.centerAtActor(rockActor);
                    rockActor.remove();
                }
            }

            Iterator var9 = BaseActor.getList(this.mainStage, "Laser").iterator();

            Explosion boom;
            while(var9.hasNext()) {
                rocklaserActor = (BaseActor)var9.next();
                if (rocklaserActor.overlaps(rockActor)) {
                    boom = new Explosion(0.0F, 0.0F, this.mainStage);
                    boom.centerAtActor(rockActor);
                    rocklaserActor.remove();
                    rockActor.remove();
                }
            }

            var9 = BaseActor.getList(this.mainStage, "RockLaser").iterator();

            while(var9.hasNext()) {
                rocklaserActor = (BaseActor)var9.next();
                if (rocklaserActor.overlaps(this.spaceship)) {
                    if (this.spaceship.shieldPower <= 0) {
                        boom = new Explosion(0.0F, 0.0F, this.mainStage);
                        boom.centerAtActor(this.spaceship);
                        this.spaceship.remove();
                        this.spaceship.setPosition(-1000.0F, -1000.0F);
                        BaseActor messageLose = new BaseActor(0.0F, 0.0F, this.uiStage);
                        messageLose.loadTexture("assets/message-lose.png");
                        messageLose.centerAtPosition(400.0F, 300.0F);
                        messageLose.setOpacity(0.0F);
                        messageLose.addAction(Actions.fadeIn(1.0F));
                        this.gameOver = true;
                    } else {
                        var10000 = this.spaceship;
                        var10000.shieldPower -= 10;
                        boom = new Explosion(0.0F, 0.0F, this.mainStage);
                        boom.centerAtActor(rocklaserActor);
                        rocklaserActor.remove();
                    }
                }
            }
        }

        if (!this.gameOver && BaseActor.count(this.mainStage, "Rock") == 0) {
            BaseActor messageWin = new BaseActor(0.0F, 0.0F, this.uiStage);
            messageWin.loadTexture("assets/message-win.png");
            messageWin.centerAtPosition(400.0F, 300.0F);
            messageWin.setOpacity(0.0F);
            messageWin.addAction(Actions.fadeIn(1.0F));
            this.gameOver = true;
        }

    }

    public boolean keyDown(int keycode) {
        if (keycode == 62) {
            this.spaceship.shoot();
        }

        if (keycode == 52) {
            this.spaceship.warp();
        }

        return false;
    }
}
