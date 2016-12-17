package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tho.nill.sqlmock.AbfrageDaten;
import tho.nill.sqlmock.AbfrageKey;
import tho.nill.sqlmock.AbfrageParameter;
import tho.nill.sqlmock.AbfrageRepository;

public class TesteAbfrageRepository {

    
    
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
}
