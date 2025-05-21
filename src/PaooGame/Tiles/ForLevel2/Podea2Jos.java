package PaooGame.Tiles.ForLevel2;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class Podea2Jos extends Tile
{

        public Podea2Jos(int id)
        {
            /// Apel al constructorului clasei de baza
            super(Assets.podea2, id);
        }
    public boolean IsSolid()
    {
        return true;
    }


}
