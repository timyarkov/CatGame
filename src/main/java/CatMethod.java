package main.java;

import main.java.gameobj.movingobj.Cat;

@FunctionalInterface
public interface CatMethod {
    public Cat spawnCatReference(); //!!! right terminology?
}