package PaooGame.Maps;

import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.io.*;
//import java.util.*;

/*! \class public class Map
    \brief Implementeaza notiunea de harta a jocului.
 */
public class Map {
    private RefLinks refLink;   /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private int width;          /*!< Latimea hartii in numar de dale.*/
    private int height;         /*!< Inaltimea hartii in numar de dale.*/
    //private int [][] tiles;     /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/
    private int[][][] layers;
    private final int NUM_LAYERS = 3; // numar layere
    private int Width2 = 32 * 100;
    private int Height2 = 32 * 6;

    /*! \fn public Map(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public Map(RefLinks refLink, int mapId) {
        /// Retine referinta "shortcut".
        this.refLink = refLink;

        ///incarca harta de start. Functia poate primi ca argument id-ul hartii ce poate fi incarcat.
        LoadWorld(mapId);
    }

    /*! \fn public  void Update()
        \brief Actualizarea hartii in functie de evenimente (un copac a fost taiat)
     */
    public void Update() {

    }

    /*! \fn public void Draw(Graphics g)
        \brief Functia de desenare a hartii.

        \param g Contextl grafi in care se realizeaza desenarea.
     */
    /*public void Draw(Graphics g)
    {
            ///Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        for(int y = 0; y < Height2/Tile.TILE_HEIGHT; y++)
        {
            for(int x = 0; x < Width2/Tile.TILE_WIDTH; x++)
            {
                if(GetTile(x,y)!=null) {
                    GetTile(x, y).Draw(g, (int) x * Tile.TILE_HEIGHT, (int) y * Tile.TILE_WIDTH);
                }
            }
        }
    }*/

    public void Draw(Graphics g) {
        ///Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        for (int layer = 0; layer < NUM_LAYERS; layer++) {
            for (int y = 0; y < Height2 / Tile.TILE_HEIGHT; y++) {
                for (int x = 0; x < Width2 / Tile.TILE_WIDTH; x++) {
                    if (GetTile(x, y,layer) != null) {
                        GetTile(x, y,layer).Draw(g, (int) x * Tile.TILE_HEIGHT, (int) y * Tile.TILE_WIDTH);
                    }
                }
            }
        }
    }

    /*! \fn public Tile GetTile(int x, int y)
        \brief Intoarce o referinta catre dala aferenta codului din matrice de dale.

        In situatia in care dala nu este gasita datorita unei erori ce tine de cod dala, coordonate gresite etc se
        intoarce o dala predefinita (ex. grassTile, mountainTile)
     */
    /*public Tile GetTile(int x, int y)
    {
        if(x < 0 || y < 0 || x >= width || y >= height)
        {
            return Tile.grassTile;
        }
        if (tiles[x][y] == -1) {
            return null;  // sau returnează o referință la o dală goală
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
        {
            return Tile.mountainTile;
        }
        return t;
    }*/

    public Tile GetTile(int x, int y, int layer) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.podea1;
        }
        if (layer < 0 || layer >= NUM_LAYERS) {
            return Tile.podea1;  // Poți returna un tile implicit dacă stratul nu este valid
        }


            int tileId = layers[layer][x][y];
            if (tileId == -1) {
                return null;  // sau returnează o referință la o dală goală
            }
            Tile t = Tile.tiles[tileId];
            if (t == null) {
                return Tile.podea1;
            }

        return t;
    }

    public int GetWidth() {
        return this.width;
    }

    public int GetHeight() {
        return this.height;
    }

    /*! \fn private void LoadWorld()
        \brief Functie de incarcare a hartii jocului.
        Aici se poate genera sau incarca din fisier harta. Momentan este incarcata static.
     */
    private void LoadWorld(int mapId) {
        //atentie latimea si inaltimea trebuiesc corelate cu dimensiunile ferestrei sau
        //se poate implementa notiunea de camera/cadru de vizualizare al hartii
        ///Se stabileste latimea hartii in numar de dale.
        width = 100;
        ///Se stabileste inaltimea hartii in numar de dale
        height = 6;
        ///Se construieste matricea de coduri de dale
        /*tiles = new int[width][height];
        //Se incarca matricea cu coduri
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = MiddleEastMap(y, x);
            }
        }*/
        layers = new int[NUM_LAYERS][width][height];

        String mapPath = "";
        switch (mapId) {
            case 1:
                mapPath = "/textures/mapsFile/map1.txt"; // Harta 1
                break;
            case 2:
                mapPath = "/textures/mapsFile/map2.txt"; // Harta 2
                break;
            case 3:
                mapPath = "/textures/mapsFile/map3.txt"; // Harta 3
                break;
            default:
                mapPath = "/textures/mapsFile/map1.txt"; // Dacă ID-ul nu este valid, se încarcă harta 1
                break;
        }
        loadLayersFromFile(mapPath, NUM_LAYERS);
    }

    private void loadLayersFromFile(String path, int numLayers) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            if (is == null) {
                throw new RuntimeException("Fișierul hărții nu a fost găsit: " + path);
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int layer = 0; layer < numLayers; layer++) {
                for (int y = 0; y < height; y++) {
                    String line = br.readLine();
                    if (line == null) {
                        throw new IOException("Fișierul s-a terminat neașteptat la layer " + layer + ", linia " + y);
                    }
                    // Verifică dacă linia este goală și sare peste ea
                    if (line.trim().isEmpty()) {
                        y--;  // scade 'y' pentru a nu trece peste o linie în continuare
                        continue;
                    }

                    String[] tokens = line.trim().split("\\s+");
                    for (int x = 0; x < width; x++) {
                        layers[layer][x][y] = Integer.parseInt(tokens[x]);
                    }
                }
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Eroare la încărcarea hărții: " + e.getMessage());
        }
    }





    /*! \fn private int MiddleEastMap(int x ,int y)
        \brief O harta incarcata static.

        \param x linia pe care se afla codul dalei de interes.
        \param y coloana pe care se afla codul dalei de interes.
     */
        /*private int MiddleEastMap ( int x, int y)
        {
            ///Definire statica a matricei de coduri de dale.
            final int map[][] = {
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,                     0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 8, 12, 16, 20, -1, -1, 24, 28, 32, 36, -1, -1, 7, 6, -1, 0, 0, 0, -1,        8, 12,  16, 20, -1, 24, 28, 32, 36, -1, 8 , 12, 16, 20, -1, -1, -1, -1, -1, -1,    41, 45, 49, 53, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 9, 13, 17, 21, -1, -1, 25, 29, 33, 37, -1, -1, 7, 6, -1, -1, 0, -1, -1,      9, 13,  17, 21, -1, 25, 29, 33, 37, -1, 9 , 13, 17, 21, -1, -1, -1, -1, -1, -1,    42, 46, 50, 54, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 10, 14, 18, 22, -1, -1, 26, 30, 34, 38, -1, -1, 7, 6, -1, -1, -1, -1, -1,    10, 14, 18, 22, -1, 26, 30, 34, 38, -1, 10, 14, 18, 22, -1, -1, -1, -1, -1, -1,    43, 47, 51, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 11, 15, 19, 23, -1, -1, 27, 31, 35, 39, -1, -1, 7, 6, -1, -1, -1, -1, -1,    11, 15, 19, 23, -1, 27, 31, 35, 39, -1, 11, 15, 19, 23, -1, -1,  0, -1, -1, -1,    44, 48, 52, 56, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 4, 0, 0, 0, 0, 0,                     0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}

            };
            return map[x][y];
        }*/


}
