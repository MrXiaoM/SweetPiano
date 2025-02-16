package top.mrxiaom.sweet.piano.utils;

import com.google.common.collect.Lists;
import org.jetbrains.annotations.Nullable;
import top.mrxiaom.pluginbase.utils.Pair;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static top.mrxiaom.pluginbase.utils.Pair.of;

public class Notes {
    private static final Map<String, Float> pitchMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public static void init() {
        pitchMap.clear();
        // https://zh.minecraft.wiki/w/%E9%9F%B3%E7%AC%A6%E7%9B%92#%E9%9F%B3%E7%AC%A6
        List<Pair<String, Float>> list = Lists.newArrayList(
                of("F#3", 0.5f),
                of("G3", 0.529732f),
                of("G#3", 0.561231f),
                of("A4", 0.594604f),
                of("A#4", 0.629961f),
                of("B4", 0.667420f),
                of("C4", 0.707107f),
                of("C#4", 0.749154f),
                of("D4", 0.793701f),
                of("D#4", 0.840896f),
                of("E4", 0.890899f),
                of("F4", 0.943874f),
                of("F#4", 1.0f),
                of("G4", 1.059463f),
                of("G#4", 1.122462f),
                of("A5", 1.189207f),
                of("A#5", 1.259921f),
                of("B5", 1.334840f),
                of("C5", 1.414214f),
                of("C#5", 1.498307f),
                of("D5", 1.587401f),
                of("D#5", 1.681793f),
                of("E5", 1.781797f),
                of("F5", 1.887749f),
                of("F#5", 2.0f));
        for (Pair<String, Float> pair : list) {
            String note = pair.getKey();
            Float pitch = pair.getValue();
            pitchMap.put(note, pitch);
            if (note.contains("#")) {
                pitchMap.put(note.replace("#", "♯"), pitch);
            }
        }
        list.clear();
    }

    @Nullable
    public static Float pitch(String note) {
        return pitchMap.get(note);
    }
}
