package tho.nill.connection.ausgabe;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import tho.nill.connection.AbfrageConfiguration;
import tho.nill.sqlmock.AbfrageRepository;
import tho.nill.sqlmock.AbfrageUmgebung;

public class AusgabeDataSource implements DataSource {
    private static Logger LOG = Logger.getLogger(AusgabeDataSource.class.getName());
    private static AbfrageConfiguration defaultConfiguration = new AbfrageConfiguration(
            "abfragen");


    private AbfrageRepository repository;
    private AbfrageUmgebung umgebung;
    private AbfrageConfiguration configuration;
    private PrintWriter logger;  
    private int timeout;

   public AusgabeDataSource() {
       this(AusgabeDataSource.defaultConfiguration);
   }

   public AusgabeDataSource(AbfrageConfiguration configuration) {
       super();
       this.configuration = configuration;
       this.repository = new AbfrageRepository();
       this.umgebung = new AbfrageUmgebung();;
       prepareRepository();
   }

   protected void prepareRepository() {
       this.umgebung.prepareRepository(this.repository,this.configuration);
   }

   public static void setDefaultConfiguration(
           AbfrageConfiguration configuration) {
       AusgabeDataSource.defaultConfiguration = configuration;
   }

  
    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return logger;    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return timeout;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return LOG;
    }

    @Override
    public void setLogWriter(PrintWriter logger) throws SQLException {
        this.logger = logger;        
    }

    @Override
    public void setLoginTimeout(int timeout) throws SQLException {
     this.timeout = timeout; 
    }

    @Override
    public boolean isWrapperFor(Class<?> arg0) throws SQLException {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> arg0) throws SQLException {
        return null;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return new AusgabeBasisConnection(repository,umgebung);
    }

    @Override
    public Connection getConnection(String arg0, String arg1)
            throws SQLException {
        return new AusgabeBasisConnection(repository,umgebung);
    }

}
