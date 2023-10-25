package labs.grishchenko;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Class for calculating math expression from string
 */
public class Calculator {
    private List<String> parseExpression(String expression) {
        List<String> tokens = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(expression, "+-*/() ", true);
        while (st.hasMoreTokens()) {
            tokens.add(st.nextToken());
        }
        return tokens;
    }

    private boolean isDouble(String token) {
        try {
            Double.parseDouble(token);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private TokenType getTokenType(String token) {
        if (isDouble(token)) {
            return TokenType.NUMBER;
        }
        if (token.equals("(")) {
            return TokenType.OPEN_BRACKET;
        }
        if (token.equals(")")) {
            return TokenType.CLOSE_BRACKET;
        }
        if ("+-*/".contains(token)) {
            return TokenType.OPERATION;
        }
        return TokenType.UNEXPECTED;
    }

    private int getPriority(String token) {
        if (token.equals("*") || token.equals("/")) {
            return 2;
        }
        if (token.equals("+") || token.equals("-")) {
            return 1;
        }
        return 0;
    }

    private Double doOperation(String operation, Double first, Double second) throws Exception {
        switch (operation) {
            case "+":
                return second + first;
            case "-":
                return second - first;
            case "*":
                return second * first;
            case "/":
                if (first == 0) {
                    throw new ArithmeticException("Can't divide by zero!");
                }
                return second / first;
            default:
                throw new RuntimeException("Invalid token: " + operation);
        }
    }

    /**
     * Method for calculating expression
     * @param expression string expression
     * @return Double - calculated answer for expression
     * @throws Exception if expression invalid or invalid token found
     * @throws ArithmeticException division by zero found
     */
    public Double calculate(String expression) throws Exception {
        List<String> tokens = parseExpression(expression);
        Stack<Double> numbers = new Stack<>();
        Stack<String> operations = new Stack<>();

        for (String token : tokens) {
            if (token.equals(" ")) {
                continue;
            }
            switch (getTokenType(token)) {
                case NUMBER:
                    numbers.push(Double.parseDouble(token));
                    break;
                case OPEN_BRACKET:
                    operations.push(token);
                    break;
                case CLOSE_BRACKET:
                    while (!operations.isEmpty() && getTokenType(operations.peek()) != TokenType.OPEN_BRACKET) {
                        numbers.push(doOperation(operations.pop(), numbers.pop(), numbers.pop()));
                    }
                    operations.pop();
                    break;
                case OPERATION:
                    while (!operations.isEmpty() && getPriority(operations.peek()) >= getPriority(token)) {
                        numbers.push(doOperation(operations.pop(), numbers.pop(), numbers.pop()));
                    }
                    operations.push(token);
                    break;
                case UNEXPECTED:
                    throw new RuntimeException("Invalid token: " + token);
            }
        }

        while (!operations.isEmpty()) {
            numbers.push(doOperation(operations.pop(), numbers.pop(), numbers.pop()));
        }

        if (numbers.size() != 1) {
            throw new RuntimeException("Invalid expression");
        }

        return numbers.pop();
    }
}
