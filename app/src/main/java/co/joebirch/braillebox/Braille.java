package co.joebirch.braillebox;

public enum Braille {

    A('a', 100000), B('b', 101000), C('c', 110000), D('d', 110100), E('e', 100100), F('f', 111000),
    G('g', 111100), H('h', 101100), I('i', 010100), J('j', 011100), K('k', 100010), L('l', 101010),
    M('m', 110010), N('n', 110110), O('o', 100110), P('p', 111010), Q('q', 111110), R('r', 101110),
    S('s', 011010), T('t', 011110), U('u', 100011), V('v', 101011), W('w', 011101), X('x', 110011),
    Y('y', 110111), Z('z', 100111), ONE('a', 100000), TWO('b', 101000), THREE('c', 110000),
    FOUR('d', 110100), FIVE('e', 100100), SIX('f', 111000), SEVEN('g', 111100), EIGHT('h', 101100),
    NINE('i', 010100), ZERO('j', 011100), PERIOD('.', 001101), COMMA(',', 001000),
    QUESTION_MARK('?', 001011), EXCLAMATION_MARK('!', 001110), COLON(':', 001100),
    SEMICOLON(';', 001010);

    private final Character key;
    private final int value;

    Braille(Character key, int value) {
        this.key = key;
        this.value = value;
    }

    public Character getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public static Braille fromKey(Character character) {
        for (Braille braille : Braille.values()) {
            if (braille.getKey().equals(character)) {
                return braille;
            }
        }
        return QUESTION_MARK;
    }

}
