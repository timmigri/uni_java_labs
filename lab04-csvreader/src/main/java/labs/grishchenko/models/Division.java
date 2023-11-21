package labs.grishchenko.models;

/*
 * Person's Division model
 */
public class Division {
    public final int id;
    public final String name;
    public static int ID_COUNTER = 1;

     public Division(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Division(String name) {
        this.id = ID_COUNTER++;
        this.name = name;
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
