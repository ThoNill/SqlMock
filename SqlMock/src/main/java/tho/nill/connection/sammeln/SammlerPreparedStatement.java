package tho.nill.connection.sammeln;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

import tho.nill.sqlmock.AbfrageRepository;

public class SammlerPreparedStatement extends SammlerStatement implements
        PreparedStatement {
    private PreparedStatement stmt;

    public SammlerPreparedStatement(PreparedStatement stmt,
            AbfrageRepository repository) {
        super(stmt, repository);
        this.stmt = stmt;
    }

    @Override
    public void addBatch() throws SQLException {
        stmt.addBatch();
    }

    @Override
    public void clearParameters() throws SQLException {
        stmt.clearParameters();
    }

    @Override
    public boolean execute() throws SQLException {
        return setBooleanResult(stmt.execute());
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        ResultSet result = stmt.executeQuery();
        super.saveResultSet(result);
        result.close();
        return stmt.executeQuery();
    }

    @Override
    public int executeUpdate() throws SQLException {
        return setIntResult(stmt.executeUpdate());
    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return stmt.getParameterMetaData();
    }

    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {
        stmt.setArray(parameterIndex, x);
        
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length)
            throws SQLException {
        stmt.setAsciiStream(parameterIndex, x, length);
        
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length)
            throws SQLException {
        stmt.setAsciiStream(parameterIndex, x, length);
        
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x)
            throws SQLException {
        stmt.setAsciiStream(parameterIndex, x);
        
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x)
            throws SQLException {
        stmt.setBigDecimal(parameterIndex, x);
        
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length)
            throws SQLException {
        stmt.setBinaryStream(parameterIndex, x, length);
        
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length)
            throws SQLException {
        stmt.setBinaryStream(parameterIndex, x, length);
        
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x)
            throws SQLException {
        stmt.setBinaryStream(parameterIndex, x);
        
    }

    @Override
    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        stmt.setBlob(parameterIndex, x);
        
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length)
            throws SQLException {
        stmt.setBlob(parameterIndex, inputStream, length);
        
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream)
            throws SQLException {
        stmt.setBlob(parameterIndex, inputStream);
        
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        stmt.setBoolean(parameterIndex, x);
        
    }

    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        stmt.setByte(parameterIndex, x);
        
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        stmt.setBytes(parameterIndex, x);
        
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length)
            throws SQLException {
        stmt.setCharacterStream(parameterIndex, reader, length);
        
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader,
            long length) throws SQLException {
        stmt.setCharacterStream(parameterIndex, reader, length);
        
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader)
            throws SQLException {
        stmt.setCharacterStream(parameterIndex, reader);
        
    }

    @Override
    public void setClob(int parameterIndex, Clob x) throws SQLException {
        stmt.setClob(parameterIndex, x);
        
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length)
            throws SQLException {
        stmt.setClob(parameterIndex, reader, length);
        
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        stmt.setClob(parameterIndex, reader);
        
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal)
            throws SQLException {
        stmt.setDate(parameterIndex, x, cal);
        
    }

    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {
        stmt.setDate(parameterIndex, x);
        
    }

    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {
        stmt.setDouble(parameterIndex, x);
        
    }

    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        stmt.setFloat(parameterIndex, x);
        
    }

    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {
        stmt.setInt(parameterIndex, x);
        
    }

    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        stmt.setLong(parameterIndex, x);
        
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value,
            long length) throws SQLException {
        stmt.setNCharacterStream(parameterIndex, value, length);
        
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value)
            throws SQLException {
        stmt.setNCharacterStream(parameterIndex, value);
        
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        stmt.setNClob(parameterIndex, value);
        
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length)
            throws SQLException {
        stmt.setNClob(parameterIndex, reader, length);
        
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        stmt.setNClob(parameterIndex, reader);
        
    }

    @Override
    public void setNString(int parameterIndex, String value)
            throws SQLException {
        stmt.setNString(parameterIndex, value);
        
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName)
            throws SQLException {
        stmt.setNull(parameterIndex, sqlType, typeName);
        
    }

    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        stmt.setNull(parameterIndex, sqlType);
        
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType,
            int scaleOrLength) throws SQLException {
        stmt.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
        
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType)
            throws SQLException {
        stmt.setObject(parameterIndex, x, targetSqlType);
        
    }

    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {
        stmt.setObject(parameterIndex, x);
        
    }

    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {
        stmt.setRef(parameterIndex, x);
        
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        stmt.setRowId(parameterIndex, x);
        
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject)
            throws SQLException {
        stmt.setSQLXML(parameterIndex, xmlObject);
        
    }

    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        stmt.setShort(parameterIndex, x);
        
    }

    @Override
    public void setString(int parameterIndex, String x) throws SQLException {
        stmt.setString(parameterIndex, x);
        
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal)
            throws SQLException {
        stmt.setTime(parameterIndex, x, cal);
        
    }

    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {
        stmt.setTime(parameterIndex, x);
        
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal)
            throws SQLException {
        stmt.setTimestamp(parameterIndex, x, cal);
        
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x)
            throws SQLException {
        stmt.setTimestamp(parameterIndex, x);
        
    }

    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {
        stmt.setURL(parameterIndex, x);
        
    }

    @Override
    public void setUnicodeStream(int parameterIndex, InputStream x, int length)
            throws SQLException {
        stmt.setUnicodeStream(parameterIndex, x, length);
        
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return stmt.getMetaData();
    }

}
