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
import dk.sdu.mmmi.cbse.common.data.Scale;
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import static dk.sdu.mmmi.cbse.common.data.types.EntityType.MAPTILE;
import dk.sdu.mmmi.cbse.common.data.types.MapTileType;
import dk.sdu.mmmi.cbse.common.services.IContentService;
import org.openide.util.Lookup;

/**
 *
 * @author SoA
 */
public class TileMap implements IContentService{

    DisposableList entities = new DisposableList();
    private int width = 10, height = 6;
    private int tileSize = 64;
    
    @Override
    public void add(Object o) {
        System.out.println("init map tile");
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                Entity e = new Entity();
                context(e).add(EntityType.class, MAPTILE);
                context(e).add(ImageAsset.class, new ImageAsset(getTileImg(x,y,e)));
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
        {1, 1, 1, 1, 2, 2},
        {1, 1, 1, 0, 0, 2},
        {2, 0, 1, 0, 0, 0},
        {2, 0, 1, 0, 1, 1},
        {2, 2, 1, 1, 1, 1},
        {2, 2, 0, 0, 0, 1},
        {2, 1, 1, 1, 1, 1},
        {0, 1, 0, 0, 2, 2},
        {2, 1, 0, 2, 2, 2},
        {0, 1, 0, 0, 2, 2},
    };
    
    
    private String getTileImg(int x, int y, Entity e) {
        ClassLoader cl = Lookup.getDefault().lookup(ClassLoader.class);
        switch(map[x][y]){
            case 0:
                context(e).add(MapTileType.class, MapTileType.GRASS);
                return cl.getResource("assets/images/grass.png").toExternalForm();
            case 1:
                context(e).add(MapTileType.class, MapTileType.DIRT);
                return cl.getResource("assets/images/dirt.png").toExternalForm();
            case 2:
                context(e).add(MapTileType.class, MapTileType.WATER);
                return cl.getResource("assets/images/water.png").toExternalForm();
            default: 
                context(e).add(MapTileType.class, MapTileType.GRASS);
                return cl.getResource("assets/images/grass.png").toExternalForm();
        }

                
    }
    
}
