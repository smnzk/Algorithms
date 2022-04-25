public class Flower {

    String[] attributes;

    String type;

    public Flower(String attributes) {
        this.attributes = attributes.split(",");
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        for(String s : attributes) {
            System.out.println(s);
        }
        return type;
    }
}
