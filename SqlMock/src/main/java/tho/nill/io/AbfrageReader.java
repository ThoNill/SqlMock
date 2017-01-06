package tho.nill.io;

import java.io.IOException;
import java.util.List;

import tho.nill.sqlmock.AbfrageDaten;

public interface AbfrageReader {
    AbfrageDaten read() throws IOException;
    List<ReturnValue>  readReturnValues() throws IOException;

    boolean hasData();
}
