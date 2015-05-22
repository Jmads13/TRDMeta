package dk.sdu.mmmi.cbse.tower;

import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.ImageAsset;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.Scale;
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import static dk.sdu.mmmi.cbse.common.data.types.EntityType.TOWER;
import dk.sdu.mmmi.cbse.common.services.IContentService;
import org.openide.util.Lookup;


/**
 *
 * @author Tenna
 */
public class Tower implements IContentService {
    
    Object o;
    
    @Override
    public void add(Object world){
        this.o = world;
    }
    
    public static Entity createTower(){
        Entity tower = new Entity();
        
        ClassLoader cl = Lookup.getDefault().lookup(ClassLoader.class);
        String url = cl.getResource("assets/images/Nazi_Tank.png").toExternalForm();
        
        context(tower).add(EntityType.class, TOWER);
        context(tower).add(ImageAsset.class, new ImageAsset(url));
        context(tower).add(Position.class, new Position(500,100));
        context(tower).add(Scale.class, new Scale(1f,1f));
        
        
        return tower;
    }
    
    @Override
    public void remove(){
        for(Entity e : context(o).all(Entity.class)){
            if(context(e).one(EntityType.class) == EntityType.TOWER){
                e.setDestroyed(true);
            }
        }
    }
    
}
