package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import tho.nill.connection.AbfrageConfiguration;
import tho.nill.connection.ausgabe.AusgabeConnection;
import tho.nill.connection.sammeln.SammlerConnection;

public class StoredProcedureTest {
    private static final Logger LOG = Logger.getLogger(StoredProcedureTest.class);


    public StoredProcedureTest() {
    }

    private static final String storedProcedure = " CREATE PROCEDURE pageStudents (IN name CHAR(40), OUT anzahl INT) LANGUAGE "
            + " JAVA PARAMETER STYLE JAVA READS SQL DATA "
            + "DYNAMIC RESULT SETS 2 EXTERNAL "
            + " NAME 'test.StoredProcedureTest.procedure' ";
    
    public static final String SQL1 = "SELECT vorname, name from kunde where name = ? ";
    public static final String SQL2 = "SELECT name from kunde order by name asc ";

    @Ignore
    @Test
    public void testIt() {
        Connection conn = null;
        try {
            conn = createTestConnection();
            testFürDieseConnetion(conn);
        } catch (SQLException sqlex) {
            LOG.error("Ausnahme {} ",sqlex);
            fail("Nicht erwartete Ausnahme");
        } catch (Exception ex) {
            LOG.error("Ausnahme {}" + ex);
            fail("Nicht erwartete Ausnahme");
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException ex2) {
                LOG.error("Ausnahme {}" + ex2);
                fail("Nicht erwartete Ausnahme");
            }
        }

    }


    @Test
    public void testItSammelAusgabe() {
        Connection conn = null;
        try {
            conn = createTestConnection();
            
            SammlerConnection.setDefaultConfiguration(new AbfrageConfiguration("procedureTest"));
            Connection sammler = new SammlerConnection(conn);
            testFürDieseConnetion(sammler);
            sammler.close();
            
            AusgabeConnection.setDefaultConfiguration(new AbfrageConfiguration("procedureTest"));
            Connection ausgabe = new AusgabeConnection();
            testFürDieseConnetion(ausgabe);
            ausgabe.close();
            
        } catch (SQLException sqlex) {
            LOG.error("Ausnahme {} ",sqlex);
            fail("Nicht erwartete Ausnahme");
        } catch (Exception ex) {
            LOG.error("Ausnahme {} ",ex);
            fail("Nicht erwartete Ausnahme");
        } finally {            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException ex2) {
                LOG.error("Ausnahme {} ",ex2);
                fail("Nicht erwartete Ausnahme");
            }
        }

    }

    
    
    protected void testFürDieseConnetion(Connection conn) {
        CallableStatement cs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        try {

            cs = conn.prepareCall("call pageStudents(?,?) ");
            cs.registerOutParameter(2, Types.INTEGER);
            cs.setString(1, "Noether");
            boolean mitResultSet = cs.execute();
            int anzahl = cs.getInt(2);
            assertEquals(12, anzahl);
            if (mitResultSet) {
                rs1 = cs.getResultSet();
                if (rs1 != null) {
                    while (rs1.next()) {
                        assertEquals("Emmy", rs1.getString(1).trim());
                    }
                }
                boolean moreResults = cs.getMoreResults();
                if (moreResults) {
                    rs2 = cs.getResultSet();
                    if (rs2 != null) {
                        if (rs2.next()) {
                            assertEquals("Artin", rs2.getString(1).trim());
                        }
                    }
                } else {
                    fail("Es sollte mehr als ein ResultSet geben");
                }
            } else {
                fail("Es sollte ein ResultSet geben");
            }
        } catch (SQLException sqlex) {
            LOG.error("Ausnahme {} ",sqlex);
            fail("Nicht erwartete Ausnahme");
        } catch (Exception ex) {
            LOG.error("Ausnahme {} ",ex);
            fail("Nicht erwartete Ausnahme");
        } finally {
            try {
                if (rs1 != null) {
                    rs1.close();
                }
                if (rs2 != null) {
                    rs2.close();
                }
                if (cs != null)
                    cs.close();
            } catch (SQLException ex2) {
                LOG.error("Ausnahme {} ",ex2);
                fail("Nicht erwartete Ausnahme");
            }
        }
    }

    protected Connection createTestConnection() throws InstantiationException,
            IllegalAccessException, ClassNotFoundException, SQLException {
        Connection conn;
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        conn = DriverManager
                .getConnection("jdbc:derby:memory:derbyDb;create=true");
        LOG.debug("Connection established successfully!");

        createTestDaten(conn);
        return conn;
    }

    public final static void procedure(String name, int[] anzahl,
            ResultSet[] rs1, ResultSet[] rs2) throws SQLException {
        Connection con = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        try {
            anzahl[0] = 12;
            con = DriverManager.getConnection("jdbc:default:connection");
            ps1 = con.prepareStatement(SQL1);
            ps1.setString(1, name);
            rs1[0] = ps1.executeQuery();
            ps2 = con.prepareStatement(SQL2);
            rs2[0] = ps2.executeQuery();

            LOG.debug("Abfrage ausgeführt");
        } catch (SQLException sqlex) {
            LOG.error("Ausnahme {} ",sqlex);
            throw sqlex;
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException sqlex1) {
                LOG.error("Ausnahme {} ",sqlex1);
            }
        }
    }

    private void createTestDaten(Connection con) throws SQLException {

        createTestTable(con);
        createProcedure(con);
        insertTestData(con, new String[][] { { "Carl Friedrich", "Gauß" } });
        insertTestData(con, new String[][] { { "Emmy", "Noether" } });
        insertTestData(con, new String[][] { { "Emil", "Artin" } });
        insertTestData(con, new String[][] { { "Leonhard", "Euler" } });
    }

    private void insertTestData(Connection con, String[][] daten)
            throws SQLException {
        for (String[] name : daten) {
            update(con, "insert into kunde ( vorname,name) values ('" + name[0]
                    + "','" + name[1] + "')");
        }
    }

    private void createTestTable(Connection con) throws SQLException {
        update(con, "create table kunde ( vorname char(40), name char(40) ) ");
    }

    private void createProcedure(Connection con) throws SQLException {
        update(con, storedProcedure);
    }

    private int update(Connection con, String statement) throws SQLException {
        Statement stmt = con.createStatement();
        int erg = stmt.executeUpdate(statement);
        stmt.close();
        return erg;
    }

}
