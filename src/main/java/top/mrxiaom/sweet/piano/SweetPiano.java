package top.mrxiaom.sweet.piano;
        
import de.tr7zw.changeme.nbtapi.utils.MinecraftVersion;
import top.mrxiaom.pluginbase.BukkitPlugin;
import top.mrxiaom.sweet.piano.utils.Notes;

public class SweetPiano extends BukkitPlugin {
    public static SweetPiano getInstance() {
        return (SweetPiano) BukkitPlugin.getInstance();
    }

    public SweetPiano() {
        super(options()
                .bungee(false)
                .adventure(true)
                .database(false)
                .reconnectDatabaseWhenReloadConfig(false)
                .vaultEconomy(false)
                .scanIgnore("top.mrxiaom.sweet.piano.libs")
        );
    }

    @Override
    protected void beforeLoad() {
        Notes.init();
        MinecraftVersion.replaceLogger(getLogger());
        MinecraftVersion.disableUpdateCheck();
        MinecraftVersion.disableBStats();
        MinecraftVersion.getVersion();
    }

    @Override
    protected void afterEnable() {
        getLogger().info("SweetPiano 加载完毕");
    }
}
