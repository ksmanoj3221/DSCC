package practical6;

import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

public class LibraryClient {

    public static void main(String[] args) {
        try {
            // Lookup the remote object
            LibraryService libraryService = (LibraryService) Naming.lookup("rmi://localhost/LibraryService");
            Scanner scanner = new Scanner(System.in);
            String expression;
            
            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1: Get all books");
                System.out.println("2: Get book by ID");
                System.out.println("exit: Quit");
                
                expression = scanner.nextLine();

                if (expression.equalsIgnoreCase("exit")) {
                    break;
                }

                switch (expression) {
                    case "1":
                        List<Book> books = libraryService.getAllBooks();
                        System.out.println("Books in the Library:");
                        for (Book book : books) {
                            System.out.println(book);
                        }
                        break;

                    case "2":
                        System.out.println("Enter the book ID:");
                        int bookId = Integer.parseInt(scanner.nextLine());
                        Book book = libraryService.getBook(bookId);
                        if (book != null) {
                            System.out.println("Retrieved Book: " + book);
                        } else {
                            System.out.println("No book found with ID: " + bookId);
                        }
                        break;
                        
                    
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
