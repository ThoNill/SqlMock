package tho.nill.sqlmock;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import tho.nill.io.AbfrageReader;
import tho.nill.io.AbfrageWriter;

public class AbfrageRepository {
    private HashMap<AbfrageKey, AbfrageDaten> map;

    public AbfrageRepository() {
        super();
        map = new HashMap<>();
    }

    public AbfrageDaten get(AbfrageKey key) {
        return map.get(key);
    }

    public AbfrageDaten put(AbfrageDaten value) {
        return map.put(value.getKey(), value);
    }

    public void write(AbfrageWriter writer) throws IOException, SQLException {
        for (AbfrageDaten daten : map.values()) {
            writer.write(daten);
        }
    }

    public void read(AbfrageReader reader) throws IOException {
        while (reader.hasData()) {
            AbfrageDaten daten = reader.read();
            if (daten != null) {
                this.put(daten);
            }
        }
    }

}
