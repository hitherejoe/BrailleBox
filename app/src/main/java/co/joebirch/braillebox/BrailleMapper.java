package co.joebirch.braillebox;

import java.util.ArrayList;
import java.util.List;

public class BrailleMapper {

    public BrailleMapper() {

    }

    private List<Integer> mapFromString(String text) {
        List<Integer> integer = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            integer.add(Braille.fromKey(Character.toLowerCase(text.charAt(i))).getValue());
        }
        return integer;
    }

}
