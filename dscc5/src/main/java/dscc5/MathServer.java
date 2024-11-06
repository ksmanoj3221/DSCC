package dscc5;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class MathServer {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099); // Create RMI registry on port 1099
            MathEvaluatorImpl evaluator = new MathEvaluatorImpl();
            Naming.rebind("MathEvaluator", evaluator); // Bind the remote object
            System.out.println("MathServer is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
