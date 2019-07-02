package club.issizler.optimize.utils;

import club.issizler.optimize.mixin.accessors.ExplosionAccessorMixin;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class ExplosionCacheKey {
    private final World world;
    private final double posX, posY, posZ;
    private final double minX, minY, minZ;
    private final double maxX, maxY, maxZ;

    public ExplosionCacheKey(ExplosionAccessorMixin explosion, Box aabb) {
        this.world = explosion.getWorld();
        this.posX = explosion.getX();
        this.posY = explosion.getY();
        this.posZ = explosion.getZ();
        this.minX = aabb.minX;
        this.minY = aabb.minY;
        this.minZ = aabb.minZ;
        this.maxX = aabb.maxX;
        this.maxY = aabb.maxY;
        this.maxZ = aabb.maxZ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExplosionCacheKey cacheKey = (ExplosionCacheKey) o;

        if (Double.compare(cacheKey.posX, posX) != 0) return false;
        if (Double.compare(cacheKey.posY, posY) != 0) return false;
        if (Double.compare(cacheKey.posZ, posZ) != 0) return false;
        if (Double.compare(cacheKey.minX, minX) != 0) return false;
        if (Double.compare(cacheKey.minY, minY) != 0) return false;
        if (Double.compare(cacheKey.minZ, minZ) != 0) return false;
        if (Double.compare(cacheKey.maxX, maxX) != 0) return false;
        if (Double.compare(cacheKey.maxY, maxY) != 0) return false;
        if (Double.compare(cacheKey.maxZ, maxZ) != 0) return false;
        return world.equals(cacheKey.world);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = world.hashCode();
        temp = Double.doubleToLongBits(posX);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(posY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(posZ);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(minX);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(minY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(minZ);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(maxX);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(maxY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(maxZ);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}