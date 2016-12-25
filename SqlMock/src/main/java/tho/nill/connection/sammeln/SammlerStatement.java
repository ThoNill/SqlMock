package tho.nill.connection.sammeln;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

import tho.nill.db.AbfrageUmgebung;
import tho.nill.db.StatementBasis;
import tho.nill.io.AbfrageRepository;

public class SammlerStatement extends StatementBasis implements Statement {
    private Statement stmt;
    

    public SammlerStatement(Statement stmt, AbfrageRepository repository,
            AbfrageUmgebung umgebung,String sql) {
        super(repository, umgebung,sql);
        this.stmt = stmt;
    }

    @Override
    public void addBatch(String sql) throws SQLException {
        stmt.addBatch(sql);
    }

    @Override
    public void cancel() throws SQLException {
        stmt.cancel();
    }

    @Override
    public void clearBatch() throws SQLException {
        stmt.clearBatch();
    }

    @Override
    public void clearWarnings() throws SQLException {
        stmt.clearWarnings();
    }

    @Override
    public void close() throws SQLException {
        stmt.close();
    }

    @Override
    public void closeOnCompletion() throws SQLException {
        stmt.closeOnCompletion();
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys)
            throws SQLException {
        updateOrInsert(sql);
        return stmt.execute(sql, autoGeneratedKeys);
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        updateOrInsert(sql);
        return stmt.execute(sql, columnIndexes);
    }

    @Override
    public boolean execute(String sql, String[] columnNames)
            throws SQLException {
        updateOrInsert(sql);
        return stmt.execute(sql, columnNames);
    }

    @Override
    public boolean execute(String sql) throws SQLException {
        updateOrInsert(sql);
        return stmt.execute(sql);
    }

    @Override
    public int[] executeBatch() throws SQLException {
        updateOrInsert(getStmtString());
        return stmt.executeBatch();
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        updateOrInsert(sql);
        saveResultSet(sql);
        return this.stmt.executeQuery(sql);
    }

    protected void saveResultSet(String sql) throws SQLException {
        ResultSet result = this.stmt.executeQuery(sql);
        super.saveResultSet(result,sql);
        result.close();
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys)
            throws SQLException {
        updateOrInsert(sql);
        return stmt.executeUpdate(sql, autoGeneratedKeys);
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes)
            throws SQLException {
        updateOrInsert(sql);
        return stmt.executeUpdate(sql, columnIndexes);
    }

    @Override
    public int executeUpdate(String sql, String[] columnNames)
            throws SQLException {
        updateOrInsert(sql);
        return stmt.executeUpdate(sql, columnNames);
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        updateOrInsert(sql);
        return stmt.executeUpdate(sql);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return stmt.getConnection();
    }

    @Override
    public int getFetchDirection() throws SQLException {
        return stmt.getFetchDirection();
    }

    @Override
    public int getFetchSize() throws SQLException {
        return stmt.getFetchSize();
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return stmt.getGeneratedKeys();
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return stmt.getMaxFieldSize();
    }

    @Override
    public int getMaxRows() throws SQLException {
        return stmt.getMaxRows();
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        return stmt.getMoreResults();
    }

    @Override
    public boolean getMoreResults(int current) throws SQLException {
        return stmt.getMoreResults(current);
    }

    @Override
    public int getQueryTimeout() throws SQLException {
        return stmt.getQueryTimeout();
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        updateOrInsert(getStmtString());
        saveResultSet();
        return lookupResultSet();
    }

    protected void saveResultSet() throws SQLException {
        ResultSet result = this.stmt.getResultSet();
        super.saveResultSet(result);
        result.close();
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        return stmt.getResultSetConcurrency();
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return stmt.getResultSetHoldability();
    }

    @Override
    public int getResultSetType() throws SQLException {
        return stmt.getResultSetType();
    }

    @Override
    public int getUpdateCount() throws SQLException {
        return stmt.getUpdateCount();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return stmt.getWarnings();
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return stmt.isCloseOnCompletion();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return stmt.isClosed();
    }

    @Override
    public boolean isPoolable() throws SQLException {
        return stmt.isPoolable();
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return stmt.isWrapperFor(iface);
    }

    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        stmt.setEscapeProcessing(enable);
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        stmt.setFetchDirection(direction);
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        stmt.setFetchSize(rows);
    }

    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        stmt.setMaxFieldSize(max);
    }

    @Override
    public void setMaxRows(int max) throws SQLException {
        stmt.setMaxRows(max);
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        stmt.setPoolable(poolable);
    }

    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        stmt.setQueryTimeout(seconds);
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return stmt.unwrap(iface);
    }

    @Override
    public void setCursorName(String arg0) throws SQLException {
        stmt.setCursorName(arg0);
    }

}
