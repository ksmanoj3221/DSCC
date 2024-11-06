package exam;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class MultiplicationClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Get the remote object from the RMI registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            MultiplicationService service = (MultiplicationService) registry.lookup("MultiplicationService");

            // Get two numbers from the user
            System.out.print("Enter first number: ");
            int num1 = scanner.nextInt();
            System.out.print("Enter second number: ");
            int num2 = scanner.nextInt();

            // Call the remote method to multiply
            int result = service.multiply(num1, num2);
            System.out.println("Multiplication Result: " + result);

            // Check if the result is greater than 250
            if (result > 250) {
                System.out.println("The multiplication result is greater than 250.");
            } else {
                System.out.println("The multiplication result is not greater than 250.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
