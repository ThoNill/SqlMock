package tho.nill.io;

import java.io.IOException;
import java.io.Reader;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.RowSetMetaDataImpl;

import tho.nill.sqlmock.AbfrageDaten;
import tho.nill.sqlmock.AbfrageKey;
import tho.nill.sqlmock.AbfrageParameter;

public class CsvReader implements AbfrageReader {
    private Reader reader;
    private boolean hasData;

    public CsvReader(Reader reader) {
        super();
        this.reader = reader;
        this.hasData = true;
    }

    @Override
    public AbfrageDaten read() throws IOException {
        AbfrageKey key = readAbfrageKey();
        ResultSetMetaData metaData = readMetaData();
        Object daten[][] = readData();
        return new AbfrageDaten(key, daten, metaData);
    }

    private ResultSetMetaData readMetaData() throws IOException {
        prüfeKennung("Meta");
        int spaltenAnzahl = readInt();
        RowSetMetaDataImpl metaData = null;
        if (spaltenAnzahl > 0) {
            metaData = new RowSetMetaDataImpl();
            try {
                metaData.setColumnCount(spaltenAnzahl);
                for (int i = 1; i <= spaltenAnzahl; i++) {
                    metaData.setColumnLabel(i, readtring());
                }

            } catch (SQLException e) {
                throw new RuntimeException(
                        "Unerwartete SQL Exception bei Erzeugen eines RowSetMetaDataImpl");
            }
        }
        return metaData;
    }

    private AbfrageKey readAbfrageKey() throws IOException {
        prüfeKennung("Abfrage");
        String statement = readtring().toString();
        int index = readInt();
        List<AbfrageParameter> parameter = readParameter();
        return new AbfrageKey(statement, index, parameter);
    }

    private List<AbfrageParameter> readParameter() throws IOException {
        List<AbfrageParameter> parameter = new ArrayList<>();
        prüfeKennung("Parameter");
        int anzahl = readInt();
        for (int i = 1; i <= anzahl; i++) {
            parameter.add(readParameter(i));
        }
        return parameter;
    }

    private AbfrageParameter readParameter(int index) throws IOException {
        Object wert = readtring();
        return new AbfrageParameter(index, wert);
    }

    private Object[][] readData() throws IOException {
        prüfeKennung("Daten");
        int zeileAnzahl = readInt();
        Object[][] daten = null;
        if (zeileAnzahl > 0) {
            int spaltenAnzahl = readInt();
            daten = new Object[zeileAnzahl][spaltenAnzahl];
            for (int zeile = 0; zeile < zeileAnzahl; zeile++) {
                for (int spalte = 0; spalte < spaltenAnzahl; spalte++) {
                    daten[zeile][spalte] = readtring();
                }
            }
        }
        return daten;
    }

    private void prüfeKennung(String erwartet) throws IOException {
        String kennung = readtring();
        if (!erwartet.equals(kennung)) {
            new IllegalArgumentException("Keine " + erwartet + " Kennnung");
        }
    }

    private int readInt() throws IOException {
        return Integer.parseInt(readtring());
    }

    private String readtring() throws IOException {
        return readUntil(reader, '|');
    }

    @Override
    public boolean hasData() {
        return hasData;
    }

    public String readUntil(Reader reader, char stop) throws IOException {
        StringBuilder builder = new StringBuilder();
        char[] einZeichen = new char[1];
        int anz = 0;
        char z;
        do {
            anz = reader.read(einZeichen);
            if (anz == -1) {
                hasData = false;
            }
            z = einZeichen[0];
            if (z == '\\') {
                anz = reader.read(einZeichen);
                if (anz == -1) {
                    hasData = false;
                }
                z = einZeichen[0];
                if (anz == 1) {
                    builder.append(z);
                } else {
                    throw new RuntimeException(
                            "Escapezeichen ohne anschlißendes Zeichen im Stream");
                }
            } else {
                if (anz == 1 && z != stop) {
                    builder.append(z);
                }
            }
        } while (anz == 1 && z != stop);

        return builder.toString();
    }

}
