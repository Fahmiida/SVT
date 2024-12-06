
public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }

    public String generateRandomNumber() {
        int number = (int) (Math.random() * 100);
        return String.valueOf(number);
    }
}

