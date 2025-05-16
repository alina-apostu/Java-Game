package PaooGame;

import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Graphics;
import PaooGame.Items.Hero;
import PaooGame.Items.NPC.*;
import PaooGame.Tiles.Tile;
import PaooGame.Maps.Map;
import PaooGame.RefLinks;


public class CollisionHandler
{
    private String powerMessage = "";
    private boolean isPowerMessageDisplayed = false;
    private final int tileSize = 32;
    private RefLinks refLinks;


    public CollisionHandler(RefLinks refLinks) {
        this.refLinks = refLinks;
        CollisionStrategyRegistry.registerStrategy(0, new Floor1());
        CollisionStrategyRegistry.registerStrategy(57, new BookTile());//podea1
    }

    public void checkCollision(Hero hero, Mouse mouse)
    {
        if(hero.getBounds().intersects(mouse.getBounds()) && mouse.isConsumed() == false)
        {
            String color = mouse.getCurrentColor();

            switch (color)
            {
                case "purple":
                    hero.setPower("minge de foc");
                    powerMessage = "You have gained the Fireball power!";
                    break;
                case "green":
                    hero.setPower("zbor");
                    powerMessage = "";
                    break;
                case "blue":
                    hero.setPower("invizibilitate");
                    powerMessage = "";
                    break;
            }
            mouse.setConsumed(true);

            Game.isPaused = true;
            hero.SetYMove(0);
            hero.SetXMove(0);

            showPowerMessage(hero, mouse);
        }
    }

    public void showPowerMessage(Hero hero, Mouse mouse)
    {
        if(isPowerMessageDisplayed == false)
        {
            Game.isPaused = true;
            // cream un dialog nou
            JDialog dialog = new JDialog();
            dialog.setTitle("Power Gained");
            dialog.setModal(true); // adica utilizatorul trebuie sa inchida aceasta fereastra inainte de a reveni la joc
            dialog.setLayout(new BorderLayout());
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // la inchidere dialocul sa fie inchis nu toata aplicatia
            dialog.setSize(300,150); // dimensiunea ferestrei
            dialog.setLocationRelativeTo(null);

            JLabel messageLabel = new JLabel(powerMessage, SwingConstants.CENTER); // cream o eticheta care contine mesajul si il centram orizontal
            dialog.add(messageLabel, BorderLayout.CENTER); // adaugam eticheta cu mesajul in mijlocul ferestrei

            JButton continueButton = new JButton("Continue"); // cream un buton pe care scrie continue
            continueButton.addActionListener(e ->
            {
                dialog.dispose();
                isPowerMessageDisplayed = false;
                Game.isPaused = false;
                Game.getRefLinks().GetKeyManager().resetKeys();

                mouse.showSparkle(); // afisam sclipiciul
            });

            // panou pentru buton
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(continueButton);
            dialog.add(buttonPanel,BorderLayout.SOUTH); // si il adaugam in sudul ferestrei

            isPowerMessageDisplayed = true;
            dialog.setVisible(true);
        }
    }


    public void checkTileCollision(Hero hero)
    {
        Rectangle heroBounds = hero.getBounds();
        int tileSize = Tile.TILE_WIDTH;

        int tileLeft = heroBounds.x / tileSize; // coloana tileului din stânga unde începe eroul
        int tileRight = (heroBounds.x + heroBounds.width) / tileSize;
        int tileTop = heroBounds.y / tileSize; // linia tileului de sus unde începe eroul
        int tileBottom = (heroBounds.y + heroBounds.height) / tileSize;

        Map map = refLinks.GetMap();

        for (int layer = 0; layer < map.getNUM_LAYERS(); layer++)
        {
            for (int row = tileTop; row <= tileBottom; row++)
            {
                for (int col = tileLeft; col <= tileRight; col++)
                {
                    Tile tile = map.GetTile(layer, col, row);
                    if (tile == null || !tile.IsSolid()) continue;

                    Rectangle tileBounds = new Rectangle(col * tileSize, row * tileSize, tileSize, tileSize);

                    if (heroBounds.intersects(tileBounds))
                    {
                        int tileId = tile.GetId();
                        CollisionStrategy strategy = CollisionStrategyRegistry.getStrategy(tileId);
                        if (strategy != null) {
                            strategy.handleCollision(hero, tileBounds);
                        }

                    }
                }
            }
        }
    }












}
