# SqlMock
Mocking von Sql Connections/Statements/ResultSets

Das Mocking erfolgt in zwei Schritten.
Im Ersten werden die SQL Befehle auf die übliche Datenbankconnection abgesetzt,
und die Ergebnisse über eine SammlerConnection aufgesammelt.
Die gesammelten Ergebnisse werden in eine Datei geschrieben.

Beispiel mit H2:

     Class.forName("org.h2.Driver");
     Connection con = DriverManager.getConnection("jdbc:h2:mem:");
     SammlerConnection.setDefaultConfiguration(new AbfrageConfiguration("SammelDatei"));
     SammlerConnection sammler = new SammlerConnection(con);

     Statement stmt = sammler.createStatement();
     
     ...
     
Im zweiten Schritt können die so aufgesammelten Daten, wieder abgespielt werden.
Dazu dient eine AusgabeConnection, die eine Connection mockt. 

       AusgabeConnection.setDefaultConfiguration(new AbfrageConfiguration("SammelDatei"));
       AusgabeConnection sammler = new AusgabeConnection();
       Statement stmt = sammler.createStatement();
       ...

Zuest werden also die Daten für den Mock mit einer normalen Connection gesammelt,
um später in einem Unit-Test wieder verwendet zu werden.



