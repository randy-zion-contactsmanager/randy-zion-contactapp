package App;
import java.io.*;
import java.util.*;

public class ContactManager {
    private final List<Contact> contacts;
    private final String fileName;

    public ContactManager(String fileName) {
        this.fileName = fileName;
        this.contacts = new ArrayList<>();
        readContactsFromFile();
    }

    private void readContactsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String name = parts[0].trim();
                String phoneNumber = parts[1].trim();
                Contact contact = new Contact(name, phoneNumber);
                contacts.add(contact);
            }
        } catch (IOException e) {
            System.err.println("Error reading contacts from file: " + e.getMessage());
        }
    }

    public void writeContactsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Contact contact : contacts) {
                writer.write(contact.getName() + " | " + contact.getPhoneNumber());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void addContact(String name, String phoneNumber) {
        Contact contact = new Contact(name, phoneNumber);
        contacts.add(contact);
    }

    public void deleteContact(String name) {
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getName().equals(name)) {
                iterator.remove();
            }
        }
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                return contact;
            }
        }
        return null;
    }

    public void displayContacts() {
        System.out.println("Name | Phone number");
        System.out.println("-------------------");
        for (Contact contact : contacts) {
            System.out.println(contact.toString());
        }
    }
}
