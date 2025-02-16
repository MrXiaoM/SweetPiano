package top.mrxiaom.sweet.piano.func;
        
import top.mrxiaom.sweet.piano.SweetPiano;

@SuppressWarnings({"unused"})
public abstract class AbstractPluginHolder extends top.mrxiaom.pluginbase.func.AbstractPluginHolder<SweetPiano> {
    public AbstractPluginHolder(SweetPiano plugin) {
        super(plugin);
    }

    public AbstractPluginHolder(SweetPiano plugin, boolean register) {
        super(plugin, register);
    }
}
