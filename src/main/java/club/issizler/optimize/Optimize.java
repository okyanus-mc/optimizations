package club.issizler.optimize;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.SyntaxError;
import net.fabricmc.api.DedicatedServerModInitializer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class Optimize implements DedicatedServerModInitializer {

    public static boolean USE_FAST_EXPLOSIONS = false;
    public static boolean USE_FAST_REDSTONE;

    public static int MAX_BOOK_PAGE_SIZE = 2560;
    public static double MAX_BOOK_TOTAL_SIZE_MULTIPLIER = 0.98;

    public static int PACKET_RATE_LIMIT = 250;
    public static double PACKET_RATE_LIMIT_INTERVAL = 10.0;

    @Override
    public void onInitializeServer() {
        try {
            Jankson js = Jankson.builder().build();
            File configFile = new File("config/okyanus.optimizations.json");

            if (!configFile.getParentFile().exists())
                configFile.getParentFile().mkdirs();

            if (!configFile.exists()) {
                InputStream defaultConfig = getClass().getResourceAsStream("/okyanusopt.defaultconf.json");
                Files.copy(defaultConfig, configFile.toPath());
            }

            JsonObject config = js.load(configFile);

            USE_FAST_EXPLOSIONS = config.get(Boolean.class, "optimizations.fastExplosions");
            USE_FAST_REDSTONE = config.get(Boolean.class, "optimizations.fastRedstone");

            MAX_BOOK_PAGE_SIZE = config.get(Integer.class, "limits.book.maxPageSize");
            MAX_BOOK_TOTAL_SIZE_MULTIPLIER = config.get(Double.class, "limits.book.totalSizeMultiplier");

            PACKET_RATE_LIMIT = config.get(Integer.class, "limits.packet.rateLimit");
            PACKET_RATE_LIMIT_INTERVAL = config.get(Double.class, "limits.packet.rateLimitInterval");

            System.err.println("THIS SERVER HAS OKYANUS OPTIMIZATIONS RUNNING");
            System.err.println("MODS MIGHT BREAK! DONT ASK IN THEIR ISSUE TRACKER UNLESS YOU'RE ABSOLUTELY SURE IT'S THEIR FAULT");
        } catch (IOException | SyntaxError e) {
            throw new RuntimeException("Error while loading configuration!", e);
        }
    }

}
