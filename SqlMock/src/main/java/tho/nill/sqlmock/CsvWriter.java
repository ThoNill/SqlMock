package tho.nill.sqlmock;

import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import tho.nill.konvertieren.ReturnValue;

public class CsvWriter implements AbfrageWriter {
    private static final Logger LOG = Logger.getLogger(CsvWriter.class);
    
    private Writer writer;
 
    public CsvWriter(Writer writer) {
        super();
        this.writer = writer;
    }


    @Override
    public void writeAbfrageErgebnis(AbfrageErgebnis daten) throws IOException,
            SQLException {
        writeMetaData(daten.getMetaData());
        write("DatenList");
        writeDaten(daten.getDaten());
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
                try {
                    write(metaData.getColumnName(i));
                } catch (Exception e) {
                    write("");
                    LOG.debug("Kein ColumnName im MetaModell vorhanden", e);
                }
                write(metaData.getColumnLabel(i));
                try {
                    write(metaData.getColumnTypeName(i));
                } catch (Exception e) {
                    write("");
                    LOG.debug("Kein TypeName im MetaModell vorhanden", e);
                }
                try {
                    write(metaData.getColumnType(i));
                } catch (Exception e) {
                    write("0");
                    LOG.debug("Kein ColumnType im MetaModell vorhanden", e);
                }

            }
        }
    }

    private void writeDaten(Object[][] daten) throws IOException {
        write("Daten");
        int zeilenAnzahl = (daten == null) ? 0 : daten.length;
        write(zeilenAnzahl);
        if (daten != null) {
            int spaltenAnzahl = (daten.length > 0) ? daten[0].length : 0;
            if (zeilenAnzahl > 0) {
                write(spaltenAnzahl);
                for (int zeile = 0; zeile < daten.length; zeile++) {
                    for (int spalte = 0; spalte < daten[0].length; spalte++) {
                        write(daten[zeile][spalte]);
                    }
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
 
    @Override
    public void write(List<ReturnValue> returnValues) throws IOException {
        for (ReturnValue v : returnValues) {
            writer.append(v.getName());
            writer.append('|');
            writer.append(v.getString());
            writer.append('\n');
        }
    }

}
