import java.util.ArrayList;

class User {
    private String name;
    private String email;
    private String password;

    ArrayList<Book> requestedBooks = new ArrayList<>();

    public User(String name, String email, String password, Book requestedBook) {
        this.name = name;
        this.email = email;
        this.password = password;
        if (requestedBook != null) {
            requestedBooks.add(requestedBook);
        }
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public ArrayList<Book> getRequestedBooks() {
        return requestedBooks;
    }

    public void setRequestedBooks(Book requestedBook) {
        requestedBooks.add(requestedBook);
    }
}
