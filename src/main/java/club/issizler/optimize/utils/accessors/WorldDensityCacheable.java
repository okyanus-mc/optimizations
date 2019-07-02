package club.issizler.optimize.utils.accessors;

import club.issizler.optimize.utils.ExplosionCacheKey;

import java.util.Map;

public interface WorldDensityCacheable {

    Map<ExplosionCacheKey, Float> getExplosionDensityCache();

}
