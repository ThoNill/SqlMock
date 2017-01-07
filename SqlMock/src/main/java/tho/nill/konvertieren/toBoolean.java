package tho.nill.konvertieren;

public class toBoolean implements DatenKonverter {
    @Override
    public Boolean konvertieren(Object obj) {
        return "1".equals(obj.toString()) || "true".equals(obj.toString()) || "TRUE".equals(obj.toString());
    }
}
