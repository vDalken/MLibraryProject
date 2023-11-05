import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        Library library = new Library();
        boolean isLoggedIn = false, isThereThatBookName = false;
        do {
            System.out.println("0- Leave\n1- Register\n2- Login\n3- See available books\n4- See requested books\n5- Log out");
            userInput = scanner.next();
            switch (userInput) {
                case "1":
                    if (!isLoggedIn) {
                        System.out.println("First Name:");
                        String registeredName = scanner.next();
                        String registeredEmail;
                        boolean isThereEmail;
                        do {
                            System.out.println("Email:");
                            registeredEmail = scanner.next();
                            isThereEmail = library.isThereEmail(registeredEmail);
                            if (isThereEmail)
                                System.out.println("\u001b[31;1m>There's already an account with the same email, choose another one \u001b[0m");
                        } while (isThereEmail);
                        System.out.println("Password:");
                        String registeredPassword = scanner.next();
                        library.registerUser(registeredName, registeredEmail, registeredPassword);
                        System.out.println("\u001b[32;1m>Your account has been created, now you have to log in \u001b[0m");
                    } else {
                        System.out.println("\u001b[31;1m>You should log out before registering a new account \u001b[0m");
                    }
                    break;
                case "2":
                    if (!isLoggedIn) {
                        System.out.println("Email:");
                        String loginEmail = scanner.next();
                        System.out.println("Password:");
                        String loginPassword = scanner.next();
                        isLoggedIn = library.isAccount(loginEmail, loginPassword);
                        if (isLoggedIn && !library.isAdmin()) {
                            System.out.println("\u001b[32;1m>You're logged in! \u001b[0m");
                        } else if (library.isAdmin()) {
                            String input;
                            System.out.println("\u001b[32;1m>Welcome admin \u001b[0m");
                            do {
                                System.out.println("0- Log out\n1- Add Books\n2- Remove Books\n3- See Books\n4- See Requested Books");
                                input = scanner.next();
                                switch (input) {
                                    case "0":
                                        System.out.println("\u001b[33;1m>Bye bye admin \u001b[0m");
                                        isLoggedIn = false;
                                        break;
                                    case "1":
                                        library.showBooks();
                                        System.out.println("Give me the name of the book");
                                        scanner.nextLine();
                                        String name = scanner.nextLine();
                                        System.out.println("Give me the number of pages");
                                        int numberOfPages = scanner.nextInt();
                                        System.out.println("Give me the number of copies");
                                        int numberOfCopies = scanner.nextInt();
                                        Book book = new Book(name.trim(), numberOfPages, numberOfCopies);
                                        library.addBook(book);
                                        System.out.println("\u001b[32;1m>Books added! \u001b[0m");
                                        break;
                                    case "2":
                                        String removedBookName = "";
                                        int numberOfPagesOfRemovedBook = 0;
                                        boolean isThereThatBook = false;

                                        do {
                                            library.showBooks();
                                            System.out.println("\u001b[31;1m>Type 0 to leave if you want to leave this section\u001b[0m\nGive me the name of the book you want to remove");
                                            scanner.nextLine();
                                            removedBookName = scanner.nextLine();
                                            if (!removedBookName.equals("0")) {
                                                System.out.println("Give me the number of pages");
                                                numberOfPagesOfRemovedBook = scanner.nextInt();
                                                isThereThatBook = library.isThereThatBook(removedBookName.trim(), numberOfPagesOfRemovedBook);
                                                if (!isThereThatBook && numberOfPagesOfRemovedBook != 0) {
                                                    System.out.println("\u001b[31;1m>There is no such book in our inventory! Try again \u001b[0m");
                                                } else if (isThereThatBook) {
                                                    library.removeBook(removedBookName, numberOfPagesOfRemovedBook);
                                                    System.out.println("\u001b[32;1m>All of the copies of the book were removed! \u001b[0m");
                                                } else {
                                                    System.out.println("\u001b[31;1m>You left this section! \u001b[0m");
                                                }
                                            } else {
                                                System.out.println("\u001b[31;1m>You left this section! \u001b[0m");
                                            }
                                        } while (!isThereThatBook && !removedBookName.equals("0") && numberOfPagesOfRemovedBook != 0);
                                        break;
                                    case "3":
                                        library.showBooks();
                                        break;
                                    case "4":
                                        library.showRequestedBooks();
                                        break;
                                    default:
                                        System.out.println("\u001b[31;1mOption not available\u001b[0m");
                                        break;
                                }
                            } while (!input.equals("0"));
                        } else {
                            System.out.println("\u001b[31;1m>Account not found! \u001b[0m");
                        }
                    }else{
                        System.out.println("\u001b[31;1m>You're already logged in\u001b[0m");
                    }
                    break;
                case "3":
                    library.showBooks();
                    System.out.println("0- To go to Main Menu\n1- Request Book");
                    String choice = scanner.next();
                    switch (choice) {
                        case "0":
                            break;
                        case "1":
                            if (isLoggedIn) {
                                System.out.println("Tell me the name of the book you want");
                                scanner.nextLine();
                                String bookName = scanner.nextLine();
                                library.requestBook(bookName);
                            } else {
                                System.out.println("\u001b[31;1m>You have to log in first!\u001b[0m");
                            }
                            break;
                        default:
                            System.out.println("\u001b[31;1m>Option not available!\u001b[0m");
                            break;
                    }
                    break;
                case "4":
                    if (isLoggedIn) {
                        library.showRequestedBooksOfLoggedUser();
                    } else {
                        System.out.println("\u001b[31;1m>You have to log in! \u001b[0m");
                    }
                    break;
                case "5":
                    if (isLoggedIn) {
                        library.logOutUser();
                        System.out.println("\u001b[31;1m>You've logged out \u001b[0m");
                        isLoggedIn = false;
                    } else {
                        System.out.println("\u001b[31;1m>You can't log out because you're not even logged in \u001b[0m");
                    }
                    break;
                default:
                    if (!userInput.equals("0")) {
                        System.out.println("\u001b[31;1m>Option not available!\u001b[0m");
                    }
                    break;
            }
        } while (!(userInput.equals("0")));
        System.out.println("\u001b[33;1m>See you next time!\u001b[0m");
    }
}
