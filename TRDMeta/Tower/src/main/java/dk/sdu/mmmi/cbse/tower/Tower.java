package dk.sdu.mmmi.cbse.tower;


import com.decouplink.DisposableList;
import com.decouplink.Link;
import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.ImageAsset;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.Scale;
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import static dk.sdu.mmmi.cbse.common.data.types.EntityType.TOWER;
import dk.sdu.mmmi.cbse.common.services.IContentService;


/**
 *
 * @author Tenna
 */
public class Tower implements IContentService {
    
    DisposableList entities = new DisposableList();
    
    @Override
    public void add(Object world){
        //Entity to = createTower();
        //Link<Entity> tow = context(world).add(Entity.class, to);
        //entities.add(tow);
    }
    
    public static Entity createTower(){
        Entity tower = new Entity();
        
        context(tower).add(EntityType.class, TOWER);
        context(tower).add(ImageAsset.class, new ImageAsset("images/Nazi_Tank.png"));
        context(tower).add(Position.class, new Position(500,100));
        context(tower).add(Scale.class, new Scale(1f,1f));
        
        
        return tower;
    }
    
    @Override
    public void remove(){
        entities.dispose();
    }
    
}
