package tho.nill.sqlmock;

import java.sql.ResultSetMetaData;

public class AbfrageDaten {

    private AbfrageKey key;
    private AbfrageErgebnis ergebnis;

    public AbfrageDaten(AbfrageKey key, Object[][] daten,
            ResultSetMetaData metaData) {
        this(key, new AbfrageErgebnis(daten, metaData));
    }

    public AbfrageDaten(AbfrageKey key, Object[][] daten) {
        this(key, new AbfrageErgebnis(daten, null));
    }

    public AbfrageDaten(AbfrageKey key, AbfrageErgebnis ergebnis) {
        super();
        this.key = key;
        this.ergebnis = ergebnis;

    }

    public AbfrageKey getKey() {
        return key;
    }

    public AbfrageErgebnis getErgebnis() {
        return ergebnis;
    }

    @Override
    public String toString() {
        return "AbfrageDaten [key=" + key + ", ergebnis=" + ergebnis + "]";
    }

}
