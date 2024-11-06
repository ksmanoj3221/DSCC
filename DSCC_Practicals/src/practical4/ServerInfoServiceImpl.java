package practical4;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class ServerInfoServiceImpl extends UnicastRemoteObject implements ServerInfoService {
    private ServerInfoDAO serverInfoDAO;

    protected ServerInfoServiceImpl() throws RemoteException {
        super();
        serverInfoDAO = new ServerInfoDAO();
    }

    @Override
    public ServerInfo getServerInfo() throws RemoteException {
        try {
            return serverInfoDAO.getServerInfo();
        } catch (SQLException e) {
            throw new RemoteException("Database error", e);
        }
    }
}
