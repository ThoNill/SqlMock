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
import tho.nill.io.CsvReader;
import tho.nill.io.CsvWriter;
import tho.nill.sqlmock.AbfrageRepository;
import tho.nill.sqlmock.SqlMockException;

public class SammlerConnection implements Connection {
    private static final Logger LOG = Logger.getLogger(SammlerConnection.class);

    
    private static AbfrageConfiguration defaultConfiguration = new AbfrageConfiguration(
            "abfragen");

    private AbfrageConfiguration configuration;
    private AbfrageRepository repository;
    private AbfrageUmgebung umgebung;

    private Connection con;

    public static void setDefaultConfiguration(
            AbfrageConfiguration configuration) {
        SammlerConnection.defaultConfiguration = configuration;
    }

    public SammlerConnection(Connection con) {
        this(con, SammlerConnection.defaultConfiguration);
    }

    public SammlerConnection(Connection con, AbfrageConfiguration configuration) {
        super();
        this.con = con;
        this.configuration = configuration;
        this.repository = new AbfrageRepository();
        this.umgebung = new AbfrageUmgebung();
     //   prepareRepository();
    }

    private void prepareRepository() {
        File datei = new File(configuration.getFileName());
        if (datei.isFile() && datei.canRead()) {
            try {
                Reader input = new FileReader(datei);
                CsvReader reader = new CsvReader(input);
                repository.read(reader);
                input.close();
            } catch (IOException e) {
                LOG.error("Unerwartete Ausnahme {}",e);
                throw new SqlMockException("Ausnahme beim Lesen von Datei "
                        + datei.getAbsolutePath(), e);
            }
        }

    }

    private void writeRepository() {
        File datei = new File(configuration.getFileName());
        if (!datei.exists()) {
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

    @Override
    public void abort(Executor executor) throws SQLException {
        con.abort(executor);
    }

    @Override
    public void clearWarnings() throws SQLException {
        con.clearWarnings();
    }

    @Override
    public void close() throws SQLException {
        con.close();
        writeRepository();
    }

    @Override
    public void commit() throws SQLException {
        con.commit();
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements)
            throws SQLException {
        return con.createArrayOf(typeName, elements);
    }

    @Override
    public Blob createBlob() throws SQLException {
        return con.createBlob();
    }

    @Override
    public Clob createClob() throws SQLException {
        return con.createClob();
    }

    @Override
    public NClob createNClob() throws SQLException {
        return con.createNClob();
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return con.createSQLXML();
    }

    
    @Override
    public Statement createStatement() throws SQLException {
        Statement stmt =  con.createStatement();
        return new SammlerStatement(stmt, repository, umgebung,"");
    }

    @Override
    public Statement createStatement(int resultSetType,
            int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        Statement stmt = con.createStatement(resultSetType, resultSetConcurrency,
                resultSetHoldability);
        return new SammlerStatement(stmt, repository, umgebung,"");
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency)
            throws SQLException {
        Statement stmt =con.createStatement(resultSetType, resultSetConcurrency);
        return new SammlerStatement(stmt, repository, umgebung,"");
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes)
            throws SQLException {
        return con.createStruct(typeName, attributes);
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return con.getAutoCommit();
    }

    @Override
    public String getCatalog() throws SQLException {
        return con.getCatalog();
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return con.getClientInfo();
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        return con.getClientInfo(name);
    }

    @Override
    public int getHoldability() throws SQLException {
        return con.getHoldability();
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return con.getMetaData();
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return con.getNetworkTimeout();
    }

    @Override
    public String getSchema() throws SQLException {
        return con.getSchema();
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return con.getTransactionIsolation();
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return con.getTypeMap();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return con.getWarnings();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return con.isClosed();
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return con.isReadOnly();
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        return con.isValid(timeout);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return con.isWrapperFor(iface);
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        return con.nativeSQL(sql);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType,
            int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        CallableStatement stmt = con.prepareCall(sql, resultSetType, resultSetConcurrency,
                resultSetHoldability);
        return new SammlerCallableStatement(stmt, repository, umgebung,"");
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType,
            int resultSetConcurrency) throws SQLException {
        CallableStatement stmt = con.prepareCall(sql, resultSetType, resultSetConcurrency);
        return new SammlerCallableStatement(stmt, repository, umgebung,"");

    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        CallableStatement stmt = con.prepareCall(sql);
        return new SammlerCallableStatement(stmt, repository, umgebung,sql);

    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType,
            int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        PreparedStatement stmt = con.prepareStatement(sql, resultSetType, resultSetConcurrency,
                resultSetHoldability);
        return new SammlerPreparedStatement(stmt, repository, umgebung,"");

    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType,
            int resultSetConcurrency) throws SQLException {
        PreparedStatement stmt = con.prepareStatement(sql, resultSetType, resultSetConcurrency);
        return new SammlerPreparedStatement(stmt, repository, umgebung,sql);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
            throws SQLException {
        PreparedStatement stmt = con.prepareStatement(sql, autoGeneratedKeys);
        return new SammlerPreparedStatement(stmt, repository, umgebung,sql);

    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
            throws SQLException {
        PreparedStatement stmt = con.prepareStatement(sql, columnIndexes);
        return new SammlerPreparedStatement(stmt, repository, umgebung,sql);

    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames)
            throws SQLException {
        PreparedStatement stmt = con.prepareStatement(sql, columnNames);
        return new SammlerPreparedStatement(stmt, repository, umgebung,sql);
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        PreparedStatement stmt = con.prepareStatement(sql);
        return new SammlerPreparedStatement(stmt, repository, umgebung,sql);
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        con.releaseSavepoint(savepoint);
    }

    @Override
    public void rollback() throws SQLException {
        con.rollback();
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        con.rollback(savepoint);
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        con.setAutoCommit(autoCommit);
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
        con.setCatalog(catalog);
    }

    @Override
    public void setClientInfo(Properties properties)
            throws SQLClientInfoException {
        con.setClientInfo(properties);
    }

    @Override
    public void setClientInfo(String name, String value)
            throws SQLClientInfoException {
        con.setClientInfo(name, value);
    }

    @Override
    public void setHoldability(int holdability) throws SQLException {
        con.setHoldability(holdability);
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds)
            throws SQLException {
        con.setNetworkTimeout(executor, milliseconds);
    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        con.setReadOnly(readOnly);
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return con.setSavepoint();
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        return con.setSavepoint(name);
    }

    @Override
    public void setSchema(String schema) throws SQLException {
        con.setSchema(schema);
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        con.setTransactionIsolation(level);
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        con.setTypeMap(map);
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return con.unwrap(iface);
    }

}
