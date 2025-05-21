package PaooGame.Tiles.ForLevel3.CarteJos1;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class CCC3 extends Tile
{

        public CCC3(int id)
        {
            /// Apel al constructorului clasei de baza
            super(Assets.ccc3, id);
        }
    @Override
    public boolean IsSolid()
    {
        return true;
    }

}
