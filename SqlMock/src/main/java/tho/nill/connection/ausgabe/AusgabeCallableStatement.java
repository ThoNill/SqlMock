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

import tho.nill.db.AbfrageUmgebung;
import tho.nill.io.AbfrageRepository;

public class AusgabeCallableStatement extends AusgabePreparedStatement implements
        CallableStatement {
    private CallableStatement stmt;

    public AusgabeCallableStatement(Connection con,
            AbfrageRepository repository, AbfrageUmgebung umgebung,
            String stmtString,int resultSetType,
            int resultSetConcurrency, int resultSetHoldability) {
        super(con, repository, umgebung, stmtString, resultSetType,resultSetConcurrency,resultSetHoldability);
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
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public BigDecimal getBigDecimal(String parameterName) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
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
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public boolean getBoolean(String parameterName) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public byte getByte(int parameterIndex) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public byte getByte(String parameterName) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
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

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Date getDate(String parameterName, Calendar cal) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Date getDate(String parameterName) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public double getDouble(int parameterIndex) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public double getDouble(String parameterName) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
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

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public float getFloat(String parameterName) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public int getInt(int parameterIndex) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public int getInt(String parameterName) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public long getLong(int parameterIndex) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public long getLong(String parameterName) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
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

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Object getObject(int parameterIndex, Map<String, Class<?>> map)
            throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Object getObject(int parameterIndex) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public <T> T getObject(String parameterName, Class<T> type)
            throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Object getObject(String parameterName, Map<String, Class<?>> map)
            throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Object getObject(String parameterName) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
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

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public short getShort(String parameterName) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public String getString(int parameterIndex) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public String getString(String parameterName) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Time getTime(int parameterIndex, Calendar cal) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Time getTime(int parameterIndex) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Time getTime(String parameterName, Calendar cal) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Time getTime(String parameterName) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Timestamp getTimestamp(int parameterIndex, Calendar cal)
            throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Timestamp getTimestamp(int parameterIndex) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Timestamp getTimestamp(String parameterName, Calendar cal)
            throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
    }

    @Override
    public Timestamp getTimestamp(String parameterName) throws SQLException {

        throw new SQLFeatureNotSupportedException("Not implemented");
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
        setParameter(parameterName, x);

    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x, long length)
            throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x)
            throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setBigDecimal(String parameterName, BigDecimal x)
            throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, int length)
            throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, long length)
            throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x)
            throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setBlob(String parameterName, Blob x) throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream,
            long length) throws SQLException {
        setParameter(parameterName, inputStream);

    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream)
            throws SQLException {
        setParameter(parameterName, inputStream);

    }

    @Override
    public void setBoolean(String parameterName, boolean x) throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setByte(String parameterName, byte x) throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setBytes(String parameterName, byte[] x) throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader,
            int length) throws SQLException {
        setParameter(parameterName, reader);

    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader,
            long length) throws SQLException {
        setParameter(parameterName, reader);

    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader)
            throws SQLException {
        setParameter(parameterName, reader);

    }

    @Override
    public void setClob(String parameterName, Clob x) throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setClob(String parameterName, Reader reader, long length)
            throws SQLException {
        setParameter(parameterName, reader);

    }

    @Override
    public void setClob(String parameterName, Reader reader)
            throws SQLException {
        setParameter(parameterName, reader);

    }

    @Override
    public void setDate(String parameterName, Date x, Calendar cal)
            throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setDate(String parameterName, Date x) throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setDouble(String parameterName, double x) throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setFloat(String parameterName, float x) throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setInt(String parameterName, int x) throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setLong(String parameterName, long x) throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value,
            long length) throws SQLException {
        setParameter(parameterName, value);

    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value)
            throws SQLException {
        setParameter(parameterName, value);

    }

    @Override
    public void setNClob(String parameterName, NClob value) throws SQLException {
        setParameter(parameterName, value);

    }

    @Override
    public void setNClob(String parameterName, Reader reader, long length)
            throws SQLException {
        setParameter(parameterName, reader);

    }

    @Override
    public void setNClob(String parameterName, Reader reader)
            throws SQLException {
        setParameter(parameterName, reader);

    }

    @Override
    public void setNString(String parameterName, String value)
            throws SQLException {
        setParameter(parameterName, value);

    }

    @Override
    public void setNull(String parameterName, int sqlType, String typeName)
            throws SQLException {
        setParameter(parameterName, null);

    }

    @Override
    public void setNull(String parameterName, int sqlType) throws SQLException {
        setParameter(parameterName, null);

    }

    @Override
    public void setObject(String parameterName, Object x) throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType)
            throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType,
            int scale) throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setRowId(String parameterName, RowId x) throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setSQLXML(String parameterName, SQLXML xmlObject)
            throws SQLException {
        setParameter(parameterName, xmlObject);

    }

    @Override
    public void setShort(String parameterName, short x) throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setString(String parameterName, String x) throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setTime(String parameterName, Time x) throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setTime(String parameterName, Time x, Calendar cal)
            throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x)
            throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x, Calendar cal)
            throws SQLException {
        setParameter(parameterName, x);

    }

    @Override
    public void setURL(String parameterName, URL val) throws SQLException {
        setParameter(parameterName, val);

    }

    @Override
    public boolean wasNull() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented");
    }

}