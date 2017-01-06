package tho.nill.connection.ausgabe;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
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
import tho.nill.sqlmock.SqlMockException;

public class AusgabeConnection extends AusgabeBasisConnection {
     private static AbfrageConfiguration defaultConfiguration = new AbfrageConfiguration(
            "abfragen");

     private AbfrageConfiguration configuration;
 

    public AusgabeConnection() {
        this(AusgabeConnection.defaultConfiguration);
    }

    public AusgabeConnection(AbfrageConfiguration configuration) {
        super(new AbfrageRepository(),new AbfrageUmgebung());
        this.configuration = configuration;
        prepareRepository();
    }

    protected void prepareRepository() {
        this.umgebung.prepareRepository(this.repository,this.configuration);
    }

    public static void setDefaultConfiguration(
            AbfrageConfiguration configuration) {
        AusgabeConnection.defaultConfiguration = configuration;
    }

   

}
