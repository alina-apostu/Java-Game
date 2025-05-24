package PaooGame.Tiles.ForLevel3.CarteJos2;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class CCC9 extends Tile
{

        public CCC9(int id)
        {
            /// Apel al constructorului clasei de baza
            super(Assets.ccc9, id);
        }
    public boolean IsSolid()
    {
        return true;
    }

}
