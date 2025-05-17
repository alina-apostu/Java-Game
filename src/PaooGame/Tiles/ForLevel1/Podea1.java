package PaooGame.Tiles.ForLevel1;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class Podea1 extends Tile
{

        public Podea1(int id)
        {
            /// Apel al constructorului clasei de baza
            super(Assets.podea1, id);
        }


       @Override
        public boolean IsSolid()
    {
        return true;
    }

}
