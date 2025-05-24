package PaooGame.Collision;

import PaooGame.DataBase.DataBaseManager;
import PaooGame.Game;
import PaooGame.Items.Hero;
import PaooGame.Items.Character;
import PaooGame.PublicGamaData;

import javax.swing.*;
import java.awt.*;



public class WinTile implements CollisionStrategy {

    private boolean triggered = false; // ne asigurăm că se execută o singură dată

    public WinTile() {
        System.out.println("Finish tile collision created");
    }

    @Override
    public void handleCollisionTile(Hero hero, Rectangle tileBounds) {
        if (triggered) return;

        if (hero.getBounds().intersects(tileBounds)) {
            triggered = true;

            Game.isPaused = true;
            hero.SetXMove(0);
            hero.SetYMove(0);

            JOptionPane.showMessageDialog(null,
                    "Congratulations!\nYou finished the game!!",
                    "Victory",
                    JOptionPane.INFORMATION_MESSAGE);


            //salvare nume jucator si scor in baza de date

            DataBaseManager db = DataBaseManager.getInstance();
            db.connect();
            db.createTable(); // doar o dată e ok, nu se strică dacă există deja

            String playerName = PublicGamaData.playerName;
            int score = PublicGamaData.score;
            String character = PublicGamaData.characterType;
            int level =PublicGamaData.currentLevel;

            if (playerName != null && character != null) {
                db.savePlayer(playerName, score, level, character);
            }

            db.close();

            try {
                Thread.sleep(500); // Așteaptă 0.5 secunde înainte sa se inchida jocul
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.exit(0); // se inchide jocul
        }
    }

    @Override
    public void handleCollisionCharacter(Hero hero, Character character) {
        // Nu tratezi coliziuni cu personaje în această strategie
    }
}
