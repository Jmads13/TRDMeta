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
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import static dk.sdu.mmmi.cbse.common.data.types.EntityType.PLAYER;
import dk.sdu.mmmi.cbse.common.data.types.LevelType;
import dk.sdu.mmmi.cbse.common.data.types.WaveType;
import dk.sdu.mmmi.cbse.common.services.IContentService;
import dk.sdu.mmmi.cbse.common.services.IUpdateService;
import org.openide.util.Lookup;
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

    private GroupLayer rootLayer;
    private Object world;
    private Entity player;
    
    public TRDMain(int updateRate) {
        super(updateRate);
    }

    @Override
    public void init() {
        rootLayer = graphics().rootLayer();
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
        
        System.out.println("ContentServices located: " +Lookup.getDefault().lookupAll(IContentService.class).size());
        System.out.println("UpdateableServices located: " +Lookup.getDefault().lookupAll(IUpdateService.class).size());
        
    }
    
    //Updates all entities via service providers
    @Override
    public void update(int delta) {
        for(IUpdateService service : Lookup.getDefault().lookupAll(IUpdateService.class)){
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
            }
            
            //Adds a mouse listener to the ImageLayer of the player (This should be used to place towers)
            //TODO //FIXME
            if(context(e).one(EntityType.class) == PLAYER){
                view.addListener(mouseDerp(e));
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
    
    //Creates an ImageLayer for an Entity
    private ImageLayer createImageAsset(final Entity entity) {

        ImageAsset sprite = context(entity).one(ImageAsset.class);

        String imagePath = sprite.getImageFilePath();
        Image image = assets().getImageSync(imagePath);
        ImageLayer viewLayer = graphics().createImageLayer(image);
        viewLayer.setOrigin(image.width() / 2f, image.height() / 2f);

        context(entity).add(ImageLayer.class, viewLayer);
        rootLayer.add(viewLayer);
        
        return viewLayer;
    }
    
    //Player mouse adapter, for placing towers
    //See Mouse.LayerAdapter for overrideable methods
    private Mouse.LayerAdapter mouseDerp(Entity e){
        Mouse.LayerAdapter ma = new Mouse.LayerAdapter() {
            @Override
            public void onMouseDown(Mouse.ButtonEvent event) {
                
            }
        };
        return ma;
    }
    
    private void initServices(){
        //Find all content services, and add their content(entities) to the world
        for(IContentService service : Lookup.getDefault().lookupAll(IContentService.class)){
            service.add(world);
        }
        
        //Locate the player entity
        for (Entity entity : context(world).all(Entity.class)) {
            if (context(entity).one(EntityType.class) == PLAYER) {
                this.player = entity;
            }
        }
    }
}
