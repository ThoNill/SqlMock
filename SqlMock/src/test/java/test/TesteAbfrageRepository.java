package test;

import static org.junit.Assert.*;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.RowSetMetaDataImpl;

import org.junit.Test;

import tho.nill.io.CsvReader;
import tho.nill.io.CsvWriter;
import tho.nill.sqlmock.AbfrageDaten;
import tho.nill.sqlmock.AbfrageKey;
import tho.nill.sqlmock.AbfrageParameter;
import tho.nill.sqlmock.AbfrageRepository;

public class TesteAbfrageRepository {

    @Test 
    public void read() {
        Reader reader = new CharArrayReader("ein|".toCharArray());
        try {
            CsvReader r = new CsvReader(reader);
            assertEquals("ein", r.readUntil(reader, '|'));
        } catch (IOException e) {
            e.printStackTrace();
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
            e.printStackTrace();
           fail("Nicht erwartete Asnahme");
        }
    } 
    
     @Test 
     public void abfrage() {
         AbfrageRepository repo = new AbfrageRepository();
         
         AbfrageKey key = erzeugeKey();
         
         Object [][] datenArray = { { "200"} };
         AbfrageDaten daten = new AbfrageDaten(key,datenArray);
         
         repo.put(daten);
         AbfrageDaten gefunden = repo.get(erzeugeAbfrageKey());
         
         assertNotNull(gefunden);
         assertEquals(daten.getKey(),gefunden.getKey());
     }
     
     @Test 
     public void abfrageFalscherKey() {
         AbfrageRepository repo = new AbfrageRepository();
         
         AbfrageKey key = erzeugeKey();
         
         Object [][] datenArray = { { "200"} };
         AbfrageDaten daten = new AbfrageDaten(key, datenArray);
         
         repo.put(daten);
         AbfrageDaten gefunden = repo.get(erzeugeFalschenKey());
         
         assertNull(gefunden);
     }

    protected AbfrageKey erzeugeKey() {
         List<AbfrageParameter> parameter = new ArrayList<>();
         parameter.add(new AbfrageParameter(1,"1"));
         parameter.add(new AbfrageParameter(2,"Stuttgart"));
         AbfrageKey key = new AbfrageKey("select count(*) from kunden where aktiv = ? and ort = ? ",1,parameter);
        return key;
    }
    
    protected AbfrageKey erzeugeAbfrageKey() {
        List<AbfrageParameter> parameter = new ArrayList<>();
        parameter.add(new AbfrageParameter(2,"Stuttgart"));
        parameter.add(new AbfrageParameter(1,"1"));
        AbfrageKey key = new AbfrageKey("select count(*) from kunden where aktiv = ? and ort = ? ",1,parameter);
       return key;
   }
    
    protected AbfrageKey erzeugeFalschenKey() {
        List<AbfrageParameter> parameter = new ArrayList<>();
        parameter.add(new AbfrageParameter(1,"1"));
        AbfrageKey key = new AbfrageKey("select count(*) from kunden where aktiv = ? and ort = ? ",1,parameter);
       return key;
   }
    
    @Test 
    public void readWrite()  {
        
        Object [][] datenArray = { { "200"} };
        readWrite(datenArray,null);
    } 
    
    @Test 
    public void readWriteLeereDaten()  {
        
        Object [][] datenArray = { { } };
        readWrite(datenArray,null);
     
    }
    
    @Test 
    public void readWriteNullDaten()  {
        
        Object [][] datenArray = null;
        readWrite(datenArray,null);
     
    }  
    
    @Test 
    public void readWriteMitMetaDaten() throws SQLException  {
        
        
        RowSetMetaDataImpl metaData = new RowSetMetaDataImpl();
        metaData.setColumnCount(4);
        metaData.setColumnLabel(1,"Eins");
        metaData.setColumnLabel(2,"Zwei");
        metaData.setColumnLabel(3,"Drei");
        metaData.setColumnLabel(4,"Vier");
        
        Object [][] datenArray = null;
        readWrite(datenArray,metaData);
     
    }  

    protected void readWrite(Object[][] datenArray,ResultSetMetaData metaData) {
        AbfrageKey key = erzeugeKey();
        AbfrageDaten daten = new AbfrageDaten(key, datenArray,metaData );
        
        CharArrayWriter writer = new CharArrayWriter();
        CsvWriter csvWriter = new CsvWriter(writer);
        try {
            csvWriter.write(daten);
            Reader reader = new CharArrayReader(writer.toCharArray());
            CsvReader csvReader = new CsvReader(reader);
            AbfrageDaten neuGelesen = csvReader.read();
            assertEquals(daten.getKey(),neuGelesen.getKey());
            if (datenArray != null) {
                assertEquals(daten.getDaten().length,neuGelesen.getDaten().length);
                assertArrayEquals(daten.getDaten(),neuGelesen.getDaten());
            } else {
                assertNull(neuGelesen.getDaten());
            }
            if (metaData == null) {
                assertNull(neuGelesen.getMetaData());
            } else {
                assertEquals(metaData.getColumnCount(),neuGelesen.getMetaData().getColumnCount());
                assertEquals(metaData.getColumnLabel(2),neuGelesen.getMetaData().getColumnLabel(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("unerwartete Ausnahme");
        }
    } 
}
