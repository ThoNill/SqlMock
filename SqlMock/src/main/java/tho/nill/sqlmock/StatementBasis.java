package tho.nill.sqlmock;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import tho.nill.konvertieren.KonverterGruppe;

public class StatementBasis  {
    private AbfrageRepository repository;

    public StatementBasis(AbfrageRepository repository) {
        super();
        if (repository == null) {
            throw new IllegalArgumentException(
                    "Parameter repository must not be null");
        }
        this.repository = repository;

    }

 
    public ResultSet lookupResultSet(String stmt) {
        return lookupResultSet();
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

    public ResultSet saveResultSet(ResultSet result) throws SQLException {
        try {
            AbfrageErgebnis ergebnis = new AbfrageErgebnis(result);
            repository.putReturnValue(ergebnis);

            return ergebnis.createResultSet();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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

    public int setIntResult( int result) {
        repository.putReturnValue(Integer.toString(result));
        return result;
    }

    public boolean setBooleanResult(boolean result) {
        repository.putReturnValue((result) ? "1" : "0");
        return result;
    }

    public <T> T getResultParameterValue(int index, Class<T> type) {
        String erg = repository.returnString();
        if (erg != null) {
            return KonverterGruppe.konvertieren(type, erg);
        }
        return null;
    }

    public <T> T getResultParameterValue(String name, Class<T> type) {
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
