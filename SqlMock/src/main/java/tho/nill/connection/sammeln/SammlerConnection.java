package tho.nill.connection.sammeln;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import tho.nill.connection.AbfrageConfiguration;
import tho.nill.sqlmock.AbfrageRepository;
import tho.nill.sqlmock.AbfrageUmgebung;

public class SammlerConnection extends SammlerBasisConnection {
    private static final Logger LOG = Logger.getLogger(SammlerConnection.class);

     private static AbfrageConfiguration defaultConfiguration = new AbfrageConfiguration(
            "abfragen");

    private AbfrageConfiguration configuration;
 
    public static void setDefaultConfiguration(
            AbfrageConfiguration configuration) {
        SammlerConnection.defaultConfiguration = configuration;
    }

    public SammlerConnection(Connection con) {
        this(con, SammlerConnection.defaultConfiguration);
    }

    public SammlerConnection(Connection con, AbfrageConfiguration configuration) {
        super(con,new AbfrageRepository(),new AbfrageUmgebung());
        this.configuration = configuration;
    }

    @Override
    public void close() throws SQLException  {
        super.close();
        writeRepository();
    }
 
    protected void writeRepository() {
        this.umgebung.writeRepository(this.repository, this.configuration);
    }

}
