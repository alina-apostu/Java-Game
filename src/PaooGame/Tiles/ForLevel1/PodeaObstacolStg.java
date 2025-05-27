package PaooGame.Tiles.ForLevel1;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class PodeaObstacolStg extends Tile
{

        public PodeaObstacolStg(int id)
        {
            /// Apel al constructorului clasei de baza
            super(Assets.podeastg, id);
        }


      @Override
        public boolean IsSolid()
    {
        return true;
    }

}
