import java.util.Scanner;
import java.util.function.Function;

public class Calculator {

    // Basic operations
    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        return b == 0 ? Double.NaN : a / b;
    }

    public static double modulus(double a, double b) {
        return b == 0 ? Double.NaN : a % b;
    }

    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    // Integral computation
    public static double definiteIntegral(Function<Double, Double> function, double a, double b, int n) {
        double h = (b - a) / n;
        double integral = 0.5 * (function.apply(a) + function.apply(b));
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            integral += function.apply(x);
        }
        return integral * h;
    }

    // Display menu
    public static void displayMenu() {
        System.out.println("\nWelcome to the Calculator!");
        System.out.println("Choose an operation:");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.println("5. Modulus");
        System.out.println("6. Exponentiation");
        System.out.println("7. Definite Integral");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            displayMenu();
            choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("Exiting. Thank you for using the calculator!");
                break;
            }

            // Basic operations
            if (choice >= 1 && choice <= 6) {
                System.out.print("Enter the first number: ");
                double num1 = scanner.nextDouble();
                System.out.print("Enter the second number: ");
                double num2 = scanner.nextDouble();

                double result = switch (choice) {
                    case 1 -> add(num1, num2);
                    case 2 -> subtract(num1, num2);
                    case 3 -> multiply(num1, num2);
                    case 4 -> divide(num1, num2);
                    case 5 -> modulus(num1, num2);
                    case 6 -> power(num1, num2);
                    default -> Double.NaN;
                };

                System.out.println("The result is: " + result);

            } else if (choice == 7) {
                // Definite Integral
                System.out.println("Choose a function:");
                System.out.println("1. x^2");
                System.out.println("2. sin(x)");
                System.out.println("3. cos(x)");
                System.out.print("Enter your choice: ");
                int funcChoice = scanner.nextInt();

                Function<Double, Double> function = switch (funcChoice) {
                    case 1 -> x -> x * x;
                    case 2 -> Math::sin;
                    case 3 -> Math::cos;
                    default -> null;
                };

                if (function == null) {
                    System.out.println("Invalid function choice.");
                    continue;
                }

                System.out.print("Enter the lower limit (a): ");
                double a = scanner.nextDouble();
                System.out.print("Enter the upper limit (b): ");
                double b = scanner.nextDouble();
                System.out.print("Enter the number of intervals (n): ");
                int n = scanner.nextInt();

                double result = definiteIntegral(function, a, b, n);
                System.out.println("The integral result is: " + result);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
