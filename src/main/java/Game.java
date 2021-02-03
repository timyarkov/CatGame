package main.java;

import main.java.application.MainApp;
import main.java.GameStates;
import main.java.Tickable;
import main.java.GameObj;
import main.java.gameobj.StaticObj;
import main.java.gameobj.staticobj.Wall;
import main.java.gameobj.staticobj.Spawn;
import main.java.gameobj.staticobj.spawn.PlayerSpawn;
import main.java.gameobj.staticobj.spawn.CatSpawn;
import main.java.gameobj.MovingObj;
import main.java.gameobj.movingobj.LaserPointer;
import main.java.gameobj.movingobj.Cat;
import main.java.gameobj.movingobj.cat.Runny;
import main.java.gameobj.movingobj.cat.Jumpy;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import processing.core.PApplet;
import processing.core.PImage;

public class Game {
    private GameStates state; //!!! Implement me after actual game is working!

    // To be used in calculating relative measurements
    // Calculated to fit a GRIDSIZE * GRIDSIZE grid
    private static final int GRIDSIZE = 300; //!!! need to test and experiment with this one
    private static final int OBJSIZE = 30; //!!! this too
    private int deviceWidth;
    private int deviceHeight;
    private float scaler; // How much to scale measurements by, given the resolution

    private Map<String, PImage> sprites; // Sprites to use in the game

    // Game board components; set up at initial setup
    private List<StaticObj> board; //!!!deprecated
    private List<Wall> walls;
    private PlayerSpawn playerSpawn;
    private List<CatSpawn> catSpawns;
    
    // Gameplay components; newly made each game session, destroyed after
    private List<GameObj> gameObjs;
    private List<MovingObj> entities;
    private LaserPointer player;
    private List<Cat> cats;

    public Game(MainApp app) {
        // Something must be reaaally wrong if this branch needs to be used
        if (MainApp.SCREENWIDTH < 0) {
            this.deviceWidth = 480; // Lowest reasonable resolution I can think of 
        } else if (MainApp.SCREENHEIGHT < 0) {
            this.deviceHeight = 640;
        }

        this.deviceWidth = MainApp.SCREENWIDTH;
        this.deviceHeight = MainApp.SCREENHEIGHT;

        this.initialise(app); // Initalise the game
    }

    // Setup Methods
    public void initialise(MainApp app) {
        // Initialise all variables
        // Calculate scaling factor
        this.scaler = this.deviceWidth / 300; //!!! probably need to test, really unsure on the maths here

        // State setup; start in title state
        this.state = GameStates.Title;

        this.sprites = new HashMap<String, PImage>();
        // !!! When there are sprites, load them here via app.loadImage(...)
        // !!! For now though, just leave as blank
        this.sprites.put("Wall", new PImage());
        this.sprites.put("Treat", new PImage());
        this.sprites.put("LaserPointer", new PImage());
        this.sprites.put("Runny", new PImage());
        this.sprites.put("Jumpy", new PImage());
        this.sprites.put("Stumbly", new PImage());
        this.sprites.put("CatSpawn", new PImage());

        //!!!!!!!!!!below is sooo messy, ill need to clean it up

        // Wall setup; create boundaries
        this.walls = new ArrayList<Wall>();
        this.walls.add(new Wall(0, 0, this.deviceWidth, this.deviceHeight * 0.1f, 
                            this.sprites.get("Wall"))); // Top bound (10% from top)
        //!!! clean this multiline mess sometime
        this.walls.add(new Wall(0, this.deviceHeight * 0.1f + this.scaler * Game.GRIDSIZE, this.deviceWidth, 
                            this.deviceHeight - (this.deviceHeight * 0.1f + this.scaler * Game.GRIDSIZE), 
                            this.sprites.get("Wall"))); // Bottom bound (rest after play area)
        // Left bound (little bit showing)
        this.walls.add(new Wall(-1, 0, 6, this.deviceHeight, this.sprites.get("Wall"))); 
        // Right bound (little bit showing)
        this.walls.add(new Wall(this.deviceWidth - 5, 0, 6, this.deviceHeight, this.sprites.get("Wall"))); 
        
        // Spawn setups; player spawn in middle of play area, and cat spawn around top
        // Offsets of object widths to make spawned objects centered
        this.playerSpawn = new PlayerSpawn((this.deviceWidth / 2) - Game.OBJSIZE, 
                                                (this.deviceHeight * 0.1f) + (this.scaler * (Game.GRIDSIZE / 2f)) - Game.OBJSIZE,
                                                Game.OBJSIZE, Game.OBJSIZE);
        //Add catspawn(s)
    }

    // Getter Methods: General Game Attributes
    public GameStates getState() {
        return this.state;
    }

    public int getDeviceWidth() {
        return this.deviceWidth;
    }

    public int getDeviceHeight() {
        return this.deviceHeight;
    }

    public Map<String, PImage> getSprites() {
        return this.sprites;
    }

    // Getter methods: Game Board Components
    @Deprecated
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

    // Getter methods: Gameplay components
    public List<GameObj> getGameObjs() {
        return this.gameObjs;
    }

    public List<MovingObj> getEntities() {
        return this.entities;
    }

    public LaserPointer getPlayer() {
        return this.player;
    }

    public List<Cat> getCats() {
        return this.cats;
    }

    // Game functionality
    public void tick() {
        // Run tick methods for each possible state
        if (this.getState() == GameStates.InGame) {
            // Run all tickables (entities for now)
            for (Tickable obj : this.getEntities()) {
                obj.tick(this);
            }
        }
    }
}