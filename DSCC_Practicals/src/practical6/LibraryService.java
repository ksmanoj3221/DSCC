package practical6;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface LibraryService extends Remote {
    List<Book> getAllBooks() throws RemoteException;
    Book getBook(int bookId) throws RemoteException;
}
