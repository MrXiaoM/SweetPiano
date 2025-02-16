package top.mrxiaom.sweet.piano;
        
import org.jetbrains.annotations.NotNull;
import top.mrxiaom.pluginbase.BukkitPlugin;
import top.mrxiaom.pluginbase.EconomyHolder;

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
    protected void afterEnable() {
        getLogger().info("SweetPiano 加载完毕");
    }
}
