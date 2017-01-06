package tho.nill.konvertieren;

import java.util.HashMap;

public class KonverterGruppe {
    private final static HashMap<Class<?>, DatenKonverter> gruppe;

    static {
        gruppe = new HashMap<>();
        gruppe.put(Long.class, new ToLong());
        gruppe.put(Integer.class, new ToInteger());
        gruppe.put(String.class, new ToString());
        gruppe.put(Boolean.class, new toBoolean());
        
    }

    public static <T> T konvertieren(Class<T> cl, Object obj) {
        if (obj != null) {
            DatenKonverter konverter = gruppe.get(cl);
            if (konverter == null) {
                return cl.cast(obj);
            }
            return konverter.konvertieren(obj);
        } else {
            return null;
        }
    }
}
