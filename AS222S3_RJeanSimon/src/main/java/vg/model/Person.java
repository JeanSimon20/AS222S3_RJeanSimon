package vg.model;

public class Person {

    private Integer personid;
    private String name;

    public Person() {

    }

    @Override
    public String toString() {
        return "Person{" +
                "personid=" + personid +
                ", name='" + name + '\'' +
                '}';
    }

    public Person(Integer personid, String name) {
        this.personid = personid;
        this.name = name;
    }

    public Integer getPersonid() {
        return personid;
    }

    public void setPersonid(Integer personid) {
        this.personid = personid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
