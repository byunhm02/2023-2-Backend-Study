public class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public void speak() {
        System.out.println(name + " says " + getSound());
    }

    protected String getSound() {
        return "Unknown sound";
    }
}

