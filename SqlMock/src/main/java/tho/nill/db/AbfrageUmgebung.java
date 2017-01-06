package tho.nill.db;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import tho.nill.connection.AbfrageConfiguration;
import tho.nill.connection.sammeln.SammlerConnection;
import tho.nill.io.AbfrageRepository;
import tho.nill.io.CsvReader;
import tho.nill.io.CsvWriter;
import tho.nill.sqlmock.SqlMockException;

public class AbfrageUmgebung {
    private static final Logger LOG = Logger.getLogger(AbfrageUmgebung.class);
 
    public AbfrageUmgebung() {
        super();
    }

    private int updateCount = 0;

    public int getUpdateCount() {
        return updateCount;
    }

    public void updateOrInsert() {
        updateCount++;
    }
    
    public void prepareRepository(AbfrageRepository repository, AbfrageConfiguration configuration) {
        File datei = new File(configuration.getFileName());
        if (datei.isFile() && datei.canRead()) {
            try {
                LOG.debug("Öffne Datei: " + configuration.getFileName());
                Reader input = new FileReader(datei);
                CsvReader reader = new CsvReader(input);
                repository.read(reader);
                input.close();
            } catch (IOException e) {
                throw new SqlMockException("Ausnahme beim Lesen von Datei "
                        + datei.getAbsolutePath(), e);
            }
        }

    }
    
    public void writeRepository(AbfrageRepository repository, AbfrageConfiguration configuration) {
        File datei = new File(configuration.getFileName());
        if (!datei.exists() || configuration.isOverwrite()) {
            try {
                Writer output = new FileWriter(datei);
                CsvWriter writer = new CsvWriter(output);
                repository.write(writer);
                output.close();
            } catch (IOException e) {
                LOG.error("Unerwartete Ausnahme {}",e);
                throw new SqlMockException("Ausnahme beim Schreiben von Datei "
                        + datei.getAbsolutePath(), e);
            } catch (SQLException e) {
                LOG.error("Unerwartete Ausnahme {}",e);
                throw new SqlMockException("Ausnahme beim Schreiben von Datei "
                        + datei.getAbsolutePath(), e);
            }
        }

    }



}
