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
import PaooGame.Tiles.ForLevel2.*;
import PaooGame.Tiles.ForLevel2.Copac.*;
import PaooGame.Tiles.ForLevel2.Shelf3.*;
import PaooGame.Tiles.ForLevel2.Fundal.*;
import PaooGame.Tiles.ForLevel3.CarteCotor1.*;
import PaooGame.Tiles.ForLevel3.CarteJos1.*;
import PaooGame.Tiles.ForLevel3.CarteJos2.*;
import PaooGame.Tiles.ForLevel3.CarteMare1.*;
import PaooGame.Tiles.ForLevel3.CarteMare2.*;
import PaooGame.Tiles.ForLevel3.Fantoma.Fantoma1;
import PaooGame.Tiles.ForLevel3.Fantoma.Fantoma2;
import PaooGame.Tiles.ForLevel3.FinalWeb.*;
import PaooGame.Tiles.ForLevel3.Geam.*;
import PaooGame.Tiles.ForLevel3.Magic.*;
import PaooGame.Tiles.ForLevel3.Panza.Panza1;
import PaooGame.Tiles.ForLevel3.Panza.Panza2;
import PaooGame.Tiles.ForLevel3.Panza.Panza3;
import PaooGame.Tiles.ForLevel3.TipuriLemn.Lemn;
import PaooGame.Tiles.ForLevel3.TipuriLemn.Lemn3;
import PaooGame.Tiles.ForLevel3.TipuriLemn.LemnObstacol;


import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile
{
    private static final int NO_TILES   = 200;
    public static Tile[] tiles          = new Tile[NO_TILES];       /*!< Vector de referinte de tipuri de dale.*/

        /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
        /// o singura data in memorie

    /*public static Tile grassTile        = new GrassTile(99);     /*!< Dala de tip iarba*/
   // public static Tile mountainTile     = new MountainTile(98);  /*!< Dala de tip munte/piatra*/
    //public static Tile waterTile        = new WaterTile(97);     /*!< Dala de tip apa*/
    //public static Tile treeTile         = new TreeTile(96);      /*!< Dala de tip copac*/
    //public static Tile soilTile         = new SoilTile(95);      /*!< Dala de tip sol/pamant*/
    //public static Tile fundal        = new Fundal(94);

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
    public static Tile podea2     = new Podea2(67);

    public static Tile copac1     = new Copac1(68);
    public static Tile copac2     = new Copac2(69);
    public static Tile copac3     = new Copac3(70);
    public static Tile copac4     = new Copac4(71);
    public static Tile copac5     = new Copac5(72);
    public static Tile copac6     = new Copac6(73);
    public static Tile copac7     = new Copac7(74);
    public static Tile copac8     = new Copac8(75);
    public static Tile copac9     = new Copac9(76);
    public static Tile copac10     = new Copac10(77);
    public static Tile copac11     = new Copac11(78);
    public static Tile copac12     = new Copac12(79);
    public static Tile copac13     = new Copac13(80);

    public static Tile bookShelf33     = new BookShelf33(81);
    public static Tile bookShelf34     = new BookShelf34(82);
    public static Tile bookShelf35     = new BookShelf35(83);
    public static Tile bookShelf36     = new BookShelf36(84);
    public static Tile bookShelf37     = new BookShelf37(85);
    public static Tile bookShelf38     = new BookShelf38(86);
    public static Tile bookShelf39     = new BookShelf39(87);
    public static Tile bookShelf40     = new BookShelf40(88);
    public static Tile bookShelf41     = new BookShelf41(89);
    public static Tile bookShelf42     = new BookShelf42(90);
    public static Tile bookShelf43     = new BookShelf43(91);
    public static Tile bookShelf44     = new BookShelf44(92);
    public static Tile bookShelf45     = new BookShelf45(93);
    public static Tile bookShelf46     = new BookShelf46(94);
    public static Tile bookShelf47     = new BookShelf47(95);
    public static Tile bookShelf48     = new BookShelf48(96);

    public static Tile fundal1     = new Fundal1(97);
    public static Tile potiune1     = new Potiune1(98);

    public static Tile lemn     = new Lemn(99);
    public static Tile fundal2     = new Fundal2(100);

    public static Tile magic1     = new Magic1(101);
    public static Tile magic2     = new Magic2(102);
    public static Tile magic3     = new Magic3(103);
    public static Tile magic4     = new Magic4(104);
    public static Tile magic5     = new Magic5(105);
    public static Tile magic6     = new Magic6(106);
    public static Tile magic7     = new Magic7(107);
    public static Tile magic8     = new Magic8(108);
    public static Tile magic9     = new Magic9(109);
    public static Tile magic10    = new Magic10(110);
    public static Tile magic11    = new Magic11(111);
    public static Tile magic12    = new Magic12(112);
    public static Tile magic13     = new Magic13(113);
    public static Tile magic14    = new Magic14(114);
    public static Tile magic15    = new Magic15(115);
    public static Tile magic16    = new Magic16(116);
    public static Tile magic17     = new Magic17(117);
    public static Tile magic18    = new Magic18(118);
    public static Tile magic19    = new Magic19(119);
    public static Tile magic20    = new Magic20(120);
    public static Tile magic21    = new Magic21(121);
    public static Tile magic22    = new Magic22(122);
    public static Tile magic23    = new Magic23(123);


    public static Tile c1   = new C1(124);
    public static Tile c2   = new C2(125);
    public static Tile c3   = new C3(126);
    public static Tile c4   = new C4(127);
    public static Tile c5   = new C5(128);
    public static Tile c6   = new C6(129);


    public static Tile cc1   = new CC1(130);
    public static Tile cc2   = new CC2(131);
    public static Tile cc3   = new CC3(132);

    public static Tile web1   = new Web1(133);

    public static Tile lemn3     = new Lemn3(134);

    public static Tile ccc1   = new CCC1(135);
    public static Tile ccc2   = new CCC2(136);
    public static Tile ccc3   = new CCC3(137);
    public static Tile ccc4   = new CCC4(138);
    public static Tile ccc5   = new CCC5(139);
    public static Tile ccc6   = new CCC6(140);

    public static Tile fantoma1   = new Fantoma1(141);
    public static Tile fantoma2   = new Fantoma2(142);

    public static Tile panza1   = new Panza1(143);
    public static Tile panza2   = new Panza2(144);

    public static Tile ccc7   = new CCC7(145);
    public static Tile ccc8   = new CCC8(146);
    public static Tile ccc9   = new CCC9(147);
    public static Tile ccc10  = new CCC10(148);
    public static Tile ccc11  = new CCC11(149);
    public static Tile ccc12  = new CCC12(150);

    public static Tile c7   = new C7(151);
    public static Tile c8   = new C8(152);
    public static Tile c9   = new C9(153);
    public static Tile c10  = new C10(154);
    public static Tile c11  = new C11(155);
    public static Tile c12  = new C12(156);

    public static Tile panza3   = new Panza3(157);

    public static Tile geam1   = new Geam1(158);
    public static Tile geam2   = new Geam2(159);
    public static Tile geam3   = new Geam3(160);
    public static Tile geam4   = new Geam4(161);
    public static Tile geam5   = new Geam5(162);
    public static Tile geam6   = new Geam6(163);


    public static Tile podea1obstacol  = new Podea1Obstacol(164);
    public static Tile podea2jos  = new Podea2Jos(165);
    public static Tile lemnObstacol     = new LemnObstacol(166);






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

    public static Tile getTileSimple(int id) {
        if (id < 0 || id >= tiles.length) {
            return null;
        }
        return tiles[id];
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
