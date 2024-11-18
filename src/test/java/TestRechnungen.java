import Backend.Rechnungen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestRechnungen {

    /* Test Umrechnung Wechselkurs anhand vom Eingabe- und Ausgabekurs mit den Beispielen:
    - Euro nach USD (einfache Umrechnung)
    - Yen nach Yen (gleiche Währung bedeutet 1)
    - Bath nach Yen (doppelte Umrechnung)
    - ungültige Angabe "hallo" im Eingabe- und Ausgabekurs
    Achtung: funktioniert nur bei hardcodierten Wechselkursen. Ansonsten müsste dieser hier erst ermittelt werden.
     */
    static Stream<Arguments> wechselkursumrechnung(){
        return Stream.of(
                Arguments.of("Euro", "USD", 1.05F),
                Arguments.of("Yen", "Yen", 1.00F),
                Arguments.of("Bath", "Yen", 4.43515F),
                Arguments.of("hallo", "Euro", 0.0F),
                Arguments.of("Euro", "hallo", 0.0F)
        );
    }

    @ParameterizedTest
    @MethodSource("wechselkursumrechnung")
    public void testErmittelnWechselkurs(String eingabe, String ausgabe, Float ergebnis){
        Float EingabeToAusgabe = Rechnungen.ermittelnWechselkurs(eingabe, ausgabe);
        assertEquals(ergebnis,EingabeToAusgabe);
    }


    /* Test Umrechnung mit Wechselkurs mit folgenden Beispielen:
    - Bei Multiplikation mit 0 ist das Ergebnis 0
    - Multiplikation mit Minus (wird von der GUI aber verhindert)
    - Gültige Multiplikationen
    - Infinity (hier null)
    - sehr großes Ergebnis
    In Float kann nicht mehr als 1 Punkt angegeben werden. Der Test mit mehr als einem Punkt wird nicht getestet.

     */
    static Stream<Arguments> Multiplikation(){
        return Stream.of(
                Arguments.of(000F, 1F, 0F),
                Arguments.of(1F, 000F, 0F),
                Arguments.of(-1F, 1F, -1F),
                Arguments.of(2F, 1.05F, 2.1F),
                Arguments.of(1F, 1F, 1F),
                Arguments.of(2F, 4.43515F, 8.8703F),
                Arguments.of(20000000000000000000F, 20000000000000000000F, null),
                Arguments.of(2000000000000000000F, 2000000000000000000F, 4.0E36F)
        );
    }

    @ParameterizedTest
    @MethodSource("Multiplikation")
    public void testErmittelnAusgabe(Float eingabewaehrung, Float wechselkurs, Float ergebnis){
        Float Ergebnis = null;
        try {
            Ergebnis = Rechnungen.ermittelnAusgabe(eingabewaehrung, wechselkurs);
        } catch (Exception e) {
            Assertions.assertThrows(Exception.class, () -> Rechnungen.ermittelnAusgabe(20000000000000000000F, 20000000000000000000F));
            //throw new RuntimeException(e);
        }
        assertEquals(ergebnis,Ergebnis);
    }
}
