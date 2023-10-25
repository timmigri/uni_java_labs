import labs.grishchenko.Calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCalculator {
    private Calculator calculator = new Calculator();

    private void calculateWithHandledException(String expression, double expectedValue) {
        try {
            Assertions.assertEquals(calculator.calculate(expression), expectedValue, 0.00001);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void test_simpleExpressions() {
        calculateWithHandledException("3 +5", 8);
        calculateWithHandledException("8 *   4 ", 32);
        calculateWithHandledException("3.5 - 2.1", 1.4);
        calculateWithHandledException("5 / 4", 1.25);
        calculateWithHandledException("(5) + 3", 8);
        calculateWithHandledException("5.376 + 2.84", 8.216);
    }

    @Test
    public void test_complexExpressions() {
        calculateWithHandledException("(3 + 5) / 2 - 4.1", -0.1);
        calculateWithHandledException("5 / 2 - 2 * (4 - 1)", -3.5);
        calculateWithHandledException("4 / 5 + (2 / 5 - 1 / 5)", 1);
        calculateWithHandledException("8 / (4 - 3) * 5 - 2", 38);
        calculateWithHandledException("3.43 - 2.13 + 5.19", 6.49);
    }

    @Test
    public void test_invalidExpressions() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            calculator.calculate("- 2 / 3");
        });
        Assertions.assertThrows(RuntimeException.class, () -> {
            calculator.calculate("(5 - 3 / 2");
        });
        Assertions.assertThrows(RuntimeException.class, () -> {
            calculator.calculate("5 + 3 / 2t");
        });
        Assertions.assertThrows(ArithmeticException.class, () -> {
            calculator.calculate("5 / (3 * 2 - 6)");
        });
    }
}
