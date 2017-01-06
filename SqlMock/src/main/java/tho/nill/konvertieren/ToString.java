package tho.nill.konvertieren;

public class ToString implements DatenKonverter {
    public String konvertieren(Object obj) {
        return obj.toString();
    }
}
