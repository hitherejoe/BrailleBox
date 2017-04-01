package co.joebirch.braillebox;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import co.joebirch.braillebox.data.Braille;
import co.joebirch.braillebox.util.BrailleMapper;

import static junit.framework.Assert.assertEquals;

public class BrailleMapperTest {

    private BrailleMapper brailleMapper;

    @Before
    public void setup() {
        brailleMapper = new BrailleMapper();
    }

    @Test
    public void mapsSingleWordToBrailleRepresentatino() throws Exception {
        String word = "hello";
        List<String> brailleSequence = new ArrayList<>();
        brailleSequence.add(Braille.H.getValue());
        brailleSequence.add(Braille.E.getValue());
        brailleSequence.add(Braille.L.getValue());
        brailleSequence.add(Braille.L.getValue());
        brailleSequence.add(Braille.O.getValue());

        List<String> braille = brailleMapper.mapFromString(word);

        assertEquals(brailleSequence, braille);
    }

    @Test
    public void mapsAlphabetToBrailleRepresentatino() throws Exception {
        String word = "wxyzabcdstuvmnopijklqrefgh";
        List<String> brailleSequence = new ArrayList<>();
        brailleSequence.add(Braille.W.getValue());
        brailleSequence.add(Braille.X.getValue());
        brailleSequence.add(Braille.Y.getValue());
        brailleSequence.add(Braille.Z.getValue());
        brailleSequence.add(Braille.A.getValue());
        brailleSequence.add(Braille.B.getValue());
        brailleSequence.add(Braille.C.getValue());
        brailleSequence.add(Braille.D.getValue());
        brailleSequence.add(Braille.S.getValue());
        brailleSequence.add(Braille.T.getValue());
        brailleSequence.add(Braille.U.getValue());
        brailleSequence.add(Braille.V.getValue());
        brailleSequence.add(Braille.M.getValue());
        brailleSequence.add(Braille.N.getValue());
        brailleSequence.add(Braille.O.getValue());
        brailleSequence.add(Braille.P.getValue());
        brailleSequence.add(Braille.I.getValue());
        brailleSequence.add(Braille.J.getValue());
        brailleSequence.add(Braille.K.getValue());
        brailleSequence.add(Braille.L.getValue());
        brailleSequence.add(Braille.Q.getValue());
        brailleSequence.add(Braille.R.getValue());
        brailleSequence.add(Braille.E.getValue());
        brailleSequence.add(Braille.F.getValue());
        brailleSequence.add(Braille.G.getValue());
        brailleSequence.add(Braille.H.getValue());

        List<String> braille = brailleMapper.mapFromString(word);

        assertEquals(brailleSequence, braille);
    }

    @Test
    public void mapsNumericalStringToBrailleRepresentatino() throws Exception {
        String word = "1574";
        List<String> brailleSequence = new ArrayList<>();
        brailleSequence.add(Braille.ONE.getValue());
        brailleSequence.add(Braille.FIVE.getValue());
        brailleSequence.add(Braille.SEVEN.getValue());
        brailleSequence.add(Braille.FOUR.getValue());

        List<String> braille = brailleMapper.mapFromString(word);

        assertEquals(brailleSequence, braille);
    }

    @Test
    public void mapsAllNumbersStringToBrailleRepresentatino() throws Exception {
        String word = "6701458923";
        List<String> brailleSequence = new ArrayList<>();
        brailleSequence.add(Braille.SIX.getValue());
        brailleSequence.add(Braille.SEVEN.getValue());
        brailleSequence.add(Braille.ZERO.getValue());
        brailleSequence.add(Braille.ONE.getValue());
        brailleSequence.add(Braille.FOUR.getValue());
        brailleSequence.add(Braille.FIVE.getValue());
        brailleSequence.add(Braille.EIGHT.getValue());
        brailleSequence.add(Braille.NINE.getValue());
        brailleSequence.add(Braille.TWO.getValue());
        brailleSequence.add(Braille.THREE.getValue());

        List<String> braille = brailleMapper.mapFromString(word);

        assertEquals(brailleSequence, braille);
    }

    @Test
    public void mapsSpecialCharactersStringToBrailleRepresentatino() throws Exception {
        String word = ".,?!";
        List<String> brailleSequence = new ArrayList<>();
        brailleSequence.add(Braille.PERIOD.getValue());
        brailleSequence.add(Braille.COMMA.getValue());
        brailleSequence.add(Braille.QUESTION_MARK.getValue());
        brailleSequence.add(Braille.EXCLAMATION_MARK.getValue());

        List<String> braille = brailleMapper.mapFromString(word);

        assertEquals(brailleSequence, braille);
    }

    @Test
    public void mapsAllSpecialCharactersStringToBrailleRepresentatino() throws Exception {
        String word = ".#,?:!;";
        List<String> brailleSequence = new ArrayList<>();
        brailleSequence.add(Braille.PERIOD.getValue());
        brailleSequence.add(Braille.HASHTAG.getValue());
        brailleSequence.add(Braille.COMMA.getValue());
        brailleSequence.add(Braille.QUESTION_MARK.getValue());
        brailleSequence.add(Braille.COLON.getValue());
        brailleSequence.add(Braille.EXCLAMATION_MARK.getValue());
        brailleSequence.add(Braille.SEMICOLON.getValue());

        List<String> braille = brailleMapper.mapFromString(word);

        assertEquals(brailleSequence, braille);
    }

    @Test
    public void mapsMixedCharacterStringToBrailleRepresentatino() throws Exception {
        String word = "hi?76!there,joe.";
        List<String> brailleSequence = new ArrayList<>();
        brailleSequence.add(Braille.H.getValue());
        brailleSequence.add(Braille.I.getValue());
        brailleSequence.add(Braille.QUESTION_MARK.getValue());
        brailleSequence.add(Braille.SEVEN.getValue());
        brailleSequence.add(Braille.SIX.getValue());
        brailleSequence.add(Braille.EXCLAMATION_MARK.getValue());
        brailleSequence.add(Braille.T.getValue());
        brailleSequence.add(Braille.H.getValue());
        brailleSequence.add(Braille.E.getValue());
        brailleSequence.add(Braille.R.getValue());
        brailleSequence.add(Braille.E.getValue());
        brailleSequence.add(Braille.COMMA.getValue());
        brailleSequence.add(Braille.J.getValue());
        brailleSequence.add(Braille.O.getValue());
        brailleSequence.add(Braille.E.getValue());
        brailleSequence.add(Braille.PERIOD.getValue());

        List<String> braille = brailleMapper.mapFromString(word);

        assertEquals(brailleSequence, braille);
    }

}