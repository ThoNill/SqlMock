package tho.nill.connection;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import tho.nill.io.CsvReader;
import tho.nill.sqlmock.SqlMockException;

public class AbfrageConfiguration {
    private String fileName;
    private boolean overwrite;

    public AbfrageConfiguration(String fileName) {
        this(fileName,true);
    }

    public AbfrageConfiguration(String fileName,boolean overwrite) {
        super();
        this.fileName = fileName;
        this.overwrite = overwrite;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        
        this.fileName = fileName;
    }

    public boolean isOverwrite() {
        return overwrite;
    }
 
}
