package PaooGame;

import PaooGame.Maps.Map;
import PaooGame.GameWindow.GameWindow;

public class Main
{
    public static void main(String[] args)
    {
        Game paooGame = new Game("PaooGame", 3200, 2*6*32);
        paooGame.StartGame();
    }
}
