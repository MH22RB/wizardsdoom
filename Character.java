import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Base class for all game characters
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Character extends Entity
{
    
    private int direction;
    
    private List<Integer> eastSprites = new ArrayList<Integer>();
    private List<Integer> westSprites = new ArrayList<Integer>();
    private List<Integer> northSprites = new ArrayList<Integer>();
    private List<Integer> southSprites = new ArrayList<Integer>();

    private int animationSprite = 0;
    private int animationFrame = 0;
    

    protected static final int EAST = 0;
    protected static final int WEST = 1;
    protected static final int NORTH = 2;
    protected static final int SOUTH = 3;    

    
    private int speed = 10;
    private int strength = 10;
    private int health = 10;
    private int power = 10;
    
    private boolean frozen = false;
    private boolean dialoguing = false;
    private boolean speaking = false;
    
    SpeechBubble speechBubble;

    
    public void act() {
    }
    
    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
        resetAnimation();
    }

    
    public void setEastSprites(int begin, int end) {
        eastSprites.clear();
        for(int i = begin; i <= end; ++i) {
            eastSprites.add(i);
        }
    }
    
    public void setWestSprites(int begin, int end) {
        westSprites.clear();
        for(int i = begin; i <= end; ++i) {
            westSprites.add(i);
        }
    }
    
    public void setNorthSprites(int begin, int end) {
        northSprites.clear();
        for(int i = begin; i <= end; ++i) {
            northSprites.add(i);
        }
    }
    
    
    public void setSouthSprites(int begin, int end) {
        southSprites.clear();
        for(int i = begin; i <= end; ++i) {
            southSprites.add(i);
        }
    }    
    
    
    public void freeze() {
        this.frozen = true;
    }

    public void unfreeze() {
        this.frozen = false;
    }
    
    public boolean isFrozen() {
        return this.frozen;
    }

    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void increaseSpeed(int ammount) {
        this.speed += ammount;
    }
    
    public void increaseHealth(int ammount) {
        this.health += ammount;
    }
    
    public void increasePower(int ammount) {
        this.power += ammount;
    }
    
    public void increaseStrength(int ammount) {
        this.strength += ammount;
    }
    
    /**
     * @return the strength
     */
    public int getStrength() {
        return strength;
    }

    /**
     * @param strength the strength to set
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return the power
     */
    public int getPower() {
        return power;
    }

    /**
     * @param power the power to set
     */
    public void setPower(int power) {
        this.power = power;
    }
    
    public void speak(String text) {
        if (speechBubble == null) {
            speechBubble = new SpeechBubble(this, text);
            getWorld().addObject(speechBubble, 
                                 this.getX() + 50, 
                                 this.getY() - 50);
        }
        speaking = true;
    }
    
    public boolean isSpeaking() {
        return this.speaking;
    }
    
    public void stopSpeak() {
        if (speechBubble != null) {
            getWorld().removeObject(speechBubble);
            speechBubble = null;
        }
        speaking = false;
    }
    
    public abstract void interact(Character c);
    
   /**
     * Test if we can move forward. Return true if we can, false otherwise.
     */
    public boolean hasAnyoneNear(int range)
    {
        List objects = getObjectsInRange(range, null);
        if(objects.isEmpty()) {
            return true;
        }
        return false;
    }      
    
    public void resetAnimation() {
        animationFrame = 0;
        animationSprite = 0;
        switch(getDirection()) {
            case SOUTH :
                setSprite(southSprites.get(0));
                break;
            case EAST :
                setSprite(eastSprites.get(0));
                break;
            case NORTH :
                setSprite(northSprites.get(0));
                break;
            case WEST :
                setSprite(westSprites.get(0));
                break;
        }
    }
    
    public void animate() {
        if((++animationFrame % 6) == 0) 
        {
            switch(getDirection()) {
                case SOUTH :
                    setSprite(southSprites.get(animationSprite++ % southSprites.size()));
                    break;
                case EAST :
                    setSprite(eastSprites.get(animationSprite++ % eastSprites.size()));
                    break;
                case NORTH :
                    setSprite(northSprites.get(animationSprite++ % northSprites.size()));
                    break;
                case WEST :
                    setSprite(westSprites.get(animationSprite++ % westSprites.size()));
                    break;
            }
            animationFrame = 0;
        }
    }    
    
}
