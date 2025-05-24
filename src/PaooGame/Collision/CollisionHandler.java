package PaooGame.Collision;

import java.awt.Rectangle;

import PaooGame.Items.Hero;
import PaooGame.Items.Character;
import PaooGame.Items.NPC.*;
import PaooGame.Items.ShadowSpider;
import PaooGame.Items.SpiderBlue;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;
import PaooGame.Maps.Map;


public class CollisionHandler
{
    private final int tileSize = 32;
    private RefLinks refLinks;

    public CollisionHandler(RefLinks refLinks)
    {
        this.refLinks = refLinks;
        //CollisionStrategyRegistry.registerStrategy(0, new Floor1());
        CollisionStrategyRegistry.registerStrategy(57, new BookTile());
        CollisionStrategyRegistry.registerStrategy(58, new BookTile());//podea1
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
                if(redSpider.isDead() == false)
                {
                    CollisionStrategy strategy = CollisionStrategyRegistry.getCharacterStrategy(RedSpider.class);
                    if (strategy != null)
                        strategy.handleCollisionCharacter(hero, character);
                }
            }
        }
        else if(character instanceof SpiderBlue)
        {
            SpiderBlue blueSpider = (SpiderBlue) character;
            if (hero.getBounds().intersects(blueSpider.getBounds()) && hero.itWasStungByBlueSpider(character) == false)
            {
                if(blueSpider.isDead() == false)
                {
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

    public void checkTileCollision(Hero hero) {
        Rectangle heroBounds = hero.getBounds();
        int tileSize = Tile.TILE_WIDTH;

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

                        //System.out.println(tileBounds);


                        if (heroBounds.intersects(tileBounds)) {
                            //System.out.println(heroBounds + " " + tileBounds);
                            int tileId = tile.GetId();
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