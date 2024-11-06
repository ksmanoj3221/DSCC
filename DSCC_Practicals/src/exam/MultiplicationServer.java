package exam;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MultiplicationServer {
    public static void main(String[] args) {
        try {
            // Create an instance of the implementation class
            MultiplicationService service = new MultiplicationServiceImpl();
            
            // Bind the service to the RMI registry
            Registry registry = LocateRegistry.createRegistry(1099); // Default port
            registry.rebind("MultiplicationService", service);
            
            System.out.println("Multiplication Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
