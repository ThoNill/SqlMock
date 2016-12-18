package tho.nill.db;

public class AbfrageUmgebung {
 
    public AbfrageUmgebung() {
        super();
    }

    private int updateCount=0;

    public int getUpdateCount() {
        return updateCount;
    }
    
    public void updateOrInsert() {
        updateCount++;
    }
    
}
