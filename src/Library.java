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
        boolean isBookAvailable = false;
        int bookIndex = 0;
        for (int i = 0; i < availableBooks.size(); i++) {
            if (availableBooks.get(i).getName().equals(bookName)) {
                isBookAvailable = true;
                bookIndex = i;
                break;
            }
        }
        if (isBookAvailable) {
            if (requestedBooks.isEmpty()) {
                availableBooks.get(bookIndex).setNumberOfCopies(availableBooks.get(bookIndex).getNumberOfCopies() - 1);
                int numberOfPages = availableBooks.get(bookIndex).getNumberOfPages();
                Book book = new Book(bookName, numberOfPages, 1);
                if (availableBooks.get(bookIndex).getNumberOfCopies() == 0) {
                    availableBooks.remove(availableBooks.get(bookIndex));
                }
                requestedBooks.add(book);
                for (int i1 = 0; i1 < requestedBooks.size(); i1++) {
                    if (requestedBooks.get(i1).getName().equals(bookName)) {
                        requestedBooks.get(i1).setNumberOfCopies(1);
                        Book newBook = new Book(bookName, numberOfPages, 1);
                        loggedUser.setRequestedBooks(newBook);
                    }
                }
            } else {
                availableBooks.get(bookIndex).setNumberOfCopies(availableBooks.get(bookIndex).getNumberOfCopies() - 1);

                if (availableBooks.get(bookIndex).getNumberOfCopies() == 0) {
                    availableBooks.remove(availableBooks.get(bookIndex));
                }


                boolean isTheBookThere = false;
                int index = 0;
                for (int i1 = 0; i1 < requestedBooks.size(); i1++) {
                    if (requestedBooks.get(i1).getName().equals(bookName)) {
                        isTheBookThere = true;
                        index = i1;
                        break;
                    }
                }
                if (isTheBookThere) {
                    //System.out.println(loggedUser.getRequestedBooks().get(1).getNumberOfCopies());
                    requestedBooks.get(index).setNumberOfCopies(requestedBooks.get(index).getNumberOfCopies() + 1);
                    System.out.println(loggedUser.getRequestedBooks());
                    // System.out.println(loggedUser.getRequestedBooks().get(1).getNumberOfCopies());
                    if (loggedUser.getRequestedBooksSize() == 0) {
                        Book newBook2 = new Book(bookName, requestedBooks.get(index).getNumberOfPages(), 1);
                        loggedUser.setRequestedBooks(newBook2);
                    } else {
                        boolean isTheBookInUserRequestedBooks = false;
                        int otherIndex = 0;
                        for (int i2 = 0; i2 < loggedUser.getRequestedBooksSize(); i2++) {
                            if (loggedUser.getRequestedBooks().get(i2).getName().equals(bookName)) {
                                isTheBookInUserRequestedBooks = true;
                                otherIndex = i2;
                                break;
                            }
                        }
                        System.out.println(isTheBookInUserRequestedBooks);
                        System.out.println(otherIndex);
                        if (isTheBookInUserRequestedBooks) {
                            System.out.println(loggedUser.getRequestedBooks().get(otherIndex).getNumberOfCopies());
                            loggedUser.getRequestedBooks().get(otherIndex).setNumberOfCopies(loggedUser.getRequestedBooks().get(otherIndex).getNumberOfCopies() + 1);
                            System.out.println(loggedUser.getRequestedBooks().get(otherIndex).getNumberOfCopies());
                        } else {
                            Book newBook3 = new Book(bookName, requestedBooks.get(index).getNumberOfPages(), 1);
                            loggedUser.setRequestedBooks(newBook3);
                        }

                    }
                    //loggedUser.setRequestedBooks(requestedBooks.get(i1));
                } else {
                    System.out.println("ok");
                    Book newBook1 = new Book(bookName, requestedBooks.get(index).getNumberOfPages(), 1);
                    loggedUser.setRequestedBooks(newBook1);
                    requestedBooks.add(newBook1);
                }
            }
        } else {
            System.out.println("\u001b[31;1m>Book not found!\u001b[0m");
        }

    }

    public void showRequestedBooksOfLoggedUser() {
        for (int i = 0; i < users.size(); i++) {
            if (loggedUser.toString().equals(users.get(i).toString())) {
                System.out.println("Requested Books of " + users.get(i).getName() + ":");
                System.out.println(users.get(i).getRequestedBooks());
            }
        }
    }

    public void showRequestedBooks() {
        for (int i = 0; i < requestedBooks.size(); i++) System.out.println(requestedBooks.get(i));
    }

    public boolean isThereEmail(String email) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email)) {
                return true;
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
