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
import dk.sdu.mmmi.cbse.common.services.ILevelContentService;

/**
 *
 * @author SoA
 */
public class Enemy implements ILevelContentService {

    DisposableList entities = new DisposableList();

    @Override
    public void add(Object world, int level) {
        int xpos;
        int ypos;
        switch (level) { //example setups for loading different levels
            //these setups include setting starting positions and directions
            //to test having enemies coming in from different sides
            //(is this ok for a factory pattern to get as parameters??)
            default:
                break;
            case 1:
                for (int i = 0; i < 15; i++) {
                    xpos = -75 * i;
                    ypos = 200;
                    Entity e = EnemyFactory.createEnemy(new Position(xpos,ypos), 
                                                        new Velocity(1,0));
                    Link<Entity> el = context(world).add(Entity.class, e);
                    entities.add(el);
                }
                break;
            case 2:
                for (int i = 0; i < 5; i++) {
                    xpos = 100;
                    ypos = -75 * i;
                    Entity e = EnemyFactory.createEnemy(new Position(xpos,ypos), 
                                                        new Velocity(0,1));
                    Link<Entity> el = context(world).add(Entity.class, e);
                    entities.add(el);
                }
                break;
        }
    }

    @Override
    public void remove() {
        entities.dispose();
    }

}
