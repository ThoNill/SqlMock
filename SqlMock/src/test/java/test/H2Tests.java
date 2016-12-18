package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.RowSetMetaDataImpl;

import org.junit.Ignore;
import org.junit.Test;

import tho.nill.db.AbfrageUmgebung;
import tho.nill.db.DataResultSet;
import tho.nill.db.StatementBasis;
import tho.nill.sqlmock.AbfrageRepository;

public class H2Tests {

    @Test
    public void datenbankConnectinDa() {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:mem:");
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            fail("Unerwartete Ausnahme");
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
            fail("Unerwartete Ausnahme");
        }
    }
    
 
    @Test
    public void datenbankAbfrage() {
        try {
            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection("jdbc:h2:mem:");
            
            createTestTable(con);
            insertTestData(con,new String[][]{{"Thomas","Nill"}});
            
            
            AbfrageRepository repository = new AbfrageRepository();
            AbfrageUmgebung umgebung = new AbfrageUmgebung();
            
            String stmt = "select * from kunde ";
            StatementBasis basis = new StatementBasis(repository, umgebung, stmt);
            
            ResultSet result = select(con,stmt);
            basis.saveResultSet(result);
            
            ResultSet lookupResult = basis.lookupResultSet();
            
            assertTrue(lookupResult.next());
            assertEquals("Thomas",lookupResult.getString(1));
            assertEquals("Nill",lookupResult.getString(2));
            
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            fail("Unerwartete Ausnahme");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Unerwartete Ausnahme");
        }
    }

    private void insertTestData(Connection con,String[][] daten) throws SQLException {
        for (String [] name : daten) {
            update(con,"insert into kunde ( vorname,name) values ('" + name[0] + "','" + name[1] +"')");
        }
    } 
    
 
    private void createTestTable(Connection con) throws SQLException {
        update(con,"create table kunde ( vorname char, name char) ");
        
    }
    private int update(Connection con,String statement) throws SQLException {
        Statement stmt = con.createStatement();
        return stmt.executeUpdate(statement);
    }
    
    private ResultSet select(Connection con,String statement) throws SQLException {
        Statement stmt = con.createStatement();
        return stmt.executeQuery(statement);
    }
}
