package PaooGame;

import java.awt.Rectangle;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import PaooGame.Items.Hero;
import PaooGame.Items.NPC.*;
import PaooGame.Maps.Map;
import PaooGame.Tiles.Tile;


public class CollisionHandler
{
    private String powerMessage = "";
    private boolean isMessageDisplayed = false;
    private final int tileSize = 32;
    private RefLinks refLinks;


    public CollisionHandler(RefLinks refLinks) {
        this.refLinks = refLinks;
        //CollisionStrategyRegistry.registerStrategy(0, new Floor1());
        CollisionStrategyRegistry.registerStrategy(57, new BookTile());
        CollisionStrategyRegistry.registerStrategy(58, new BookTile());
        CollisionStrategyRegistry.registerStrategy(98, new PotionTile());
    }

    public void checkCollisionMouse(Hero hero, Mouse mouse)
    {
        // soricei
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

    public void checkCollisionRedSpider(Hero hero, RedSpider redSpider)
    {
        // paianjeni rosii
        // panza
        Rectangle webBounds = redSpider.getWebBounds();
        if(webBounds != null && hero.getBounds().intersects(webBounds))
        {
            Game.isPaused = true;
            hero.SetXMove(0);
            hero.SetYMove(0);
            showWebCaughtMessage();
        }
    }

    public void showPowerMessage(Hero hero, Mouse mouse)
    {
        if(isMessageDisplayed == false)
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
                isMessageDisplayed = false;
                Game.isPaused = false;
                Game.getRefLinks().GetKeyManager().resetKeys();

                mouse.showSparkle(); // afisam sclipiciul
            });

            // panou pentru buton
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(continueButton);
            dialog.add(buttonPanel,BorderLayout.SOUTH); // si il adaugam in sudul ferestrei

            isMessageDisplayed = true;
            dialog.setVisible(true);
        }
    }

    private void showWebCaughtMessage()
    {
        if(isMessageDisplayed == false)
        {
            JDialog dialog = new JDialog();
            dialog.setTitle("Caught in web!");
            dialog.setModal(true);
            dialog.setLayout(new BorderLayout());
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setSize(300, 150);
            dialog.setLocationRelativeTo(null);

            JLabel messageLabel = new JLabel("You've been caught by the red spider's web! You lost one life!", SwingConstants.CENTER);
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


    public void checkTileCollision(Hero hero)
    {
        Rectangle heroBounds = hero.getBounds();
        int tileSize = Tile.TILE_WIDTH;

        int tileLeft = heroBounds.x / tileSize; // coloana tileului din stânga unde începe eroul
        int tileRight = (heroBounds.x + heroBounds.width) / tileSize;
        int tileTop = heroBounds.y / tileSize; // linia tileului de sus unde începe eroul
        int tileBottom = (heroBounds.y + heroBounds.height) / tileSize;



        Map map = refLinks.GetMap();

       // System.out.println("mapid="+map.getLevelIndex());

        for (int layer = 0; layer <map.getNUM_LAYERS(); layer++)
        {
           // System.out.println("layer="+layer);
            for (int row = tileTop; row <= tileBottom; row++)
            {
                for (int col = tileLeft; col <= tileRight; col++)
                {
                    Tile tile = map.GetTile(col,row,layer);
                    if (tile != null && tile.IsSolid())
                    {
                        System.out.println(tileLeft + " " + tileRight + " " + tileTop + " " + tileBottom);

                        System.out.println("Tile ID: " + tile.GetId() + " at " + col + "," + row);


                        Rectangle tileBounds = new Rectangle(col * tileSize, row * tileSize, tileSize, tileSize);

                        System.out.println(tileBounds);


                        if (heroBounds.intersects(tileBounds)) {
                            System.out.println(heroBounds + " " + tileBounds);
                            int tileId = tile.GetId();
                            CollisionStrategy strategy = CollisionStrategyRegistry.getStrategy(tileId);
                            if (strategy != null) {
                                strategy.handleCollisionTile(hero, tileBounds);
                            }

                        }
                    }
                }
            }
        }
    }








}
