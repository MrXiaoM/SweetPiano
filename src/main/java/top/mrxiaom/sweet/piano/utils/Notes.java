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
                // 低于 0.5 的会被截止，无法继续下探音域
                of("F#3", 0.5f),     // 2^-(12/12)
                of("G3", 0.529732f), // 2^-(11/12)
                of("G#3", 0.561231f),// 2^-(10/12)
                of("A4", 0.594604f), // 2^-(9/12)
                of("A#4", 0.629961f),// 2^-(8/12)
                of("B4", 0.667420f), // 2^-(7/12)
                of("C4", 0.707107f), // 2^-(6/12)
                of("C#4", 0.749154f),// 2^-(5/12)
                of("D4", 0.793701f), // 2^-(4/12)
                of("D#4", 0.840896f),// 2^-(3/12)
                of("E4", 0.890899f), // 2^-(2/12)
                of("F4", 0.943874f), // 2^-(1/12)
                //
                of("F#4", 1.0f),     // 2^-(0/12)
                of("G4", 1.059463f), // 2^(1/12)
                of("G#4", 1.122462f),// 2^(2/12)
                of("A5", 1.189207f), // 2^(3/12)
                of("A#5", 1.259921f),// 2^(4/12)
                of("B5", 1.334840f), // 2^(5/12)
                of("C5", 1.414214f), // 2^(6/12)
                of("C#5", 1.498307f),// 2^(7/12)
                of("D5", 1.587401f), // 2^(8/12)
                of("D#5", 1.681793f),// 2^(9/12)
                of("E5", 1.781797f), // 2^(10/12)
                of("F5", 1.887749f), // 2^(11/12)
                of("F#5", 2.0f));    // 2^(12/12)
                // 高于 2.0 的会被截止，无法继续上探音域

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
