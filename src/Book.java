class Book {
    private String name;
    private int numberOfPages;

    private int numberOfCopies;

    public Book(String name, int numberOfPages, int numberOfCopies) {
        this.name = name;
        this.numberOfPages = numberOfPages;
        this.numberOfCopies = numberOfCopies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    @Override
    public String toString() {
        return "Book: " +
                "Name:'" + name + '\'' +
                ", Number Of Pages: " + numberOfPages +
                ", Number Of Copies: " + numberOfCopies;
    }
}
