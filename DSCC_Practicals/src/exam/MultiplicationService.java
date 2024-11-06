package exam;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MultiplicationService extends Remote {
    int multiply(int a, int b) throws RemoteException;
}

