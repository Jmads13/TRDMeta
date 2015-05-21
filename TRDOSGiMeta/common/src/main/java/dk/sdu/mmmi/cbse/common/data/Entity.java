package dk.sdu.mmmi.cbse.common.data;


/**
 *
 * @author SoA
 */
public final class Entity {
    
    private boolean destroyed, hit;
    
    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean dead) {
        this.destroyed = dead;
    }
    
    public void setHit(Boolean hit){
        this.hit = hit;
    }
    
    public boolean isHit(){
        return hit;
    }
    
}
