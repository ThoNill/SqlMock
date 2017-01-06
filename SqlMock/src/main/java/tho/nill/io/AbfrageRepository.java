package tho.nill.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tho.nill.db.DataResultSet;
import tho.nill.sqlmock.AbfrageDaten;
import tho.nill.sqlmock.AbfrageErgebnis;
import tho.nill.sqlmock.AbfrageKey;

public class AbfrageRepository {
    private HashMap<AbfrageKey, AbfrageDaten> map;
    private List<ReturnValue> returnValues = new ArrayList<>();
    private int indexReturnValue = 0;

    public AbfrageRepository() {
        super();
        map = new HashMap<>();
    }

    public void putReturnValue(String text) {
        ReturnValue v = new ReturnValue(text);
        returnValues.add(v);
    }

    public void putReturnValue(ResultSet result) throws SQLException, IOException {
        AbfrageErgebnis erg = new AbfrageErgebnis(result);
        putReturnValue(erg);
    }

    public void putReturnValue(AbfrageErgebnis erg) throws IOException,
            SQLException {
        Writer writer = new CharArrayWriter();
        CsvWriter csvWriter = new CsvWriter(writer);
        csvWriter.writeAbfrageErgebnis(erg);
        putReturnValue(writer.toString());
    }
    
    public ResultSet returnResultSet() throws IOException {
        String ergString = returnString();
        ByteArrayInputStream byteArray = new ByteArrayInputStream(ergString.getBytes());

        CsvReader csvReader = new CsvReader(new InputStreamReader(byteArray));
        AbfrageErgebnis erg = csvReader.readAbfrageErgebnis();     
        return new DataResultSet(erg.getDaten(),erg.getMetaData());
   }

    public int returnInt() {
        ReturnValue v = returnValues.get(indexReturnValue);
        indexReturnValue++;
        return v.getInt();
    }

    public String returnString() {
        ReturnValue v = returnValues.get(indexReturnValue);
        indexReturnValue++;
        return v.getString();
    }

    public boolean returnBoolean() {
        ReturnValue v = returnValues.get(indexReturnValue);
        indexReturnValue++;
        return v.getBoolean();
    }

    public AbfrageDaten get(AbfrageKey key) {
        return map.get(key);
    }

    public AbfrageDaten put(AbfrageDaten value) {
        return map.put(value.getKey(), value);
    }

    public void write(AbfrageWriter writer) throws IOException, SQLException {
        writer.write(returnValues);
        /*
        for (AbfrageDaten daten : map.values()) {
            writer.write(daten);
        }*/
    }

    public void read(AbfrageReader reader) throws IOException {
        returnValues = reader.readReturnValues();
    /*    while (reader.hasData()) {
            AbfrageDaten daten = reader.read();
            if (daten != null) {
                this.put(daten);
            }
        }*/
    }

}
