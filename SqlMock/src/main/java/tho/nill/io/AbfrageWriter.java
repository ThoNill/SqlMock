package tho.nill.io;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import tho.nill.sqlmock.AbfrageDaten;

public interface AbfrageWriter {
    void write(AbfrageDaten daten) throws IOException, SQLException;
    void write(List<ReturnValue> returnValues) throws IOException;
}
