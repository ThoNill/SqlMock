package tho.nill.sqlmock.copy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AbfrageDaten {
    
    private AbfrageKey key;
    private Object[][] daten;
    
    public AbfrageDaten(AbfrageKey key, Object[][] daten) {
        super();
        this.key = key;
        this.daten = daten;
    }

    public AbfrageKey getKey() {
        return key;
    }

    public Object[][] getDaten() {
        return daten;
    }
 
}
