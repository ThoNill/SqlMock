package tho.nill.konvertieren;

public class ToString implements DatenKonverter {
    @Override
    public String konvertieren(Object obj) {
        return obj.toString();
    }
}
