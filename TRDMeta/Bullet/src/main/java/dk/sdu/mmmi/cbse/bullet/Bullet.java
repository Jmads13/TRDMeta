package dk.sdu.mmmi.cbse.bullet;

import com.decouplink.DisposableList;
import com.decouplink.Link;
import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.ImageAsset;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.Scale;
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import static dk.sdu.mmmi.cbse.common.data.types.EntityType.BULLET;
import dk.sdu.mmmi.cbse.common.services.IContentService;



/**
 *
 * @author LarsTrierStormDanmark
 */
public class Bullet implements IContentService 
{
    
    DisposableList entities = new DisposableList();
    
    @Override
    public void add(Object world)
    {
     Entity bu = createBullet();
     Link<Entity> bul = context(world).add(Entity.class, bu);
     entities.add(bul);
    }
    
    public static Entity createBullet()
    {
        Entity bullet = new Entity();
        
        context(bullet).add(EntityType.class, BULLET);
        context(bullet).add(ImageAsset.class, new ImageAsset("images/tennisbold.png"));
        context(bullet).add(Scale.class, new Scale(0.5f ,0.5f));
        context(bullet).add(Position.class, new Position(200, 200));
                
        return bullet;
    }
    
    @Override
    public void remove()
    {
        entities.dispose();
    }
    
}
