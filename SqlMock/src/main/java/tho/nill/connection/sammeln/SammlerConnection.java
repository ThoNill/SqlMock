package tho.nill.connection.sammeln;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import org.apache.log4j.Logger;

import tho.nill.connection.AbfrageConfiguration;
import tho.nill.db.AbfrageUmgebung;
import tho.nill.io.AbfrageRepository;
import tho.nill.io.CsvReader;
import tho.nill.io.CsvWriter;
import tho.nill.sqlmock.SqlMockException;

public class SammlerConnection extends SammlerBasisConnection {
    private static final Logger LOG = Logger.getLogger(SammlerConnection.class);

     private static AbfrageConfiguration defaultConfiguration = new AbfrageConfiguration(
            "abfragen");

    private AbfrageConfiguration configuration;
 
    public static void setDefaultConfiguration(
            AbfrageConfiguration configuration) {
        SammlerConnection.defaultConfiguration = configuration;
    }

    public SammlerConnection(Connection con) {
        this(con, SammlerConnection.defaultConfiguration);
    }

    public SammlerConnection(Connection con, AbfrageConfiguration configuration) {
        super(con,new AbfrageRepository(),new AbfrageUmgebung());
        this.configuration = configuration;
    }

    public void close() throws SQLException  {
        super.close();
        writeRepository();
    }
 
    protected void writeRepository() {
        this.umgebung.writeRepository(this.repository, this.configuration);
    }

}
