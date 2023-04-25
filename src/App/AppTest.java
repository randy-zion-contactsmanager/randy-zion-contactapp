package App;
import java.util.Scanner;

public class AppTest {
    private static App.ContactManager contactManager;

    public static void main(String[] args) {
        contactManager = new App.ContactManager("App/contacts.txt");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    contactManager.displayContacts();
                    break;
                case "2":
                    addContact(scanner);
                    break;
                case "3":
                    searchContact(scanner);
                    break;
                case "4":
                    deleteContact(scanner);
                    break;
                case "5":
                    contactManager.writeContactsToFile();
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
            }
        }
    }


    private static void printMenu() {
        System.out.println("1. View contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit.");
        System.out.print("Enter an option (1, 2, 3, 4 or 5): ");
    }

    private static void addContact(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumberInput = scanner.nextLine();
        String phoneNumber = phoneNumberInput.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3");
        contactManager.addContact(name, phoneNumber);
        System.out.println("Contact added!.");
    }


    private static void searchContact(Scanner scanner) {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();
        Contact contact = contactManager.searchContact(name);
        if (contact != null) {
            System.out.println("Contact found:");
            System.out.println(contact.toString());
        } else {
            System.out.println("Contact not found.");
        }
    }

    private static void deleteContact(Scanner scanner) {
        System.out.print("Enter name to delete: ");
        String name = scanner.nextLine();
        contactManager.deleteContact(name);
        System.out.println("Contact deleted!.");
    }
}
