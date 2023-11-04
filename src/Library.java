import java.util.ArrayList;
import java.util.Scanner;

class Library {
    ArrayList<Book> availableBooks = new ArrayList<>();

    ArrayList<Book> requestedBooks = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();

    private User loggedUser;

    public Library() {
        User admin = new User("admin", "admin", "admin", null);
        users.add(admin);
        Book book1 = new Book("A vida", 301131, 1);
        Book book2 = new Book("A maravilha", 2313, 5);
        Book book3 = new Book("O mar", 1296534, 1);
        availableBooks.add(book1);
        availableBooks.add(book2);
        availableBooks.add(book3);
    }

    public void addBook(Book book) {
        boolean isThere = false;
        for (int i = 0; i < availableBooks.size(); i++) {
            if (availableBooks.get(i).getName().equals(book.getName())) {
                availableBooks.get(i).setNumberOfCopies(availableBooks.get(i).getNumberOfCopies() + book.getNumberOfCopies());
                isThere = true;
                break;
            }
        }
        if (!isThere) {
            availableBooks.add(book);
        }
    }

    public void removeBook(String removedBookName, int numberOfPagesOfRemovedBook) {
        for (int i = 0; i < availableBooks.size(); i++) {
            if (availableBooks.get(i).getName().equals(removedBookName) && availableBooks.get(i).getNumberOfPages() == numberOfPagesOfRemovedBook) {
                //Book toBeRemovedBook = new Book(removedBookName, numberOfPagesOfRemovedBook,availableBooks.get(i).getNumberOfPages());
                availableBooks.remove(availableBooks.get(i));
            }
        }
    }

    public void registerUser(String name, String email, String password) {
        User user = new User(name, email, password, null);
        users.add(user);
    }

    public boolean isAccount(String email, String password) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email) && users.get(i).getPassword().equals(password)) {
                loggedUser = users.get(i);
                return true;
            }
        }
        return false;
    }

    public boolean isAdmin() {
        if (loggedUser != null && loggedUser.getEmail().equals("admin")) {
            return true;
        }
        return false;
    }

    public void showBooks() {
        for (int i = 0; i < availableBooks.size(); i++) {
            System.out.println(availableBooks.get(i).toString());
        }
    }

    public void requestBook(String bookName) {
        for (int i = 0; i < availableBooks.size(); i++) {
            if (availableBooks.get(i).getName().equals(bookName)) {
                if (requestedBooks.isEmpty()) {
                    availableBooks.get(i).setNumberOfCopies(availableBooks.get(i).getNumberOfCopies() - 1);
                    Book book = new Book(availableBooks.get(i).getName(), availableBooks.get(i).getNumberOfPages(), availableBooks.get(i).getNumberOfCopies());
                    requestedBooks.add(book);
                    for (int i1 = 0; i1 < requestedBooks.size(); i1++) {
                        if (requestedBooks.get(i1).getName().equals(bookName)) {
                            System.out.println(availableBooks.get(i).getNumberOfCopies());
                            requestedBooks.get(i1).setNumberOfCopies(1);
                            Book newBook = new Book(requestedBooks.get(i1).getName(), requestedBooks.get(i1).getNumberOfPages(), requestedBooks.get(i1).getNumberOfCopies());
                            loggedUser.setRequestedBooks(newBook);
                            System.out.println(availableBooks.get(i).getNumberOfCopies());
                            System.out.println(requestedBooks.get(i1).getNumberOfCopies());
                        }
                    }
                } else {
                    availableBooks.get(i).setNumberOfCopies(availableBooks.get(i).getNumberOfCopies() - 1);
                    if (availableBooks.get(i).getNumberOfCopies() == 0) {
                        availableBooks.remove(availableBooks.get(i));
                    }
                    for (int i1 = 0; i1 < requestedBooks.size(); i1++) {
                        if (requestedBooks.get(i1).getName().equals(bookName)) {
                            System.out.println(requestedBooks.get(i1).getNumberOfCopies());
                            requestedBooks.get(i1).setNumberOfCopies(requestedBooks.get(i1).getNumberOfCopies() + 1);
                            System.out.println(requestedBooks.get(i1).getNumberOfCopies());
                            for (int i2 = 0; i2 < loggedUser.getRequestedBooksSize(); i2++) {
                                if (loggedUser.getRequestedBooks().get(i2).getName().equals(bookName)) {
                                    loggedUser.getRequestedBooks().get(i2).setNumberOfCopies(loggedUser.getRequestedBooks().get(i2).getNumberOfCopies() + 1);
                                }
                            }
                            //loggedUser.setRequestedBooks(requestedBooks.get(i1));
                        } else {
                            Book newBook1 = new Book(bookName, requestedBooks.get(i1).getNumberOfPages(), 1);
                            loggedUser.setRequestedBooks(newBook1);
                        }
                    }
                }
            }
        }
    }

    public void showRequestedBooks() {
        for (int i = 0; i < users.size(); i++) {
            if (loggedUser.toString().equals(users.get(i).toString())) {
                System.out.println("Requested Books of " + users.get(i).getName() + ":");
                System.out.println(users.get(i).getRequestedBooks());
            }
        }
    }

    public boolean isThereEmail(String email) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean isThereThatBook(String name, int numberOfPages) {
        for (int i = 0; i < availableBooks.size(); i++) {
            if (availableBooks.get(i).getName().equals(name) && availableBooks.get(i).getNumberOfPages() == numberOfPages) {
                return true;
            }
        }
        return false;
    }

    public void logOutUser() {
        loggedUser = null;
    }
}
