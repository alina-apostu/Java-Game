package PaooGame.Collision;

import PaooGame.Game;
import PaooGame.Items.Character;
import PaooGame.Items.Hero;
import PaooGame.Items.NPC.RedSpider;
import PaooGame.PublicGamaData;

import javax.swing.*;
import java.awt.*;

public class RedSpiderCollision implements CollisionStrategy
{
    private boolean isMessageDisplayed = false;

    @Override
    public void handleCollisionTile(Hero hero, Rectangle tileBounds)
    {
        // nu se foloseste aici
    }

    @Override
    public void handleCollisionCharacter(Hero hero, Character character)
    {
        RedSpider redSpider = (RedSpider)character;
        Rectangle webBounds = redSpider.getWebBounds();

        if (webBounds != null && redSpider.isWebFullyOpened() == true)
        {
            PublicGamaData.subbScore(50);

            Game.isPaused = true;
            hero.SetXMove(0);
            hero.SetYMove(0);
            showWebCaughtMessage();
        }
        else if (hero.itWasStungByRedSpider(character) == false && hero.getBounds().intersects(redSpider.getBounds()))
        {
            Game.isPaused = true;
            hero.SetXMove(0);
            hero.SetYMove(0);
            showMessage();
            hero.setWasStungByRedSpider(redSpider);
        }
    }

    private void showWebCaughtMessage()
    {
        if (isMessageDisplayed == false) {
            JDialog dialog = new JDialog();
            dialog.setTitle("Caught in a red spider web!");
            dialog.setModal(true);
            dialog.setLayout(new BorderLayout());
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setSize(400, 150);
            dialog.setLocationRelativeTo(null);

            JLabel messageLabel = new JLabel("<html>You've been caught by the red spider's web!<br>You lost one life!</html>", SwingConstants.CENTER);
            dialog.add(messageLabel, BorderLayout.CENTER);

            JButton continueButton = new JButton("Continue");
            continueButton.addActionListener(e ->
            {
                dialog.dispose();
                isMessageDisplayed = false;
                Game.isPaused = false;
                Game.getRefLinks().GetKeyManager().resetKeys();
            });

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(continueButton);
            dialog.add(buttonPanel, BorderLayout.SOUTH);

            isMessageDisplayed = true;
            dialog.setVisible(true);
        }
    }

    public void showMessage()
    {
        if (isMessageDisplayed == false)
        {
            JDialog dialog = new JDialog();
            dialog.setTitle("Stung by a red spider!");
            dialog.setModal(true);
            dialog.setLayout(new BorderLayout());
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setSize(400, 150);
            dialog.setLocationRelativeTo(null);

            JLabel messageLabel = new JLabel("<html>You've been stung by a red spider!<br>You lost one life!</html>", SwingConstants.CENTER);
            dialog.add(messageLabel, BorderLayout.CENTER);

            JButton continueButton = new JButton("Continue");
            continueButton.addActionListener(e ->
            {
                dialog.dispose();
                isMessageDisplayed = false;
                Game.isPaused = false;
                Game.getRefLinks().GetKeyManager().resetKeys();
            });

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(continueButton);
            dialog.add(buttonPanel, BorderLayout.SOUTH);

            isMessageDisplayed = true;
            dialog.setVisible(true);
        }
    }
}
