package PaooGame.States;

import PaooGame.Game;
import PaooGame.Items.Character;
import PaooGame.Items.Hero;
import PaooGame.Items.NPC.Mouse;
import PaooGame.Items.NPC.RedSpider;
import PaooGame.Items.ShadowSpider;
import PaooGame.Items.SpiderBlue;
import PaooGame.Maps.Map;
import PaooGame.RefLinks;
import PaooGame.Collision.CollisionHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*! \class public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State
{
    private Hero hero;  /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    private Map map;    /*!< Referinta catre harta curenta.*/
    
    private Mouse mouse1;
    private Mouse mouse2;
    private ArrayList<Mouse> mice;

    private ArrayList<RedSpider> redSpiders;
    
    private ArrayList<SpiderBlue> blueSpiders;

    private CollisionHandler collisionHandler;

    private ShadowSpider shadowSpider;

    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLink, String selectedCharacter)
    {
            ///Apel al constructorului clasei de baza
        super(refLink);
            ///Construieste harta jocului

        map = new Map(refLink,3);
            ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);
            ///Construieste eroul

        hero = new Hero(refLink,50, 108, selectedCharacter);
        refLink.SetHero(hero);

        mice = new ArrayList<>();
        blueSpiders = new ArrayList<>();
        redSpiders = new ArrayList<>();

        int x=map.getLevelIndex();
        System.out.println(x);
        switch (map.getLevelIndex()) {
            case 1:
                //mouse1 = new Mouse(refLink, 300, 111, "purple");
                //mouse2 = new Mouse(refLink, 422, 120, "green");
                mice.add(new Mouse(refLink, 300, 111, "purple"));
                mice.add(new Mouse(refLink, 700, 111, "green"));
                mice.add(new Mouse(refLink, 1000, 111, "purple"));
                break;

            case 2:
                mice.add(new Mouse(refLink, 650, 111,"green"));
                blueSpiders.add(new SpiderBlue(refLink, 300, 101));
                blueSpiders.add(new SpiderBlue(refLink, 900, 101));
                blueSpiders.add(new SpiderBlue(refLink, 1400, 101));
                blueSpiders.add(new SpiderBlue(refLink, 2100, 101));
                blueSpiders.add(new SpiderBlue(refLink, 2700, 101));
                blueSpiders.add(new SpiderBlue(refLink, 2750, 36));
                blueSpiders.add(new SpiderBlue(refLink, 3050, 101));
                break;

            case 3:
                mice.add(new Mouse(refLink, 700, 111, "blue"));
                mice.add(new Mouse(refLink, 1900, 111, "purple"));
                blueSpiders.add(new SpiderBlue(refLink, 280, 101));
                //blueSpiders.add(new SpiderBlue(refLink, 300, 101));
                //spiders.add(new SpiderBlue(refLink, 320, 101));
                blueSpiders.add(new SpiderBlue(refLink, 900, 101));
                blueSpiders.add(new SpiderBlue(refLink, 1400, 101));
                redSpiders.add(new RedSpider(refLink, 600, 110));
                redSpiders.add(new RedSpider(refLink,600, 110));
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

        collisionHandler.checkTileCollision(hero);

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

        if(shadowSpider!=null)
        {
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

        if(shadowSpider!=null) shadowSpider.Draw(g);
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
