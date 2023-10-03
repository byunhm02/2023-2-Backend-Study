public class Main {
    public static void main(String[] args) {
        Calculator calculator = new BasicCalculator();
        int result1 = calculator.add(1234, 4321);
        int result2 = calculator.subtract(911, 170);
        int result3 = calculator.multiply(2, 3);
        int result4 = calculator.divide(33, 11);

        System.out.println("1234 + 4321 = " + result1);
        System.out.println("911 - 170 = " + result2);
        System.out.println("2 * 3 = " + result3);
        System.out.println("33 / 11 = " + result4);

        Animal cat = new Cat();
        Animal dog = new Dog();

        cat.speak();
        dog.speak();
    }
}
