package labs.grishchenko;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String expression = System.console().readLine();
        try {
            Double res = calculator.calculate(expression);
            System.out.println(res);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}