package labs.grishchenko.models;

/*
 * Person's Division model
 */
public class Division {
    private final int id;
    private final String name;

     public Division(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Division #%o: %s", id, name);
    }

    @Override
    public boolean equals(Object object){
        if (object == this) {
            return true;
        }
 
        if (!(object instanceof Division)) {
            return false;
        }
         
        Division other = (Division) object;
        return id == other.id && name.equals(other.name);
    }
}
