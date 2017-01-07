package tho.nill.sqlmock;

import java.io.IOException;
import java.util.List;

import tho.nill.konvertieren.ReturnValue;

public interface AbfrageReader {
    AbfrageErgebnis readAbfrageErgebnis() throws IOException;
    List<ReturnValue>  readReturnValues() throws IOException;

    boolean hasData();
}
