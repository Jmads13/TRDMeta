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
import dk.sdu.mmmi.cbse.common.services.IContentService;

/**
 *
 * @author SoA
 */
public class Enemy implements IContentService{
    
    DisposableList entities = new DisposableList();

    @Override
    public void add(Object world) {
        Entity e = EnemyFactory.createEnemy();
        Link<Entity> el = context(world).add(Entity.class, e);
        entities.add(el);
        System.out.println("Enemy added");
    }

    @Override
    public void remove() {
        entities.dispose();
    }
    
}
