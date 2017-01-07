package tho.nill.connection.sammeln;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import tho.nill.connection.AbfrageConfiguration;
import tho.nill.sqlmock.AbfrageRepository;
import tho.nill.sqlmock.AbfrageUmgebung;

public class SammlerDataSource implements DataSource {
    private static AbfrageConfiguration defaultConfiguration = new AbfrageConfiguration(
            "abfragen");
    private DataSource dataSOurce;

    private AbfrageRepository repository;
    private AbfrageUmgebung umgebung;
    private AbfrageConfiguration configuration;
 
    public SammlerDataSource(DataSource dataSource) {
        this(dataSource,SammlerDataSource.defaultConfiguration);
    }

    public SammlerDataSource(DataSource dataSource,AbfrageConfiguration configuration) {
        super();
        this.dataSOurce = dataSource;
        this.configuration = configuration;
        this.repository = new AbfrageRepository();
        this.umgebung = new AbfrageUmgebung();;
    }

    public void closeRepository() {
        this.umgebung.writeRepository(this.repository, this.configuration);
    }

    public static void setDefaultConfiguration(
            AbfrageConfiguration configuration) {
        SammlerDataSource.defaultConfiguration = configuration;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return dataSOurce.getLogWriter();
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return dataSOurce.getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return dataSOurce.getParentLogger();
    }

    @Override
    public void setLogWriter(PrintWriter logger) throws SQLException {
        dataSOurce.setLogWriter(logger);
    }

    @Override
    public void setLoginTimeout(int timeout) throws SQLException {
        dataSOurce.setLoginTimeout(timeout);
    }

    @Override
    public boolean isWrapperFor(Class<?> cl) throws SQLException {
        return cl.isAssignableFrom(dataSOurce.getClass());
    }

    @Override
    public <T> T unwrap(Class<T> cl) throws SQLException {
        return cl.cast(dataSOurce);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return new SammlerBasisConnection(dataSOurce.getConnection(),repository, umgebung);
    }

    @Override
    public Connection getConnection(String username, String password)
            throws SQLException {
        return new SammlerBasisConnection(dataSOurce.getConnection(username, password),repository, umgebung);
    }

}
