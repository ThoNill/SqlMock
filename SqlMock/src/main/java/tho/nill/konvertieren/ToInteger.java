package tho.nill.konvertieren;

public class ToInteger implements DatenKonverter {
    public Integer konvertieren(Object obj) {
        return Integer.parseInt(obj.toString());
    }
}
