package tho.nill.connection;


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
