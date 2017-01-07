package tho.nill.konvertieren;

public class ToLong implements DatenKonverter {
    @Override
    public Long konvertieren(Object obj) {
        return Long.parseLong(obj.toString());
    }
}
