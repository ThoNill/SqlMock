package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.RowSetMetaDataImpl;

import org.apache.log4j.Logger;
import org.junit.Test;

import tho.nill.connection.AbfrageConfiguration;
import tho.nill.connection.ausgabe.AusgabeConnection;
import tho.nill.connection.sammeln.SammlerConnection;
import tho.nill.db.AbfrageUmgebung;
import tho.nill.db.DataResultSet;
import tho.nill.db.StatementBasis;
import tho.nill.sqlmock.AbfrageRepository;

public class H2Tests {
    private static final Logger LOG = Logger.getLogger(H2Tests.class);


    @Test
    public void datenbankConnectinDa() {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:mem:");
            conn.close();
        } catch (ClassNotFoundException e) {
            LOG.error("Unerwartete Ausnahme {}",e);
            fail("Unerwartete Ausnahme");
        } catch (SQLException e) {
            LOG.error("Unerwartete Ausnahme {}",e);
            fail("Unerwartete Ausnahme");
        }
    }

    @Test
    public void DataResultSet() {
        try {
            RowSetMetaDataImpl metaData = new RowSetMetaDataImpl();
            metaData.setColumnCount(3);
            metaData.setColumnLabel(1, "Eins");
            metaData.setColumnLabel(2, "Zwei");
            metaData.setColumnLabel(3, "Drei");

            Object[][] daten = { { "1", "22", "3" }, { "1", "2", "3" },
                    { "1", "2", "3" }, { "1", "2", "3" } };
            DataResultSet result = new DataResultSet(daten, metaData);
            assertTrue(result.isBeforeFirst());

            assertTrue(result.next());
            assertEquals(1, result.getInt(1));
            assertEquals(22, result.getInt(2));
            assertEquals(3, result.getInt(3));
            assertEquals(1, result.getInt("Eins"));
            assertEquals(22, result.getInt("Zwei"));
            assertEquals(3, result.getInt("Drei"));
            assertFalse(result.isClosed());
            result.close();
            assertTrue(result.isClosed());
        } catch (SQLException e) {
            LOG.error("Unerwartete Ausnahme {}",e);
            fail("Unerwartete Ausnahme");
        }
    }

    @Test
    public void datenbankAbfrage() {
        try {
            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection("jdbc:h2:mem:");

            createTestTable(con);
            insertTestData(con, new String[][] { { "Thomas", "Nill" } });

            AbfrageRepository repository = new AbfrageRepository();
            AbfrageUmgebung umgebung = new AbfrageUmgebung();

            String stmt = "select * from kunde ";
            StatementBasis basis = new StatementBasis(repository, umgebung,
                    stmt);

            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(stmt);
            basis.saveResultSet(result);
            result.close();
            statement.close();
            
            ResultSet lookupResult = basis.lookupResultSet();

            assertTrue(lookupResult.next());
            assertEquals("Thomas", lookupResult.getString(1));
            assertEquals("Nill", lookupResult.getString(2));

            con.close();
        } catch (ClassNotFoundException e) {
            LOG.error("Unerwartete Ausnahme {}",e);
            fail("Unerwartete Ausnahme");
        } catch (SQLException e) {
            LOG.error("Unerwartete Ausnahme {}",e);
            fail("Unerwartete Ausnahme");
        }
    }

    private void insertTestData(Connection con, String[][] daten)
            throws SQLException {
        for (String[] name : daten) {
            update(con, "insert into kunde ( vorname,name) values ('" + name[0]
                    + "','" + name[1] + "')");
        }
    }

    private void createTestTable(Connection con) throws SQLException {
        update(con,"DROP ALL OBJECTS");
        update(con,"SET COLLATION GERMAN STRENGTH SECONDARY");
        update(con, "create table kunde ( vorname char, name char) ");

    }

    private int update(Connection con, String statement) throws SQLException {
        Statement stmt = con.createStatement();
        int erg = stmt.executeUpdate(statement);
        stmt.close();
        return erg;
    }

    
    @Test
    public void datenbankAbfrageSammeln() {
        try {
            
            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection("jdbc:h2:mem:");
            

            createTestTable(con);
            insertTestData(con, new String[][] { { "Carl Friedrich", "Gauﬂ" } });
            insertTestData(con, new String[][] { { "Emmy", "Noether" } });
            insertTestData(con, new String[][] { { "Emil", "Artin" } });
            insertTestData(con, new String[][] { { "Leonhard", "Euler" } });
            
            SammlerConnection.setDefaultConfiguration(new AbfrageConfiguration("testFile"));
            SammlerConnection sammler = new SammlerConnection(con);
            Statement stmt = sammler.createStatement();

            String stmtString = "select * from kunde order by vorname asc";
            
            ResultSet result = stmt.executeQuery(stmtString);
            if(result.next()) {
                assertEquals("Carl Friedrich", result.getString(1));
            }
            result.close();
            sammler.close();
        } catch (Exception e) {
            LOG.error("Unerwartete Ausnahme {}",e);
            fail("Unerwartete Ausnahme");
        } 
    }
    @Test
    public void datenbankAbfrageAusgeben() {
        try {
            
            AusgabeConnection.setDefaultConfiguration(new AbfrageConfiguration("testFile"));
            AusgabeConnection sammler = new AusgabeConnection();
            Statement stmt = sammler.createStatement();

            String stmtString = "select * from kunde order by vorname asc";
          
            ResultSet result = stmt.executeQuery(stmtString);
            if(result.next()) {
               assertEquals("Carl Friedrich", result.getString(1));
            }
            result.close();
            sammler.close();
        } catch (Exception e) {
            LOG.error("Unerwartete Ausnahme {}",e);
            fail("Unerwartete Ausnahme");
        } 
    }

}
