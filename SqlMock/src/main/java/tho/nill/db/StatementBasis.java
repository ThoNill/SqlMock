package tho.nill.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tho.nill.sqlmock.AbfrageDaten;
import tho.nill.sqlmock.AbfrageKey;
import tho.nill.sqlmock.AbfrageParameter;
import tho.nill.sqlmock.AbfrageRepository;

public class StatementBasis extends ResultSet2Array {
    private AbfrageRepository repository;
    private AbfrageUmgebung umgebung;
    private List<AbfrageParameter> parameter;
    private String stmt;
    private boolean isUpdateOrInsert = false;

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
        isUpdateOrInsert = (stmt.toLowerCase().contains("update") || stmt
                .toLowerCase().contains("insert"));
    }

    private List<AbfrageParameter> getParameter() {
        if (parameter == null) {
            parameter = new ArrayList<>();
        }
        return parameter;
    }

    public void execute() {
        if (isUpdateOrInsert) {
            umgebung.updateOrInsert();
        }
    }

    public void setObject(int parameterIndex, Object value) {
        getParameter().add(new AbfrageParameter(parameterIndex, value));
    }

    public ResultSet lookupResultSet() {
        AbfrageKey key = createKey();
        AbfrageDaten daten = repository.get(key);
        if (daten == null) {
            throw new IllegalArgumentException(
                    "Die Daten zu dieser Abfrage wurde nicht gefunden: " + key);
        }
        return new DataResultSet(daten.getDaten(), daten.getMetaData());
    }

    public void saveResultSet(ResultSet result) throws SQLException {
        AbfrageDaten daten = createDaten(result);
        repository.put(daten);
    }

    private AbfrageDaten createDaten(ResultSet result) throws SQLException {
        ResultSetMetaData meta = convertMetaData(result);
        return new AbfrageDaten(createKey(), toData(result), meta);
    }

    private AbfrageKey createKey() {
        return new AbfrageKey(stmt, umgebung.getUpdateCount(), getParameter());
    }

}
