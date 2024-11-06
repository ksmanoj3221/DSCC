package practical6;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class LibraryServer {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099); // Create RMI registry on port 1099
            LibraryServiceImpl libraryService = new LibraryServiceImpl();
            Naming.rebind("LibraryService", libraryService); // Bind the remote object
            System.out.println("LibraryServer is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
