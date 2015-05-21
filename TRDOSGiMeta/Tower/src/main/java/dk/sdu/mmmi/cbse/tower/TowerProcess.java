/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.tower;

import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameTime;
import dk.sdu.mmmi.cbse.common.data.ImageAsset;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.Range;
import dk.sdu.mmmi.cbse.common.data.Rotation;
import dk.sdu.mmmi.cbse.common.data.Scale;
import dk.sdu.mmmi.cbse.common.data.types.Available;
import dk.sdu.mmmi.cbse.common.data.types.BehaviorType;
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import static dk.sdu.mmmi.cbse.common.data.types.EntityType.TOWER;
import dk.sdu.mmmi.cbse.common.data.types.MapTileType;
import dk.sdu.mmmi.cbse.common.services.IUpdateService;
import org.openide.util.Lookup;

/**
 *
 * @author Pasoa
 */
class TowerProcess implements IUpdateService {

    Position playerPos = new Position(0, 0);
    Entity tile;

    @Override
    public void update(Object o, Entity entity) {
        if (context(entity).one(EntityType.class) == EntityType.PLAYER) {
            playerPos = context(entity).one(Position.class);
        }
        if (context(entity).one(EntityType.class).equals(TOWER)) {
            if(toTile(o, playerPos.x, playerPos.y) != null){
                tile = toTile(o, playerPos.x, playerPos.y);
                if (context(entity).one(BehaviorType.class).equals(BehaviorType.SPAWNING)) {
                    if(checkTile(tile) != null){
                        entity.setDestroyed(true); //Destroy's drag and drop entity

                        ClassLoader cl = Lookup.getDefault().lookup(ClassLoader.class);
                        String url = cl.getResource("assets/images/Nazi_Tank.png").toExternalForm();

                        Entity tower = new Entity();
                        context(tower).add(EntityType.class, TOWER);
                        context(tower).add(Range.class, new Range(200));
                        context(tower).add(BehaviorType.class, BehaviorType.SHOOT);
                        context(tower).add(Rotation.class, new Rotation());
                        context(tower).add(GameTime.class, new GameTime(10));
                        context(tower).add(ImageAsset.class, new ImageAsset(url));
                        context(tower).add(Position.class, (checkTile(tile)));
                        context(tower).add(Scale.class, new Scale(1f, 1f));
                        context(o).add(Entity.class, tower);
                                    context(tile).remove(Available.AVAIL);
            context(tile).add(Available.class, Available.BLOCKED);
                    }
                }
            }

        }
    }
    
    private Position checkTile(Entity tile){
        Available av = context(tile).one(Available.class);
        if(av.equals(Available.AVAIL)){
            Position tilePos = context(tile).one(Position.class);
            return tilePos;
        }
        else return null;
    }
    
    private Entity toTile(Object o, float x, float y){
        for(Entity e : context(o).all(Entity.class))
            if(context(e).one(EntityType.class) == EntityType.MAPTILE){
                Position tilePos = context(e).one(Position.class);
                if(playerPos.x >= tilePos.x-32 && playerPos.x <= tilePos.x+32){
                    if(playerPos.y >= tilePos.y-32 && playerPos.y <= tilePos.y+32f){
                        if(context(e).one(MapTileType.class)==MapTileType.GRASS){
                            return e;
                        }
                    }
                }
                
            }
        return null;
    }

}
