package top.mrxiaom.sweet.piano.func.entry;

import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.Nullable;
import top.mrxiaom.pluginbase.utils.Util;
import top.mrxiaom.sweet.piano.func.KeysManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeySet {
    public final String id;
    public final String material;
    public final String display;
    public final List<String> description;
    public final Map<Integer, KeyProperty> keys;

    public KeySet(String id, String material, String display, List<String> description, Map<Integer, KeyProperty> keys) {
        this.id = id;
        this.material = material;
        this.display = display;
        this.description = description;
        this.keys = keys;
    }

    @Nullable
    public static KeySet load(KeysManager parent, ConfigurationSection config, String id) {
        String material = config.getString("material");
        if (material == null) {
            parent.warn("[keys/" + id + "] 未输入 material");
            return null;
        }
        String display = config.getString("display", "");
        List<String> description = config.getStringList("description");
        Map<Integer, KeyProperty> keys = new HashMap<>();
        ConfigurationSection section = config.getConfigurationSection("keys");
        if (section != null) for (String key : section.getKeys(false)) {
            Integer i = Util.parseInt(key).orElse(null);
            if (i != null && i >= 1 && i <= 8) {
                int slot = i - 1;
                KeyProperty loaded = KeyProperty.load(parent, id, section, key, slot);
                if (loaded != null) {
                    keys.put(slot, loaded);
                }
            }
        }
        return new KeySet(id, material, display, description, keys);
    }
}
