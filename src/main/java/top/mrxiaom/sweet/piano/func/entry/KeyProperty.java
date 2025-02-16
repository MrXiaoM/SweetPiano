package top.mrxiaom.sweet.piano.func.entry;

import org.bukkit.configuration.ConfigurationSection;
import top.mrxiaom.pluginbase.utils.Util;
import top.mrxiaom.sweet.piano.func.KeysManager;
import top.mrxiaom.sweet.piano.utils.Notes;

import java.util.List;

public class KeyProperty {
    public final int slot;
    public final String material;
    public final String display;
    public final List<String> lore;
    public final float pitch;

    public KeyProperty(int slot, String material, String display, List<String> lore, float pitch) {
        this.slot = slot;
        this.material = material;
        this.display = display;
        this.lore = lore;
        this.pitch = pitch;
    }

    public static KeyProperty load(KeysManager parent, String id, ConfigurationSection section, String key, int slot) {
        String material = section.getString(key + ".material");
        if (material == null) {
            parent.warn("[keys/" + id + "] 按键 " + key + " 未输入 material");
            return null;
        }
        String display = section.getString(key + ".display", "");
        List<String> lore = section.getStringList(key + ".lore");
        float pitch;
        String note = section.getString(key + ".note");
        if (note != null) {
            Float notePitch = Notes.pitch(note);
            if (notePitch == null) {
                parent.warn("[keys/" + id + "] 按键 " + key + " 输入的 note 不可用");
                return null;
            }
            pitch = notePitch;
        } else {
            Float configPitch = Util.parseFloat(section.getString(key + ".pitch", "1.0")).orElse(null);
            if (configPitch == null || configPitch < 0.5f || configPitch > 2.0f) {
                parent.warn("[keys/" + id + "] 按键 " + key + " 输入的 pitch 不可用");
                return null;
            }
            pitch = configPitch;
        }
        return new KeyProperty(slot, material, display, lore, pitch);
    }
}
