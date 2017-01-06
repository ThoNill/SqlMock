package tho.nill.konvertieren;

@FunctionalInterface
public interface DatenKonverter {

  <T> T konvertieren(Object obj);
  
}