package tho.nill.io;

import java.io.IOException;

import tho.nill.sqlmock.AbfrageDaten;

public interface AbfrageWriter {
        void write(AbfrageDaten daten) throws IOException;
}
