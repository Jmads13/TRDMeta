/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.sdu.mmmi.cbse.common.data.types;

/**
 *
 * @author SoA
 * ASTAR = When an enemy entity is moving
 * SHOOT = When a tower is able to shoot (Enemy within range)
 * HIT = Entity is hit (Could be deleted)
 * PLACING = Player has clicked an item in the store, and is now placing it
 * SPAWNING = Monster has been read from XML and is waiting to get spawned
 */
public enum BehaviorType {
    ASTAR, SHOOT, HIT, PLACING, SPAWNING, INIT;
}
