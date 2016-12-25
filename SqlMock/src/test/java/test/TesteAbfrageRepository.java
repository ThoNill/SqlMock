package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.RowSetMetaDataImpl;

import org.apache.log4j.Logger;
import org.junit.Test;

import tho.nill.io.AbfrageRepository;
import tho.nill.io.CsvReader;
import tho.nill.io.CsvWriter;
import tho.nill.sqlmock.AbfrageDaten;
import tho.nill.sqlmock.AbfrageErgebnis;
import tho.nill.sqlmock.AbfrageKey;
import tho.nill.sqlmock.AbfrageParameter;

public class TesteAbfrageRepository {
    private static final Logger LOG = Logger.getLogger(TesteAbfrageRepository.class);


    @Test
    public void read() {
        Reader reader = new CharArrayReader("ein|".toCharArray());
        try {
            CsvReader r = new CsvReader(reader);
            assertEquals("ein", r.readUntil(reader, '|'));
        } catch (IOException e) {
            LOG.error("Unerwartete Ausnahme {}",e);
            fail("Nicht erwartete Asnahme");
        }
    }

    @Test
    public void readLeer() {
        Reader reader = new CharArrayReader("|".toCharArray());
        try {
            CsvReader r = new CsvReader(reader);
            assertEquals("", r.readUntil(reader, '|'));
        } catch (IOException e) {
            LOG.error("Unerwartete Ausnahme {}",e);
            fail("Nicht erwartete Asnahme");
        }
    }

    @Test
    public void readMitEscape() {
        Reader reader = new CharArrayReader("ein\\||zwei|".toCharArray());
        try {
            CsvReader r = new CsvReader(reader);
            assertEquals("ein|", r.readUntil(reader, '|'));
        } catch (IOException e) {
            LOG.error("Unerwartete Ausnahme {}",e);
            fail("Nicht erwartete Asnahme");
        }
    }

    @Test
    public void abfrage() {
        AbfrageRepository repo = new AbfrageRepository();

        AbfrageKey key = erzeugeKey();

        Object[][] datenArray = { { "200" } };
        AbfrageDaten daten = new AbfrageDaten(key, datenArray);

        repo.put(daten);
        AbfrageDaten gefunden = repo.get(erzeugeAbfrageKey());

        assertNotNull(gefunden);
        assertEquals(daten.getKey(), gefunden.getKey());
    }

    @Test
    public void abfrageFalscherKey() {
        AbfrageRepository repo = new AbfrageRepository();

        AbfrageKey key = erzeugeKey();

        Object[][] datenArray = { { "200" } };
        AbfrageDaten daten = new AbfrageDaten(key, datenArray);

        repo.put(daten);
        AbfrageDaten gefunden = repo.get(erzeugeFalschenKey());

        assertNull(gefunden);
    }

    protected AbfrageKey erzeugeKey() {
        List<AbfrageParameter> parameter = new ArrayList<>();
        parameter.add(new AbfrageParameter(1, "1"));
        parameter.add(new AbfrageParameter(2, "Stuttgart"));
        AbfrageKey key = new AbfrageKey(
                "select count(*) from kunden where aktiv = ? and ort = ? ", 1,
                parameter);
        return key;
    }

    protected AbfrageKey erzeugeAbfrageKey() {
        List<AbfrageParameter> parameter = new ArrayList<>();
        parameter.add(new AbfrageParameter(2, "Stuttgart"));
        parameter.add(new AbfrageParameter(1, "1"));
        AbfrageKey key = new AbfrageKey(
                "select count(*) from kunden where aktiv = ? and ort = ? ", 1,
                parameter);
        return key;
    }

    protected AbfrageKey erzeugeFalschenKey() {
        List<AbfrageParameter> parameter = new ArrayList<>();
        parameter.add(new AbfrageParameter(1, "1"));
        AbfrageKey key = new AbfrageKey(
                "select count(*) from kunden where aktiv = ? and ort = ? ", 1,
                parameter);
        return key;
    }

    @Test
    public void readWrite() {

        Object[][] datenArray = { { "200" } };
        readWrite(datenArray, null);
    }

    @Test
    public void readWriteLeereDaten() {

        Object[][] datenArray = { {} };
        readWrite(datenArray, null);

    }

    @Test
    public void readWriteNullDaten() {

        Object[][] datenArray = null;
        readWrite(datenArray, null);

    }

    @Test
    public void readWriteMitMetaDaten() throws SQLException {

        RowSetMetaDataImpl metaData = new RowSetMetaDataImpl();
        metaData.setColumnCount(4);
        metaData.setColumnLabel(1, "Eins");
        metaData.setColumnLabel(2, "Zwei");
        metaData.setColumnLabel(3, "Drei");
        metaData.setColumnLabel(4, "Vier");

        Object[][] datenArray = null;
        readWrite(datenArray, metaData);

    }

    protected void readWrite(Object[][] datenArray, ResultSetMetaData metaData) {
        AbfrageKey key = erzeugeKey();
        AbfrageDaten daten = new AbfrageDaten(key, datenArray, metaData);

        CharArrayWriter writer = new CharArrayWriter();
        CsvWriter csvWriter = new CsvWriter(writer);
        try {
            csvWriter.write(daten);
            Reader reader = new CharArrayReader(writer.toCharArray());
            CsvReader csvReader = new CsvReader(reader);
            AbfrageDaten neueDaten = csvReader.read();
            assertEquals(daten.getKey(), neueDaten.getKey());
            AbfrageErgebnis ergebnis = daten.getErgebnis();
            AbfrageErgebnis neuesErgebnis = neueDaten.getErgebnis();
            if (datenArray != null) {
                assertEquals(ergebnis.getDaten().length,
                        neuesErgebnis.getDaten().length);
                assertArrayEquals(ergebnis.getDaten(), neuesErgebnis.getDaten());
            } else {
                assertNull(neuesErgebnis.getDaten());
            }
            if (metaData == null) {
                assertNull(neuesErgebnis.getMetaData());
            } else {
                assertEquals(metaData.getColumnCount(), neuesErgebnis
                        .getMetaData().getColumnCount());
                assertEquals(metaData.getColumnLabel(2), neuesErgebnis
                        .getMetaData().getColumnLabel(2));
            }
        } catch (Exception e) {
            LOG.error("Unerwartete Ausnahme {}",e);
            fail("unerwartete Ausnahme");
        }
    }
}
