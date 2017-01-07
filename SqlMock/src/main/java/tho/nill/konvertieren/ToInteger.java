package tho.nill.konvertieren;

public class ToInteger implements DatenKonverter {
    @Override
    public Integer konvertieren(Object obj) {
        return Integer.parseInt(obj.toString());
    }
}
