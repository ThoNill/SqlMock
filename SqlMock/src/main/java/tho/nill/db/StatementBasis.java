package tho.nill.db;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tho.nill.io.AbfrageRepository;
import tho.nill.konvertieren.KonverterGruppe;
import tho.nill.sqlmock.AbfrageDaten;
import tho.nill.sqlmock.AbfrageErgebnis;
import tho.nill.sqlmock.AbfrageKey;
import tho.nill.sqlmock.AbfrageParameter;

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
 //       updateOrInsert(stmt);
 //       lookupErgebnis(stmt);
    }

    public void updateOrInsert(String stmt) {
  //      boolean isUpdateOrInsert = !stmt.toLowerCase().contains("select");
  //      if (isUpdateOrInsert) {
  //          StackTraceElement stack[] = Thread.currentThread().getStackTrace();
  //          System.out.println("UI " + this.getClass().getSimpleName() + " " + stack[2].getMethodName());
  //          umgebung.updateOrInsert();
  //      }

    }

    public void setParameter(int parameterIndex, Object value) {
   //     eventuellNeueParameterErzeugen();
   //     getParameter().add(new AbfrageParameter(parameterIndex, value));
    }

    public void setParameter(String parameterName, Object value) {
    //    eventuellNeueParameterErzeugen();
    //    getParameter().add(new AbfrageParameter(parameterName, value));
    }

    protected void eventuellNeueParameterErzeugen() {
        if (neueParameterErzeugen) {
            parameter = new ArrayList<>();
            neueParameterErzeugen = false;
        }
    }

    public ResultSet lookupResultSet(String stmt) {
       // lookupErgebnis(stmt);
      //  return new DataResultSet(aktuellesErgebnis.getDaten(),             aktuellesErgebnis.getMetaData());
        return lookupResultSet();
    }

    protected void lookupErgebnis(String stmt) {
   //     AbfrageKey key = createKey(stmt);
   //     AbfrageDaten daten = repository.get(key);
   //     if (daten == null) {
   //         throw new IllegalArgumentException(
   //                 "Die Daten zu dieser Abfrage wurde nicht gefunden: " + key);
   //     }
   //     aktuellesErgebnis = daten.getErgebnis();
    }

    public ResultSet lookupResultSet() {
        try {
            return repository.returnResultSet();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(
                    "Das ResultSet konnte nicht erzeugt werden ");
            }
    }

    public ResultSet saveResultSet(ResultSet result, String stmt)
            throws SQLException {
        try {
        AbfrageDaten daten = createDaten(result, stmt);
            repository.putReturnValue(daten.getErgebnis());

            return daten.getErgebnis().createResultSet();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
     //   neueParameterErzeugen = false;
    }

    public ResultSet saveResultSet(ResultSet result) throws SQLException {
        return this.saveResultSet(result, this.stmt);
    }

    private AbfrageDaten createDaten(ResultSet result, String stmt)
            throws SQLException {
        AbfrageErgebnis ergebnis = new AbfrageErgebnis(result);
        ergebnis.setFunktion(funktion);
        ergebnis.setIntResult(intResult);
        ergebnis.setBooleanResult(booleanResult);
        ergebnis.setResultParameter(resultParameter);
        return new AbfrageDaten(createKey(stmt), ergebnis);
    }

    public <T> T addResultParameter(int parameterIndex, Object value,
            Class<T> iface) {
        repository.putReturnValue(value.toString());
        return iface.cast(value);
    }

    public <T> T addResultParameter(String name, Object value, Class<T> iface) {
        repository.putReturnValue(value.toString());
        return iface.cast(value);
    }

    public int setIntResult(String function, int result) {
        repository.putReturnValue(Integer.toString(result));
        return result;
    }

    public boolean setBooleanResult(String function, boolean result) {
        repository.putReturnValue((result) ? "1" : "0");
        return result;
   //     this.booleanResult = result;
   //     this.funktion = function;
   //     return this.booleanResult;
    }

    private AbfrageKey createKey(String stmt) {
        return new AbfrageKey(stmt, umgebung.getUpdateCount(), getParameter());
    }

    public String getStmtString() {
        return stmt;
    }
/*
    public AbfrageParameter getResultParameter(int index) {
        return aktuellesErgebnis.getResultParameter(index);
    }
    public AbfrageParameter getResultParameter(String name) {
        return aktuellesErgebnis.getResultParameter(name);
    }
  */  
    public <T> T getResultParameterValue(int index,Class<T> type) {
        String erg = repository.returnString();
        if (erg != null) {
            return KonverterGruppe.konvertieren(type, erg);
        }
        return null;
    }
    


    public <T> T getResultParameterValue(String name,Class<T> type) {
        String erg = repository.returnString();
        if (erg != null) {
            return KonverterGruppe.konvertieren(type, erg);
        }
        return null;
    }
  
    
    public int getIntResult() {
        return repository.returnInt();
    }

    public boolean getBooleanResult() {
        return repository.returnBoolean();
    }

    public boolean hasMoreDaten() {
        return repository.returnBoolean();
    }

}
