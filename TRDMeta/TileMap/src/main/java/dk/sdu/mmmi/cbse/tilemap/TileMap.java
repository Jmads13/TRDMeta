/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.sdu.mmmi.cbse.tilemap;

import com.decouplink.DisposableList;
import com.decouplink.Link;
import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.ImageAsset;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.Rotation;
import dk.sdu.mmmi.cbse.common.data.Scale;
import dk.sdu.mmmi.cbse.common.data.Velocity;
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import static dk.sdu.mmmi.cbse.common.data.types.EntityType.MAPTILE;
import dk.sdu.mmmi.cbse.common.services.IContentService;

/**
 *
 * @author SoA
 */
public class TileMap implements IContentService{

    DisposableList entities = new DisposableList();
    private int length = 5, height = 5;
    private int tileSize = 64;
    
    @Override
    public void add(Object o) {
        System.out.println("init map tile");
        for(int x = 0; x < length; x++){
            for(int y = 0; y < height; y++){
                Entity e = new Entity();
                System.out.println("creating map tile");
                context(e).add(EntityType.class, MAPTILE);
                context(e).add(ImageAsset.class, new ImageAsset(getTileImg(x,y)));
                context(e).add(Position.class, new Position(x*tileSize+(tileSize/2), y*tileSize+(tileSize/2))); //tileSize/2 er for at flytte den fra "Origin" pladsen (Se Core.paint())
                context(e).add(Scale.class, new Scale(1.0f, 1.0f));
                
                Link<Entity> el = context(o).add(Entity.class, e);
                entities.add(el);
            }
        }
        

    }

    @Override
    public void remove() {
        entities.dispose();
    }
    
    int[][] map = {
        {1, 0, 0, 0, 0},
        {1, 1, 1, 0, 0},
        {2, 0, 1, 0, 0},
        {2, 0, 1, 0, 0},
        {0, 2, 1, 1, 1},
        {0, 0, 2, 2, 2},
    };
    
    
    private String getTileImg(int x, int y) {
        switch(map[x][y]){
            case 0:
                return "images/grass.png";
            case 1:
                return "images/dirt.png";
            case 2:
                return "images/water.png";
            default: 
                return "images/grass.png";  
        }

                
    }
    
}
