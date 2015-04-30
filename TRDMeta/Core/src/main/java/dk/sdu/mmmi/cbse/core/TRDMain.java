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
import dk.sdu.mmmi.cbse.common.data.types.EntitySubType;
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import static dk.sdu.mmmi.cbse.common.data.types.EntityType.PLAYER;
import dk.sdu.mmmi.cbse.common.data.types.LevelType;
import dk.sdu.mmmi.cbse.common.data.types.WaveType;
import dk.sdu.mmmi.cbse.common.services.IContentService;
import dk.sdu.mmmi.cbse.common.services.IUpdateService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import playn.core.Game;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Mouse;
import playn.core.PlayN;
import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

/**
 *
 * @author SoA
 */
public class TRDMain extends Game.Default{

    private GroupLayer backgroundLayer;
    private Object world;
    private Entity player;
    private AnnotationConfigApplicationContext ctx;
    
    public TRDMain(int updateRate) {
        super(updateRate);
    }

    @Override
    public void init() {
        backgroundLayer = graphics().rootLayer();
        world = new Object();
        System.out.println("Height of application: " +graphics().height());
        System.out.println("Width of application: " +graphics().width());
        
        //Somewhat deprecated spawning system
        context(world).add(LevelType.class, LevelType.ONE);
        context(world).add(WaveType.class, WaveType.ONE);
        
        //Services
        initServices();
        
        //Add a mouselistener to the game, and let it keep a reference for the player entity
        PlayN.mouse().setListener(new InputController(player));
        
        System.out.println("ContentServices located: " +getContentServices().size());
        System.out.println("UpdateableServices located: " +getUpdateServices().size());
        
    }
    
    //Updates all entities via service providers
    @Override
    public void update(int delta) {
        for(IUpdateService service : getUpdateServices()){
            for (Entity e : context(world).all(Entity.class)) {
                service.update(world, e);
            }
        }
    }
    
    //Paint all entities
    @Override
    public void paint(float alpha){ 
        for (Entity e : context(world).all(Entity.class)) {
            
            //get details of entity for drawing
            ImageLayer view = context(e).one(ImageLayer.class);
            Position p = context(e).one(Position.class);
            Rotation r = context(e).one(Rotation.class);
            Scale s = context(e).one(Scale.class);
            
            if (view == null) {
                view = createImageAsset(e);
                if(context(e).one(EntitySubType.class) == EntitySubType.BUYING_NAZITANK){
                    view.addListener(LayerListenerController.getInstance(player).setTankListener(e));
                }
                if(context(e).one(EntitySubType.class) == EntitySubType.BUYING_GASCHAMBER){
                    view.addListener(LayerListenerController.getInstance(player).setChamberListener(e));
                }
                if(context(e).one(EntityType.class)== EntityType.TOWER){
                    view.addListener(LayerListenerController.getInstance(player).setTowerListener(e));
                }
            }
            

            view.setTranslation(p.x, p.y);
            if(r != null){
                view.setRotation(r.angle);
            }
            view.setAlpha(1.0f);
            view.setScale(s.x, s.y);
            
            if (e.isDestroyed()) {
                backgroundLayer.remove(view);
                context(world).remove(e);
            }
            //TODO viewLayer.setDepth(Float f);
        }
    }
    
    //Creates an ImageLayer for an Entity
    private ImageLayer createImageAsset(final Entity entity) {

        ImageAsset sprite = context(entity).one(ImageAsset.class);

        String imagePath = sprite.getImageFilePath();
        Image image = assets().getImageSync(imagePath);
        ImageLayer viewLayer = graphics().createImageLayer(image);
        viewLayer.setOrigin(image.width() / 2f, image.height() / 2f);

        context(entity).add(ImageLayer.class, viewLayer);
        backgroundLayer.add(viewLayer);
        
        return viewLayer;
    }
    
    private void initServices(){
        ctx = new AnnotationConfigApplicationContext();
        ctx.scan("dk.sdu");
        ctx.refresh();
        
        //A terrible solution to the problem of components loading in the wrong order
        //If any method for ordering the load order of components using Spring is found
        //then this should be removed - @Order and @DependsOn does not seem to care
        List<IContentService> contentList = new ArrayList(getContentServices());
        Collections.reverse(contentList);
        
        //Find all content services, and add their content(entities) to the world
        for(IContentService service : contentList){
            System.out.println("Adding service:" + service.getClass().getName());
            service.add(world);
        }
        
        //Locate the player entity
        for (Entity entity : context(world).all(Entity.class)) {
            if (context(entity).one(EntityType.class) == PLAYER) {
                this.player = entity;
            }
        }   
    }
    
    private Collection<? extends IContentService> getContentServices() {
        return ctx.getBeansOfType(IContentService.class).values();
    }

    private Collection<? extends IUpdateService> getUpdateServices() {
        return ctx.getBeansOfType(IUpdateService.class).values();
    }
}
