package PaooGame.Tiles.ForLevel3.Magic;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class Magic11 extends Tile
{

        public Magic11(int id)
        {
            /// Apel al constructorului clasei de baza
            super(Assets.magic11, id);
        }

    @Override
    public boolean IsSolid()
    {
        return true;
    }


}
