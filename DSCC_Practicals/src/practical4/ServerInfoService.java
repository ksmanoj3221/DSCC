package practical4;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInfoService extends Remote {
    ServerInfo getServerInfo() throws RemoteException;
}