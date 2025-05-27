package PaooGame.Tiles.ForLevel1.Trepte;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class TreaptaJosFaraColiz extends Tile
{
    public TreaptaJosFaraColiz(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.treaptaJosFaraColiz, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }

}
