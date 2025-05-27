package PaooGame.Items;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

/*! \class public class Hero extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea
        atacul (nu este implementat momentan)
        dreptunghiul de coliziune
 */
public class Hero extends Character {
    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/
    private BufferedImage[] characterUp; // in functie de ce personaj este ales in meniu
    private BufferedImage[] characterRight;
    private int characterIndex; // index care indica cadrul curent din animatie

    private boolean isAnimating = false; //
    private String currentAnimation = ""; // "up", "right", "left"
    private long lastFrameTime = 0; // cand a fost schimbat ultimul cadru
    private final long frameInterval = 200; // cât de des (în ms) să schimbe cadrul

    // pentru saritura
    private boolean isJumping = false; // daca personajul este in faza de urcare (saritura)
    private boolean isFalling = false; // daca personajul este in faza de coborare din saritura
    private float jumpSpeed = 3f; // viteza de urcare/coborare in saritura
    private final float maxJumpSpeed = 5f; // viteza initiala de saritura
    private final float gravity = 0.5f; // acceleratia gravitationala (cat de repede cade)
    private long lastJumpTime = 0; // momentul ultimei apasari pe tasta up
    private final long jumpBoost = 260; //(ms) daca se apasa rede iar tasta up creste inaltimea sariturii
    private float groundLevelY; // y unde se afla solul pe care coboara personajul
    private boolean facingRight = true;

    //private boolean isOnGroundThisFrame = false;
    private boolean onTile = false;
    private RefLinks refLink;
    private String characterType;

    private String playerName;//nume jucator curent

    private int lives=5; //numarul de vieti  la fiecare nivel
    private final int MAX_LIVES = 5;

    // pentru puteri
    private String power; // puterea luate de la soricei
    private int fireBallPower = 0;
    private int flyPower = 0;
    private int invisibilityPower = 0;

    private FireBall fireBall;
    private boolean firePressedLast = false;

    private boolean shortFlyActive = false;
    private boolean isFlying = false;
    private long flyStartTime = 0;
    private float flySpeed = 2f; // viteza cu care se ridica de la sol sau coboara pe sol
    private final long shortFlyDuration = 5000; // 1.5 sec
    private float maxFlyHeightY;

    private long invisibilityStartTime = 0;
    private boolean invisible = false;
    private final long invisibilityDuration = 5000; // 3 sec

    // folosim HashSet pentru ca nu permite elemente duplicate
    private Set<Character> stungByBlueSpiders = new HashSet<>();
    private Set<Character> stungByRedSpiders = new HashSet<>();

    private boolean stungByShadowSpider = false;

    private ArrayList<Character> enemies;

    private boolean crackedTileCollisionThisFrame = false;

    /*! \fn public Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public Hero(RefLinks refLink, float x, float y, String characterName)
    {
        //Apel al constructorului clasei de baza
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH +10, Character.DEFAULT_CREATURE_HEIGHT +10);

        this.refLink=refLink;
        this.characterType = characterName;

        if (characterName.equals("Luna")) {
            characterUp = Assets.lunaUp;
            characterRight = Assets.lunaRight;
        } else if (characterName.equals("Freya")) {
            characterUp = Assets.freyaUp;
            characterRight = Assets.freyaRight;
        } else if (characterName.equals("Ember")) {
            characterUp = Assets.emberUp;
            characterRight = Assets.emberRight;
        } else throw new IllegalArgumentException("Invalid character name: " + characterName);

        //Seteaza imaginea de start a eroului
        image = characterRight[0];
        characterIndex = 0; // se incepe de la primul cadru
        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        normalBounds.x = 16;
        normalBounds.y = 16;
        normalBounds.width = 32;
        normalBounds.height = 32;

        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea de atac
        attackBounds.x = 10;
        attackBounds.y = 10;
        attackBounds.width = 38;
        attackBounds.height = 38;

        this.groundLevelY = y; // retinem nivelul solului
    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    @Override
    public void Update()
    {
        //Verifica daca a fost apasata o tasta
        GetInput();

        // timpul curent in ms
        // este folosit pentru a controla timpul intre schimbarea cadrelor animatiei
        long currentTime = System.currentTimeMillis();

        if(isFlying == true)
        {
            // se ridica de la sol
            if(y > maxFlyHeightY)
            {
                y -= flySpeed;
                if(y < maxFlyHeightY)
                    y = maxFlyHeightY;
            }

            if(refLink.GetKeyManager().left)
            {
                facingRight = false;
                xMove = -speed;
                if (!isAnimating) {
                    isAnimating = true;
                    currentAnimation = "left";
                    characterIndex = 0;
                    image = flipImageHorizontally(characterRight[characterIndex]);
                    lastFrameTime = currentTime;
                }
            }
            else if (refLink.GetKeyManager().right)
            {
                facingRight = true;
                xMove = speed;
                if (!isAnimating) {
                    isAnimating = true;
                    currentAnimation = "right";
                    characterIndex = 0;
                    image = characterRight[characterIndex];
                    lastFrameTime = currentTime;
                }
            }
            else
            {
                xMove = 0;
                isAnimating = false;
            }

            if(currentTime - flyStartTime >= shortFlyDuration)
            {
                isFlying = false;
                isFalling = true;
                jumpSpeed = flySpeed; // viteza cu care cade
            }

            Move();
            return; // iesim din update
        }

        if(invisible == true)
        {
            if(currentTime - invisibilityStartTime >= invisibilityDuration)
                invisible = false;
        }

        if (isAnimating && currentTime - lastFrameTime > frameInterval) {
            characterIndex++; // se schimba cadrul animatiei
            lastFrameTime = currentTime;

            switch (currentAnimation) {
                case "up":
                    if (characterIndex >= characterUp.length) {
                        characterIndex = 0;
                        isAnimating = false;
                    }
                    else
                    {
                        if (facingRight)
                            image = characterUp[characterIndex];
                        else
                            image = flipImageHorizontally(characterUp[characterIndex]);
                    }
                    break;

                case "right":
                    if (characterIndex >= characterRight.length) {
                        characterIndex = 0;
                        isAnimating = false;
                    } else {
                        image = characterRight[characterIndex];
                    }
                    break;

                case "left":
                    if (characterIndex >= characterRight.length) {
                        characterIndex = 0;
                        isAnimating = false;
                    } else {
                        image = flipImageHorizontally(characterRight[characterIndex]);
                    }
                    break;
            }
        }

        // saritura
        if (isJumping) {
            y -= jumpSpeed; // urca
            jumpSpeed -= gravity; // se incetineste saritura

            // personajul a atins varful sariturii
            if (jumpSpeed <= 0) {
                isJumping = false;
                isFalling = true; // personajul incepe sa cada
            }
        } else if (isFalling) {
            y += jumpSpeed; // cade
            jumpSpeed += gravity; // se accelereaza cadearea

            if (y >= groundLevelY) {
                y = groundLevelY;
                isFalling = false; // se opreste caderea
                jumpSpeed = 0; // viteza de saritura se reseteaza
            }
        }
      
        if(fireBall !=null && fireBall.isActive())
        {
            fireBall.Update();
            if(fireBall.isActive() == false)
                fireBall = null;
        }

        Move();


    }

    private BufferedImage flipImageHorizontally(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage flippedImg = new BufferedImage(width, height, img.getType());
        Graphics2D g = flippedImg.createGraphics();
        g.drawImage(img, 0, 0, width, height, width, 0, 0, height, null);
        g.dispose();
        return flippedImg;
    }

    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput()
    {
        if (Game.isPaused == true) {
            SetXMove(0);
            SetYMove(0);
            return;
        }

        ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;

        // sus
        long currentTime = System.currentTimeMillis(); // timul actual in milisecunde
        if (refLink.GetKeyManager().up) {
            // daca personajul nu sare sau coboara se initiaza o saritura
            if (!isJumping && !isFalling) {
                isJumping = true;
                jumpSpeed = maxJumpSpeed;
                lastJumpTime = currentTime;
            }
            // daca personajul sare sau cade si jucatorul apasa repde din nou tasta up saritura o sa aiba un boost
            else if ((isJumping || isFalling) && (currentTime - lastJumpTime < jumpBoost)) {
                jumpSpeed += 1f;
                // daca viteza sariturii depaseste maximul o facem egala cu maximul
                if (jumpSpeed > maxJumpSpeed)
                    jumpSpeed = maxJumpSpeed;
            }

            // daca nu se afla deja intr-o animatie se incepe una
            if (!isAnimating) {
                isAnimating = true;
                currentAnimation = "up";
                characterIndex = 0;
                if(facingRight == true)
                    image = characterUp[characterIndex];
                else
                    image = flipImageHorizontally(characterRight[characterIndex]);
                lastFrameTime = currentTime;
            }
        }

        // dreapta
        if (refLink.GetKeyManager().right)
        {
            facingRight = true;
            xMove = speed;
            if (!isAnimating) {
                isAnimating = true;
                currentAnimation = "right";
                characterIndex = 0;
                image = characterRight[characterIndex];
                lastFrameTime = currentTime;
            }
        }

        // stanga
        if (refLink.GetKeyManager().left)
        {
            facingRight = false;
            xMove = -speed;
            if (!isAnimating) {
                isAnimating = true;
                currentAnimation = "left";
                characterIndex = 0;
                image = flipImageHorizontally(characterRight[characterIndex]);
                lastFrameTime = currentTime;
            }
        }

        // tasta A - minge de foc
        if(refLink.GetKeyManager().foc)
        {
            System.out.println("FireBallPower: " + fireBallPower);  // test
            if(fireBallPower > 0)
            {
                if (firePressedLast == false)
                {
                    shootFireball();
                    firePressedLast = true;
                }
                else
                {
                    firePressedLast = false;
                }
                usePower("minge de foc");
            }
            else
                JOptionPane.showMessageDialog(null, "You don't have any more uses left for the fire ball power!", "Warning", JOptionPane.WARNING_MESSAGE);

            Game.getRefLinks().GetKeyManager().resetKeys();
        }

        // tasta W - zbor scurt
        if(refLink.GetKeyManager().zbor)
        {
            if(flyPower > 0)
            {
                if (isFlying == false && isJumping == false && isFalling == false)
                {
                    isFlying = true;
                    flyStartTime = System.currentTimeMillis();
                    maxFlyHeightY = groundLevelY - 80;
                }
                usePower("zbor");
            }
            else
                JOptionPane.showMessageDialog(null, "You don't have any more uses left for the flying power!", "Warning", JOptionPane.WARNING_MESSAGE);

            Game.getRefLinks().GetKeyManager().resetKeys();
        }

        // tasta D - invizibilitate
        if(refLink.GetKeyManager().invizibil)
        {
            if(invisibilityPower > 0)
            {
                invisible = true;
                invisibilityStartTime = System.currentTimeMillis();
                usePower("invizibilitate");
            }
            else
                JOptionPane.showMessageDialog(null, "You don't have any more uses left for the invisibility power!", "Warning", JOptionPane.WARNING_MESSAGE);

            Game.getRefLinks().GetKeyManager().resetKeys();
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafi in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g)
    {
        if(invisible == true)
        {
            Graphics2D g2d = (Graphics2D) g.create();
            float alpha = 0.3f; // seteaza transparenta

            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2d.drawImage(image, (int)x, (int)y, width, height, null);
        }
        else
        {
            g.drawImage(image, (int) x, (int) y, width, height, null);
        }

        if(fireBall !=null && fireBall.isActive())
            fireBall.Draw(g);

        //pentru a vedea limitele de coliziune erou
        /*Rectangle bounds = getBounds();
        g.setColor(Color.RED); // culoare vizibilă
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);*/
    }

    public void setPower(String power)
    {
        this.power = power;
    }

    public String getPower()
    {
        return power;
    }

    public boolean itWasStungByShadowSpider()
    {
        return stungByShadowSpider;
    }

    public void setStungByShadowSpider(boolean x)
    {
        stungByShadowSpider = x;
    }

    @Override
    public Rectangle getBounds() 
    {
        return new Rectangle((int) (x+20), (int) (y+20), width-34 , height-40);
    }

    public boolean itWasStungByBlueSpider(Character spider)
    {
       return stungByBlueSpiders.contains(spider);
    }

    public void setWasStungByBlueSpider(Character spider)
    {
        stungByBlueSpiders.add(spider);
    }

    public boolean itWasStungByRedSpider(Character spider)
    {
        return stungByRedSpiders.contains(spider);
    }

    public void setWasStungByRedSpider(Character spider)
    {
        stungByRedSpiders.add(spider);
    }

    public void setFalling(boolean falling) {
        this.isFalling = falling;
    }

    public boolean getisFalling() {
        return this.isFalling;

    }

    public void setisJumping(boolean jumping) {
        this.isJumping = jumping;
    }

    public boolean getisJumping() {
        return isJumping;
    }


    public void setJumpSpeed(float jumpSpeed) {
        this.jumpSpeed = jumpSpeed;
    }

    public float getJumpSpeed() {
        return jumpSpeed;
    }
    public float getGroundLevelY() {
        return groundLevelY;
    }
    public void setGroundLevelY(float groundLevelY) {
        this.groundLevelY = groundLevelY;
    }

    private void shootFireball()
    {
        if(fireBall == null)
        {
            float fbX;
            float fbY = y + 10;
            int direction;

            if(facingRight == true)
            {
                fbX = x + width;
                direction = 1;
            }
            else
            {
                fbX = x - 16;
                direction = -1;
            }

            fireBall = new FireBall(refLink, fbX, fbY, direction,enemies);
        }
    }

    public void setEnemies(ArrayList<Character> enemies)
    {
        this.enemies = new ArrayList<>();
        this.enemies = enemies;
    }

    @Override
    public void Die()
    {

    }

    public int getBoundsYOffset() {
        return bounds.y;
    }
    public int getBoundsXOffset() {
        return bounds.x;
    }

    /*public void setOnGroundThisFrame(boolean value) {
        isOnGroundThisFrame = value;
    }

    public boolean isOnGroundThisFrame() {
        return isOnGroundThisFrame;
    }*/

    public float GetGravity() {
        return this.gravity;
    }

    public boolean isOnTile() {
        return onTile;
    }

    public void setOnTile(boolean val) {
        onTile = val;
    }
    public void resetOnTile() {
        onTile = false;
    }

    public float GetGroundLevelY() {
        return this.groundLevelY;
    }

    public RefLinks GetReflink() {
        return super.refLink;
    }

    public String getCharacterType() {
        return characterType;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
  
    public void addPower(String power)
    {
        if(power.equals("minge de foc"))
            fireBallPower++;
        else if(power.equals("zbor"))
            flyPower++;
        else if(power.equals("invizibilitate"))
            invisibilityPower++;
    }

    public void usePower(String power)
    {
        if(power.equals("minge de foc"))
            fireBallPower--;
        else if(power.equals("zbor"))
            flyPower--;
        else if(power.equals("invizibilitate"))
            invisibilityPower--;
    }

    public void resetLives() {
        lives = MAX_LIVES;
    }

    public int getLives() {
        return lives;
    }

    public void loseLife() {
        if (lives > 0) {
            lives--;
        }
    }

    public boolean isCrackedTileCollisionThisFrame() {
        return crackedTileCollisionThisFrame;
    }

    public void setCrackedTileCollisionThisFrame(boolean value) {
        crackedTileCollisionThisFrame = value;
    }

    private Set<Point> crackedTileCollisionHistory = new HashSet<>();

    public boolean hasCollidedWithCrackedTile(Point tilePosition) {
        return crackedTileCollisionHistory.contains(tilePosition);
    }

    public void markCrackedTileCollision(Point tilePosition) {
        crackedTileCollisionHistory.add(tilePosition);
    }


}