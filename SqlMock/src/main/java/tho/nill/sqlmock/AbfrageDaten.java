package tho.nill.sqlmock;

import java.sql.ResultSetMetaData;

public class AbfrageDaten {

    private AbfrageKey key;
    private Object[][] daten;
    private ResultSetMetaData metaData;

    public AbfrageDaten(AbfrageKey key, Object[][] daten) {
        this(key, daten, null);
    }

    public AbfrageDaten(AbfrageKey key, Object[][] daten,
            ResultSetMetaData metaData) {
        super();
        this.key = key;
        this.daten = daten;
        this.metaData = metaData;
    }

    public AbfrageKey getKey() {
        return key;
    }

    public Object[][] getDaten() {
        return daten;
    }

    public ResultSetMetaData getMetaData() {
        return metaData;
    }

}
