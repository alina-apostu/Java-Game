package PaooGame.Tiles;

import PaooGame.Tiles.ForLevel1.*;
import PaooGame.Tiles.ForLevel1.Carti.*;
import PaooGame.Tiles.ForLevel1.Flacari.*;
import PaooGame.Tiles.ForLevel1.Shelf1.*;
import PaooGame.Tiles.ForLevel1.Shelf2.*;
import PaooGame.Tiles.ForLevel1.Scari.*;
import PaooGame.Tiles.ForLevel1.Stand.*;
import PaooGame.Tiles.ForLevel1.Turnuri.*;
import PaooGame.Tiles.ForLevel1.Trepte.*;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile
{
    private static final int NO_TILES   = 100;
    public static Tile[] tiles          = new Tile[NO_TILES];       /*!< Vector de referinte de tipuri de dale.*/

        /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
        /// o singura data in memorie

    public static Tile grassTile        = new GrassTile(99);     /*!< Dala de tip iarba*/
    public static Tile mountainTile     = new MountainTile(98);  /*!< Dala de tip munte/piatra*/
    public static Tile waterTile        = new WaterTile(97);     /*!< Dala de tip apa*/
    public static Tile treeTile         = new TreeTile(96);      /*!< Dala de tip copac*/
    public static Tile soilTile         = new SoilTile(95);      /*!< Dala de tip sol/pamant*/
    public static Tile fundal        = new Fundal(94);

    public static Tile podea1        = new Podea1(0);
    public static Tile turn        = new Turn(1);
    public static Tile turn2        = new Turn2(2);
    public static Tile turn3        = new Turn3(3);
    public static Tile treaptaJos        = new TreaptaJos(4);
    public static Tile treaptaJos2        = new TreaptaJos2(5);
    public static Tile franghie        = new Franghie(6);
    public static Tile franghie2        = new Franghie2(7);

    public static Tile bookShelf        = new BookShelf(8);
    public static Tile bookShelf2        = new BookShelf2(9);
    public static Tile bookShelf3       = new BookShelf3(10);
    public static Tile bookShelf4      = new BookShelf4(11);
    public static Tile bookShelf5      = new BookShelf5(12);
    public static Tile bookShelf6      = new BookShelf6(13);
    public static Tile bookShelf7      = new BookShelf7(14);
    public static Tile bookShelf8      = new BookShelf8(15);
    public static Tile bookShelf9      = new BookShelf9(16);
    public static Tile bookShelf10      = new BookShelf10(17);
    public static Tile bookShelf11     = new BookShelf11(18);
    public static Tile bookShelf12     = new BookShelf12(19);
    public static Tile bookShelf13     = new BookShelf13(20);
    public static Tile bookShelf14     = new BookShelf14(21);
    public static Tile bookShelf15     = new BookShelf15(22);
    public static Tile bookShelf16     = new BookShelf16(23);

    public static Tile bookShelf17        = new BookShelf17(24);
    public static Tile bookShelf18       = new BookShelf18(25);
    public static Tile bookShelf19      = new BookShelf19(26);
    public static Tile bookShelf20     = new BookShelf20(27);
    public static Tile bookShelf21      = new BookShelf21(28);
    public static Tile bookShelf22     = new BookShelf22(29);
    public static Tile bookShelf23      = new BookShelf23(30);
    public static Tile bookShelf24     = new BookShelf24(31);
    public static Tile bookShelf25     = new BookShelf25(32);
    public static Tile bookShelf26     = new BookShelf26(33);
    public static Tile bookShelf27     = new BookShelf27(34);
    public static Tile bookShelf28     = new BookShelf28(35);
    public static Tile bookShelf29     = new BookShelf29(36);
    public static Tile bookShelf30     = new BookShelf30(37);
    public static Tile bookShelf31     = new BookShelf31(38);
    public static Tile bookShelf32     = new BookShelf32(39);



    public static Tile scara1     = new Scara1(41);
    public static Tile scara2     = new Scara2(42);
    public static Tile scara3     = new Scara3(43);
    public static Tile scara4     = new Scara4(44);
    public static Tile scara5     = new Scara5(45);
    public static Tile scara6     = new Scara6(46);
    public static Tile scara7     = new Scara7(47);
    public static Tile scara8     = new Scara8(48);
    public static Tile scara9     = new Scara9(49);
    public static Tile scara10     = new Scara10(50);
    public static Tile scara11    = new Scara11(51);
    public static Tile scara12     = new Scara12(52);
    public static Tile scara13     = new Scara13(53);
    public static Tile scara14     = new Scara14(54);
    public static Tile scara15    = new Scara15(55);
    public static Tile scara16     = new Scara16(56);

    public static Tile carte1     = new Carte1(57);
    public static Tile carte2     = new Carte2(58);

    public static Tile flacara     = new Flacara(59);
    public static Tile flacara2     = new Flacara2(60);

    public static Tile stand1     = new Stand1(61);
    public static Tile stand2     = new Stand2(62);
    public static Tile stand3     = new Stand3(63);

    public static Tile stand4     = new Stand4(64);
    public static Tile stand5     = new Stand5(65);

    public static Tile clepsidra     = new Clepsidra(66);





    public static final int TILE_WIDTH  = 32;                       /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 32;                       /*!< Inaltimea unei dale.*/

    protected BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;                                         /*!< Id-ul unic aferent tipului de dala.*/

    /*! \fn public Tile(BufferedImage texture, int id)
        \brief Constructorul aferent clasei.

        \param image Imaginea corespunzatoare dalei.
        \param id Id-ul dalei.
     */
    public Tile(BufferedImage image, int idd)
    {
        img = image;
        id = idd;

        tiles[id] = this;
    }

    /*! \fn public void Update()
        \brief Actualizeaza proprietatile dalei.
     */
    public void Update()
    {

    }

    /*! \fn public void Draw(Graphics g, int x, int y)
        \brief Deseneaza in fereastra dala.

        \param g Contextul grafic in care sa se realizeze desenarea
        \param x Coordonata x in cadrul ferestrei unde sa fie desenata dala
        \param y Coordonata y in cadrul ferestrei unde sa fie desenata dala
     */
    public void Draw(Graphics g, int x, int y)
    {
            /// Desenare dala
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    /*! \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
     */
    public boolean IsSolid()
    {
        return false;
    }

    /*! \fn public int GetId()
        \brief Returneaza id-ul dalei.
     */
    public int GetId()
    {
        return id;
    }
}
