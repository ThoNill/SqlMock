package tho.nill.io;

import java.io.IOException;

import tho.nill.sqlmock.AbfrageDaten;

public interface AbfrageReader {
    AbfrageDaten read() throws IOException;

    boolean hasData();
}
