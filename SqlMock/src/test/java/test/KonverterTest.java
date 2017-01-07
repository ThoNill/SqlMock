package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tho.nill.konvertieren.KonverterGruppe;


public class KonverterTest {

    @Test
    public void testInteger() {
        int erg = KonverterGruppe.konvertieren(Integer.class,"12");
        assertEquals(12, erg);
    }

    @Test
    public void testLong() {
        long erg = KonverterGruppe.konvertieren(Long.class,"12");
        assertEquals(12, erg);
    }

    @Test
    public void testString() {
        String erg = KonverterGruppe.konvertieren(String.class,"Mars");
        assertEquals("Mars", erg);
    }

    @Test
    public void testUnmatched() {
        Unmatched erg = KonverterGruppe.konvertieren(Unmatched.class,new Unmatched("unbekannt"));
        assertEquals(new Unmatched("unbekannt"), erg);
    }
    
    class Unmatched {
        private String name;


        public Unmatched(String name) {
            super();
            this.name = name;
        }


        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            return result;
        }


        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Unmatched other = (Unmatched) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;
            return true;
        }


        private KonverterTest getOuterType() {
            return KonverterTest.this;
        }
        
        
    }

}
