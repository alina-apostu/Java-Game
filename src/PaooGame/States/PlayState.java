package PaooGame.States;

import PaooGame.DataBase.DataBaseManager;
import PaooGame.Game;
import PaooGame.Items.Character;
import PaooGame.Graphics.Assets;
import PaooGame.Items.Hero;
import PaooGame.Items.NPC.Mouse;
import PaooGame.Items.NPC.RedSpider;
import PaooGame.Items.ShadowSpider;
import PaooGame.Items.SpiderBlue;
import PaooGame.Maps.Map;
import PaooGame.PublicGameData;
import PaooGame.RefLinks;
import PaooGame.Collision.CollisionHandler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

// clasa PlayState implementeaza/controleaza jocul
public class PlayState extends State
{
    private Hero hero;  /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    private Map map;    /*!< Referinta catre harta curenta.*/
    
    private ArrayList<Mouse> mice;

    private ArrayList<RedSpider> redSpiders;

    private ArrayList<SpiderBlue> blueSpiders;

    private CollisionHandler collisionHandler;

    private ShadowSpider shadowSpider;

    public PlayState(RefLinks refLink, String selectedCharacter, int levelIndex, String playerName)
    {
        //Apel al constructorului clasei de baza
        super(refLink);

        //Construieste harta jocului
        map = new Map(refLink,levelIndex);
        //Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);

        //Construieste eroul
        hero = new Hero(refLink,PublicGameData.playerPosX, PublicGameData.playerPosY, selectedCharacter);
        hero.setPlayerName(playerName);
        hero.setPower("minge de foc", PublicGameData.fireballPower);
        hero.setPower("zbor", PublicGameData.flyPower);
        hero.setPower("invizibilitate", PublicGameData.invisibilityPower);
        hero.setLives(PublicGameData.lives);
        if(!PublicGameData.loadedFromSave)
        {
            hero.SetX(50);
            hero.SetY(108);
            hero.setPower("minge de foc", 0);
            hero.setPower("zbor", 0);
            hero.setPower("invizibilitate", 0);
            hero.resetLives();
        }
        //hero.resetLives(); // se reseteaza viețile la începutul nivelului
        refLink.SetHero(hero);

        mice = new ArrayList<>();
        blueSpiders = new ArrayList<>();
        redSpiders = new ArrayList<>();

        int x=map.getLevelIndex();
        System.out.println(x);
        switch (map.getLevelIndex()) {
            case 1:
                mice.add(new Mouse(refLink, 150, 111, "purple"));
                mice.add(new Mouse(refLink, 700, 111, "green"));
                mice.add(new Mouse(refLink, 1000, 111, "purple"));
                mice.add(new Mouse(refLink,2080,78,"green"));
                mice.add(new Mouse(refLink, 2700,78,"purple"));
                mice.add(new Mouse(refLink, 1400, 111,"green"));
                break;

            case 2:
                mice.add(new Mouse(refLink, 200, 111,"green"));
                mice.add(new Mouse(refLink, 720, 78, "purple"));
                mice.add(new Mouse(refLink, 1200, 111, "green"));
                mice.add(new Mouse(refLink, 1600, 111, "purple"));
                mice.add(new Mouse(refLink, 2000, 111, "green"));
                mice.add(new Mouse(refLink, 2600, 48, "purple"));
                mice.add(new Mouse(refLink, 3000, 111, "green"));
                //mice.add(new Mouse(refLink, 600, 111, "blue"));
                blueSpiders.add(new SpiderBlue(refLink, 300, 101));
                blueSpiders.add(new SpiderBlue(refLink, 900, 101));
                blueSpiders.add(new SpiderBlue(refLink, 1400, 101));
                blueSpiders.add(new SpiderBlue(refLink, 2100, 101));
                blueSpiders.add(new SpiderBlue(refLink, 2700, 101));
                blueSpiders.add(new SpiderBlue(refLink, 2750, 36));
                blueSpiders.add(new SpiderBlue(refLink, 3050, 101));

                break;


            case 3:
                //mice.add(new Mouse(refLink, 150, 111, "purple"));
                mice.add(new Mouse(refLink, 185, 73,"green"));
                mice.add(new Mouse(refLink, 740, 111, "purple"));
                mice.add(new Mouse(refLink, 1240, 111, "purple"));
                mice.add(new Mouse(refLink, 570, 73, "purple"));
                mice.add(new Mouse(refLink, 1300, 50, "blue"));
                mice.add(new Mouse(refLink, 1900, 111, "purple"));
                blueSpiders.add(new SpiderBlue(refLink, 280, 101));
                blueSpiders.add(new SpiderBlue(refLink, 300, 101));
                mice.add(new Mouse(refLink, 305, 111, "purple"));
                blueSpiders.add(new SpiderBlue(refLink, 900, 101));
                blueSpiders.add(new SpiderBlue(refLink, 1400, 101));
                redSpiders.add(new RedSpider(refLink, 600, 110));
                //redSpiders.add(new RedSpider(refLink, 2000, 110));
                redSpiders.add(new RedSpider(refLink, 2300, 110));
                //blueSpiders.add(new SpiderBlue(refLink, 2920, 101));
                redSpiders.add(new RedSpider(refLink, 2760, 110));
                redSpiders.add(new RedSpider(refLink, 2780, 110));
                shadowSpider = new ShadowSpider(refLink,3000,94);
                break;
        }

        collisionHandler = new CollisionHandler(refLink);
        hero.setEnemies(getAllEnemies());
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override
    public void Update()
    {
        map.Update();


        //collisionHandler.checkTileCollision(hero);


        collisionHandler.checkCharacterCollision(hero,shadowSpider);

        for (Mouse mouse : mice)
        {
            collisionHandler.checkCharacterCollision(hero,mouse);
        }

        for (SpiderBlue spider : blueSpiders)
        {
            collisionHandler.checkCharacterCollision(hero,spider);
        }

        for(RedSpider spider : redSpiders)
        {
            collisionHandler.checkCharacterCollision(hero,spider);
        }

        if (!Game.isPaused)
        {
            hero.Update();  // Doar dacă nu e pauză, îl laș să se miște
        }

        for(Mouse mouse : mice)
        {
            if(mouse!=null)
                mouse.Update();
        }

        for(RedSpider spider : redSpiders)
        {
            if(spider != null)
                spider.Update();
        }

        for (SpiderBlue spider : blueSpiders) {
            spider.Update();
        }
        if(shadowSpider != null) {
            shadowSpider.Update();
        }

        // dacă jucătorul are 0 vieți, restart la nivel actual
        if (hero.getLives()<=0) {

            javax.swing.JOptionPane.showMessageDialog(null,
                    "You have lost all lives for this level.\nRestarting the level...",
                    "Level Restart",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);

            int currentLevel = map.getLevelIndex();
            String selectedCharacter = hero.getCharacterType();
            String playerName = hero.getPlayerName();

            // se reseteaza starea de joc cu aceiași parametri
            State.SetState(new PlayState(refLink, selectedCharacter, currentLevel, playerName));
        }
        //pentru optiunea de pauza joc

        if(refLink.GetKeyManager().esc) {
            State.SetState(new PauseState(refLink, this, hero));
            return; // se oprește Update-ul curent
        }

    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        map.Draw(g);
        Graphics2D gg = (Graphics2D) g;
        collisionHandler.checkTileCollision(hero,  gg);

        for(Mouse mouse : mice)
        {
            if(mouse!=null)
                mouse.Draw(g);
        }

        for(RedSpider spider : redSpiders)
        {
            if(spider != null)
                spider.Draw(g);
        }

        hero.Draw(g);
        for (SpiderBlue spider : blueSpiders)
        {
            spider.Draw(g);
        }
        if(shadowSpider != null) {
            shadowSpider.Draw(g);
        }


        if(shadowSpider!=null) shadowSpider.Draw(g);

        //afisare jucator, nivel, scor

        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("Georgia", Font.PLAIN, 12));
        FontMetrics fm = g2d.getFontMetrics(); //se obtine metrica fonctului actual :inaltime, latime, .....

        String infoText1 = "Player: " + hero.getPlayerName() +
                " | Score: " + PublicGameData.score +
                " | Level: " + map.getLevelIndex();

        /*String infoText2 = "Lives: " + hero.getLives();

        int textWidth = Math.max(fm.stringWidth(infoText1), fm.stringWidth(infoText2));//latimea textulului cel mai lat
        int textHeight = fm.getHeight(); //inaltimea textului
        */
        int textWidth = fm.stringWidth(infoText1);
        int textHeight = fm.getHeight();
        int lineSpacing = 5; // spatiu intre linii
        int padding = 10; //spatiu folosit pentru margini

        // se obtin coordonatele camerei
        double camX = refLink.getCamera().getX();
        double camY = refLink.getCamera().getY();

        //pozitia si dimensiunile chenarului gri transparent
        int boxX = (int) camX + 10; //coordonata x pentru colt stanga sus
        int boxY = (int) camY + 10; //coordonata y pentru colt stanga sus
        int boxWidth = textWidth + padding * 2;
        int boxHeight = textHeight +padding ;

        // Fundal gri transparent
        Color transparentGray = new Color(50, 50, 50, 150);
        g2d.setColor(transparentGray);
        g2d.fillRoundRect(boxX, boxY, boxWidth, boxHeight, 15, 15); // dreptunghi cu colturile rotunjite

        // Text alb
        g2d.setColor(Color.WHITE);
        int textX = boxX + padding;
        int textY = boxY + fm.getAscent() + (padding / 2);
        g2d.drawString(infoText1, textX, textY);

        //desenare tile cu inima pentru numarul de vieti

        int heartWidth = 48;
        int heartHeight = 48;
        int heartX = boxX + 4;
        int heartY = boxY + 8;
        int livesX = heartX + heartWidth-12;
        int livesY = heartY + heartHeight/2;

        if (Assets.inima != null)
        {

            g2d.drawImage(Assets.inima, heartX, heartY, heartWidth, heartHeight, null);

            g2d.setFont(new Font("Georgia", Font.BOLD, 10));

            g2d.setColor(Color.WHITE);
            g2d.drawString("x " + hero.getLives(), livesX, livesY);
        }

        else {
            g2d.drawString("" + hero.getLives(), boxX + 10, boxY + boxHeight + 20);
        }

        // afisarea puterilor
        int mouseWidth = 40;
        int mouseHeight = 40;
        int mouseY = boxY -3;
        int mouseX = livesX + 10;
        int numberX = mouseX + mouseWidth - 6;
        int numberY = livesY + 1;

        if(Assets.purpleMouse[2] != null)
        {
            g2d.drawImage(Assets.purpleMouse[2], mouseX, mouseY, mouseWidth, mouseHeight,null);
            g2d.setFont(new Font("Georgia", Font.BOLD, 10));
            g2d.setColor(Color.WHITE);
            g2d.drawString("x " + hero.getPower("minge de foc"), numberX, numberY);
        }

        mouseX = numberX + 10;
        numberX = mouseX + mouseWidth - 6;
        if(Assets.greenMouse[2] != null)
        {
            g2d.drawImage(Assets.greenMouse[2], mouseX, mouseY, mouseWidth, mouseHeight,null);
            g2d.setFont(new Font("Georgia", Font.BOLD, 10));
            g2d.setColor(Color.WHITE);
            g2d.drawString("x " + hero.getPower("zbor"), numberX, numberY);
        }

        mouseX = numberX + 10;
        numberX = mouseX + mouseWidth - 6;
        if(Assets.blueMouse[2] != null)
        {
            g2d.drawImage(Assets.blueMouse[2], mouseX, mouseY, mouseWidth, mouseHeight,null);
            g2d.setFont(new Font("Georgia", Font.BOLD, 10));
            g2d.setColor(Color.WHITE);
            g2d.drawString("x " + hero.getPower("invizibilitate"), numberX, numberY);
        }
    }

    public Hero getPlayer()
    {
        return hero;
    }

    public Map getMap()
    {
        return map;
    }

    public ArrayList<Character> getAllEnemies()
    {
        ArrayList<Character> enemies = new ArrayList<>();
        if(mice != null)
        {
            for(Mouse mouse : mice)
                enemies.add(mouse);
        }
        if(blueSpiders != null)
        {
            for (SpiderBlue spider : blueSpiders)
                enemies.add(spider);
        }
        if(redSpiders !=null)
        {
            for(RedSpider spider : redSpiders)
                enemies.add(spider);
        }
        if(shadowSpider != null)
        {
            enemies.add(shadowSpider);
        }

        return enemies;
    }
}
