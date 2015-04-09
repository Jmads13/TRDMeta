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
import dk.sdu.mmmi.cbse.common.data.types.WaveType;
import dk.sdu.mmmi.cbse.common.helper.XMLReader;
import dk.sdu.mmmi.cbse.common.services.IContentService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
        int waveEnemyCount = 0;
        NodeList waves = null;
        try {
            List<Element> elements = XMLReader.readXML("mapinfo/EnemyWaves.xml");

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
        } catch (IOException e) {
            System.out.println("Could not load XML file for wave information, defaulting to "+waveEnemyCount);
        }

        //Creating enemies based on wave info given
        for (int i = 0; i < waveEnemyCount; i++) {
            xpos = -75 * i;
            ypos = 200;
            Entity e = EnemyFactory.createEnemy(new Position(xpos, ypos), new Velocity(1, 0));
            Link<Entity> el = context(world).add(Entity.class, e);
            entities.add(el);
        }

    }

    @Override
    public void remove() {
        entities.dispose();
    }

}
