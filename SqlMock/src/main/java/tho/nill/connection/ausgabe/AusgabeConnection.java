package tho.nill.connection.ausgabe;

import tho.nill.connection.AbfrageConfiguration;
import tho.nill.sqlmock.AbfrageRepository;
import tho.nill.sqlmock.AbfrageUmgebung;

public class AusgabeConnection extends AusgabeBasisConnection {
     private static AbfrageConfiguration defaultConfiguration = new AbfrageConfiguration(
            "abfragen");

     private AbfrageConfiguration configuration;
 

    public AusgabeConnection() {
        this(AusgabeConnection.defaultConfiguration);
    }

    public AusgabeConnection(AbfrageConfiguration configuration) {
        super(new AbfrageRepository(),new AbfrageUmgebung());
        this.configuration = configuration;
        prepareRepository();
    }

    protected void prepareRepository() {
        this.umgebung.prepareRepository(this.repository,this.configuration);
    }

    public static void setDefaultConfiguration(
            AbfrageConfiguration configuration) {
        AusgabeConnection.defaultConfiguration = configuration;
    }

   

}
