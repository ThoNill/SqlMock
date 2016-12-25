package test;

import java.sql.Connection;
import java.sql.SQLException;

public interface SqlAktion {
    void perform(Connection con)  throws SQLException ;
}
