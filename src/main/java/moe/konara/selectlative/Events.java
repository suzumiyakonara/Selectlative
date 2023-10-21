package moe.konara.selectlative;

import net.minecraft.advancements.Advancement;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static moe.konara.selectlative.Selectlative.*;
import static moe.konara.selectlative.Utils.*;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.FORGE)
public class Events {
    @SubscribeEvent
    public static void onAdvancement(final AdvancementEvent event) {
        String advancement_id = event.getAdvancement().getId().toString();
        if(CONFIG.Advancement_LogInfo)
            LOGGER.info(advancement_id);

        if(CONFIG.contain(advancement_id)) {
            DATA.SetGranted(advancement_id);
            for (ServerPlayer player : event.getEntity().getServer().getPlayerList().getPlayers()) {
                if (event.getEntity().getUUID() != player.getUUID()) {
                    GrantAchievement(player, advancement_id);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLogIn(final PlayerEvent.PlayerLoggedInEvent event)
    {
        DATA.read();
        for(SelectedAdvancement ad : DATA.Advancement_List){
            if(ad.status){
                GrantAchievement(GetServerPlayer(event.getEntity()),ad.id);
            }
        }
    }
}