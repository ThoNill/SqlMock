package tho.nill.konvertieren;



public class ReturnValue {
    private String text;
    private String name;

    public ReturnValue(String name,String text) {
        super();
        this.name = name;
        this.text = text;
    }
  
    public int getInt() {
        return KonverterGruppe.konvertieren(Integer.class,text);
    }
 
    public boolean getBoolean() {
        return KonverterGruppe.konvertieren(Boolean.class,text);
    }


    public String getString() {
        return text;
    }

    public String getName() {
        return name;
    }

    
    @Override
    public String toString() {
        return "ReturnValue [text=" + text + "]";
    }
 
}
