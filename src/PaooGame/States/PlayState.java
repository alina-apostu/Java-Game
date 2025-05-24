package PaooGame.States;

import PaooGame.Game;
import PaooGame.Items.Hero;
import PaooGame.Items.NPC.Mouse;
import PaooGame.Items.NPC.RedSpider;
import PaooGame.Items.ShadowSpider;
import PaooGame.Items.SpiderBlue;
import PaooGame.Maps.Map;
import PaooGame.PublicGamaData;
import PaooGame.RefLinks;
import PaooGame.Collision.CollisionHandler;

import java.awt.*;
import java.util.ArrayList;

/*! \class public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State
{
    private Hero hero;  /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    private Map map;    /*!< Referinta catre harta curenta.*/
    
    private Mouse mouse1;
    private Mouse mouse2;
    private Mouse mouse3;
    private Mouse mouse4;
    private RedSpider redSpider;
    private RedSpider redSpider2;
    
    private ArrayList<SpiderBlue> spiders;

    private CollisionHandler collisionHandler;

    private ShadowSpider shadowSpider;

    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLink, String selectedCharacter, int levelIndex, String playerName)
    {
            ///Apel al constructorului clasei de baza
        super(refLink);
            ///Construieste harta jocului

        map = new Map(refLink,levelIndex);
            ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);
            ///Construieste eroul

        hero = new Hero(refLink,50, 108, selectedCharacter);
        hero.setPlayerName(playerName);
        refLink.SetHero(hero);

        spiders = new ArrayList<>();
        int x=map.getLevelIndex();
        System.out.println(x);
        switch (map.getLevelIndex()) {
            case 1:
                mouse1 = new Mouse(refLink, 200, 111, "purple");
                mouse2 = new Mouse(refLink, 422, 120, "green");
                mouse3 = new Mouse(refLink, 500, 111, "green");
                mouse4 = new Mouse(refLink, 600, 111, "blue");

                break;

            case 2:

                spiders.add(new SpiderBlue(refLink, 300, 101));
                spiders.add(new SpiderBlue(refLink, 900, 101));
                spiders.add(new SpiderBlue(refLink, 1400, 101));
                spiders.add(new SpiderBlue(refLink, 2100, 101));
                spiders.add(new SpiderBlue(refLink, 2700, 101));
                spiders.add(new SpiderBlue(refLink, 2750, 36));
                spiders.add(new SpiderBlue(refLink, 3050, 101));
                break;

            case 3:
                spiders.add(new SpiderBlue(refLink, 280, 101));
                spiders.add(new SpiderBlue(refLink, 300, 101));
                //spiders.add(new SpiderBlue(refLink, 320, 101));
                spiders.add(new SpiderBlue(refLink, 850, 101));
                spiders.add(new SpiderBlue(refLink, 1400, 101));
                redSpider = new RedSpider(refLink, 600, 110);
                redSpider2 = new RedSpider(refLink,600, 110);
                shadowSpider = new ShadowSpider(refLink,3000,94);

                break;
        }

        collisionHandler = new CollisionHandler(refLink);
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override
    public void Update()
    {
        map.Update();


        collisionHandler.checkTileCollision(hero);


        collisionHandler.checkCharacterCollision(hero,mouse1);
        collisionHandler.checkCharacterCollision(hero, mouse3);
        collisionHandler.checkCharacterCollision(hero, mouse4);



        collisionHandler.checkCharacterCollision(hero,redSpider);

        collisionHandler.checkCharacterCollision(hero,shadowSpider);

        for (SpiderBlue spider : spiders)
        {
            collisionHandler.checkCharacterCollision(hero,spider);
        }

        if (!Game.isPaused)
        {
            hero.Update();  // Doar dacă nu e pauză, îl laș să se miște
        }

        if(mouse1 != null) mouse1.Update();
        if(mouse2 != null) mouse2.Update();
        if(mouse3 != null) mouse3.Update();
        if(mouse4 != null) mouse4.Update();

        if(redSpider != null) redSpider.Update();
        if(redSpider2 != null) redSpider2.Update();

        for (SpiderBlue spider : spiders) {
            spider.Update();
        }
        if(shadowSpider != null) {
            shadowSpider.Update();
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
        if(mouse1 != null) mouse1.Draw(g);
        if(mouse2 != null) mouse2.Draw(g);
        if(mouse3 != null) mouse3.Draw(g);
        if(mouse4 != null) mouse4.Draw(g);

        if(redSpider != null) redSpider.Draw(g);
        //if(redSpider2 != null) redSpider2.Draw(g);

        hero.Draw(g);
        for (SpiderBlue spider : spiders)
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

        String infoText = "Player: " + hero.getPlayerName() +
                " | Score: " + PublicGamaData.score +
                " | Level: " + map.getLevelIndex();

        int textWidth = fm.stringWidth(infoText); //latimea textulului
        int textHeight = fm.getHeight(); //inaltimea textului
        int padding = 10; //spatiu folosit pentru margini

        // se obtin coordonatele camerei
        double camX = refLink.getCamera().getX();
        double camY = refLink.getCamera().getY();

        //pozitia si dimensiunile chenarului gri transparent
        int boxX = (int) camX + 10; //coordonata x pentru colt stanga sus
        int boxY = (int) camY + 10; //coordonata y pentru colt stanga sus
        int boxWidth = textWidth + padding * 2;
        int boxHeight = textHeight + padding;

        // Fundal gri transparent
        Color transparentGray = new Color(50, 50, 50, 150);
        g2d.setColor(transparentGray);
        g2d.fillRoundRect(boxX, boxY, boxWidth, boxHeight, 15, 15); // dreptunghi cu colturile rotunjite

        // Text alb
        g2d.setColor(Color.WHITE);
        g2d.drawString(infoText, boxX + padding, boxY + fm.getAscent() + (padding / 2));


    }

    public Hero getPlayer()
    {
        return hero;
    }

    public Map getMap()
    {
        return map;
    }
}
