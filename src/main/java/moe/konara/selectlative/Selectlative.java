package moe.konara.selectlative;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.io.File;

@Mod(Selectlative.MODID)
public class Selectlative {
    public static final String MODID = "selectlative";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static Config CONFIG;
    public static Data DATA;
    public static String CONFIG_FOLDER;
    public static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public Selectlative() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        CONFIG_FOLDER = event.getServer().getServerDirectory().getPath()+"/config/Selectlative/";
        if(!new File(CONFIG_FOLDER).exists())
            new File(CONFIG_FOLDER).mkdirs();
        CONFIG = new Config();
        CONFIG.read();
        CONFIG.save();
        DATA = new Data();
        DATA.read();
        DATA.save();

    }
}
