package tho.nill.sqlmock;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tho.nill.db.DataResultSet;
import tho.nill.db.ResultSet2Array;

public class AbfrageErgebnis extends ResultSet2Array{

    private ResultSetMetaData metaData;
    private String funktion;
    private List<AbfrageParameter> parameter = new ArrayList<>();
    private List<Object[][]> mehrDaten = new ArrayList<>();
    private int intResult;
    private boolean booleanResult;
    private int maxSchonAbgerufen = -1;

    public AbfrageErgebnis(ResultSet result) throws SQLException {
        super();
        this.metaData = convertMetaData(result);
        this.funktion = "Unbekannt";
        parameter = new ArrayList<>();
        mehrDaten = new ArrayList<>();
        mehrDaten.add(toData(result));
    }

    public AbfrageErgebnis(Object[][] daten, ResultSetMetaData metaData) {
        this.metaData = metaData;
        this.funktion = "Unbekannt";
        parameter = new ArrayList<>();
        mehrDaten = new ArrayList<>();
        mehrDaten.add(daten);
   
    
    }

    public List<Object[][]> getDatenListe() {
        return mehrDaten;
    }

    public Object[][] getDaten() {
        maxSchonAbgerufen++;
        if (maxSchonAbgerufen >= 0 && maxSchonAbgerufen < mehrDaten.size()) {
            return mehrDaten.get(maxSchonAbgerufen);
        }
        return null;
    }

    public boolean hasMoreDaten() {
        return (maxSchonAbgerufen >= -1 && maxSchonAbgerufen < mehrDaten.size() - 1);
    }

    public void setDaten(Object[][] daten) {
        mehrDaten.add(0, daten);
    }

    public String getFunktion() {
        return funktion;
    }

    public void setFunktion(String funktion) {
        if (funktion != null) {
            this.funktion = funktion;
        }
    }

    public ResultSetMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(ResultSetMetaData metaData) {
        this.metaData = metaData;
    }

    public List<AbfrageParameter> getResultParameter() {
        return parameter;
    }

    public void setResultParameter(List<AbfrageParameter> parameter) {
        this.parameter = parameter;
    }

    public boolean addResultParameter(AbfrageParameter arg0) {
        return parameter.add(arg0);
    }

    public Object[][] getMehrDaten(int index) {
        if (index >= 0 && index < mehrDaten.size()) {
            return mehrDaten.get(index);
        }
        return null;
    }

    public List<Object[][]> getMehrDaten() {
        return mehrDaten;
    }

    public AbfrageParameter getResultParameter(int index) {
        for(AbfrageParameter p : parameter) {
            if (index == p.getIndex()) {
                return p;
            }
        }
        return parameter.get(index);
    }
    
    public AbfrageParameter getResultParameter(String name) {
        for(AbfrageParameter p : parameter) {
            if (name.equals(p.getName())) {
                return p;
            }
        }
        return null;
    }


    public void addMehrDaten(int index, Object[][] element) {
        mehrDaten.add(index, element);
    }

    public int getIntResult() {
        return intResult;
    }

    public void setIntResult(int intResult) {
        this.intResult = intResult;
    }

    public boolean getBooleanResult() {
        return booleanResult;
    }

    public void setBooleanResult(boolean booleanResult) {
        this.booleanResult = booleanResult;
    }

    @Override
    public String toString() {
        return "AbfrageErgebnis [metaData=" + metaData + ", funktion="
                + funktion + ", parameter=" + parameter + ", mehrDaten="
                + mehrDaten + ", intResult=" + intResult + ", booleanResult="
                + booleanResult + ", maxSchonAbgerufen=" + maxSchonAbgerufen
                + "]";
    }
    
   public ResultSet createResultSet() {   
    return new DataResultSet(getDaten(),getMetaData());
}

}
