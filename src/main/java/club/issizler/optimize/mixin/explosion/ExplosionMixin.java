// https://github.com/PaperMC/Paper/blob/ver/1.14/Spigot-Server-Patches/0032-Optimize-explosions.patch

package club.issizler.optimize.mixin.explosion;

import club.issizler.optimize.Optimize;
import club.issizler.optimize.mixin.accessors.ExplosionAccessorMixin;
import club.issizler.optimize.utils.ExplosionCacheKey;
import club.issizler.optimize.utils.accessors.WorldDensityCacheable;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Explosion.class)
public abstract class ExplosionMixin {

    @Shadow
    @Final
    private World world;

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/explosion/Explosion;getExposure(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/entity/Entity;)F"), method = "collectBlocksAndDamageEntities")
    private float oky$collectBlocksAndDamageEntities$optimize(Vec3d vec3d_1, Entity entity_1) {
        if (!Optimize.USE_FAST_EXPLOSIONS)
            return Explosion.getExposure(vec3d_1, entity_1);

        return getBlockDensity(vec3d_1, entity_1);
    }

    private float getBlockDensity(Vec3d vec3d_1, Entity entity_1) {
        ExplosionCacheKey key = new ExplosionCacheKey((ExplosionAccessorMixin) this, entity_1.getBoundingBox());

        Float blockDensity = ((WorldDensityCacheable) world).getExplosionDensityCache().get(key);
        if (blockDensity == null) {
            blockDensity = Explosion.getExposure(vec3d_1, entity_1);
            ((WorldDensityCacheable) world).getExplosionDensityCache().put(key, blockDensity);
        }

        return blockDensity;
    }

}
