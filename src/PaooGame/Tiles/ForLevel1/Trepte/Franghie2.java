package PaooGame.Tiles.ForLevel1.Trepte;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class Franghie2 extends Tile
{
    public Franghie2(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.franghie2, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }

}
