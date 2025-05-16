package PaooGame.States;

import PaooGame.Game;
import PaooGame.Items.Hero;
import PaooGame.Items.NPC.Mouse;
import PaooGame.Items.NPC.RedSpider;
import PaooGame.Items.ShadowSpider;
import PaooGame.Items.SpiderBlue;
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

        hero = new Hero(refLink,50, 115, selectedCharacter);

        spiders = new ArrayList<>();
        int x=map.getLevelIndex();
        System.out.println(x);
        switch (map.getLevelIndex()) {
            case 1:
                mouse1 = new Mouse(refLink, 300, 111, "purple");
                mouse2 = new Mouse(refLink, 422, 120, "green");
                break;

            case 2:
                spiders.add(new SpiderBlue(refLink, 300, 115));
                spiders.add(new SpiderBlue(refLink, 900, 115));
                spiders.add(new SpiderBlue(refLink, 1400, 115));
                spiders.add(new SpiderBlue(refLink, 2100, 115));
                spiders.add(new SpiderBlue(refLink, 2700, 115));
                spiders.add(new SpiderBlue(refLink, 2750, 45));
                spiders.add(new SpiderBlue(refLink, 3050, 115));
                break;

            case 3:
                spiders.add(new SpiderBlue(refLink, 280, 115));
                spiders.add(new SpiderBlue(refLink, 300, 115));
                spiders.add(new SpiderBlue(refLink, 320, 115));
                spiders.add(new SpiderBlue(refLink, 850, 115));
                spiders.add(new SpiderBlue(refLink, 1400, 115));
                redSpider = new RedSpider(refLink, 500, 115);
                redSpider2 = new RedSpider(refLink,600, 115);
                shadowSpider = new ShadowSpider(refLink,3000,115);
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

        if(mouse1 != null) {
            collisionHandler.checkCollisionMouse(hero, mouse1);
        }
        if(mouse2 != null) {
            collisionHandler.checkCollisionMouse(hero, mouse2);
        }

        if(redSpider != null)
            collisionHandler.checkCollisionRedSpider(hero,redSpider);
        if(redSpider2 != null)
            collisionHandler.checkCollisionRedSpider(hero,redSpider2);

        if (!Game.isPaused)
        {
            hero.Update();  // Doar dacă nu e pauză, îl lași să se miște


        }
        if(mouse1 != null) mouse1.Update();
        if(mouse2 != null) mouse2.Update();

        if(redSpider != null) redSpider.Update();
        if(redSpider2 != null) redSpider2.Update();

        for (SpiderBlue spider : spiders) {
            spider.Update();
        }

        shadowSpider.Update();
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

        if(redSpider != null) redSpider.Draw(g);
        if(redSpider2 != null) redSpider2.Draw(g);

        hero.Draw(g);
        for (SpiderBlue spider : spiders) {
            spider.Draw(g);
        }

        shadowSpider.Draw(g);
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
