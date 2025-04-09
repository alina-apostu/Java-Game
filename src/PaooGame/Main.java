package PaooGame;

import PaooGame.GameWindow.GameWindow;

public class Main
{
    public static void main(String[] args)
    {
        Game paooGame = new Game("PaooGame", 640, 192);
        paooGame.StartGame();
    }
}
