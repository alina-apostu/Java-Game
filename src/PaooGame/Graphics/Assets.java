package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage heroLeft;
    public static BufferedImage heroRight;

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


    public static BufferedImage bookShelf;
    public static BufferedImage bookShelf2;
    public static BufferedImage bookShelf3;
    public static BufferedImage bookShelf4;
    public static BufferedImage bookShelf5;
    public static BufferedImage bookShelf6;
    public static BufferedImage bookShelf7;
    public static BufferedImage bookShelf8;
    public static BufferedImage bookShelf9;
    public static BufferedImage bookShelf10;
    public static BufferedImage bookShelf11;
    public static BufferedImage bookShelf12;
    public static BufferedImage bookShelf13;
    public static BufferedImage bookShelf14;
    public static BufferedImage bookShelf15;
    public static BufferedImage bookShelf16;

    public static BufferedImage bookShelf17;
    public static BufferedImage bookShelf18;
    public static BufferedImage bookShelf19;
    public static BufferedImage bookShelf20;
    public static BufferedImage bookShelf21;
    public static BufferedImage bookShelf22;
    public static BufferedImage bookShelf23;
    public static BufferedImage bookShelf24;
    public static BufferedImage bookShelf25;
    public static BufferedImage bookShelf26;
    public static BufferedImage bookShelf27;
    public static BufferedImage bookShelf28;
    public static BufferedImage bookShelf29;
    public static BufferedImage bookShelf30;
    public static BufferedImage bookShelf31;
    public static BufferedImage bookShelf32;

    public static BufferedImage stand1;

    public static BufferedImage[] lunaUp, lunaRight;
    public static BufferedImage[] freyaUp, freyaRight;
    public static BufferedImage[] emberUp, emberRight;



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
        SpriteSheet sheet3 = new SpriteSheet(ImageLoader.LoadImage("/textures/bookShelf.png"));
        SpriteSheet sheet4 = new SpriteSheet(ImageLoader.LoadImage("/textures/bookShelf2.png"));
        SpriteSheet sheet5 = new SpriteSheet(ImageLoader.LoadImage("/textures/paianjen_2.png"));

        SpriteSheet lunaSheet = new SpriteSheet(ImageLoader.LoadImage("/characters/luna.png"));
        SpriteSheet freyaSheet = new SpriteSheet(ImageLoader.LoadImage("/characters/freya.png"));
        SpriteSheet emberSheet = new SpriteSheet(ImageLoader.LoadImage("/characters/ember.png"));

        // Se obtin subimaginile corespunzatoare elementelor necesare.

        lunaUp = new BufferedImage[4];
        lunaRight = new BufferedImage[2];
        freyaUp = new BufferedImage[4];
        freyaRight = new BufferedImage[2];
        emberUp = new BufferedImage[4];
        emberRight = new BufferedImage[2];

        for(int i=0;i<4;i++)
        {
            lunaUp[i] = lunaSheet.crop(i,0);
            freyaUp[i] = freyaSheet.crop(i,0);
            emberUp[i] = emberSheet.crop(i,0);
        }
        for(int i=0;i<2;i++)
        {
            lunaRight[i] = lunaSheet.crop(i,1);
            freyaRight[i] = freyaSheet.crop(i,1);
            emberRight[i] = emberSheet.crop(i,1);
        }

        podea1=sheet.crop(0,3);
        turn=sheet2.crop(1,10);
        turn2=sheet2.crop(1,9);
        turn3=sheet2.crop(1,8);
        treaptaJos=sheet2.crop(5,8);
        treaptaJos2=sheet2.crop(4,8);
        franghie=sheet2.crop(5,7);
        franghie2=sheet2.crop(4,7);
        bookShelf=sheet3.crop(16,4);
        bookShelf2=sheet3.crop(16,5);
        bookShelf3=sheet3.crop(16,6);
        bookShelf4=sheet3.crop(16,7);
        bookShelf5=sheet3.crop(17,4);
        bookShelf6=sheet3.crop(17,5);
        bookShelf7=sheet3.crop(17,6);
        bookShelf8=sheet3.crop(17,7);
        bookShelf9=sheet3.crop(18,4);
        bookShelf10=sheet3.crop(18,5);
        bookShelf11=sheet3.crop(18,6);
        bookShelf12=sheet3.crop(18,7);
        bookShelf13=sheet3.crop(19,4);
        bookShelf14=sheet3.crop(19,5);
        bookShelf15=sheet3.crop(19,6);
        bookShelf16=sheet3.crop(19,7);


        bookShelf17=sheet3.crop(4,4);
        bookShelf18=sheet3.crop(4,5);
        bookShelf19=sheet3.crop(4,6);
        bookShelf20=sheet3.crop(4,7);
        bookShelf21=sheet3.crop(5,4);
        bookShelf22=sheet3.crop(5,5);
        bookShelf23=sheet3.crop(5,6);
        bookShelf24=sheet3.crop(5,7);
        bookShelf25=sheet3.crop(6,4);
        bookShelf26=sheet3.crop(6,5);
        bookShelf27=sheet3.crop(6,6);
        bookShelf28=sheet3.crop(6,7);
        bookShelf29=sheet3.crop(7,4);
        bookShelf30=sheet3.crop(7,5);
        bookShelf31=sheet3.crop(7,6);
        bookShelf32=sheet3.crop(7,7);

        stand1=sheet4.crop(0,0);
    }
}
