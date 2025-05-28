package PaooGame.Collision;

import java.awt.*;

import java.util.Set;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import PaooGame.Game;
import PaooGame.Items.Hero;
import PaooGame.Items.Character;
import PaooGame.Items.NPC.*;
import PaooGame.Items.ShadowSpider;
import PaooGame.Items.SpiderBlue;
import PaooGame.Maps.Map;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;



public class CollisionHandler
{

    private final int tileSize = 32;
    private RefLinks refLinks;

    public CollisionHandler(RefLinks refLinks)
    {
        this.refLinks = refLinks;
        //CollisionStrategyRegistry.registerStrategy(0, new Floor1());
        CollisionStrategyRegistry.registerStrategy(57, new BookTile());
        CollisionStrategyRegistry.registerStrategy(58, new BookTile());
        CollisionStrategyRegistry.registerStrategy(98, new PotionTile());
        CollisionStrategyRegistry.registerStrategy(2, new WallTileStgDrt());
        CollisionStrategyRegistry.registerStrategy(164, new ObstacolStgDrtUp());
        CollisionStrategyRegistry.registerStrategy(0, new TavanTile());


        //nivel2
        CollisionStrategyRegistry.registerStrategy(67, new ObstacolStgDrtUp());
        CollisionStrategyRegistry.registerStrategy(165, new TavanTile());

        //nivel3
        CollisionStrategyRegistry.registerStrategy(99, new TavanTile());
        CollisionStrategyRegistry.registerStrategy(138, new ObstacolStgDrtUp());
        CollisionStrategyRegistry.registerStrategy(139, new ObstacolStgDrtUp());

        CollisionStrategyRegistry.registerStrategy(148, new ObstacolStgDrtUp());
        CollisionStrategyRegistry.registerStrategy(149, new ObstacolStgDrtUp());

        CollisionStrategyRegistry.registerStrategy(166, new ObstacolStgDrtUp());

        CollisionStrategyRegistry.registerStrategy(158, new ObstacolStgDrtUp());
        CollisionStrategyRegistry.registerStrategy(159, new ObstacolStgDrtUp());
        CollisionStrategyRegistry.registerStrategy(160, new ObstacolStgDrtUp());
        CollisionStrategyRegistry.registerStrategy(161, new ObstacolStgDrtUp());
        CollisionStrategyRegistry.registerStrategy(162, new ObstacolStgDrtUp());
        CollisionStrategyRegistry.registerStrategy(163, new ObstacolStgDrtUp());

        CollisionStrategyRegistry.registerStrategy(48, new LevelTransitionTile());

        CollisionStrategyRegistry.registerStrategy(110, new WinTile());

        //CollisionStrategyRegistry.registerStrategy(5, new CrackedTile());
        //CollisionStrategyRegistry.registerStrategy(4, new CrackedTile());
        CollisionStrategyRegistry.registerStrategy(167, new WallDrt());
        CollisionStrategyRegistry.registerStrategy(168, new WallTileStgDrt());

        CollisionStrategyRegistry.registerStrategy(7, new CrackedTile());
        //CollisionStrategyRegistry.registerStrategy(6, new CrackedTile());



        CollisionStrategyRegistry.registerCharacterStrategy(Mouse.class, new MouseCollision());
        CollisionStrategyRegistry.registerCharacterStrategy(RedSpider.class, new RedSpiderCollision());
        CollisionStrategyRegistry.registerCharacterStrategy(SpiderBlue.class, new BlueSpiderCollision());
        CollisionStrategyRegistry.registerCharacterStrategy(ShadowSpider.class, new ShadowSpiderCollision());
    }

    public void checkCharacterCollision(Hero hero, Character character)
    {
        if(character == null) return;

        if (character instanceof Mouse)
        {
            Mouse mouse = (Mouse)character;
            if (hero.getBounds().intersects(mouse.getBounds()))
            {
                CollisionStrategy strategy = CollisionStrategyRegistry.getCharacterStrategy(Mouse.class);
                if (strategy != null)
                    strategy.handleCollisionCharacter(hero, character);
            }
        }
        else if (character instanceof RedSpider)
        {
            RedSpider redSpider = (RedSpider)character;
            Rectangle webBounds = redSpider.getWebBounds();

            if ((webBounds != null && hero.getBounds().intersects(webBounds)) || (hero.itWasStungByRedSpider(character) == false && hero.getBounds().intersects(redSpider.getBounds())))
            {

                hero.loseLife();//eroul pierde o viata de fiecare data cand se intalneste cu un paianjen
                CollisionStrategy strategy = CollisionStrategyRegistry.getCharacterStrategy(RedSpider.class);
                if (strategy != null)
                    strategy.handleCollisionCharacter(hero, character);
                //hero.setWasStungByRedSpider(character);

            }
        }
        else if(character instanceof SpiderBlue)
        {
            SpiderBlue blueSpider = (SpiderBlue) character;
            if (hero.getBounds().intersects(blueSpider.getBounds()) && hero.itWasStungByBlueSpider(character) == false)
            {
                if(blueSpider.isDead() == false)
                {
                    hero.loseLife();//eroul pierde o viata de fiecare data cand se intalneste cu un paianjen
                    CollisionStrategy strategy = CollisionStrategyRegistry.getCharacterStrategy(SpiderBlue.class);
                    if (strategy != null)
                        strategy.handleCollisionCharacter(hero, character);
                }
            }
        }
        else if(character instanceof ShadowSpider)
        {
            ShadowSpider shadowSpider = (ShadowSpider)character;
            Rectangle webBounds = shadowSpider.getWebBounds();

            if ((webBounds != null && hero.getBounds().intersects(webBounds)) || (hero.getBounds().intersects(shadowSpider.getBounds())))
            {
                if(shadowSpider.isDead() == false)
                {
                    CollisionStrategy strategy = CollisionStrategyRegistry.getCharacterStrategy(ShadowSpider.class);
                    if (strategy != null)
                        strategy.handleCollisionCharacter(hero, character);
                }
            }
        }
    }

    public void checkTileCollision(Hero hero, Graphics2D g) {

        Set<Integer> debugTileIds = Set.of(57, 58, 7, 6); //pentru debug tilebounds
        hero.setCrackedTileCollisionThisFrame(false);//pentru actualizarea la fiecare frame, coliziune cu fisurile din podea


        Rectangle heroBounds = hero.getBounds();
        int tileSize = Tile.TILE_WIDTH;

        hero.resetOnTile();


        int tileLeft = heroBounds.x / tileSize; // coloana tileului din stânga unde începe eroul
        int tileRight = (heroBounds.x + heroBounds.width) / tileSize;
        int tileTop = heroBounds.y / tileSize; // linia tileului de sus unde începe eroul
        int tileBottom = (heroBounds.y + heroBounds.height) / tileSize;


        Map map = refLinks.GetMap();

        // System.out.println("mapid="+map.getLevelIndex());

        for (int layer = 0; layer < map.getNUM_LAYERS(); layer++) {
            // System.out.println("layer="+layer);
            for (int row = tileTop; row <= tileBottom; row++) {
                for (int col = tileLeft; col <= tileRight; col++) {
                    Tile tile = map.GetTile(col, row, layer);
                    if (tile != null && tile.IsSolid()) {

                        //System.out.println(tileLeft + " " + tileRight + " " + tileTop + " " + tileBottom);

                        //System.out.println("Tile ID: " + tile.GetId() + " at " + col + "," + row);
                        Rectangle tileBounds = new Rectangle(col * tileSize, row * tileSize, tileSize, tileSize);

                        /*int tileId1 = tile.GetId();
                        if(tileId1==5 || tileId1==4)//
                        {
                            tileBounds = new Rectangle(col * tileSize, ((row) * (tileSize)-20 ), tileSize , (tileSize +15));

                            if (debugTileIds.contains(tile.GetId())) {
                                g.setColor(Color.RED);
                                g.drawRect(tileBounds.x, tileBounds.y, tileBounds.width, tileBounds.height);
                            }
                        }*/


                        if (heroBounds.intersects(tileBounds)) {

                            int heroBottomY = heroBounds.y + heroBounds.height;
                            int tileTopY = tileBounds.y;

                            // dacă partea de jos a eroului e aproape de partea de sus a tile-ului (adică stă pe el)
                            if (Math.abs(heroBottomY - tileTopY) <= 5) {
                                hero.setOnTile(true);
                            }

                            //System.out.println(heroBounds + " " + tileBounds);
                            int tileId = tile.GetId();
                            if(tileId==58 || tileId==57 )//daca sunt tileuri de tip carti, micsoram putin inaltimea lui tilebounds
                            {
                                tileBounds = new Rectangle(col * tileSize, ((row) * (tileSize)+15), tileSize-5, (tileSize-15));

                                /*if (debugTileIds.contains(tile.GetId())) {
                                    g.setColor(Color.RED);
                                    g.drawRect(tileBounds.x, tileBounds.y, tileBounds.width, tileBounds.height);
                                }*/


                                if (heroBounds.intersects(tileBounds)) {
                                    CollisionStrategy strategy = CollisionStrategyRegistry.getStrategy(tileId);
                                    if (strategy != null) {
                                        strategy.handleCollisionTile(hero, tileBounds);
                                    }

                                }

                            }

                            else  if(tileId==7 )
                            {
                                tileBounds = new Rectangle((col * tileSize+17), ((row) * (tileSize)+15), tileSize-15, (tileSize-28));

                                /*if (debugTileIds.contains(tile.GetId())) {
                                    g.setColor(Color.RED);
                                    g.drawRect(tileBounds.x, tileBounds.y, tileBounds.width, tileBounds.height);
                                }*/ //pentru a vedea fizic limitele tile ului


                                if (heroBounds.intersects(tileBounds)) {
                                    CollisionStrategy strategy = CollisionStrategyRegistry.getStrategy(tileId);
                                    if (strategy != null) {
                                        strategy.handleCollisionTile(hero, tileBounds);
                                    }

                                }

                            }
                            else  if(tileId==6 )
                            {
                                tileBounds = new Rectangle((col * tileSize+10), ((row) * (tileSize)+15), tileSize-15, (tileSize-24));

                                /*if (debugTileIds.contains(tile.GetId())) {
                                    g.setColor(Color.RED);
                                    g.drawRect(tileBounds.x, tileBounds.y, tileBounds.width, tileBounds.height);
                                } *///pentru a vedea fizic limitele tile ului


                                if (heroBounds.intersects(tileBounds)) {
                                    CollisionStrategy strategy = CollisionStrategyRegistry.getStrategy(tileId);
                                    if (strategy != null) {
                                        strategy.handleCollisionTile(hero, tileBounds);
                                    }

                                }

                            }


                            else {
                                CollisionStrategy strategy = CollisionStrategyRegistry.getStrategy(tileId);

                                if (strategy != null) {
                                    strategy.handleCollisionTile(hero, tileBounds);
                                }
                            }

                        }
                    }
                }
            }
        }

    }
}

