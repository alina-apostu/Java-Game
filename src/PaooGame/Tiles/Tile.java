package PaooGame.Tiles;

import PaooGame.Tiles.ForLevel1.*;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile
{
    private static final int NO_TILES   = 48;
    public static Tile[] tiles          = new Tile[NO_TILES];       /*!< Vector de referinte de tipuri de dale.*/

        /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
        /// o singura data in memorie

    public static Tile grassTile        = new GrassTile(30);     /*!< Dala de tip iarba*/
    public static Tile mountainTile     = new MountainTile(31);  /*!< Dala de tip munte/piatra*/
    public static Tile waterTile        = new WaterTile(32);     /*!< Dala de tip apa*/
    public static Tile treeTile         = new TreeTile(33);      /*!< Dala de tip copac*/
    public static Tile soilTile         = new SoilTile(34);      /*!< Dala de tip sol/pamant*/
    public static Tile fundal        = new Fundal(35);

    public static Tile podea1        = new Podea1(0);
    public static Tile turn        = new Turn(1);
    public static Tile turn2        = new Turn2(2);
    public static Tile turn3        = new Turn3(3);
    public static Tile treaptaJos        = new TreaptaJos(4);
    public static Tile treaptaJos2        = new TreaptaJos2(5);
    public static Tile franghie        = new Franghie(6);
    public static Tile franghie2        = new Franghie2(7);


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
