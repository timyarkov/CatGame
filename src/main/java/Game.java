package main.java;

import main.java.GameStates;
import main.java.gameobj.StaticObj;
import main.java.gameobj.staticobj.Wall;
import main.java.gameobj.MovingObj;
import main.java.gameobj.movingobj.LaserPointer;
import main.java.gameobj.movingobj.Cat;
import main.java.gameobj.movingobj.cat.Runny;
import main.java.gameobj.movingobj.cat.Jumpy;
import java.util.List;
import java.util.Map;
import processing.core.PApplet;
import processing.core.PImage;

public class Game {
    private GameStates state; //!!! Implement me after actual game is working!

    // To be used in calculating board size
    private int deviceWidth;
    private int deviceHeight;

    private Map<String, PImage> sprites; // Sprites to use in the game

    // Game board components
    private List<StaticObj> board;
    private List<Wall> walls;
    private PlayerSpawn playerSpawn;
    private List<CatSpawn> catSpawns;
    
    // Gameplay components
    private List<MovingObj> entities;
    private LaserPointer player;
    private List<Cat> cats;

    public Game(int deviceWidth, int deviceHeight, Map<String, PImage> sprites) {
        // Something must be reaaally wrong if this branch needs to be used
        if (deviceWidth < 0) {
            this.deviceWidth = 480; // Lowest reasonable resolution I can think of 
        } else if (deviceHeight < 0) {
            this.deviceHeight = 640;
        }

        this.deviceWidth = deviceWidth;
        this.deviceHeight = deviceHeight;

        this.initialise(); // Initalise the game
    }

    // Setup Methods
    public void initialise() {
        // implement me!!!!
    }

    // Getter Methods: General Game Attributes
    public GameStates getState() {
        return this.state;
    }

    public int getDeviceWidth() {
        return this.deviceWidth;
    }

    public int getDeviceHeight() {
        return this.deviceHeight
    }

    public Map<String, PImage> getSprites() {
        return this.sprites;
    }

    // Getter methods: Board Attributes
    public List<StaticObj> getBoard() {
        return this.board;
    }

    public List<Wall> getWalls() {
        return this.walls;
    }

    public PlayerSpawn getPlayerSpawn() {
        return this.playerSpawn;
    }

    public List<CatSpawn> getCatSpawns() {
        return this.catSpawns;
    }

    // Getter methods: Gameplay objects
    public List<MovingObj> getEntities() {
        return this.entities;
    }

    public LaserPointer getPlayer() {
        return this.player;
    }

    public List<Cat> getCats() {
        return this.cats;
    }
}