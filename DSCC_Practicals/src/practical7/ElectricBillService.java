package practical7;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ElectricBillService extends Remote {
    List<Bill> getAllBills() throws RemoteException;
    Bill getBill(int consumerId) throws RemoteException;
}
