package tho.nill.sqlmock.copy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AbfrageKey {
    
    private String statement;
    private int index;
    private List<AbfrageParameter> parameter = new ArrayList<>();
   
    public AbfrageKey(String statement,List<AbfrageParameter> parameter ) {
        super();
        this.statement = statement;
        Collections.sort(parameter);
        this.parameter = Collections.unmodifiableList(parameter);
    }

    public String getStatement() {
        return statement;
    }

    public int getIndex() {
        return index;
    }


    public List<AbfrageParameter> getParameter() {
        return parameter;
    }




    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + index;
        result = prime * result
                + ((parameter == null) ? 0 : parameter.hashCode());
        result = prime * result
                + ((statement == null) ? 0 : statement.hashCode());
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
        AbfrageKey other = (AbfrageKey) obj;
        if (index != other.index)
            return false;
        if (parameter == null) {
            if (other.parameter != null)
                return false;
        } else if (!parameter.equals(other.parameter))
            return false;
        if (statement == null) {
            if (other.statement != null)
                return false;
        } else if (!statement.equals(other.statement))
            return false;
        return true;
    }




    @Override
    public String toString() {
        return "Abfagedaten [statement=" + statement + ", index=" + index
                + ", parameter=" + parameter;
    }

}
