package net.thedragonteam.armorplus.entity.render;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.entity.entityzombie.EntityEnderDragonZombie;

import static net.thedragonteam.armorplus.util.Utils.setRL;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
@SideOnly(Side.CLIENT)
public class RenderEnderDragonZombie extends RenderBiped<EntityEnderDragonZombie> {

    public static final Factory FACTORY = new Factory();

    private ResourceLocation mobTexture = setRL("textures/entity/ender_dragon_zombie.png");

    public RenderEnderDragonZombie(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelZombie(), 0.5f);
        this.layerRenderers.get(0);
        this.addLayer(new LayerHeldItem(this));
        new LayerBipedArmor(this) {
            @Override
            protected void initArmor() {
                this.modelLeggings = new ModelZombie(0.5f, true);
                this.modelArmor = new ModelZombie(1.0f, true);
            }
        };
    }

    //Using Minecraft Zombie's Texture and Re-Texturing it
    @Override
    protected ResourceLocation getEntityTexture(EntityEnderDragonZombie entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityEnderDragonZombie> {
        @Override
        public Render<? super EntityEnderDragonZombie> createRenderFor(RenderManager manager) {
            return new RenderEnderDragonZombie(manager);
        }
    }

}