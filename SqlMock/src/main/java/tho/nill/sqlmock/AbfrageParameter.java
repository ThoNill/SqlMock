package tho.nill.sqlmock;

public class AbfrageParameter implements Comparable<AbfrageParameter> {
    private int index;
    private String name;
    private Object value;

    public AbfrageParameter(int index, Object value) {
        this(index, "", value);
    }

    public AbfrageParameter(String name, Object value) {
        this(0, name, value);
    }

    public AbfrageParameter(int index, String name, Object value) {
        super();
        this.index = index;
        this.name = name;
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
        if (index > 0 && o.index > 0) {
            return index - o.index;
        }

        if (index == 0 && o.index == 0) {
            return name.compareTo(o.name);
        }
        if (index == 0 && o.index > 0) {
            return -1;
        }
        return 1;

    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + index;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
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
        return "AbfrageParameter [index=" + index + ", name=" + name
                + ", value=" + value + "]";
    }

}
