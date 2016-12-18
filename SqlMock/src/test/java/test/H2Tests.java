package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.rowset.RowSetMetaDataImpl;

import org.junit.Test;

import tho.nill.db.DataResultSet;

public class H2Tests {

    @Test
    public void test() {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test");
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        try {
            RowSetMetaDataImpl metaData = new RowSetMetaDataImpl();
            metaData.setColumnCount(3);
            metaData.setColumnLabel(1, "Eins");
            metaData.setColumnLabel(2, "Zwei");
            metaData.setColumnLabel(3, "Drei");
            metaData.setColumnLabel(4, "Vier");
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
        }
    }
}
