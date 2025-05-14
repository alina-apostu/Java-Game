package PaooGame.States;

import PaooGame.Game;
import PaooGame.Items.Hero;
import PaooGame.Items.NPC.Mouse;
import PaooGame.Items.NPC.RedSpider;
import PaooGame.Items.NPC.SpiderBlue;
import PaooGame.Maps.Map;
import PaooGame.RefLinks;
import PaooGame.CollisionHandler;

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
    private RedSpider redSpider;
    private RedSpider redSpider2;
    
    private ArrayList<SpiderBlue> spiders;

    private CollisionHandler collisionHandler;

    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLink, String selectedCharacter)
    {
            ///Apel al constructorului clasei de baza
        super(refLink);
            ///Construieste harta jocului

        map = new Map(refLink,1);
            ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);
            ///Construieste eroul

        hero = new Hero(refLink,50, 115, selectedCharacter);
        redSpider = new RedSpider(refLink, 600, 115);
        redSpider2 = new RedSpider(refLink,700, 115);

        spiders = new ArrayList<>();
        int x=map.getLevelIndex();
        System.out.println(x);
        if (map.getLevelIndex() == 2) { // Dacă este harta 2
            spiders.add(new SpiderBlue(refLink, 300, 115));
            spiders.add(new SpiderBlue(refLink, 900, 115));
            spiders.add(new SpiderBlue(refLink, 1400, 115));
            spiders.add(new SpiderBlue(refLink, 2100, 115));
            spiders.add(new SpiderBlue(refLink, 2700, 115));
            spiders.add(new SpiderBlue(refLink, 2750, 45));
            spiders.add(new SpiderBlue(refLink, 3050, 115));
        }

        if (map.getLevelIndex() == 1)
        {
            mouse1 = new Mouse(refLink,300,111, "purple");
            mouse2 = new Mouse(refLink, 422, 120, "green");
        }

        collisionHandler = new CollisionHandler();
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override
    public void Update()
    {
        map.Update();
        collisionHandler.checkCollision(hero, mouse1); // Verifici ÎNAINTE de mutare
        collisionHandler.checkCollision(hero,mouse2);
        if (!Game.isPaused)
        {
            hero.Update();  // Doar dacă nu e pauză, îl lași să se miște
        }
        mouse1.Update();
        mouse2.Update();
        //redSpider.Update();
        //redSpider2.Update();
        for (SpiderBlue spider : spiders) {
            spider.Update();
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
        mouse1.Draw(g);
        mouse2.Draw(g);
        //redSpider.Draw(g);
        //redSpider2.Draw(g);
        hero.Draw(g);
        for (SpiderBlue spider : spiders) {
            spider.Draw(g);
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
}
