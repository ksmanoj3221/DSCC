package practical4;

import java.rmi.Naming;

public class RMIClient {
    public static void main(String[] args) {
        try {
            ServerInfoService service = (ServerInfoService) Naming.lookup("rmi://localhost/ServerInfoService");
            ServerInfo serverInfo = service.getServerInfo();
            System.out.println("Server Info: " + serverInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
