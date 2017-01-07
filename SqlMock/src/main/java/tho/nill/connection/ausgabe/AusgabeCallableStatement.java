package tho.nill.connection.ausgabe;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

import tho.nill.sqlmock.AbfrageRepository;

public class AusgabeCallableStatement extends AusgabePreparedStatement implements
        CallableStatement {
    private CallableStatement stmt;

    public AusgabeCallableStatement(Connection con,
            AbfrageRepository repository, 
            String stmtString,int resultSetType,
            int resultSetConcurrency, int resultSetHoldability) {
        super(con, repository,stmtString, resultSetType,resultSetConcurrency,resultSetHoldability);
    }

    @Override
    public Array getArray(int parameterIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Array getArray(String parameterName) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public BigDecimal getBigDecimal(int parameterIndex, int scale)
            throws SQLException {
        return getResultParameterValue(parameterIndex, BigDecimal.class);
    }

    @Override
    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
        return getResultParameterValue(parameterIndex, BigDecimal.class);
    }

    @Override
    public BigDecimal getBigDecimal(String parameterName) throws SQLException {
        return getResultParameterValue(parameterName, BigDecimal.class);
    }

    @Override
    public Blob getBlob(int parameterIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Blob getBlob(String parameterName) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public boolean getBoolean(int parameterIndex) throws SQLException {
        return getResultParameterValue(parameterIndex, Boolean.class);
    }

    @Override
    public boolean getBoolean(String parameterName) throws SQLException {
        return getResultParameterValue(parameterName, Boolean.class);
    }

    @Override
    public byte getByte(int parameterIndex) throws SQLException {
        return getResultParameterValue(parameterIndex, Byte.class);    
    }

    @Override
    public byte getByte(String parameterName) throws SQLException {
        return getResultParameterValue(parameterName, Byte.class);  
    }

    @Override
    public byte[] getBytes(int parameterIndex) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public byte[] getBytes(String parameterName) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Reader getCharacterStream(int parameterIndex) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Reader getCharacterStream(String parameterName) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Clob getClob(int parameterIndex) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Clob getClob(String parameterName) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Date getDate(int parameterIndex, Calendar cal) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Date getDate(int parameterIndex) throws SQLException {

          return getResultParameterValue(parameterIndex, Date.class);  
    }

    @Override
    public Date getDate(String parameterName, Calendar cal) throws SQLException {

          return getResultParameterValue(parameterName, Date.class);  
    }

    @Override
    public Date getDate(String parameterName) throws SQLException {

          return getResultParameterValue(parameterName, Date.class);  
    }

    @Override
    public double getDouble(int parameterIndex) throws SQLException {

          return getResultParameterValue(parameterIndex, Double.class);  
    }

    @Override
    public double getDouble(String parameterName) throws SQLException {

          return getResultParameterValue(parameterName, Double.class);  
    }

    @Override
    public int getFetchDirection() throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public int getFetchSize() throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public float getFloat(int parameterIndex) throws SQLException {

          return getResultParameterValue(parameterIndex, Float.class);  
    }

    @Override
    public float getFloat(String parameterName) throws SQLException {

          return getResultParameterValue(parameterName, Float.class);  
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public int getInt(int parameterIndex) throws SQLException {

          return getResultParameterValue(parameterIndex, Integer.class);  
    }

    @Override
    public int getInt(String parameterName) throws SQLException {

          return getResultParameterValue(parameterName, Integer.class);  
    }

    @Override
    public long getLong(int parameterIndex) throws SQLException {

          return getResultParameterValue(parameterIndex, Long.class);  
    }

    @Override
    public long getLong(String parameterName) throws SQLException {

          return getResultParameterValue(parameterName, Long.class);  
    }

    @Override
    public Reader getNCharacterStream(int parameterIndex) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Reader getNCharacterStream(String parameterName) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public NClob getNClob(int parameterIndex) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public NClob getNClob(String parameterName) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public String getNString(int parameterIndex) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public String getNString(String parameterName) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public <T> T getObject(int parameterIndex, Class<T> type)
            throws SQLException {

          return getResultParameterValue(parameterIndex, type);  
    }

    @Override
    public Object getObject(int parameterIndex, Map<String, Class<?>> map)
            throws SQLException {

          return getResultParameterValue(parameterIndex, Object.class);  
    }

    @Override
    public Object getObject(int parameterIndex) throws SQLException {

          return getResultParameterValue(parameterIndex, Object.class);  
    }

    @Override
    public <T> T getObject(String parameterName, Class<T> type)
            throws SQLException {

          return getResultParameterValue(parameterName,type);  
    }

    @Override
    public Object getObject(String parameterName, Map<String, Class<?>> map)
            throws SQLException {

          return getResultParameterValue(parameterName, Byte.class);  
    }

    @Override
    public Object getObject(String parameterName) throws SQLException {

          return getResultParameterValue(parameterName, Byte.class);  
    }

    @Override
    public Ref getRef(int parameterIndex) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Ref getRef(String parameterName) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public RowId getRowId(int parameterIndex) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public RowId getRowId(String parameterName) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public SQLXML getSQLXML(int parameterIndex) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public SQLXML getSQLXML(String parameterName) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public short getShort(int parameterIndex) throws SQLException {

          return getResultParameterValue(parameterIndex, Short.class);  
    }

    @Override
    public short getShort(String parameterName) throws SQLException {

        return getResultParameterValue(parameterName, Short.class);
    }

    @Override
    public String getString(int parameterIndex) throws SQLException {

          return getResultParameterValue(parameterIndex, String.class);  
    }

    @Override
    public String getString(String parameterName) throws SQLException {

          return getResultParameterValue(parameterName, String.class);  
    }

    @Override
    public Time getTime(int parameterIndex, Calendar cal) throws SQLException {

          return getResultParameterValue(parameterIndex, Time.class);  
    }

    @Override
    public Time getTime(int parameterIndex) throws SQLException {

          return getResultParameterValue(parameterIndex, Time.class);  
    }

    @Override
    public Time getTime(String parameterName, Calendar cal) throws SQLException {

          return getResultParameterValue(parameterName, Time.class);  
    }

    @Override
    public Time getTime(String parameterName) throws SQLException {

          return getResultParameterValue(parameterName, Time.class);  
    }

    @Override
    public Timestamp getTimestamp(int parameterIndex, Calendar cal)
            throws SQLException {

          return getResultParameterValue(parameterIndex, Timestamp.class);  
    }

    @Override
    public Timestamp getTimestamp(int parameterIndex) throws SQLException {

          return getResultParameterValue(parameterIndex, Timestamp.class);  
    }

    @Override
    public Timestamp getTimestamp(String parameterName, Calendar cal)
            throws SQLException {

          return getResultParameterValue(parameterName, Timestamp.class);  
    }

    @Override
    public Timestamp getTimestamp(String parameterName) throws SQLException {

          return getResultParameterValue(parameterName, Timestamp.class);  
    }

    @Override
    public URL getURL(int parameterIndex) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public URL getURL(String parameterName) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType, int scale)
            throws SQLException {

    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType,
            String typeName) throws SQLException {

    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType)
            throws SQLException {

    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType,
            int scale) throws SQLException {

    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType,
            String typeName) throws SQLException {

    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType)
            throws SQLException {

    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x, int length)
            throws SQLException {
        

    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x, long length)
            throws SQLException {
        

    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x)
            throws SQLException {
        

    }

    @Override
    public void setBigDecimal(String parameterName, BigDecimal x)
            throws SQLException {
        

    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, int length)
            throws SQLException {
        

    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, long length)
            throws SQLException {
        

    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x)
            throws SQLException {
        

    }

    @Override
    public void setBlob(String parameterName, Blob x) throws SQLException {
        

    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream,
            long length) throws SQLException {
        

    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream)
            throws SQLException {
        

    }

    @Override
    public void setBoolean(String parameterName, boolean x) throws SQLException {
        

    }

    @Override
    public void setByte(String parameterName, byte x) throws SQLException {
        

    }

    @Override
    public void setBytes(String parameterName, byte[] x) throws SQLException {
        

    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader,
            int length) throws SQLException {
        

    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader,
            long length) throws SQLException {
        

    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader)
            throws SQLException {
        

    }

    @Override
    public void setClob(String parameterName, Clob x) throws SQLException {
        

    }

    @Override
    public void setClob(String parameterName, Reader reader, long length)
            throws SQLException {
        

    }

    @Override
    public void setClob(String parameterName, Reader reader)
            throws SQLException {
        

    }

    @Override
    public void setDate(String parameterName, Date x, Calendar cal)
            throws SQLException {
        

    }

    @Override
    public void setDate(String parameterName, Date x) throws SQLException {
        

    }

    @Override
    public void setDouble(String parameterName, double x) throws SQLException {
        

    }

    @Override
    public void setFloat(String parameterName, float x) throws SQLException {
        

    }

    @Override
    public void setInt(String parameterName, int x) throws SQLException {
        

    }

    @Override
    public void setLong(String parameterName, long x) throws SQLException {
        

    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value,
            long length) throws SQLException {
        

    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value)
            throws SQLException {
        

    }

    @Override
    public void setNClob(String parameterName, NClob value) throws SQLException {
        

    }

    @Override
    public void setNClob(String parameterName, Reader reader, long length)
            throws SQLException {
        

    }

    @Override
    public void setNClob(String parameterName, Reader reader)
            throws SQLException {
        

    }

    @Override
    public void setNString(String parameterName, String value)
            throws SQLException {
        

    }

    @Override
    public void setNull(String parameterName, int sqlType, String typeName)
            throws SQLException {
        

    }

    @Override
    public void setNull(String parameterName, int sqlType) throws SQLException {
        

    }

    @Override
    public void setObject(String parameterName, Object x) throws SQLException {
        

    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType)
            throws SQLException {
        

    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType,
            int scale) throws SQLException {
        

    }

    @Override
    public void setRowId(String parameterName, RowId x) throws SQLException {
        

    }

    @Override
    public void setSQLXML(String parameterName, SQLXML xmlObject)
            throws SQLException {
        

    }

    @Override
    public void setShort(String parameterName, short x) throws SQLException {
        

    }

    @Override
    public void setString(String parameterName, String x) throws SQLException {
        

    }

    @Override
    public void setTime(String parameterName, Time x) throws SQLException {
        

    }

    @Override
    public void setTime(String parameterName, Time x, Calendar cal)
            throws SQLException {
        

    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x)
            throws SQLException {
        

    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x, Calendar cal)
            throws SQLException {
        

    }

    @Override
    public void setURL(String parameterName, URL val) throws SQLException {
        

    }

    @Override
    public boolean wasNull() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

}
