/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.sdu.mmmi.cbse.enemy;

import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Depth;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameTime;
import dk.sdu.mmmi.cbse.common.data.Health;
import dk.sdu.mmmi.cbse.common.data.Hitbox;
import dk.sdu.mmmi.cbse.common.data.ImageAsset;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.Radius;
import dk.sdu.mmmi.cbse.common.data.Scale;
import dk.sdu.mmmi.cbse.common.data.Speed;
import dk.sdu.mmmi.cbse.common.data.Velocity;
import dk.sdu.mmmi.cbse.common.data.types.BehaviorType;
import static dk.sdu.mmmi.cbse.common.data.types.BehaviorType.ASTAR;
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import static dk.sdu.mmmi.cbse.common.data.types.EntityType.ENEMY;
import org.openide.util.Lookup;


/**
 *
 * @author SoA
 */
public class EnemyFactory{ 
    
    
    public static Entity createEnemy(int i, Velocity direction){
        Entity enemy = new Entity();
        
        ClassLoader cl = Lookup.getDefault().lookup(ClassLoader.class);
        String url = cl.getResource("assets/images/DK.png").toExternalForm();
        
        //Should be moved to Map component, or whatever generates the level
        //Add stuff to enemy (Via common data)
        //BehaviorType set to Spawning
        context(enemy).add(EntityType.class, ENEMY);
        context(enemy).add(BehaviorType.class, BehaviorType.SPAWNING);
        context(enemy).add(Depth.class, new Depth(100));
        context(enemy).add(GameTime.class, new GameTime(i*75));
        context(enemy).add(ImageAsset.class, new ImageAsset(url));
        context(enemy).add(Health.class, new Health(100));
        context(enemy).add(Hitbox.class, new Hitbox()); //Not yet implemented
        context(enemy).add(Speed.class, new Speed(1));
        context(enemy).add(Position.class, new Position(0,200));
        context(enemy).add(Velocity.class, direction);
        context(enemy).add(Scale.class, new Scale(0.5f,0.5f));
        context(enemy).add(Radius.class, new Radius(10));
        
        //Finally return to start();
        return enemy;
    }

    

}
