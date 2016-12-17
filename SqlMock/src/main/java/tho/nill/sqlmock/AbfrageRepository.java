package tho.nill.sqlmock;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import tho.nill.io.AbfrageReader;
import tho.nill.io.AbfrageWriter;

public class AbfrageRepository {
    private HashMap<AbfrageKey,AbfrageDaten> map;
    
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
    
    public void write(AbfrageWriter writer) throws IOException {
        for(AbfrageDaten daten : map.values()) {
            writer.write(daten);
        }
    }
    
    public void read(AbfrageReader reader) throws IOException {
       while(reader.hasData()) {
           this.put(reader.read());
       }
    }

  
}
