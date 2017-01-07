package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.sql.rowset.RowSetMetaDataImpl;

import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.Test;

import tho.nill.connection.AbfrageConfiguration;
import tho.nill.connection.ausgabe.AusgabeConnection;
import tho.nill.connection.ausgabe.AusgabeDataSource;
import tho.nill.connection.sammeln.SammlerConnection;
import tho.nill.connection.sammeln.SammlerDataSource;
import tho.nill.sqlmock.AbfrageRepository;
import tho.nill.sqlmock.AbfrageUmgebung;
import tho.nill.sqlmock.DataResultSet;
import tho.nill.sqlmock.StatementBasis;

public class H2Tests {
    private static final Logger LOG = Logger.getLogger(H2Tests.class);

    @Test
    public void datenbankConnectinDa() {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:mem:");
            conn.close();
        } catch (ClassNotFoundException e) {
            LOG.error("Unerwartete Ausnahme {}", e);
            fail("Unerwartete Ausnahme");
        } catch (SQLException e) {
            LOG.error("Unerwartete Ausnahme {}", e);
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
            LOG.error("Unerwartete Ausnahme {}", e);
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
            StatementBasis basis = new StatementBasis(repository);

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
            LOG.error("Unerwartete Ausnahme {}", e);
            fail("Unerwartete Ausnahme");
        } catch (SQLException e) {
            LOG.error("Unerwartete Ausnahme {}", e);
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
        update(con, "DROP ALL OBJECTS");
        update(con, "SET COLLATION GERMAN STRENGTH SECONDARY");
        update(con, "create table kunde ( vorname char, name char) ");

    }

    private int update(Connection con, String statement) throws SQLException {
        Statement stmt = con.createStatement();
        int erg = stmt.executeUpdate(statement);
        stmt.close();
        return erg;
    }

    public void dieDatenMitEinerDataSourceSammelnUndWiederholen(
            SqlAktion aktion, String testFileName) {
        try {
            Class.forName("org.h2.Driver");
            SammlerDataSource.setDefaultConfiguration(new AbfrageConfiguration(
                    testFileName, true));

            JdbcConnectionPool cp = JdbcConnectionPool.create("jdbc:h2:mem:test",
                    "sa", "sa");
            SammlerDataSource dataSource = new SammlerDataSource(cp);
            dieDatenSammelnUndWiederholen(dataSource, aktion, testFileName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            LOG.error("Unerwartete Ausnahme {}", e);
            fail("Unerwartete Ausnahme");

        }
    }

    public void dieDatenSammelnUndWiederholen(SammlerDataSource dataSource, SqlAktion aktion,
            String testFileName) {

        try {
            testdatenErzeugen(dataSource);

            Connection sammler = dataSource.getConnection();
            aktion.perform(sammler);
            sammler.close();
            
            dataSource.closeRepository();

            
            AusgabeDataSource.setDefaultConfiguration(new AbfrageConfiguration(
                    testFileName));
            AusgabeDataSource ausgabeDataSource = new AusgabeDataSource();
            
            testdatenErzeugen(ausgabeDataSource);
   
            Connection ausgabe = ausgabeDataSource.getConnection();

            aktion.perform(ausgabe);

            ausgabe.close();

        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Unerwartete Ausnahme {}", e);
            fail("Unerwartete Ausnahme");
        }
    }

    protected void testdatenErzeugen(DataSource dataSource)
            throws SQLException {
        Connection con = dataSource.getConnection();
        createTestTable(con);
        insertTestData(con, new String[][] { { "Carl Friedrich", "Gauß" } });
        insertTestData(con, new String[][] { { "Emmy", "Noether" } });
        insertTestData(con, new String[][] { { "Emil", "Artin" } });
        insertTestData(con, new String[][] { { "Leonhard", "Euler" } });
        con.close();
    }

    
    
    public void dieDatenMitEinemDriverManagerSammelnUndWiederholen(
            SqlAktion aktion, String testFileName) {
        try {
            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection("jdbc:h2:mem:");
            dieDatenSammelnUndWiederholen(con, aktion, testFileName);
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            LOG.error("Unerwartete Ausnahme {}", e);
            fail("Unerwartete Ausnahme");

        }
    }

    public void dieDatenSammelnUndWiederholen(Connection con, SqlAktion aktion,
            String testFileName) {

        try {

            createTestTable(con);
            insertTestData(con, new String[][] { { "Carl Friedrich", "Gauß" } });
            insertTestData(con, new String[][] { { "Emmy", "Noether" } });
            insertTestData(con, new String[][] { { "Emil", "Artin" } });
            insertTestData(con, new String[][] { { "Leonhard", "Euler" } });

            SammlerConnection.setDefaultConfiguration(new AbfrageConfiguration(
                    testFileName, true));
            SammlerConnection sammler = new SammlerConnection(con);
            aktion.perform(sammler);
            sammler.close();

            AusgabeConnection.setDefaultConfiguration(new AbfrageConfiguration(
                    testFileName));
            AusgabeConnection ausgabe = new AusgabeConnection();

            aktion.perform(ausgabe);

            ausgabe.close();

        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Unerwartete Ausnahme {}", e);
            fail("Unerwartete Ausnahme");
        }
    }

    class CheckStatement implements SqlAktion {

        @Override
        public void perform(Connection con) throws SQLException {
            Statement stmt = con.createStatement();

            String stmtString = "select * from kunde order by vorname asc";

            ResultSet result = stmt.executeQuery(stmtString);
            if (result.next()) {
                assertEquals("Carl Friedrich", result.getString(1));
            } else {
                fail("Abfrage muss ein Ergebnis liefern");
            }
            result.close();
        }
    };

    @Test
    public void statement() {
        dieDatenMitEinemDriverManagerSammelnUndWiederholen(
                new CheckStatement(), "testFile1");
    }

    class CheckPreparedStatement implements SqlAktion {

        @Override
        public void perform(Connection con) throws SQLException {

            String stmtString = "select * from kunde where name = ? ";
            PreparedStatement stmt = con.prepareStatement(stmtString);
            stmt.setString(1, "Artin");
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                assertEquals("Emil", result.getString(1));
            } else {
                fail("Abfrage muss ein Ergebnis liefern");
            }
            result.close();
        }
    }

    @Test
    public void preparedStatement() {
        dieDatenMitEinemDriverManagerSammelnUndWiederholen(
                new CheckPreparedStatement(), "testFile2");
    }

    class CheckExecuteStatement implements SqlAktion {

        @Override
        public void perform(Connection con) throws SQLException {

            String stmtString = "select * from kunde where name = ? ";
            PreparedStatement stmt = con.prepareStatement(stmtString);
            stmt.setString(1, "Artin");
            if (stmt.execute()) {
                ResultSet result = stmt.getResultSet();
                if (result.next()) {
                    assertEquals("Emil", result.getString(1));
                } else {
                    fail("Abfrage muss ein Ergebnis liefern");
                }
                result.close();
            } else {
                fail("Execute gibt falsch zurück");
            }
        }
    }

    @Test
    public void executeStatement() {
        dieDatenMitEinemDriverManagerSammelnUndWiederholen(
                new CheckExecuteStatement(), "testFile3");
    }

    @Test
    public void executeStatementMitEinerDataSource() {
        dieDatenMitEinerDataSourceSammelnUndWiederholen(
                new CheckExecuteStatement(), "testFile4");
    }

}
