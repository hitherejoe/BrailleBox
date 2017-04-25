package co.joebirch.braillebox.util;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import co.joebirch.braillebox.data.Braille;

public class BrailleMapper {

    @Inject
    BrailleMapper() {

    }

    public List<String> mapFromWords(String... words) {
        List<String> braille = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            braille.addAll(mapFromString(words[i]));
            if (i < words.length - 1) braille.add(Braille.SPACE.getValue());
        }
        return braille;
    }

    public List<String> mapFromString(String text) {
        List<String> braille = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            braille.add(Braille.fromKey(
                    Character.toLowerCase(text.charAt(i))).getValue());
        }
        return braille;
    }

}
