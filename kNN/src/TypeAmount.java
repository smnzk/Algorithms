public class TypeAmount {

    String type;
    int amount;

    public TypeAmount(String type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return type + " " + amount;
    }

    @Override
    public boolean equals(Object obj) {
        TypeAmount t = (TypeAmount) obj;
        if (this.type.equals(((TypeAmount) obj).type)) {
            return true;
        } else  {
            return false;
        }
    }
}
