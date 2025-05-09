package PaooGame.Camera;
import PaooGame.Items.Item;

import java.awt.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;




public class Camera2 {
    private Rectangle bounds; // limitele vizibile ale camerei
    private double x; //coordonata x a camerei
    private double y; //coordonata y a camerei
    private double scale; // factorul de scalare al camerei


    private final int TILE_SIZE = 32;
    private final int MAP_WIDTH = 25; // numar tile uri pentru latimea camerei
    private final int MAP_HEIGHT = 6;  // 6 tile-uri pentru inaltimea camerei
    private final double TOTAL_WIDTH=100; //numar tile uri pentru latimea intregii harti

    // starea camerei; poziție și dimensiune
    public Camera2(double x, double y, double width, double height) {

        //se calculeaza rezolutia ecranului monitorului dispozitivului pe care se ruleaza
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        DisplayMode displayMode = gd.getDisplayMode();

        // Lățimea și înălțimea ecranului monitorului7\
        int screenWidth = displayMode.getWidth();
        int screenHeight = displayMode.getHeight();


        double logicWidth =width;// MAP_WIDTH * TILE_SIZE; //800
        double logicHeight = height;//MAP_HEIGHT * TILE_SIZE*2; //384


        // factorul de scalare în funcție de dimensiunea ecranului
        double scaleX = screenWidth/ logicWidth;
        double scaleY = screenHeight/ logicHeight;

        System.out.println(screenWidth+" "+screenHeight+" "+scaleX+" "+ scaleY+"gata");
        double diagonalInches = Math.sqrt(Math.pow(screenWidth, 2) + Math.pow(screenHeight, 2)) / 96; // ppi=densiotatea pixeli
        double ppi = Math.sqrt(Math.pow(screenWidth, 2) + Math.pow(screenHeight, 2)) / diagonalInches;

        this.scale = Math.min(scaleX, scaleY)* (96 / ppi);;
        //System.out.println(scale);
        this.scale=Math.min(scale, 4);
        //System.out.println(scale);
        this.scale=(int)scale;
        this.scale = Math.max(2.5, scale);
       // System.out.println(scale);
        this.bounds = new Rectangle((int) x, (int) y, (int) (logicWidth/scale), (int) (logicHeight/scale));
        this.x = x;
        this.y = y;

        //logicWidth/scale=pixeli care se vad efectiv, nu cei reali

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
        this.x=player.GetX() -  bounds.getWidth() / 2;

        if (x < 0) {x = 0;}

        else if (x > ((TOTAL_WIDTH * TILE_SIZE ) - bounds.getWidth()*scale/2))
        {
            x = (TOTAL_WIDTH* TILE_SIZE ) - bounds.getWidth()*scale/2;

        }

//bounds.getWidth()*scale/2 sunt pixeli fizici si /2 pentru a ramane la capatul din dreapta jumatate din dimensiunea camerei

        this.y=0;



    }








}



