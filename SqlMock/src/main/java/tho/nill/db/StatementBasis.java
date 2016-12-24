package tho.nill.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tho.nill.sqlmock.AbfrageDaten;
import tho.nill.sqlmock.AbfrageErgebnis;
import tho.nill.sqlmock.AbfrageKey;
import tho.nill.sqlmock.AbfrageParameter;
import tho.nill.sqlmock.AbfrageRepository;

public class StatementBasis extends ResultSet2Array {
    private AbfrageRepository repository;
    private AbfrageUmgebung umgebung;
    private List<AbfrageParameter> parameter;
    private String stmt;
    private boolean neueParameterErzeugen = false;
    
    private String funktion;
    private List<AbfrageParameter> resultParameter = new ArrayList<>();
    private List<Object[][]> mehrDaten = new ArrayList<>();
    private int intResult;
    private boolean booleanResult;
    
    private AbfrageErgebnis aktuellesErgebnis;

    public StatementBasis(AbfrageRepository repository,
            AbfrageUmgebung umgebung, String stmt) {
        super();
        if (repository == null) {
            throw new IllegalArgumentException(
                    "Parameter repository must not be null");
        }
        if (umgebung == null) {
            throw new IllegalArgumentException(
                    "Parameter umgebung must not be null");
        }
        if (stmt == null) {
            throw new IllegalArgumentException(
                    "Parameter stmt must not be null");
        }
        this.repository = repository;
        this.umgebung = umgebung;
        this.stmt = stmt;
    }

    private List<AbfrageParameter> getParameter() {
        if (parameter == null) {
            parameter = new ArrayList<>();
        }
        return parameter;
    }

    public void updateOrInsertWithLookup(String stmt) {
        boolean isUpdateOrInsert = (stmt.toLowerCase().contains("update") || stmt
                .toLowerCase().contains("insert"));
        if (isUpdateOrInsert) {
            umgebung.updateOrInsert();
        }
        lookupErgebnis(stmt);
    }

    public void updateOrInsert(String stmt) {
        boolean isUpdateOrInsert = !stmt.toLowerCase().contains("select") ;
        if (isUpdateOrInsert) {
            umgebung.updateOrInsert();
        }
        
    }

    
    public void setParameter(int parameterIndex, Object value) {
        eventuellNeueParameterErzeugen();
        getParameter().add(new AbfrageParameter(parameterIndex, value));
    }

    public void setParameter(String parameterName, Object value) {
        eventuellNeueParameterErzeugen();
        getParameter().add(new AbfrageParameter(parameterName, value));
    }

    protected void eventuellNeueParameterErzeugen() {
        if (neueParameterErzeugen) {
            parameter = new ArrayList<>();
            neueParameterErzeugen = false;
        }
    }

    public ResultSet lookupResultSet(String stmt) {
        lookupErgebnis(stmt);
        return new DataResultSet(aktuellesErgebnis.getDaten(), aktuellesErgebnis.getMetaData());
    }

    protected void lookupErgebnis(String stmt) {
        AbfrageKey key = createKey(stmt);
        AbfrageDaten daten = repository.get(key);
        if (daten == null) {
            throw new IllegalArgumentException(
                    "Die Daten zu dieser Abfrage wurde nicht gefunden: " + key);
        }
        aktuellesErgebnis = daten.getErgebnis();
    }

    public ResultSet lookupResultSet() {
        return lookupResultSet(this.stmt);
    }

    public void saveResultSet(ResultSet result, String stmt)
            throws SQLException {
        AbfrageDaten daten = createDaten(result, stmt);
        repository.put(daten);
        neueParameterErzeugen = false;
    }

    public void saveResultSet(ResultSet result) throws SQLException {
        this.saveResultSet(result,this.stmt);
    }

    private AbfrageDaten createDaten(ResultSet result, String stmt)
            throws SQLException {
        ResultSetMetaData meta = convertMetaData(result);
        AbfrageErgebnis ergebnis = new AbfrageErgebnis(toData(result), meta);
        ergebnis.setFunktion(funktion);
        ergebnis.setIntResult(intResult);
        ergebnis.setBooleanResult(booleanResult);
        ergebnis.setResultParameter(resultParameter);
        return new AbfrageDaten(createKey(stmt), ergebnis);
    }
    
    
    public <T> T addResultParameter(int parameterIndex,Object value,Class<T> iface) {
        resultParameter.add(new AbfrageParameter(parameterIndex, value));
        return iface.cast(value);
    }
    
    public <T> T addResultParameter(String name,Object value,Class<T> iface) {
        resultParameter.add(new AbfrageParameter(name, value));
        return iface.cast(value);
    }
    
    
    
    public int setIntResult(String function,int result) {
        this.intResult = result;
        this.funktion = function;
        return this.intResult;
    }
    
    public boolean setBooleanResult(String function,boolean result) {
        this.booleanResult = result;
        this.funktion = function;
        return this.booleanResult;
    }

    private AbfrageKey createKey(String stmt) {
        return new AbfrageKey(stmt, umgebung.getUpdateCount(), getParameter());
    }

    public String getStmtString() {
        return stmt;
    }

    public AbfrageParameter getResultParameter(int arg0) {
        return aktuellesErgebnis.getResultParameter(arg0);
    }

    public int getIntResult() {
        return aktuellesErgebnis.getIntResult();
    }

    public boolean getBooleanResult() {
        return aktuellesErgebnis.getBooleanResult();
    }
    public boolean hasMoreDaten() {
        return aktuellesErgebnis.hasMoreDaten();
    }

}
