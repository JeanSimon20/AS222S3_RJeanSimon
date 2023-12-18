package vg.model;

public class Area {

    private Integer areaid;
    private String name;

    public Area() {
    }

    public Area(Integer areaid, String name) {
        this.areaid = areaid;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Area{" +
                "areaid=" + areaid +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
