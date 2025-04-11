package PaooGame.Camera;

import PaooGame.Items.Item;
import PaooGame.Game;
import PaooGame.GameWindow.GameWindow;

public class Camera
{
    private float x,y;
    private final float cameraWidth = 192f;
    private final float cameraHeight = 192f;

    public Camera()
    {
        this.x=0;
        this.y=0;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public void setX(float x)
    {
        this.x=x;
    }

    public void setY(float y)
    {
        this.y=y;
    }


    public void tick(Item player)
    {
        this.x=-player.GetX()+150;
        //y = -player.GetY() + 96;

        if (x < 0) x = 0;
        //if (y < 0) y = 0;

        // Asigură-te că camera nu iese din marginea dreapta și jos a hărții
        //if (x > map.getWidth() - 192) x = map.getWidth() - 192;
        //if (y > map.getHeight() - 192) y = map.getHeight() - 192;
    }


}
