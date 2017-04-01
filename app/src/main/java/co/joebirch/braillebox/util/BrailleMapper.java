package co.joebirch.braillebox.util;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import co.joebirch.braillebox.data.Braille;

public class BrailleMapper {

    @Inject
    public BrailleMapper() {

    }

    public List<String> mapfromSequences(String... params) {
        List<String> braille = new ArrayList<>();
        for (int i = 0; i < params.length; i++) {
            braille.addAll(mapFromString(params[i]));
            if (i < params.length - 1) braille.add(Braille.SPACE.getValue());
        }
        return braille;
    }

    public List<String> mapFromString(String text) {
        List<String> integer = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            integer.add(Braille.fromKey(Character.toLowerCase(text.charAt(i))).getValue());
        }
        return integer;
    }

}
