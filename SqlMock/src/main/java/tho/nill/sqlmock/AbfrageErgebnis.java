package tho.nill.sqlmock;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.RowSetMetaDataImpl;

public class AbfrageErgebnis  {

    private ResultSetMetaData metaData;
    private Object[][] daten;

    public AbfrageErgebnis(ResultSet result) throws SQLException {
        super();
        this.metaData = convertMetaData(result);
        daten = toData(result);
    }

    public AbfrageErgebnis(Object[][] daten, ResultSetMetaData metaData) {
        this.metaData = metaData;
        this.daten = daten;
    }

    public Object[][] getDaten() {
        return daten;
    }

    public void setDaten(Object[][] daten) {
        this.daten = daten;
    }

    public ResultSetMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(ResultSetMetaData metaData) {
        this.metaData = metaData;
    }

    public ResultSet createResultSet() {
        return new DataResultSet(getDaten(), getMetaData());
    }
    
    public ResultSetMetaData convertMetaData(ResultSet result)
            throws SQLException {
        RowSetMetaDataImpl metaData = new RowSetMetaDataImpl();
        if (result != null) {
            ResultSetMetaData rMetaData = result.getMetaData();
            int spaltenAnzahl = rMetaData.getColumnCount();
            metaData.setColumnCount(spaltenAnzahl);
            for (int i = 1; i <= spaltenAnzahl; i++) {
                metaData.setColumnLabel(i, rMetaData.getColumnLabel(i));
            }
        }
        return metaData;
    }

    public Object[][] toData(ResultSet result) throws SQLException {
        ArrayList<Object[]> list = new ArrayList<>();
        int spaltenAnzahl = 0;
        if (result != null) {
            ResultSetMetaData metaData = result.getMetaData();
            spaltenAnzahl = metaData.getColumnCount();
            while (result.next()) {
                Object[] zeile = new Object[spaltenAnzahl];
                for (int i = 1; i <= spaltenAnzahl; i++) {
                    zeile[i - 1] = result.getObject(i);
                }
                list.add(zeile);
            }
        }
        return toData(list, spaltenAnzahl);
    }

    private Object[][] toData(ArrayList<Object[]> list, int spaltenAnzahl) {
        int zeilenAnzahl = list.size();
        Object[][] daten = new Object[zeilenAnzahl][spaltenAnzahl];
        int z = 0;
        int s = 0;
        for (Object[] zeile : list) {
            s = 0;
            for (Object obj : zeile) {
                daten[z][s] = obj;
                s++;
            }
            z++;
        }
        return daten;
    }


}
