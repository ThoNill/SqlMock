package tho.nill.sqlmock;

import java.io.IOException;
import java.io.Reader;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.RowSetMetaDataImpl;

import org.apache.log4j.Logger;

import tho.nill.konvertieren.ReturnValue;

public class CsvReader implements AbfrageReader {
    private static final Logger LOG = Logger.getLogger(CsvReader.class);

    private Reader reader;
    private boolean hasData;
    private char gelesenesZeichen;
    int posInLine=0;
    int line=0;

    public CsvReader(Reader reader) {
        super();
        this.reader = reader;
        this.hasData = true;
    }

 
    @Override
    public AbfrageErgebnis readAbfrageErgebnis() throws IOException {
        ResultSetMetaData metaData = readMetaData();
        prüfeKennung("DatenList");
        LOG.debug("Lese Daten ");
        Object daten[][] = readData();
        AbfrageErgebnis ergebnis = new AbfrageErgebnis(daten, metaData);
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
                    metaData.setColumnName(i, readString());
                    metaData.setColumnLabel(i, readString());
                    metaData.setColumnTypeName(i, readString());
                    metaData.setColumnType(i, readInt());
                }

                
                
            } catch (SQLException e) {
                throw new SqlMockException(
                        "Unerwartete SQL Exception bei Erzeugen eines RowSetMetaDataImpl",
                        e);
            }
        }
        return metaData;
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
        char z;
        do {
            z = einZeichenLesen();
            if (!hasData) {
                return builder.toString();
            }
            if (z == '\\') {
                z = einZeichenLesen();
                if (hasData) {
                    builder.append(z);
                } else {
                    throw new SqlMockException(
                            "Escapezeichen ohne anschließendes Zeichen im Stream");
                }
            } else if (z != stop) {
                builder.append(z);
            }

        } while (z != stop);
        LOG.debug("Gelesen: "+ builder.toString());
        return builder.toString();

    }

  
    protected char einZeichenLesen() throws IOException {
        char[] einZeichen = new char[1];
        int anz = reader.read(einZeichen);
        if (anz == -1) {
            hasData = false;
        } else {
            posInLine++;
            gelesenesZeichen = einZeichen[0];
            return gelesenesZeichen;
        }
        return 0;
    }
    
    
    @Override
    public List<ReturnValue>  readReturnValues() throws IOException {
        List<ReturnValue> returnValues = new ArrayList<>();
        while(hasData) {
            String name = readUntil(reader, '|');
            String text = readUntil(reader, '\n');
            returnValues.add(new ReturnValue(name,text));
        }
        return returnValues;
    }
 
}
