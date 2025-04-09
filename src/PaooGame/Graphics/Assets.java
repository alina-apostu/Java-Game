package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage playerLeft;
    public static BufferedImage playerRight;
    public static BufferedImage soil;
    public static BufferedImage grass;
    public static BufferedImage mountain;
    public static BufferedImage townGrass;
    public static BufferedImage townGrassDestroyed;
    public static BufferedImage townSoil;
    public static BufferedImage water;
    public static BufferedImage rockUp;
    public static BufferedImage rockDown;
    public static BufferedImage rockLeft;
    public static BufferedImage rockRight;
    public static BufferedImage tree;
    public static BufferedImage background;
    public static BufferedImage podea1;
    public static BufferedImage turn;
    public static BufferedImage turn2;
    public static BufferedImage turn3;
    public static BufferedImage treaptaJos;
    public static BufferedImage treaptaJos2;
    public static BufferedImage franghie;
    public static BufferedImage franghie2;


    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        //SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/background.png"));
        //background = ImageLoader.LoadImage("/textures/background.png");
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/podea1.png"));
        SpriteSheet sheet2 = new SpriteSheet(ImageLoader.LoadImage("/textures/turn.png"));

            /// Se obtin subimaginile corespunzatoare elementelor necesare.
        //background = sheet.crop(0, 0);

       //grass = sheet.crop(1, 0);
        /*soil = sheet.crop(1, 0);
        water = sheet.crop(2, 0);
        mountain = sheet.crop(3, 0);
        townGrass = sheet.crop(0, 1);
        townGrassDestroyed = sheet.crop(1, 1);
        townSoil = sheet.crop(2, 1);
        tree = sheet.crop(3, 1);
        playerLeft = sheet.crop(0, 2);
        playerRight = sheet.crop(1, 2);
        rockUp = sheet.crop(2, 2);
        rockDown = sheet.crop(3, 2);
        rockLeft = sheet.crop(0, 3);
        rockRight = sheet.crop(1, 3);*/
        podea1=sheet.crop(0,3);
        turn=sheet2.crop(1,10);
        turn2=sheet2.crop(1,9);
        turn3=sheet2.crop(1,8);
        treaptaJos=sheet2.crop(5,8);
        treaptaJos2=sheet2.crop(4,8);
        franghie=sheet2.crop(5,7);
        franghie2=sheet2.crop(4,7);
    }
}
