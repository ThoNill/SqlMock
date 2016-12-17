package tho.nill.sqlmock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class AbfrageParameter implements Comparable<AbfrageParameter>{
    private int index;
    private Object value;
    
    public AbfrageParameter(int index, Object value) {
        super();
        this.index = index;
        this.value = value;
    }
    
    public int getIndex() {
        return index;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public int compareTo(AbfrageParameter o) {
        return index - o.index;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + index;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        AbfrageParameter other = (AbfrageParameter) obj;
        if (index != other.index)
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "[" + index + "] = " + value;
    }
    
  
    
}
