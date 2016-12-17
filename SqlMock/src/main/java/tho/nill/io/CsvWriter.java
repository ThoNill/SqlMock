package tho.nill.io;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import tho.nill.sqlmock.AbfrageDaten;
import tho.nill.sqlmock.AbfrageKey;
import tho.nill.sqlmock.AbfrageParameter;

public class CsvWriter implements AbfrageWriter {
    private Writer writer;

    public CsvWriter(Writer writer) {
        super();
        this.writer = writer;
    }

    @Override
    public void write(AbfrageDaten daten) throws IOException {
        writeAbfrageKey(daten.getKey());
        writeDaten(daten.getDaten());
    }

    private void writeAbfrageKey(AbfrageKey key) throws IOException {
        write("Abfrage");
        write(key.getStatement());
        write(key.getIndex());
        writeParameter(key.getParameter());
        
    }
    
    private void writeParameter(List<AbfrageParameter> parameter)
            throws IOException {
        write("Parameter");
        write(parameter.size());
        for (AbfrageParameter p : parameter) {
            write(p.getValue());
        }
        
    }

    private void writeDaten(Object[][] daten) throws IOException {
        write("Daten");
        write((daten == null) ? 0 : daten.length);
        if (daten != null) {
            write((daten.length > 0) ? daten[0].length : 0);
            for (int zeile = 0; zeile < daten.length; zeile++) {
                for (int spalte = 0; spalte < daten[0].length; spalte++) {
                    write(daten[zeile][spalte]);
                }
            }
        }

    }

    private void write(Object object) throws IOException {
        ReadWriteUtil.write(writer, object.toString(), '|');
    }


}
