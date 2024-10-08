package pl.edu.pjatk.MPR_Spring_32.model;

public class Jajco {

    private String name;
    private String origin;

    public Jajco(String name, String origin){
        this.name = name;
        this.origin = origin;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public String getName() {
        return name;
    }
}
