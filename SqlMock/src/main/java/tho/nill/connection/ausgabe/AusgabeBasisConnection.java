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

public class AusgabeBasisConnection implements Connection {
    private static final Logger LOG = Logger.getLogger(AusgabeBasisConnection.class);

    protected AbfrageRepository repository;
    protected AbfrageUmgebung umgebung;

    public AusgabeBasisConnection(AbfrageRepository repository,
            AbfrageUmgebung umgebung) {
        super();
        this.repository = repository;
        this.umgebung = umgebung;
    }

   
    @Override
    public void abort(Executor executor) throws SQLException {
    }

    @Override
    public void clearWarnings() throws SQLException {
    }

    @Override
    public void close() throws SQLException {
    }

    @Override
    public void commit() throws SQLException {
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements)
            throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Blob createBlob() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Clob createClob() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public NClob createNClob() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Statement createStatement() throws SQLException {
        return new AusgabeStatement(this, repository, umgebung, "",
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT);
    }

    @Override
    public Statement createStatement(int resultSetType,
            int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        return new AusgabeStatement(this, repository, umgebung, "",
                resultSetType, resultSetConcurrency, resultSetHoldability);

    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency)
            throws SQLException {
        return new AusgabeStatement(this, repository, umgebung, "",
                resultSetType, resultSetConcurrency,
                ResultSet.HOLD_CURSORS_OVER_COMMIT);
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes)
            throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public String getCatalog() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public int getHoldability() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public String getSchema() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public boolean isClosed() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType,
            int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        return new AusgabeCallableStatement(this, repository, umgebung, sql,
                resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType,
            int resultSetConcurrency) throws SQLException {
        return new AusgabeCallableStatement(this, repository, umgebung, sql,
                resultSetType, resultSetConcurrency,
                ResultSet.HOLD_CURSORS_OVER_COMMIT);
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return new AusgabeCallableStatement(this, repository, umgebung, sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType,
            int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        return new AusgabePreparedStatement(this, repository, umgebung, sql,
                resultSetType, resultSetConcurrency, resultSetHoldability);

    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType,
            int resultSetConcurrency) throws SQLException {
        return new AusgabePreparedStatement(this, repository, umgebung, sql,
                resultSetType, resultSetConcurrency,
                ResultSet.HOLD_CURSORS_OVER_COMMIT);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
            throws SQLException {
        return new AusgabePreparedStatement(this, repository, umgebung, sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
            throws SQLException {
        return new AusgabePreparedStatement(this, repository, umgebung, sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames)
            throws SQLException {
        return new AusgabePreparedStatement(this, repository, umgebung, sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT);
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return new AusgabePreparedStatement(this, repository, umgebung, sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT);
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public void rollback() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public void setClientInfo(Properties properties)
            throws SQLClientInfoException {
    }

    @Override
    public void setClientInfo(String name, String value)
            throws SQLClientInfoException {
    }

    @Override
    public void setHoldability(int holdability) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds)
            throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public void setSchema(String schema) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

}
