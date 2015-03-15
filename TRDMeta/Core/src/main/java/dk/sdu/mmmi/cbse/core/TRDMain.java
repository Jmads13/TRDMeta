/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.sdu.mmmi.cbse.core;

import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.ImageAsset;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.Rotation;
import dk.sdu.mmmi.cbse.common.data.Scale;
import dk.sdu.mmmi.cbse.common.services.IContentService;
import dk.sdu.mmmi.cbse.common.services.IUpdateService;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import playn.core.Game;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Layer;
import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

/**
 *
 * @author SoA
 */
public class TRDMain extends Game.Default{

    GroupLayer rootLayer;
    List<Entity> EntityArr;
    Object world;
    
    public TRDMain(int updateRate) {
        super(updateRate);
    }

    @Override
    public void init() {
        rootLayer = graphics().rootLayer();
        EntityArr = new ArrayList<>();
        world = new Object();
        
        for(IContentService service : Lookup.getDefault().lookupAll(IContentService.class)){
            service.add(world);
        }
        
        System.out.println("ContentServices located: " +Lookup.getDefault().lookupAll(IContentService.class).size());
        System.out.println("UpdateableServices located: " +Lookup.getDefault().lookupAll(IUpdateService.class).size());
        
    }
    
    @Override
    public void update(int delta) {
        for(IUpdateService service : Lookup.getDefault().lookupAll(IUpdateService.class)){
            for (Entity e : context(world).all(Entity.class)) {
            
            }
        }
    }
    
    @Override
    public void paint(float alpha){ 
        for (Entity e : context(world).all(Entity.class)) {
            
            ImageLayer view = context(e).one(ImageLayer.class);
            
            Position p = context(e).one(Position.class);
            Rotation r = context(e).one(Rotation.class);
            Scale s = context(e).one(Scale.class);

            if (view == null) {
                view = createImageAsset(e);
            }
            view.setTranslation(p.x, p.y);
            //view.setRotation(r.angle);
            view.setAlpha(1.0f);
            view.setScale(s.x, s.y);

            if (e.isDestroyed()) {
                rootLayer.remove(view);
                context(world).remove(e);
            }
        }
    }
    
    private ImageLayer createImageAsset(Entity entity) {

        ImageAsset sprite = context(entity).one(ImageAsset.class);

        String imagePath = sprite.getImageFilePath();

        Image image = assets().getImageSync(imagePath);

        ImageLayer viewLayer = graphics().createImageLayer(image);
        viewLayer.setOrigin(image.width() / 2f, image.height() / 2f);

        context(entity).add(ImageLayer.class, viewLayer);
        rootLayer.add(viewLayer);

        return viewLayer;
    }
    
}
