/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.enemy;

import com.decouplink.Link;
import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.Velocity;
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import dk.sdu.mmmi.cbse.common.data.types.LevelType;
import dk.sdu.mmmi.cbse.common.data.types.WaveType;
import dk.sdu.mmmi.cbse.common.helper.XMLReader;
import dk.sdu.mmmi.cbse.common.services.IContentService;
import java.io.IOException;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author SoA
 */
public class Enemy implements IContentService {

    Object o;

    @Override
    public void add(Object world) {
        this.o = world;
        int waveEnemyCount = 1;
        NodeList waves = null;
        try {
            //Assets i PlayN er træls, så vi laver den lidt alternativt:
            List<Element> elements = XMLReader.readXML("../../../Assets/src/main/resources/assets/mapinfo/EnemyWaves.xml");

            //Finding waves for current level
            for (Element e : elements) {
                if (context(world).one(LevelType.class).toString().equals(e.getAttribute("levelid"))) {
                    waves = e.getElementsByTagName("wave");
                }
            }

            //Finding info about the current wave
            for (int i = 0; i < waves.getLength(); i++) {
                Element wave = (Element) waves.item(i);
                if (context(world).one(WaveType.class).toString().equals(wave.getAttribute("waveid"))) {
                    System.out.println("Creating wave " + wave.getAttribute("waveid"));
                    waveEnemyCount = Integer.parseInt(wave.getElementsByTagName("count").item(0).getTextContent());
                }
            }
        } catch (IOException | NullPointerException e) {
            System.out.println("Could not load XML file for wave information, defaulting to "+waveEnemyCount);
        }

        //Creating enemies based on wave info given
        for (int i = 0; i < waveEnemyCount; i++) {
            Entity e = EnemyFactory.createEnemy(i, new Velocity(1, 0));
            Link<Entity> el = context(world).add(Entity.class, e);
        }

    }

    @Override
    public void remove() {
        for(Entity e : context(o).all(Entity.class)){
            if(context(e).one(EntityType.class) == EntityType.ENEMY){
                e.setDestroyed(true);
            }
        }
    }

}
