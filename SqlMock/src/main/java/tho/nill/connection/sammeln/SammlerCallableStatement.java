package tho.nill.connection.sammeln;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

import tho.nill.sqlmock.AbfrageRepository;

public class SammlerCallableStatement extends SammlerPreparedStatement implements
        CallableStatement {
    private CallableStatement stmt;

    public SammlerCallableStatement(CallableStatement stmt,
            AbfrageRepository repository) {
        super(stmt, repository);
        this.stmt = stmt;
    }

    @Override
    public Array getArray(int parameterIndex) throws SQLException {
        return stmt.getArray(parameterIndex);
    }

    @Override
    public Array getArray(String parameterName) throws SQLException {
        return stmt.getArray(parameterName);
    }

    @Override
    public BigDecimal getBigDecimal(int parameterIndex, int scale)
            throws SQLException {
        return stmt.getBigDecimal(parameterIndex, scale);
    }

    @Override
    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
        BigDecimal value = stmt.getBigDecimal(parameterIndex);
        return addResultParameter(parameterIndex, value,BigDecimal.class);
    }

    @Override
    public BigDecimal getBigDecimal(String parameterName) throws SQLException {
        return addResultParameter(parameterName,stmt.getBigDecimal(parameterName),BigDecimal.class);
    }

    @Override
    public Blob getBlob(int parameterIndex) throws SQLException {
        return stmt.getBlob(parameterIndex);
    }

    @Override
    public Blob getBlob(String parameterName) throws SQLException {
        return stmt.getBlob(parameterName);
    }

    @Override
    public boolean getBoolean(int parameterIndex) throws SQLException {
        return addResultParameter(parameterIndex,stmt.getBoolean(parameterIndex),Boolean.class);
    }

    @Override
    public boolean getBoolean(String parameterName) throws SQLException {
        return addResultParameter(parameterName,stmt.getBoolean(parameterName),Boolean.class);
    }

    @Override
    public byte getByte(int parameterIndex) throws SQLException {
        return addResultParameter(parameterIndex,stmt.getByte(parameterIndex),Byte.class);
    }

    @Override
    public byte getByte(String parameterName) throws SQLException {
        return addResultParameter(parameterName,stmt.getByte(parameterName),Byte.class);
    }

    @Override
    public byte[] getBytes(int parameterIndex) throws SQLException {
        return stmt.getBytes(parameterIndex);
    }

    @Override
    public byte[] getBytes(String parameterName) throws SQLException {
        return stmt.getBytes(parameterName);
    }

    @Override
    public Reader getCharacterStream(int parameterIndex) throws SQLException {
        return stmt.getCharacterStream(parameterIndex);
    }

    @Override
    public Reader getCharacterStream(String parameterName) throws SQLException {
        return stmt.getCharacterStream(parameterName);
    }

    @Override
    public Clob getClob(int parameterIndex) throws SQLException {
        return stmt.getClob(parameterIndex);
    }

    @Override
    public Clob getClob(String parameterName) throws SQLException {
        return stmt.getClob(parameterName);
    }

    @Override
    public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
        return addResultParameter(parameterIndex,stmt.getDate(parameterIndex, cal),Date.class);
    }

    @Override
    public Date getDate(int parameterIndex) throws SQLException {
        return addResultParameter(parameterIndex,stmt.getDate(parameterIndex),Date.class);
    }

    @Override
    public Date getDate(String parameterName, Calendar cal) throws SQLException {
        return addResultParameter(parameterName,stmt.getDate(parameterName, cal),Date.class);
    }

    @Override
    public Date getDate(String parameterName) throws SQLException {
        return addResultParameter(parameterName, stmt.getDate(parameterName),Date.class);
    }

    @Override
    public double getDouble(int parameterIndex) throws SQLException {
        return addResultParameter(parameterIndex,stmt.getDouble(parameterIndex),Double.class);
    }

    @Override
    public double getDouble(String parameterName) throws SQLException {
        return addResultParameter(parameterName, stmt.getDouble(parameterName),Double.class);
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
    public float getFloat(int parameterIndex) throws SQLException {
        return addResultParameter(parameterIndex,stmt.getFloat(parameterIndex),Float.class);
    }

    @Override
    public float getFloat(String parameterName) throws SQLException {
        return addResultParameter(parameterName, stmt.getFloat(parameterName),Float.class);
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return stmt.getGeneratedKeys();
    }

    @Override
    public int getInt(int parameterIndex) throws SQLException {
        return addResultParameter(parameterIndex,stmt.getInt(parameterIndex),Integer.class);
    }

    @Override
    public int getInt(String parameterName) throws SQLException {
        return addResultParameter(parameterName, stmt.getInt(parameterName),Integer.class);
    }

    @Override
    public long getLong(int parameterIndex) throws SQLException {
        return addResultParameter(parameterIndex,stmt.getLong(parameterIndex),Long.class);
    }

    @Override
    public long getLong(String parameterName) throws SQLException {
        return addResultParameter(parameterName, stmt.getLong(parameterName),Long.class);
    }

    @Override
    public Reader getNCharacterStream(int parameterIndex) throws SQLException {
        return stmt.getNCharacterStream(parameterIndex);
    }

    @Override
    public Reader getNCharacterStream(String parameterName) throws SQLException {
        return stmt.getNCharacterStream(parameterName);
    }

    @Override
    public NClob getNClob(int parameterIndex) throws SQLException {
        return stmt.getNClob(parameterIndex);
    }

    @Override
    public NClob getNClob(String parameterName) throws SQLException {
        return stmt.getNClob(parameterName);
    }

    @Override
    public String getNString(int parameterIndex) throws SQLException {
        return stmt.getNString(parameterIndex);
    }

    @Override
    public String getNString(String parameterName) throws SQLException {
        return stmt.getNString(parameterName);
    }

    @Override
    public <T> T getObject(int parameterIndex, Class<T> type)
            throws SQLException {
        return addResultParameter(parameterIndex,stmt.getObject(parameterIndex, type),type);
    }

    @Override
    public Object getObject(int parameterIndex, Map<String, Class<?>> map)
            throws SQLException {
        return stmt.getObject(parameterIndex, map);
    }

    @Override
    public Object getObject(int parameterIndex) throws SQLException {
        return addResultParameter(parameterIndex,stmt.getObject(parameterIndex),Object.class);
    }

    @Override
    public <T> T getObject(String parameterName, Class<T> type)
            throws SQLException {
        return addResultParameter(parameterName, stmt.getObject(parameterName, type),type);
    }

    @Override
    public Object getObject(String parameterName, Map<String, Class<?>> map)
            throws SQLException {
        return stmt.getObject(parameterName, map);
    }

    @Override
    public Object getObject(String parameterName) throws SQLException {
        return addResultParameter(parameterName, stmt.getObject(parameterName),Object.class);
    }

    @Override
    public Ref getRef(int parameterIndex) throws SQLException {
        return stmt.getRef(parameterIndex);
    }

    @Override
    public Ref getRef(String parameterName) throws SQLException {
        return stmt.getRef(parameterName);
    }

    @Override
    public RowId getRowId(int parameterIndex) throws SQLException {
        return stmt.getRowId(parameterIndex);
    }

    @Override
    public RowId getRowId(String parameterName) throws SQLException {
        return stmt.getRowId(parameterName);
    }

    @Override
    public SQLXML getSQLXML(int parameterIndex) throws SQLException {
        return stmt.getSQLXML(parameterIndex);
    }

    @Override
    public SQLXML getSQLXML(String parameterName) throws SQLException {
        return stmt.getSQLXML(parameterName);
    }

    @Override
    public short getShort(int parameterIndex) throws SQLException {
        return addResultParameter(parameterIndex,stmt.getShort(parameterIndex),Short.class);
    }

    @Override
    public short getShort(String parameterName) throws SQLException {
        return addResultParameter(parameterName, stmt.getShort(parameterName),Short.class);
    }

    @Override
    public String getString(int parameterIndex) throws SQLException {
        return addResultParameter(parameterIndex,stmt.getString(parameterIndex),String.class);
    }

    @Override
    public String getString(String parameterName) throws SQLException {
        return addResultParameter(parameterName, stmt.getString(parameterName),String.class);
    }

    @Override
    public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
        return addResultParameter(parameterIndex,stmt.getTime(parameterIndex, cal),Time.class);
    }

    @Override
    public Time getTime(int parameterIndex) throws SQLException {
        return addResultParameter(parameterIndex,stmt.getTime(parameterIndex),Time.class);
    }

    @Override
    public Time getTime(String parameterName, Calendar cal) throws SQLException {
        return addResultParameter(parameterName, stmt.getTime(parameterName, cal),Time.class);
    }

    @Override
    public Time getTime(String parameterName) throws SQLException {
        return addResultParameter(parameterName, stmt.getTime(parameterName),Time.class);
    }

    @Override
    public Timestamp getTimestamp(int parameterIndex, Calendar cal)
            throws SQLException {
        return addResultParameter(parameterIndex,stmt.getTimestamp(parameterIndex, cal),Timestamp.class);
    }

    @Override
    public Timestamp getTimestamp(int parameterIndex) throws SQLException {
        return addResultParameter(parameterIndex,stmt.getTimestamp(parameterIndex),Timestamp.class);
    }

    @Override
    public Timestamp getTimestamp(String parameterName, Calendar cal)
            throws SQLException {
        return addResultParameter(parameterName, stmt.getTimestamp(parameterName, cal),Timestamp.class);
    }

    @Override
    public Timestamp getTimestamp(String parameterName) throws SQLException {
        return addResultParameter(parameterName, stmt.getTimestamp(parameterName),Timestamp.class);
    }

    @Override
    public URL getURL(int parameterIndex) throws SQLException {
        return stmt.getURL(parameterIndex);
    }

    @Override
    public URL getURL(String parameterName) throws SQLException {
        return stmt.getURL(parameterName);
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType, int scale)
            throws SQLException {
        stmt.registerOutParameter(parameterIndex, sqlType, scale);
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType,
            String typeName) throws SQLException {
        stmt.registerOutParameter(parameterIndex, sqlType, typeName);
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType)
            throws SQLException {
        stmt.registerOutParameter(parameterIndex, sqlType);
    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType,
            int scale) throws SQLException {
        stmt.registerOutParameter(parameterName, sqlType, scale);
    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType,
            String typeName) throws SQLException {
        stmt.registerOutParameter(parameterName, sqlType, typeName);
    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType)
            throws SQLException {
        stmt.registerOutParameter(parameterName, sqlType);
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x, int length)
            throws SQLException {
        
        stmt.setAsciiStream(parameterName, x, length);
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x, long length)
            throws SQLException {
        
        stmt.setAsciiStream(parameterName, x, length);
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x)
            throws SQLException {
        
        stmt.setAsciiStream(parameterName, x);
    }

    @Override
    public void setBigDecimal(String parameterName, BigDecimal x)
            throws SQLException {
        
        stmt.setBigDecimal(parameterName, x);
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, int length)
            throws SQLException {
        
        stmt.setBinaryStream(parameterName, x, length);
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, long length)
            throws SQLException {
        
        stmt.setBinaryStream(parameterName, x, length);
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x)
            throws SQLException {
        
        stmt.setBinaryStream(parameterName, x);
    }

    @Override
    public void setBlob(String parameterName, Blob x) throws SQLException {
        
        stmt.setBlob(parameterName, x);
    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream,
            long length) throws SQLException {
        
        stmt.setBlob(parameterName, inputStream, length);
    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream)
            throws SQLException {
        
        stmt.setBlob(parameterName, inputStream);
    }

    @Override
    public void setBoolean(String parameterName, boolean x) throws SQLException {
        
        stmt.setBoolean(parameterName, x);
    }

    @Override
    public void setByte(String parameterName, byte x) throws SQLException {
        
        stmt.setByte(parameterName, x);
    }

    @Override
    public void setBytes(String parameterName, byte[] x) throws SQLException {
        
        stmt.setBytes(parameterName, x);
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader,
            int length) throws SQLException {
        
        stmt.setCharacterStream(parameterName, reader, length);
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader,
            long length) throws SQLException {
        
        stmt.setCharacterStream(parameterName, reader, length);
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader)
            throws SQLException {
        
        stmt.setCharacterStream(parameterName, reader);
    }

    @Override
    public void setClob(String parameterName, Clob x) throws SQLException {
        
        stmt.setClob(parameterName, x);
    }

    @Override
    public void setClob(String parameterName, Reader reader, long length)
            throws SQLException {
        
        stmt.setClob(parameterName, reader, length);
    }

    @Override
    public void setClob(String parameterName, Reader reader)
            throws SQLException {
        
        stmt.setClob(parameterName, reader);
    }

    @Override
    public void setDate(String parameterName, Date x, Calendar cal)
            throws SQLException {
        
        stmt.setDate(parameterName, x, cal);
    }

    @Override
    public void setDate(String parameterName, Date x) throws SQLException {
        
        stmt.setDate(parameterName, x);
    }

    @Override
    public void setDouble(String parameterName, double x) throws SQLException {
        
        stmt.setDouble(parameterName, x);
    }

    @Override
    public void setFloat(String parameterName, float x) throws SQLException {
        
        stmt.setFloat(parameterName, x);
    }

    @Override
    public void setInt(String parameterName, int x) throws SQLException {
        
        stmt.setInt(parameterName, x);
    }

    @Override
    public void setLong(String parameterName, long x) throws SQLException {
        
        stmt.setLong(parameterName, x);
    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value,
            long length) throws SQLException {
        
        stmt.setNCharacterStream(parameterName, value, length);
    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value)
            throws SQLException {
        
        stmt.setNCharacterStream(parameterName, value);
    }

    @Override
    public void setNClob(String parameterName, NClob value) throws SQLException {
        
        stmt.setNClob(parameterName, value);
    }

    @Override
    public void setNClob(String parameterName, Reader reader, long length)
            throws SQLException {
        
        stmt.setNClob(parameterName, reader, length);
    }

    @Override
    public void setNClob(String parameterName, Reader reader)
            throws SQLException {
        
        stmt.setNClob(parameterName, reader);
    }

    @Override
    public void setNString(String parameterName, String value)
            throws SQLException {
        
        stmt.setNString(parameterName, value);
    }

    @Override
    public void setNull(String parameterName, int sqlType, String typeName)
            throws SQLException {
        
        stmt.setNull(parameterName, sqlType, typeName);
    }

    @Override
    public void setNull(String parameterName, int sqlType) throws SQLException {
        
        stmt.setNull(parameterName, sqlType);
    }

    @Override
    public void setObject(String parameterName, Object x) throws SQLException {
        
        stmt.setObject(parameterName, x);
    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType)
            throws SQLException {
        
        stmt.setObject(parameterName, x, targetSqlType);
    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType,
            int scale) throws SQLException {
        
        stmt.setObject(parameterName, x, targetSqlType);
    }

    @Override
    public void setRowId(String parameterName, RowId x) throws SQLException {
        
        stmt.setRowId(parameterName, x);
    }

    @Override
    public void setSQLXML(String parameterName, SQLXML xmlObject)
            throws SQLException {
        
        stmt.setSQLXML(parameterName, xmlObject);
    }

    @Override
    public void setShort(String parameterName, short x) throws SQLException {
        
        stmt.setShort(parameterName, x);
    }

    @Override
    public void setString(String parameterName, String x) throws SQLException {
        
        stmt.setString(parameterName, x);
    }

    @Override
    public void setTime(String parameterName, Time x) throws SQLException {
        
        stmt.setTime(parameterName, x);
    }

    @Override
    public void setTime(String parameterName, Time x, Calendar cal)
            throws SQLException {
        
        stmt.setTime(parameterName, x, cal);
    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x)
            throws SQLException {
        
        stmt.setTimestamp(parameterName, x);
    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x, Calendar cal)
            throws SQLException {
        
        stmt.setTimestamp(parameterName, x, cal);
    }

    @Override
    public void setURL(String parameterName, URL val) throws SQLException {
        
        stmt.setURL(parameterName, val);
    }

    @Override
    public boolean wasNull() throws SQLException {
        return stmt.wasNull();
    }

}
