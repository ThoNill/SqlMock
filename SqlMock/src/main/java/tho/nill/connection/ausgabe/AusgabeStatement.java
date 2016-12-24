package tho.nill.connection.ausgabe;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLWarning;
import java.sql.Statement;

import tho.nill.db.AbfrageUmgebung;
import tho.nill.db.StatementBasis;
import tho.nill.sqlmock.AbfrageRepository;

public class AusgabeStatement extends StatementBasis implements Statement {
    private boolean closeOnCompletion;
    private boolean open = true;
    private boolean poolable;
    private boolean escapeProcessing;
    private int fetchDirection;
    private int fetchSize;
    private int maxFieldSize;
    private int maxRows;
    private int queryTimeout;
    private String cursorName;
    private Connection con;
    private int resultSetConcurrency;
    private int resultSetType;
    private int resultSetHoldability;

    public AusgabeStatement(Connection con, AbfrageRepository repository,
            AbfrageUmgebung umgebung, String stmtString, int resultSetType,
            int resultSetConcurrency, int resultSetHoldability) {
        super(repository, umgebung, stmtString);
        this.con = con;
        this.resultSetType = resultSetType;
        this.resultSetConcurrency   =resultSetConcurrency;
        this.resultSetHoldability = resultSetHoldability;
    }

    @Override
    public void addBatch(String sql) throws SQLException {

    }

    @Override
    public void cancel() throws SQLException {

    }

    @Override
    public void clearBatch() throws SQLException {

    }

    @Override
    public void clearWarnings() throws SQLException {

    }

    @Override
    public void close() throws SQLException {
        this.open = false;
    }

    @Override
    public void closeOnCompletion() throws SQLException {
        this.closeOnCompletion = true;
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys)
            throws SQLException {
        updateOrInsertWithLookup(sql);
        return getBooleanResult();
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        updateOrInsertWithLookup(sql);
        return getBooleanResult();
    }

    @Override
    public boolean execute(String sql, String[] columnNames)
            throws SQLException {
        updateOrInsertWithLookup(sql);
        return getBooleanResult();
    }

    @Override
    public boolean execute(String sql) throws SQLException {
        updateOrInsertWithLookup(sql);
        return getBooleanResult();
    }

    @Override
    public int[] executeBatch() throws SQLException {
        updateOrInsertWithLookup("Batch");
        return new int[] { getIntResult() };
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        updateOrInsertWithLookup(sql);
        return lookupResultSet(sql);
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys)
            throws SQLException {
        updateOrInsertWithLookup(sql);
        return getIntResult();
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes)
            throws SQLException {
        updateOrInsertWithLookup(sql);
        return getIntResult();

    }

    @Override
    public int executeUpdate(String sql, String[] columnNames)
            throws SQLException {
        updateOrInsertWithLookup(sql);
        return getIntResult();
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        updateOrInsertWithLookup(sql);
        return getIntResult();

    }

    @Override
    public Connection getConnection() throws SQLException {
        return con;
    }

    @Override
    public int getFetchDirection() throws SQLException {
        return this.fetchDirection;
    }

    @Override
    public int getFetchSize() throws SQLException {
        return this.fetchSize;
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return this.maxFieldSize;
    }

    @Override
    public int getMaxRows() throws SQLException {
        return this.maxRows;
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        return hasMoreDaten();
    }

    @Override
    public boolean getMoreResults(int current) throws SQLException {
        return hasMoreDaten();
    }

    @Override
    public int getQueryTimeout() throws SQLException {
        return this.queryTimeout;
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        updateOrInsertWithLookup(getStmtString());
        return lookupResultSet();
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        return this.resultSetConcurrency;
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return resultSetHoldability;
    }

    @Override
    public int getResultSetType() throws SQLException {
        return resultSetType;
    }

    @Override
    public int getUpdateCount() throws SQLException {
        return getIntResult();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return null;
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return closeOnCompletion;
    }

    @Override
    public boolean isClosed() throws SQLException {
        return !open;
    }

    @Override
    public boolean isPoolable() throws SQLException {
        return poolable;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return iface.isInstance(this);
    }

    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        this.escapeProcessing = enable;
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        this.fetchDirection = direction;
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        this.fetchSize = rows;
    }

    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        this.maxFieldSize = max;
    }

    @Override
    public void setMaxRows(int max) throws SQLException {
        this.maxRows = max;
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        this.poolable = poolable;
    }

    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        this.queryTimeout = queryTimeout;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        if (iface.isInstance(this)) {
            return iface.cast(this);
        }
        return null;
    }

    @Override
    public void setCursorName(String arg0) throws SQLException {
        this.cursorName = arg0;
    }

}
