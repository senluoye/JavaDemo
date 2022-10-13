import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = 5887391604554532906L;

    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestSerializable [id=" + id + ", name=" + name + "]";
    }
}
