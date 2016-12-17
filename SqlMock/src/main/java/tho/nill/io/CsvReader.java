package tho.nill.io;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import tho.nill.sqlmock.AbfrageDaten;
import tho.nill.sqlmock.AbfrageKey;
import tho.nill.sqlmock.AbfrageParameter;

public class CsvReader implements AbfrageReader {
    private Reader reader;

    public CsvReader(Reader reader) {
        super();
        this.reader = reader;
    }

    @Override
    public AbfrageDaten read() throws IOException {
        AbfrageKey key = readAbfrageKey();
        Object daten[][] = readData();
        return new AbfrageDaten(key, daten);
    }

    private AbfrageKey readAbfrageKey() throws IOException {
        prüfeKennung("Abfrage");
        String statement = readObject().toString();
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
        Object wert = readObject();
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
                    daten[zeile][spalte] = readObject();
                }
            }
        }
        return daten;
    }

    private void prüfeKennung(String erwartet) throws IOException {
        String kennung = readObject();
        if (!erwartet.equals(kennung)) {
            new IllegalArgumentException("Keine " + erwartet + " Kennnung");
        }
    }

    private int readInt() throws IOException {
        return Integer.parseInt(readObject());
    }

    private String readObject() throws IOException {
        return ReadWriteUtil.readUntil(reader, '|');
    }


}
