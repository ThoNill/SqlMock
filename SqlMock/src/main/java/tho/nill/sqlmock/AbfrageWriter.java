package tho.nill.sqlmock;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import tho.nill.konvertieren.ReturnValue;

public interface AbfrageWriter {
    void writeAbfrageErgebnis(AbfrageErgebnis daten) throws IOException, SQLException;
    void write(List<ReturnValue> returnValues) throws IOException;
}
