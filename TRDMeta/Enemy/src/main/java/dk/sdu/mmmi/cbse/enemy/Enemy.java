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
import dk.sdu.mmmi.cbse.common.data.ImageAsset;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.Velocity;
import dk.sdu.mmmi.cbse.common.data.types.LevelType;
import static dk.sdu.mmmi.cbse.common.data.types.LevelType.ONE;
import dk.sdu.mmmi.cbse.common.helper.XMLReader;
import dk.sdu.mmmi.cbse.common.services.IContentService;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;

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
        
        List<Element> elements = XMLReader.readXML("images/EnemyWaves.xml");
        
        for(Element e : elements) {
            System.out.println(e.getAttribute("levelid")+" - "+e.getAttribute("waveid"));
            System.out.println("This wave contains "+e.getElementsByTagName("count").item(0).getTextContent()
                                +" enemies of type "+e.getElementsByTagName("type").item(0).getTextContent());
        }
        
        if (context(world).one(LevelType.class) == ONE) {
            for (int i = 0; i < 15; i++) {
                xpos = -75 * i;
                ypos = 200;
                Entity e = EnemyFactory.createEnemy(new Position(xpos, ypos), new Velocity(1, 0));
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
