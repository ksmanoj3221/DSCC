package practical6;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LibraryServiceImpl extends UnicastRemoteObject implements LibraryService {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/Library";
    private static final String USER = "root"; // replace with your MySQL username
    private static final String PASSWORD = "kSmanoj@18"; // replace with your MySQL password

    protected LibraryServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public List<Book> getAllBooks() throws RemoteException {
        List<Book> books = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Book");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("Book_id");
                String name = resultSet.getString("Book_name");
                String author = resultSet.getString("Book_author");
                books.add(new Book(id, name, author));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book getBook(int bookId) throws RemoteException {
        Book book = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Book WHERE Book_id = ?")) {
             
            statement.setInt(1, bookId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("Book_name");
                String author = resultSet.getString("Book_author");
                book = new Book(bookId, name, author);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }
}
