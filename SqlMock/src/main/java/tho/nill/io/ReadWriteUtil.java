package tho.nill.io;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class ReadWriteUtil {

    public static String readUntil(Reader reader, char stop) throws IOException {
        StringBuilder builder = new StringBuilder();
        char[] einZeichen = new char[1];
        int anz = 0;
        char z;
        do {
            anz = reader.read(einZeichen);
            z = einZeichen[0];
            if (z == '\\') {
                anz = reader.read(einZeichen);
                z = einZeichen[0];
                if (anz == 1) {
                    builder.append(z);
                } else  {
                    throw new RuntimeException("Escapezeichen ohne anschliﬂendes Zeichen im Stream");
                }
            } else {
                if (anz == 1 && z != stop) {
                    builder.append(z);
                }
            }
        } while (anz == 1 && z != stop);

        return builder.toString();
    }

    
    public static void write(Writer writer,String text,char stop) throws IOException {
        char[] zeichen = text.toCharArray();
        for(char z : zeichen) {
            if (z == stop) {
                writer.append('\\');
            }
            writer.append(z);
           
        }
        writer.append(stop);
        
    }
}
