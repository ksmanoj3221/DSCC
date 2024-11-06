package dscc5;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MathEvaluator extends Remote {
    double evaluate(String expression) throws RemoteException;
}
