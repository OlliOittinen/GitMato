/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spawnables;

/**
 *
 * @author maxki
 */
public class Snack extends AbstractSpawnables{

    public Snack() {
        init();
    }

    //on init, set the starting snack to be at a random x-location between 600 & 200
    @Override
    public void init() {
        setX( 600-(int)(Math.random()*400));
        setY(200);
    }

}

