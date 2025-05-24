package PaooGame.Collision;


import PaooGame.Items.Character;
import PaooGame.Items.Hero;
import PaooGame.Maps.Map;
import PaooGame.PublicGamaData;
import PaooGame.States.PlayState;
import PaooGame.States.State;
import PaooGame.DataBase.DataBaseManager;

import java.awt.Rectangle;

public class LevelTransitionTile implements CollisionStrategy {
    private boolean levelChanged = false;

    @Override
    public void handleCollisionTile(Hero hero, Rectangle tileBounds) {
        if (!levelChanged && hero.getBounds().intersects(tileBounds)) {
            levelChanged = true;

            int CurrentLevel = hero.GetReflink().GetMap().getLevelIndex();
            int nextLevel = CurrentLevel + 1;

            if (nextLevel <= 3) {
                PublicGamaData.currentLevel=nextLevel;
                // se seteaza noua hartă
                System.out.println("nextLevel="+nextLevel);
                hero.GetReflink().SetMap(new Map(hero.GetReflink(), nextLevel));



                PlayState nextState = new PlayState(hero.GetReflink(), hero.getCharacterType(),nextLevel,hero.getPlayerName());

                // Schimbăm starea curentă
                State.SetState(nextState);
            } else {
                System.out.println("Ai terminat toate nivelurile!");


            }
        }
    }

    @Override
    public void handleCollisionCharacter(Hero hero, Character character)
    {

    }
}
