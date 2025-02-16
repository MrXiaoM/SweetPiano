package top.mrxiaom.sweet.piano.func;

import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.Nullable;
import top.mrxiaom.pluginbase.func.AutoRegister;
import top.mrxiaom.pluginbase.utils.Util;
import top.mrxiaom.sweet.piano.SweetPiano;
import top.mrxiaom.sweet.piano.func.entry.KeySet;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

@AutoRegister
public class KeysManager extends AbstractModule {
    Map<String, KeySet> keysMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    public KeysManager(SweetPiano plugin) {
        super(plugin);
    }

    @Override
    public void reloadConfig(MemoryConfiguration config) {
        keysMap.clear();
        for (String path : config.getStringList("keys-folder")) {
            File folder = plugin.resolve(path);
            if (!folder.exists()) {
                Util.mkdirs(folder);
                if (path.equals("./keys")) {
                    plugin.saveResource("keys/c_major.yml", new File(folder, "c_major.yml"));
                    plugin.saveResource("keys/c_major_alt.yml", new File(folder, "c_major_alt.yml"));
                    plugin.saveResource("keys/just_piano_1.yml", new File(folder, "just_piano_1.yml"));
                    plugin.saveResource("keys/just_piano_2.yml", new File(folder, "just_piano_2.yml"));
                    plugin.saveResource("keys/just_piano_3.yml", new File(folder, "just_piano_3.yml"));
                }
            }
            Util.reloadFolder(folder, false, (id, file) -> {
                YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                KeySet loaded = KeySet.load(this, cfg, id);
                if (loaded != null) {
                    keysMap.put(id, loaded);
                }
            });
        }
        info("加载了 " + keysMap.size() + " 个乐器按键配置");
    }

    @Nullable
    public KeySet get(String id) {
        return keysMap.get(id);
    }

    public static KeysManager inst() {
        return instanceOf(KeysManager.class);
    }
}
