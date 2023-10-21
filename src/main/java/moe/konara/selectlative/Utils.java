package moe.konara.selectlative;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.core.Registry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.apache.logging.log4j.core.jmx.Server;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static moe.konara.selectlative.Selectlative.LOGGER;

public class Utils {
    public static String ReadFile(String path){
        if(!new File(path).exists()){
            return "";
        }
        StringBuilder result= new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!result.isEmpty())
            return result.substring(0,result.length()-1);
        else{
            return "";
        }
    }

    public static void WriteFile(String path,String content){
        File file = new File(path);
        try(PrintWriter out = new PrintWriter(file, StandardCharsets.UTF_8)) {
            out.print(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void GrantAchievement(ServerPlayer player, String advancement_id){
        for(Advancement advancement : player.getServer().getAdvancements().getAllAdvancements())
            if(advancement.getId().toString().equals(advancement_id)) {
                AdvancementProgress progress = player.getAdvancements().getOrStartProgress(advancement);
                if(!progress.isDone()) {
                    LOGGER.debug("Granting \"" + advancement_id + "\" Advancement for" + player.getName().getString());
                    //player.getAdvancements().getOrStartProgress(advancement).grantProgress(advancement.getCriteria().);
                    for (String criterion : advancement.getCriteria().keySet())
                    {
                        player.getAdvancements().award(advancement, criterion);
                    }
                }
            }
    }

    public static ServerPlayer GetServerPlayer(Player player){
        UUID uuid = player.getUUID();
        for(ServerPlayer target : player.getServer().getPlayerList().getPlayers()){
            if(uuid.equals(target.getUUID())){
                return target;
            }
        }
        return null;
    }
}
