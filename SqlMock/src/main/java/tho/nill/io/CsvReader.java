package tho.nill.io;

import java.io.IOException;
import java.io.Reader;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.RowSetMetaDataImpl;

import org.apache.log4j.Logger;

import tho.nill.sqlmock.AbfrageDaten;
import tho.nill.sqlmock.AbfrageErgebnis;
import tho.nill.sqlmock.AbfrageKey;
import tho.nill.sqlmock.AbfrageParameter;
import tho.nill.sqlmock.SqlMockException;

public class CsvReader implements AbfrageReader {
    private static final Logger LOG = Logger.getLogger(CsvReader.class);

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
        if (key != null) {
            AbfrageErgebnis ergebnis = readAbfrageErgebnis();
            return new AbfrageDaten(key, ergebnis);
        } else {
            return null;
        }
    }

    protected AbfrageErgebnis readAbfrageErgebnis() throws IOException {
        ResultSetMetaData metaData = readMetaData();
        prüfeKennung("DatenList");
        int datenAnzahl = readInt();
        Object daten[][] = readData();
        AbfrageErgebnis ergebnis = new AbfrageErgebnis(daten, metaData);

        for (int i = 1; i < datenAnzahl; i++) {
            daten = readData();
            ergebnis.addMehrDaten(i, daten);
        }
        prüfeKennung("Function");
        ergebnis.setFunktion(readString());
        List<AbfrageParameter> parameter = readParameterList("ReturnParameter");
        ergebnis.setResultParameter(parameter);
        prüfeKennung("ReturnedInt");
        ergebnis.setIntResult(readInt());
        prüfeKennung("ReturnedBoolean");
        ergebnis.setBooleanResult(readInt() == 1);
        return ergebnis;
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
                    metaData.setColumnLabel(i, readString());
                }

            } catch (SQLException e) {
                throw new SqlMockException(
                        "Unerwartete SQL Exception bei Erzeugen eines RowSetMetaDataImpl",e);
            }
        }
        return metaData;
    }

    private AbfrageKey readAbfrageKey() throws IOException {
        if (prüfeKennung("Abfrage")) {
            String statement = readString().toString();
            int index = readInt();
            List<AbfrageParameter> parameter = readParameterList("Parameter");
            return new AbfrageKey(statement, index, parameter);
        } else {
            return null;
        }
    }

    private List<AbfrageParameter> readParameterList(String name)
            throws IOException {
        List<AbfrageParameter> parameter = new ArrayList<>();
        prüfeKennung(name);
        int anzahl = readInt();
        for (int i = 1; i <= anzahl; i++) {
            parameter.add(readParameter());
        }
        return parameter;
    }

    private AbfrageParameter readParameter() throws IOException {
        int i = readInt();
        String name = readString();
        Object value = readString();
        return new AbfrageParameter(i, name, value);
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
                    daten[zeile][spalte] = readString();
                }
            }
        }
        return daten;
    }

    private boolean prüfeKennung(String erwartet) throws IOException {
        String kennung = readString();
        if (hasData() && !erwartet.equals(kennung)) {
            throw new IllegalArgumentException("Keine " + erwartet
                    + " Kennnung");
        }
        return hasData();
    }

    private int readInt() throws IOException {
        return Integer.parseInt(readString());
    }

    private String readString() throws IOException {
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
                return builder.toString();
            }
            z = einZeichen[0];
            if (z == '\\') {
                anz = reader.read(einZeichen);
                if (anz == -1) {
                    hasData = false;
                    return builder.toString();
                }
                z = einZeichen[0];
                if (anz == 1) {
                    builder.append(z);
                } else {
                    throw new SqlMockException(
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
