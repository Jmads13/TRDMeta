/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.common.data;

/**
 *
 * @author Pizzie
 */
public class Health {
    
    private float health;
    private float maximumHealth;

    public Health(float health) {
        this.health = this.maximumHealth = health;
    }

    public float getHealth() {
        return health;
    }

    public float getMaximumHealth() {
        return maximumHealth;
    }

    public int getHealthPercentage() {
        return Math.round(health / maximumHealth * 100f);
    }
    
    public void addDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }
    
    public void setHealth(float health) {
        this.health = health;
    }
    
    public boolean isAlive() {
        return health > 0;
    }
}
