/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.enemy;

import com.decouplink.DisposableList;
import com.decouplink.Link;
import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.Velocity;
import dk.sdu.mmmi.cbse.common.data.types.LevelType;
import static dk.sdu.mmmi.cbse.common.data.types.LevelType.ONE;
import dk.sdu.mmmi.cbse.common.services.IContentService;
import dk.sdu.mmmi.cbse.common.services.ILevelContentService;

/**
 *
 * @author SoA
 */
public class Enemy implements IContentService {

    DisposableList entities = new DisposableList();

    @Override
    public void add(Object world) {
        int xpos;
        int ypos;
        if(context(world).one(LevelType.class) == ONE){
                for (int i = 0; i < 15; i++) {
                    xpos = -75 * i;
                    ypos = 200;
                    Entity e = EnemyFactory.createEnemy(new Position(xpos,ypos), 
                                                        new Velocity(1,0));
                    Link<Entity> el = context(world).add(Entity.class, e);
                    entities.add(el);
                }
        }
    }

    @Override
    public void remove() {
        entities.dispose();
    }

}
