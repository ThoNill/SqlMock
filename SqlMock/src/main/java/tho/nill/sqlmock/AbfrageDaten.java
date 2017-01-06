package tho.nill.sqlmock;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class AbfrageDaten {

    private AbfrageKey key;
    private AbfrageErgebnis ergebnis;

    public AbfrageDaten(AbfrageKey key,ResultSet result) throws SQLException {
        this(key, new AbfrageErgebnis(result));
    }

    public AbfrageDaten(AbfrageKey key, Object[][] daten) {
        this(key,daten, null);
    }

    public AbfrageDaten(AbfrageKey key, AbfrageErgebnis ergebnis) {
        super();
        this.key = key;
        this.ergebnis = ergebnis;

    }

    public AbfrageDaten(AbfrageKey key, Object[][] datenArray,
            ResultSetMetaData metaData) {
        this(key, new AbfrageErgebnis(datenArray,metaData));
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
