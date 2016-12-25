package tho.nill.io;

import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import tho.nill.sqlmock.AbfrageDaten;
import tho.nill.sqlmock.AbfrageErgebnis;
import tho.nill.sqlmock.AbfrageKey;
import tho.nill.sqlmock.AbfrageParameter;

public class CsvWriter implements AbfrageWriter {
    private Writer writer;
    private boolean mitNl = false;

    public CsvWriter(Writer writer) {
        super();
        this.writer = writer;
    }

    @Override
    public void write(AbfrageDaten daten) throws IOException, SQLException {
        writeAbfrageKey(daten.getKey());
        writeAbfrageErgebnis(daten.getErgebnis());
    }

    protected void writeAbfrageErgebnis(AbfrageErgebnis daten)
            throws IOException, SQLException {
        writeMetaData(daten.getMetaData());
        nl();
        write("DatenList");
        List<Object[][]> mehrDaten = daten.getDatenListe();
        write(mehrDaten.size());
        for (Object[][] d : mehrDaten) {
            writeDaten(d);
        }
        nl();
        write("Function");
        write(daten.getFunktion());
        writeParameter("ReturnParameter", daten.getResultParameter());
        nl();
        write("ReturnedInt");
        write(daten.getIntResult());
        nl();
        write("ReturnedBoolean");
        write((daten.getBooleanResult()) ? "1" : "0");
    }

    private void writeAbfrageKey(AbfrageKey key) throws IOException {
        nl();
        write("Abfrage");
        write(key.getStatement());
        write(key.getIndex());
        writeParameter("Parameter", key.getParameter());

    }

    private void writeParameter(String name, List<AbfrageParameter> parameter)
            throws IOException {
        nl();
        write(name);
        write(parameter.size());
        for (AbfrageParameter p : parameter) {
            write(p.getIndex());
            write(p.getName());
            write(p.getValue());
        }

    }

    private void writeMetaData(ResultSetMetaData metaData) throws IOException,
            SQLException {
        nl();
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
                }
                write(metaData.getColumnLabel(i));
                try {
                    write(metaData.getColumnTypeName(i));
                } catch (Exception e) {
                    write("");
                }
                try {
                write(metaData.getColumnType(i));
                } catch (Exception e) {
                    write("0");
                }

                }
        }
    }

    private void writeDaten(Object[][] daten) throws IOException {
        nl();
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

    private void nl() throws IOException {
        if (mitNl) {
            writer.append('\n');
        } else {
            mitNl = true;
        }
    }

}
