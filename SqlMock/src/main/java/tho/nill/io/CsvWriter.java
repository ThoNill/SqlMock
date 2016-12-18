package tho.nill.io;

import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
    public void write(AbfrageDaten daten) throws IOException, SQLException {
        writeAbfrageKey(daten.getKey());
        writeMetaData(daten.getMetaData());
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

    private void writeMetaData(ResultSetMetaData metaData) throws IOException,
            SQLException {
        write("Meta");
        if (metaData == null) {
            write(0);
        } else {
            int spaltenAnzahl = metaData.getColumnCount();
            write(spaltenAnzahl);
            for (int i = 1; i <= spaltenAnzahl; i++) {
                write(metaData.getColumnLabel(i));
            }
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
        write(writer, object.toString(), '|');
    }

    public static void write(Writer writer, String text, char stop)
            throws IOException {
        char[] zeichen = text.toCharArray();
        for (char z : zeichen) {
            if (z == stop) {
                writer.append('\\');
            }
            writer.append(z);

        }
        writer.append(stop);

    }

}
