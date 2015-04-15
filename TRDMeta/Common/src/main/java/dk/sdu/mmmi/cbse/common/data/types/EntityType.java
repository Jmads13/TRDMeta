/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.sdu.mmmi.cbse.common.data.types;

/**
 *
 * @author SoA
 * Tower = Shooting enemy entities
 * Enemy = moving along path, getting shot by towers
 * Bullet = tower shot
 * Button = Menu clickable
 * Player = mouse coordinates
 * SHOP = Tower that can be purchased/clicked and placed
 */
public enum EntityType {
    TOWER, ENEMY, BULLET, BUTTON, PLAYER, SHOP, MAPTILE;
}
