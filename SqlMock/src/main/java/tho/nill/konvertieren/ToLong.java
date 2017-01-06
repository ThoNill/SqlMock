package tho.nill.konvertieren;

public class ToLong implements DatenKonverter {
    public Long konvertieren(Object obj) {
        return Long.parseLong(obj.toString());
    }
}
