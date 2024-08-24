import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    public int id;
    public String title;
    public String author;
    public String category;
    public boolean available;

    public Book(int id, String title, String author, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Member {
    public int id;
    public String name;
    public String contact;

    public Member(int id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }
}

class Library {
    public List<Book> books;
    public List<Member> members;
    public int bookIdCounter;
    public int memberIdCounter;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        bookIdCounter = 1;
        memberIdCounter = 1;
    }

    public void addBook(String title, String author, String category) {
        Book book = new Book(bookIdCounter++, title, author, category);
        books.add(book);
    }

    public void addMember(String name, String contact) {
        Member member = new Member(memberIdCounter++, name, contact);
        members.add(member);
    }

    public void displayBooks() {
        System.out.println("Books in the Library:");
        for (Book book : books) {
            System.out.println("ID: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Category: " + book.getCategory() + ", Available: " + (book.isAvailable() ? "Yes" : "No"));
        }
    }

    public void displayMembers() {
        System.out.println("Library Members:");
        for (Member member : members) {
            System.out.println("ID: " + member.getId() + ", Name: " + member.getName() + ", Contact: " + member.getContact());
        }
    }

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Library Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Display Books");
            System.out.println("4. Display Members");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    library.addBook(title, author, category);
                    System.out.println("Book added successfully.");
                    break;
                case 2:
                    System.out.print("Enter member name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter contact information: ");
                    String contact = scanner.nextLine();
                    library.addMember(name, contact);
                    System.out.println("Member added successfully.");
                    break;
                case 3:
                    library.displayBooks();
                    break;
                case 4:
                    library.displayMembers();
                    break;
                case 5:
                    System.out.println("Exiting the Library Management System.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}