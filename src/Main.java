import java.util.Scanner;

public class Main {
    static Book[] bookList = new Book[2];
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Enter 1 to show books.");
            System.out.println("Enter 2 for adding a book.");
            System.out.println("Enter 3 to search for a book.");
            System.out.println("Enter 4 to exit");
            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    showBooks();
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.println("Enter name of the book:");
                    String name = scanner.nextLine();
                    System.out.println("Enter price of the book:");
                    double price = scanner.nextDouble();
                    addBook(name, price);
                    break;
                case 3:
                    System.out.println("Enter 1 to search with a book name.");
                    System.out.println("Enter 2 to search with a book price.");
                    int userChoiceForSearch = scanner.nextInt();
                    switch (userChoiceForSearch) {
                        case 1:
                            scanner.nextLine();
                            String searchedName = scanner.nextLine();
                            Book foundBook = searchByBookName(searchedName);
                            if (foundBook == null) {
                                System.out.println("Sorry, book with this name has not been found!");
                            } else {
                                System.out.println("Found book with name: " + foundBook.name + " has price: " + foundBook.price + ".");
                            }
                            break;
                        case 2:
                            System.out.println("Enter 1 to search from min price.");
                            System.out.println("Enter 2 to search less than max price.");
                            System.out.println("Enter 3 to search in between min and max price.");
                            int choice3 = scanner.nextInt();
                            switch (choice3) {
                                case 1:
                                    System.out.println("Enter minimum price:");
                                    searchByPrice(scanner.nextDouble(), 0);
                                    break;
                                case 2:
                                    System.out.println("Enter maximum price:");
                                    searchByPrice(0, scanner.nextDouble());
                                    break;
                                case 3:
                                    System.out.println("Enter minimum and maximum prices:");
                                    searchByPrice(scanner.nextDouble(), scanner.nextDouble());
                                    break;
                            }
                    }
                case 4:
                    return;
            }
        }
    }

    public static void addBook(String name, double price) {
        boolean hasEmptySpace = true;
        for (int i = 0; i < bookList.length; i++) {
            if (bookList[i] == null) {
                bookList[i] = new Book(name, price);
                break;
            }

            if (i == bookList.length - 1 && bookList[i] != null) {
                hasEmptySpace = false;
            }
        }

        if (!hasEmptySpace) {
            Book[] newBookList = new Book[bookList.length * 2];
            for (int i = 0; i < bookList.length; i++) {
                newBookList[i] = bookList[i];
            }
            newBookList[bookList.length] = new Book(name, price);
            bookList = newBookList;
        }
    }

    public static void showBooks() {
        for (Book book : bookList) {
            if (book != null) {
                System.out.println("Book with name: " + book.name + " has price: " + book.price + ".");
            }
        }
    }

    public static Book searchByBookName(String bookName) {
        Book result = null;
        for (Book book : bookList) {
            if (book.name.toLowerCase().contains(bookName.toLowerCase())) {
                result = book;
            }
        }
        return result;
    }

    public static void searchByPrice(double minPrice, double maxPrice) {
        for (Book book : bookList) {
            if (book != null) {
                if (minPrice > 0 && maxPrice == 0 && book.price > minPrice) {
                    System.out.println("Book with name: " + book.name + " has price: " + book.price + ".");
                } else if (maxPrice > 0 && minPrice == 0 && book.price < maxPrice) {
                    System.out.println("Book with name: " + book.name + " has price: " + book.price + ".");
                } else if (minPrice > 0 && maxPrice > 0 && book.price >= minPrice && book.price <= maxPrice) {
                    System.out.println("Book with name: " + book.name + " has price: " + book.price + ".");
                }
            }
        }
    }
}