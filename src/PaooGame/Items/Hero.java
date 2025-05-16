package PaooGame.Items;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

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
    private float jumpSpeed = 4f; // viteza de urcare/coborare in saritura
    private final float maxJumpSpeed = 6f; // viteza initiala de saritura
    private final float gravity = 0.5f; // acceleratia gravitationala (cat de repede cade)
    private long lastJumpTime = 0; // momentul ultimei apasari pe tasta up
    private final long jumpBoost = 260; //(ms) daca se apasa rede iar tasta up creste inaltimea sariturii
    private float groundLevelY; // y unde se afla solul pe care coboara personajul

    private String power; // puterea luate de la soricei


    /*! \fn public Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public Hero(RefLinks refLink, float x, float y, String characterName) {
        //Apel al constructorului clasei de baza
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);

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
    public void Update() {
        //Verifica daca a fost apasata o tasta
        GetInput();

        // timpul curent in ms
        // este folosit pentru a controla timpul intre schimbarea cadrelor animatiei
        long currentTime = System.currentTimeMillis();

        if (isAnimating && currentTime - lastFrameTime > frameInterval) {
            characterIndex++; // se schimba cadrul animatiei
            lastFrameTime = currentTime;

            switch (currentAnimation) {
                case "up":
                    if (characterIndex >= characterUp.length) {
                        characterIndex = 0;
                        isAnimating = false;
                    } else {
                        image = characterUp[characterIndex];
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
    private void GetInput() {
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
                image = characterUp[characterIndex];
                lastFrameTime = currentTime;
            }
        }

        // dreapta
        if (refLink.GetKeyManager().right) {
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
        if (refLink.GetKeyManager().left) {
            xMove = -speed;
            if (!isAnimating) {
                isAnimating = true;
                currentAnimation = "left";
                characterIndex = 0;
                image = flipImageHorizontally(characterRight[characterIndex]);
                lastFrameTime = currentTime;
            }
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafi in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int) x, (int) y, width, height, null);

        ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getPower() {
        return power;
    }

    @Override

    public Rectangle getBounds() {
        return new Rectangle((int) (x + 14), (int) (y + 14), width - 28, height - 28);
    }


    public void setFalling(boolean falling) {
        this.isFalling = falling;
    }

    public boolean isFalling() {
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

}