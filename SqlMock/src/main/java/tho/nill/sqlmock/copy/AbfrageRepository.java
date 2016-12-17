package tho.nill.sqlmock.copy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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

  
}
