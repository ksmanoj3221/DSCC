package mtnl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MTNLElectricBillServiceImpl extends UnicastRemoteObject implements MTNLElectricBillService {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/MTNL_Bill";
    private static final String USER = "root"; // replace with your MySQL username
    private static final String PASSWORD = "kSmanoj@18"; // replace with your MySQL password

    protected MTNLElectricBillServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public List<Bill> getAllBills() throws RemoteException {
        List<Bill> bills = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Bill");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("consumer_id");
                String name = resultSet.getString("consumer_name");
                String dueDate = resultSet.getString("bill_due_date");
                double amount = resultSet.getDouble("bill_amount");
                bills.add(new Bill(id, name, dueDate, amount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bills;
    }

    @Override
    public Bill getBill(int consumerId) throws RemoteException {
        Bill bill = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Bill WHERE consumer_id = ?")) {
            statement.setInt(1, consumerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("consumer_name");
                String dueDate = resultSet.getString("bill_due_date");
                double amount = resultSet.getDouble("bill_amount");
                bill = new Bill(consumerId, name, dueDate, amount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bill;
    }
}
