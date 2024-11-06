package exam;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MultiplicationServiceImpl extends UnicastRemoteObject implements MultiplicationService {
    protected MultiplicationServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public int multiply(int a, int b) throws RemoteException {
        return a * b;
    }
}

