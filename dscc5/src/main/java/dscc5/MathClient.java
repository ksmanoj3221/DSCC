package dscc5;
import java.rmi.Naming;
import java.util.Scanner;

public class MathClient {

    public static void main(String[] args) {
        try {
            // Lookup the remote object
            MathEvaluator evaluator = (MathEvaluator) Naming.lookup("rmi://localhost/MathEvaluator");
            Scanner scanner = new Scanner(System.in);
            String expression;
            
            while (true) {
                System.out.println("Enter a mathematical expression (or 'exit' to quit):");
                expression = scanner.nextLine();
                
                // Check for exit condition
                if (expression.equalsIgnoreCase("exit")) {
                    break;
                }

                try {
                    double result = evaluator.evaluate(expression);
                    System.out.println("Result of '" + expression + "' is: " + result);
                } catch (Exception e) {
                    System.out.println("Error evaluating expression: " + e.getMessage());
                }
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
