package PaooGame.Camera;
import PaooGame.Items.Item;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;


public class Camera2 {
    private Rectangle bounds; // limitele vizibile ale camerei
    private double x; //coordonata x a camerei
    private double y; //coordonata y a camerei
    private double scale; // factorul de scalare al camerei

   // private int width, height;

    // starea camerei; poziție și dimensiune
    public Camera2(double x, double y, double width, double height) {
        this.bounds = new Rectangle((int) x, (int) y, (int) width, (int) height);
        this.x = x;
        this.y = y;
        this.scale = 4;

        //this.width = (int) width;
        //this.height = (int) height;
        //this.bounds = new Rectangle((int) x, (int) y, (int)(width / scale), (int)(height / scale));
    }

    // metoda prin care se setează poziția camerei
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // metoda prin care se setează factorul de scalare, daca este nevoie de el
    public void setScale(double scale) {
        this.scale = scale;
    }

    // metoda de aplicare a matricei de vizualizare a camerei
    public void apply(Graphics2D g2d) {
        AffineTransform transform = new AffineTransform();

        transform.scale(scale, scale); // aplicarea factorului de scală
        transform.translate(-x, -y); // translarea poziției camerei
        g2d.setTransform(transform);
    }

    // metoda returnează limitele vizibile ale camerei
    public Rectangle getBounds() {
        return bounds;
    }

    public void tick(Item player)
    {
        this.x=player.GetX()-100;
        //y = -player.GetY() + 96;

        if (x < 0) x = 0;
        //if (y < 0) y = 0;

        // Asigură-te că camera nu iese din marginea dreapta și jos a hărții
        //if (x > map.getWidth() - 192) x = map.getWidth() - 192;
        //if (y > map.getHeight() - 192) y = map.getHeight() - 192;
    }

   /* public void tick(Item player) {
        // Dimensiunile camerei în pixeli (înainte de scalare)
        double viewportWidth = bounds.getWidth() / scale;
        double viewportHeight = bounds.getHeight() / scale;

        // Dimensiunile hărții
        double mapWidth = 100 * 32;
        double mapHeight = 6 * 32;

        // Poziția jucătorului pe centru ecran
        x = player.GetX() - viewportWidth / 2;
        //y = player.GetY() - viewportHeight / 2;

        // Limite pe axa X
        if (x < 0) x = 0;
        if (x > mapWidth - viewportWidth) x = mapWidth - viewportWidth;

        // Limite pe axa Y
        //if (y < 0) y = 0;
        //if (y > mapHeight - viewportHeight) y = mapHeight - viewportHeight;

        // Actualizează și bounds-ul camerei
        bounds.setBounds((int)x, (int)y, (int)viewportWidth, (int)viewportHeight);
    }
*/
}



