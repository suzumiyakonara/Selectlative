package moe.konara.selectlative;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static moe.konara.selectlative.Selectlative.CONFIG_FOLDER;
import static moe.konara.selectlative.Selectlative.GSON;
import static moe.konara.selectlative.Utils.ReadFile;
import static moe.konara.selectlative.Utils.WriteFile;

public class Data {
    List<SelectedAdvancement> Advancement_List=new ArrayList<>();
    public void SetGranted(String advancement_id){
        read();
        for (SelectedAdvancement selectedAdvancement : Advancement_List) {
            if (Objects.equals(selectedAdvancement.id, advancement_id)) {
                selectedAdvancement.status=true;
                save();
                return;
            }
        }
        Advancement_List.add(new SelectedAdvancement(advancement_id));
        save();
    }

    public void read(){
        String raw_config = ReadFile(
                CONFIG_FOLDER+"Data.json");
        if(Objects.equals(raw_config, ""))
            return;
        Data file_config = Selectlative.GSON.fromJson(raw_config,Data.class);
        Advancement_List = file_config.Advancement_List;
    }

    public void save(){
        WriteFile(CONFIG_FOLDER+"Data.json", GSON.toJson(this));
    }
}
