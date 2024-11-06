package practical7;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ElectricBillServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099); // Create RMI registry on port 1099
            ElectricBillServiceImpl electricBillService = new ElectricBillServiceImpl();
            Naming.rebind("ElectricBillService", electricBillService); // Bind the remote object
            System.out.println("ElectricBillServer is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
