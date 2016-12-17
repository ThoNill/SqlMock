package test;

import static org.junit.Assert.*;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tho.nill.io.CsvReader;
import tho.nill.io.CsvWriter;
import tho.nill.io.ReadWriteUtil;
import tho.nill.sqlmock.AbfrageDaten;
import tho.nill.sqlmock.AbfrageKey;
import tho.nill.sqlmock.AbfrageParameter;
import tho.nill.sqlmock.AbfrageRepository;

public class TesteAbfrageRepository {

    @Test 
    public void read() {
        Reader reader = new CharArrayReader("ein|".toCharArray());
        try {
            assertEquals("ein", ReadWriteUtil.readUntil(reader, '|'));
        } catch (IOException e) {
            e.printStackTrace();
           fail("Nicht erwartete Asnahme");
        }
    } 
    
    @Test 
    public void readMitEscape() {
        Reader reader = new CharArrayReader("ein\\||zwei|".toCharArray());
        try {
            assertEquals("ein|", ReadWriteUtil.readUntil(reader, '|'));
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
         AbfrageDaten daten = new AbfrageDaten(key, datenArray);
         
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
        readWrite(datenArray);
    } 
    
    @Test 
    public void readWriteLeereDaten()  {
        
        Object [][] datenArray = { { } };
        readWrite(datenArray);
     
    }
    
    @Test 
    public void readWriteNullDaten()  {
        
        Object [][] datenArray = null;
        readWrite(datenArray);
     
    }  

    protected void readWrite(Object[][] datenArray) {
        AbfrageKey key = erzeugeKey();
        AbfrageDaten daten = new AbfrageDaten(key, datenArray);
        
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
        } catch (IOException e) {
            e.printStackTrace();
            fail("unerwartete Ausnahme");
        }
    } 
}
