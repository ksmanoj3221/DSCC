package mtnl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class MTNLElectricBillServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099); // Create RMI registry on port 1099
            MTNLElectricBillServiceImpl electricBillService = new MTNLElectricBillServiceImpl();
            Naming.rebind("MTNLElectricBillService", electricBillService); // Bind the remote object
            System.out.println("MTNLElectricBillServer is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
