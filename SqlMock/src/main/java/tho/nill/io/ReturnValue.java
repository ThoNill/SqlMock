package tho.nill.io;

import tho.nill.konvertieren.KonverterGruppe;

public class ReturnValue {
    private String text;

    public ReturnValue(String text) {
        super();
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

    @Override
    public String toString() {
        return "ReturnValue [text=" + text + "]";
    }
 
}
