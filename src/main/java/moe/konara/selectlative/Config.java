package moe.konara.selectlative;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static moe.konara.selectlative.Selectlative.CONFIG_FOLDER;
import static moe.konara.selectlative.Selectlative.GSON;
import static moe.konara.selectlative.Utils.ReadFile;
import static moe.konara.selectlative.Utils.WriteFile;

public class Config {
    List<String> Advancement_List;
    boolean Advancement_LogInfo;
    public Config(){
        Advancement_LogInfo=true;
        Advancement_List = new ArrayList<>();
        Advancement_List.add("minecraft:end/kill_dragon");
    }

    public void read(){
        String raw_config = ReadFile(
                CONFIG_FOLDER+"Config.json");
        if(Objects.equals(raw_config, ""))
            return;
        Config file_config = Selectlative.GSON.fromJson(raw_config,Config.class);
        Advancement_List = file_config.Advancement_List;
        Advancement_LogInfo = file_config.Advancement_LogInfo;
    }

    public void save(){
        WriteFile(CONFIG_FOLDER+"Config.json", GSON.toJson(this));
    }

    public boolean contain(String id){
        for(String aid : Advancement_List){
            if(aid.equals(id))
                return true;
        }
        return false;
    }
}
